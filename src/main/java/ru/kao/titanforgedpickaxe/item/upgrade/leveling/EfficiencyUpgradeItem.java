package ru.kao.titanforgedpickaxe.item.upgrade.leveling;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import ru.kao.titanforgedpickaxe.item.upgrade.util.UpgradeUtil;

import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.EFFICIENCY_TAG_NAME;

public class EfficiencyUpgradeItem extends LevelingUpgradeItem {
    public EfficiencyUpgradeItem(Item.Properties properties) {
        super(properties);
        NEED_EXP = 500;
    }
    public static final float MULTIPLIER = 4;
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return UpgradeUtil.tryToUpgrade(player, interactionHand,
                getNecessaryExp(),
                EFFICIENCY_TAG_NAME, LEVEL,
                (ItemStack itemStack) -> itemStack.getTag().putInt(EFFICIENCY_TAG_NAME, LEVEL));
    }

}
