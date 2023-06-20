package ru.kao.titanforgedpickaxe.item.upgrade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kao.titanforgedpickaxe.item.upgrade.util.AbstractItemProperties;

import java.util.List;

import static ru.kao.titanforgedpickaxe.item.util.TooltipFillUtil.fillTooltipNumberText;

public class LevelingUpgradeItem extends Item {
    public LevelingUpgradeItem(Properties properties) {
        super(fillProperties(properties));

        if (properties instanceof LevelingUpgradeProperties) {
            LEVEL = ((LevelingUpgradeProperties) properties).level;
        } else {
            LEVEL = 1;
        }
    }
    protected final int LEVEL;
    protected int NEED_EXP;

    @NotNull
    private static Properties fillProperties(Properties properties) {
        return properties.stacksTo(1);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        CompoundTag tag = itemStack.getTag();
        fillTooltipNumberText(
                components, tag, "tooltip.titanforgedpickaxe.upgrade.tooltip.need.mine.exp", NEED_EXP*LEVEL);
    }

    public static class LevelingUpgradeProperties extends AbstractItemProperties<LevelingUpgradeProperties> {
        int level;

        public LevelingUpgradeProperties level(int level) {
            this.level = level;
            return this;
        }
    }
}
