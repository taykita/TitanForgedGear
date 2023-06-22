package ru.kao.titanforgedpickaxe.item.upgrade;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import ru.kao.titanforgedpickaxe.item.upgrade.util.UpgradeUtil;

import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.TIER_TAG_NAME;

public class TierUpgradeItem extends LevelingUpgradeItem {
    public TierUpgradeItem(Properties properties) {
        super(properties);
        NEED_EXP = 2000;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return UpgradeUtil.tryToUpgrade(player, interactionHand,
                getNecessaryExp(),
                TIER_TAG_NAME, LEVEL,
                (ItemStack itemStack) -> itemStack.getTag().putInt(TIER_TAG_NAME, LEVEL));
    }

    @Override
    protected int getNecessaryExp() {
        return LEVEL > 3 ? LEVEL * NEED_EXP : NEED_EXP;
    }
}
