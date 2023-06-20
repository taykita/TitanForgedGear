package ru.kao.titanforgedpickaxe.item.tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kao.titanforgedpickaxe.api.tool.IImproving;

import java.util.List;

import static ru.kao.titanforgedpickaxe.item.upgrade.EfficiencyUpgradeItem.MULTIPLIER;
import static ru.kao.titanforgedpickaxe.item.upgrade.util.UpgradeUtil.TIER_LIST;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.*;
import static ru.kao.titanforgedpickaxe.item.util.TooltipFillUtil.fillTooltipIntTextFromTag;
import static ru.kao.titanforgedpickaxe.item.util.TooltipFillUtil.fillUpgradeInfo;

public class TitanPickaxeItem extends DiggerItem implements IImproving {

    public TitanPickaxeItem(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super((float)p_42962_, p_42963_, p_42961_, BlockTags.MINEABLE_WITH_PICKAXE, p_42964_);
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
        CompoundTag tag = itemStack.getTag();
        if (tag != null) {
            tag.putInt("HideFlags", 1);
            fillTooltipIntTextFromTag(components, tag, "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.mine.exp", EXP_TAG_NAME);

            fillUpgradeInfo(components, tag, EFFICIENCY_TAG_NAME,
                    "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.efficiency.level");
            fillUpgradeInfo(components, tag, FORTUNE_TAG_NAME,
                    "tooltip.titanforgedpickaxe.titan_forged_pickaxe.tooltip.fortune.level");
        }
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        CompoundTag tag = itemStack.getTag();
        if (tag == null) {
            return blockState.is(this.blocks) ? this.speed : 1.0F;
        }
        return blockState.is(this.blocks) ? this.speed + tag.getInt(EFFICIENCY_TAG_NAME)*MULTIPLIER : 1.0F;
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
