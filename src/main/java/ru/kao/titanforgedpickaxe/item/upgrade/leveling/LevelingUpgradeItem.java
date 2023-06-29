package ru.kao.titanforgedpickaxe.item.upgrade.leveling;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kao.titanforgedpickaxe.item.upgrade.UpgradeItem;
import ru.kao.titanforgedpickaxe.item.upgrade.util.AbstractItemProperties;

import java.util.List;

import static ru.kao.titanforgedpickaxe.item.util.TooltipFillUtil.fillTooltipNumberText;

public class LevelingUpgradeItem extends UpgradeItem {
    public LevelingUpgradeItem(Properties properties) {
        super(properties);

        if (properties instanceof LevelingUpgradeProperties) {
            LEVEL = ((LevelingUpgradeProperties) properties).level;
        } else {
            LEVEL = 1;
        }
    }
    protected final int LEVEL;

    @Override
    protected int getNecessaryExp() {
        return NEED_EXP * LEVEL;
    }

    public static class LevelingUpgradeProperties extends AbstractItemProperties<LevelingUpgradeProperties> {
        int level;

        public LevelingUpgradeProperties level(int level) {
            this.level = level;
            return this;
        }
    }
}
