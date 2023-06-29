package ru.kao.titanforgedpickaxe.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.TierSortingRegistry;
import org.jetbrains.annotations.Nullable;
import ru.kao.titanforgedpickaxe.api.tool.ForgedTool;
import ru.kao.titanforgedpickaxe.item.upgrade.leveling.AOEUpgradeItem;
import ru.kao.titanforgedpickaxe.item.util.BlockUtil;
import ru.kao.titanforgedpickaxe.item.util.TagUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static ru.kao.titanforgedpickaxe.item.upgrade.leveling.EfficiencyUpgradeItem.MULTIPLIER;
import static ru.kao.titanforgedpickaxe.item.upgrade.util.UpgradeUtil.TIER_LIST;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.*;
import static ru.kao.titanforgedpickaxe.item.util.TooltipFillUtil.*;
import static ru.kao.titanforgedpickaxe.util.GlobalConstant.DEFAULT_REACH_DISTANCE;

public class TitanPickaxeItem extends DiggerItem implements ForgedTool {

    public TitanPickaxeItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super((float) attackDamage, attackSpeed, tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
        this.blocks = BlockTags.MINEABLE_WITH_PICKAXE;
    }

    private final TagKey<Block> blocks;
    private final int DEFAULT_TIER = 2; // DEFAULT_TIER = getTier().getLevel()

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            CompoundTag nbt = TagUtil.getNBT(itemInHand);
            nbt.putBoolean(AUTO_SMELT_ENABLED_TAG_NAME, !nbt.getBoolean(AUTO_SMELT_ENABLED_TAG_NAME));
        }
        return InteractionResultHolder.pass(itemInHand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        CompoundTag tag = TagUtil.getNBT(itemStack);
        tag.putInt("HideFlags", 1);
        fillTooltipIntTextFromTag(components, tag, "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.mine.exp", EXP_TAG_NAME);
        fillUpgradeInfo(components, tag, EFFICIENCY_TAG_NAME,
                "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.efficiency.level");
        fillUpgradeInfo(components, tag, FORTUNE_TAG_NAME,
                "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.fortune.level");
        fillUpgradeInfo(components, tag, TIER_TAG_NAME,
                "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.tier", TIER_LIST);
        if (tag.getBoolean(AUTO_SMELT_TAG_NAME)) {
            if (tag.getBoolean(AUTO_SMELT_ENABLED_TAG_NAME)) {
                fillTooltipTextWithStyle(components, "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.auto_smelt", ChatFormatting.GOLD);
            } else {
                fillTooltipTextWithStyle(components, "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.auto_smelt", ChatFormatting.GRAY);
            }
        }
        fillUpgradeInfo(components, tag, AOE_TAG_NAME,
                "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.aoe.level");
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        CompoundTag tag = TagUtil.getNBT(itemStack);
        return blockState.is(this.blocks) ? this.speed + tag.getInt(EFFICIENCY_TAG_NAME) * MULTIPLIER : 1.0F;
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity entity) {
        CompoundTag nbt = TagUtil.getNBT(itemStack);

        int aoeLevel = nbt.getInt(AOE_TAG_NAME);
        if (aoeLevel <= 0)
            return super.mineBlock(itemStack, level, blockState, blockPos, entity);

        //MAYBE SIDE UNSAFE
        if (entity instanceof ServerPlayer player && level instanceof ServerLevel serverLevel) {
            if (!super.mineBlock(itemStack, level, blockState, blockPos, entity)) {
                return false;
            }

            AttributeInstance attribute = player.getAttribute(ForgeMod.REACH_DISTANCE.get());
            double reach = attribute != null ? attribute.getValue() : DEFAULT_REACH_DISTANCE;
            int radius = AOEUpgradeItem.additionalAOERadius * aoeLevel;

            Direction blockDirection = BlockUtil.getBlockDirection(level, player, reach);
            if (blockDirection == Direction.SOUTH || blockDirection == Direction.NORTH) {
                destroyBlocks(blockPos, player, serverLevel, radius, radius, 0);
            } else if (blockDirection == Direction.EAST || blockDirection == Direction.WEST) {
                destroyBlocks(blockPos, player, serverLevel, 0, radius, radius);
            } else if (blockDirection == Direction.UP || blockDirection == Direction.DOWN) {
                destroyBlocks(blockPos, player, serverLevel, radius, 0, radius);
            }

            return true;
        }
        return super.mineBlock(itemStack, level, blockState, blockPos, entity);
    }

    private void destroyBlocks(BlockPos blockPos, ServerPlayer player, ServerLevel serverLevel, int xRadius, int yRadius, int zRadius) {
        List<BlockPos> blockPositions = BlockUtil.getAOEBlockPos(xRadius, yRadius, zRadius, blockPos);
        List<BlockState> blockStates = blockPositions.stream().map(serverLevel::getBlockState).collect(Collectors.toList());
        BlockUtil.destroyBlocksInAOE(serverLevel, player, blockPositions, blockStates, this);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(blocks) && TierSortingRegistry.isCorrectTierForDrops(getTier(stack), state);
    }

    private Tier getTier(ItemStack itemStack) {
        CompoundTag tag = TagUtil.getNBT(itemStack);
        int tier = tag.getInt(TIER_TAG_NAME);
        if (tier < DEFAULT_TIER) {
            tag.putInt(TIER_TAG_NAME, DEFAULT_TIER);
            tier = DEFAULT_TIER;
        }
        return TIER_LIST.get(tier);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }

    @Override
    public void breakBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity entity) {
        super.mineBlock(itemStack, level, blockState, blockPos, entity);
    }
}
