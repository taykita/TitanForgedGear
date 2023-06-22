package ru.kao.titanforgedpickaxe.item.upgrade;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import ru.kao.titanforgedpickaxe.item.upgrade.util.UpgradeUtil;

import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.AUTO_SMELT_ENABLED_TAG_NAME;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.AUTO_SMELT_TAG_NAME;

public class AutoSmeltUpgrade extends UpgradeItem {
    public AutoSmeltUpgrade(Properties properties) {
        super(properties);
        //TODO pull to UpgradeItem constructor
        NEED_EXP = 10000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return UpgradeUtil.tryToUpgrade(player, interactionHand,
                getNecessaryExp(),
                AUTO_SMELT_TAG_NAME,
                (ItemStack itemStack) -> {
                    itemStack.getTag().putBoolean(AUTO_SMELT_TAG_NAME, true);
                    itemStack.getTag().putBoolean(AUTO_SMELT_ENABLED_TAG_NAME, true);
                });
    }
}
