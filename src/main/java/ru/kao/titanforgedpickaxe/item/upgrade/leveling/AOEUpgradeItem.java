package ru.kao.titanforgedpickaxe.item.upgrade.leveling;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import ru.kao.titanforgedpickaxe.item.upgrade.util.UpgradeUtil;

import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.AOE_TAG_NAME;

public class AOEUpgradeItem extends LevelingUpgradeItem {
    public AOEUpgradeItem(Properties properties) {
        super(properties);
        NEED_EXP = 3000;
    }

    public static final int additionalAOERadius = 1;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return UpgradeUtil.tryToUpgrade(player, interactionHand,
                getNecessaryExp(),
                AOE_TAG_NAME, LEVEL,
                (ItemStack itemStack) -> itemStack.getTag().putInt(AOE_TAG_NAME, LEVEL));
    }
}
