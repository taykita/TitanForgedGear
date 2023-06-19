package ru.kao.titanforgedpickaxe.item.upgrade;

import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import ru.kao.titanforgedpickaxe.item.upgrade.util.AbstractItemProperties;

public class LevelingUpgradeItem extends Item {
    public LevelingUpgradeItem(Properties properties) {
        super(fillProperties(properties));

        if (properties instanceof LevelingUpgradeProperties) {
            LEVEL = ((LevelingUpgradeProperties) properties).level;
        } else {
            LEVEL = 1;
        }
    }

    @NotNull
    private static Properties fillProperties(Properties properties) {
        return properties.stacksTo(1);
    }

    protected final int LEVEL;

    public static class LevelingUpgradeProperties extends AbstractItemProperties<LevelingUpgradeProperties> {
        int level;

        public LevelingUpgradeProperties level(int level) {
            this.level = level;
            return this;
        }
    }
}
