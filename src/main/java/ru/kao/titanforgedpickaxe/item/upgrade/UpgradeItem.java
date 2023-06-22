package ru.kao.titanforgedpickaxe.item.upgrade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static ru.kao.titanforgedpickaxe.item.util.TooltipFillUtil.fillTooltipNumberText;

public class UpgradeItem extends Item {
    public UpgradeItem(Item.Properties properties) {
        super(fillProperties(properties));
    }
    @NotNull
    private static Properties fillProperties(Properties properties) {
        return properties.stacksTo(1);
    }
    protected int NEED_EXP;

    protected int getNecessaryExp() {
        return NEED_EXP;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        fillTooltipNumberText(
                components, "tooltip.titanforgedpickaxe.upgrade.tooltip.need.mine.exp", getNecessaryExp());
    }
}
