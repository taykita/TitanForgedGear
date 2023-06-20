package ru.kao.titanforgedpickaxe.item.upgrade.util;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static ru.kao.titanforgedpickaxe.init.ModItems.TF_PICKAXE;
import static ru.kao.titanforgedpickaxe.init.ModItems.UPGRADE_ITEMS;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.EXP_TAG_NAME;

public final class UpgradeUtil {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final List<Tier> TIER_LIST = new ArrayList<>(Arrays.asList(Tiers.WOOD, Tiers.STONE, Tiers.IRON, Tiers.DIAMOND, Tiers.NETHERITE));

    @NotNull
    public static InteractionResultHolder<ItemStack> tryToUpgrade(Player player, InteractionHand interactionHand,
                                                            int necessaryExpToImprove, String upgradeNBTName, UpgradeAction upgradeAction) {
        return tryToUpgrade(player, interactionHand, necessaryExpToImprove, upgradeNBTName, 1, upgradeAction);
    }

    @NotNull
    public static InteractionResultHolder<ItemStack> tryToUpgrade(Player player, InteractionHand interactionHand,
                                                                  int necessaryExpToImprove, String upgradeNBTName,
                                                                  int level, UpgradeAction upgradeAction) {
        ItemStack itemInMainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack itemInOffHand = player.getItemInHand(InteractionHand.OFF_HAND);
        for (RegistryObject<Item> itemSupplier: UPGRADE_ITEMS.getEntries()){
            if (itemInOffHand.is(itemSupplier.get())) {
                return UpgradeUtil.upgradeTFPickaxe(itemInMainHand, itemInOffHand, necessaryExpToImprove, upgradeNBTName, level, upgradeAction);
            } else if (itemInMainHand.is(itemSupplier.get())) {
                return UpgradeUtil.upgradeTFPickaxe(itemInOffHand, itemInMainHand, necessaryExpToImprove, upgradeNBTName, level, upgradeAction);
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }

    @NotNull
    public static InteractionResultHolder<ItemStack> upgradeTFPickaxe (
            ItemStack itemInMainHand, ItemStack itemInOffHand, int necessaryExpToImprove,
            String upgradeNBTName, int level, UpgradeAction upgradeAction) {

        if (!itemInMainHand.is(TF_PICKAXE.get())) {
            LOGGER.debug("No pickaxe in hand");
            return InteractionResultHolder.fail(itemInOffHand);
        }

        CompoundTag nbt = itemInMainHand.getTag();
        if (nbt == null) {
            LOGGER.debug("Not enough mining experience");
            return InteractionResultHolder.fail(itemInOffHand);
        }

        int mineExp = nbt.getInt(EXP_TAG_NAME);

        if (mineExp < necessaryExpToImprove) {
            LOGGER.debug("Not enough mining experience");
            return InteractionResultHolder.fail(itemInOffHand);
        }

        if (nbt.getInt(upgradeNBTName) >= level) {
            LOGGER.debug("Improvement level below current");
            return InteractionResultHolder.fail(itemInOffHand);
        }

        mineExp -= necessaryExpToImprove;
        nbt.putInt(EXP_TAG_NAME, mineExp);

        upgradeAction.action(itemInMainHand);

        LOGGER.debug("Successfully level up");

        return InteractionResultHolder.success(ItemStack.EMPTY);
    }

    @FunctionalInterface
    public interface UpgradeAction {
        void action(ItemStack itemStack);
    }
}
