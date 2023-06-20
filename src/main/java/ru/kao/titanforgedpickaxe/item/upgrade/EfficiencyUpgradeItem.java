package ru.kao.titanforgedpickaxe.item.upgrade;

import com.mojang.logging.LogUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import ru.kao.titanforgedpickaxe.item.upgrade.util.UpgradeUtil;

import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.EFFICIENCY_TAG_NAME;

public class EfficiencyUpgradeItem extends LevelingUpgradeItem {
    public EfficiencyUpgradeItem(Properties properties) {
        super(properties);
        NEED_EXP = 500;
    }
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final float MULTIPLIER = 3;
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return UpgradeUtil.tryToUpgrade(player, interactionHand,
                LEVEL*NEED_EXP,
                EFFICIENCY_TAG_NAME, LEVEL,
                (ItemStack itemStack) -> itemStack.getTag().putInt(EFFICIENCY_TAG_NAME, LEVEL));
    }

}
