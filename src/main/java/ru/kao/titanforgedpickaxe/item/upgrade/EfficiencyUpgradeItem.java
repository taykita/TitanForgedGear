package ru.kao.titanforgedpickaxe.item.upgrade;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.List;

import static ru.kao.titanforgedpickaxe.init.ModItems.TF_PICKAXE;
import static ru.kao.titanforgedpickaxe.init.ModItems.UPGRADE_ITEMS;
import static ru.kao.titanforgedpickaxe.item.util.TooltipFillUtil.fillTooltipNumberText;

public class EfficiencyUpgradeItem extends LevelingUpgradeItem {
    public EfficiencyUpgradeItem(Properties properties) {
        super(properties);
    }
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final int NEED_EXP = 500;
    public static final float MULTIPLIER = 3;
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemInMainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack itemInOffHand = player.getItemInHand(InteractionHand.OFF_HAND);
        for (RegistryObject<Item> itemSupplier: UPGRADE_ITEMS.getEntries()){
            if (itemInOffHand.is(itemSupplier.get())) {
                return upgradeTFPickaxe(itemInMainHand, itemInOffHand);
            } else if (itemInMainHand.is(itemSupplier.get())) {
                return upgradeTFPickaxe(itemInOffHand, itemInMainHand);
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }

    @NotNull
    private InteractionResultHolder<ItemStack> upgradeTFPickaxe(ItemStack itemInMainHand, ItemStack itemInOffHand) {
        if (!itemInMainHand.is(TF_PICKAXE.get())) {
            LOGGER.debug("No pickaxe in hand");
            return InteractionResultHolder.fail(itemInOffHand);
        }

        CompoundTag tag = itemInMainHand.getTag();
        if (tag == null) {
            LOGGER.debug("Not enough mining experience");
            return InteractionResultHolder.fail(itemInOffHand);
        }

        int mineExp = tag.getInt("mineExp");
        EfficiencyUpgradeItem efficiencyUpgradeItem = (EfficiencyUpgradeItem) itemInOffHand.getItem();
        int necessaryExpToImprove = efficiencyUpgradeItem.LEVEL * NEED_EXP;

        if (mineExp < necessaryExpToImprove) {
            LOGGER.debug("Not enough mining experience");
            return InteractionResultHolder.fail(itemInOffHand);
        }

        if (tag.getInt("efficiencyLevel") >= efficiencyUpgradeItem.LEVEL) {
            LOGGER.debug("Improvement level below current");
            return InteractionResultHolder.fail(itemInOffHand);
        }

        mineExp -= necessaryExpToImprove;
        tag.putInt("mineExp", mineExp);
        tag.putInt("efficiencyLevel", efficiencyUpgradeItem.LEVEL);
        LOGGER.debug("Successfully level up");

        return InteractionResultHolder.success(ItemStack.EMPTY);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        CompoundTag tag = itemStack.getTag();
        fillTooltipNumberText(
                components, tag, "tooltip.titanforgedpickaxe.efficiency_upgrade.tooltip.need.mine.exp", NEED_EXP*LEVEL);
    }
}
