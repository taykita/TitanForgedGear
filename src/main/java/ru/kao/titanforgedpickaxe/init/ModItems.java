package ru.kao.titanforgedpickaxe.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.kao.titanforgedpickaxe.group.MainItemGroup;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;
import ru.kao.titanforgedpickaxe.item.tool.TitanPickaxeItem;
import ru.kao.titanforgedpickaxe.item.upgrade.EfficiencyUpgradeItem;
import ru.kao.titanforgedpickaxe.item.upgrade.LevelingUpgradeItem;
import ru.kao.titanforgedpickaxe.item.upgrade.UpgradeBaseItem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class ModItems {

    public static final DeferredRegister<Item> CORE_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TitanForgedPickaxe.MODID);

    public static final RegistryObject<Item> TF_PICKAXE = CORE_ITEMS.register("titan_forged_pickaxe",
            () -> {
                ForgeTier tier = new ForgeTier(
                        10, 1000, 5, 5, 30,
                        BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.IRON_INGOT));
                return new TitanPickaxeItem(tier, 100, -2.8f,
                        new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(MainItemGroup.MAIN_ITEM_GROUP).durability(20000));
            });


    public static final DeferredRegister<Item> UPGRADE_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TitanForgedPickaxe.MODID);

    public static final RegistryObject<Item> UPGRADE_BASE = UPGRADE_ITEMS.register(
            "upgrade_base",
            () -> new UpgradeBaseItem(new Item.Properties().tab(MainItemGroup.MAIN_ITEM_GROUP)));

    public static final RegistryObject<Item> EFFICIENCY_UPGRADE_TIER_1 = UPGRADE_ITEMS.register(
            "efficiency_upgrade_level_1",
            () -> new EfficiencyUpgradeItem(new LevelingUpgradeItem.LevelingUpgradeProperties().tab(MainItemGroup.MAIN_ITEM_GROUP).level(1)));

    public static final RegistryObject<Item> EFFICIENCY_UPGRADE_TIER_2 = UPGRADE_ITEMS.register(
            "efficiency_upgrade_level_2",
            () -> new EfficiencyUpgradeItem(new LevelingUpgradeItem.LevelingUpgradeProperties().tab(MainItemGroup.MAIN_ITEM_GROUP).level(2)));

    public static final RegistryObject<Item> EFFICIENCY_UPGRADE_TIER_3 = UPGRADE_ITEMS.register(
            "efficiency_upgrade_level_3",
            () -> new EfficiencyUpgradeItem(new LevelingUpgradeItem.LevelingUpgradeProperties().tab(MainItemGroup.MAIN_ITEM_GROUP).level(3)));

    public static final RegistryObject<Item> EFFICIENCY_UPGRADE_TIER_4 = UPGRADE_ITEMS.register(
            "efficiency_upgrade_level_4",
            () -> new EfficiencyUpgradeItem(new LevelingUpgradeItem.LevelingUpgradeProperties().tab(MainItemGroup.MAIN_ITEM_GROUP).level(4)));

    public static final RegistryObject<Item> EFFICIENCY_UPGRADE_TIER_5 = UPGRADE_ITEMS.register(
            "efficiency_upgrade_level_5",
            () -> new EfficiencyUpgradeItem(new LevelingUpgradeItem.LevelingUpgradeProperties().tab(MainItemGroup.MAIN_ITEM_GROUP).level(5)));

//    public static final RegistryObject<Item> ENCHANTED_EFFICIENCY_UPGRADE_TIER_1 = UPGRADE_ITEMS.register(
//            "efficiency_upgrade_level_1",
//            () -> new EfficiencyUpgradeItem(new LevelingUpgradeItem.LevelingUpgradeProperties().tab(MainItemGroup.MAIN_ITEM_GROUP).level(1)));
//
//    public static final RegistryObject<Item> ENCHANTED_EFFICIENCY_UPGRADE_TIER_2 = UPGRADE_ITEMS.register(
//            "efficiency_upgrade_level_2",
//            () -> new EfficiencyUpgradeItem(new LevelingUpgradeItem.LevelingUpgradeProperties().tab(MainItemGroup.MAIN_ITEM_GROUP).level(2)));

}
