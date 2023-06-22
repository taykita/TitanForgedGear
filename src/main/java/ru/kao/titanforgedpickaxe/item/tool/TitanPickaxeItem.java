package ru.kao.titanforgedpickaxe.item.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;
import org.jetbrains.annotations.Nullable;
import ru.kao.titanforgedpickaxe.api.tool.IImproving;
import ru.kao.titanforgedpickaxe.item.util.TagUtil;

import java.util.List;

import static ru.kao.titanforgedpickaxe.item.upgrade.EfficiencyUpgradeItem.MULTIPLIER;
import static ru.kao.titanforgedpickaxe.item.upgrade.util.UpgradeUtil.TIER_LIST;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.*;
import static ru.kao.titanforgedpickaxe.item.util.TooltipFillUtil.*;

public class TitanPickaxeItem extends DiggerItem implements IImproving {

    public TitanPickaxeItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super((float)attackDamage, attackSpeed, tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
        this.blocks = BlockTags.MINEABLE_WITH_PICKAXE;
    }
    private final TagKey<Block> blocks;
    private final int DEFAULT_TIER = 2; // DEFAULT_TIER = getTier().getLevel()

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
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
        }
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        CompoundTag tag = TagUtil.getNBT(itemStack);
        float v = blockState.is(this.blocks) ? this.speed + tag.getInt(EFFICIENCY_TAG_NAME) * MULTIPLIER : 1.0F;
        return v;
    }

    @Override
    public boolean mineBlock(ItemStack p_40998_, Level p_40999_, BlockState p_41000_, BlockPos p_41001_, LivingEntity p_41002_) {
        return super.mineBlock(p_40998_, p_40999_, p_41000_, p_41001_, p_41002_);
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
    public int getLevel() {
        return 0;
    }
}
