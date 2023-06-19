package ru.kao.titanforgedpickaxe.item.upgrade.util;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;


abstract public class AbstractItemProperties<T extends Item.Properties> extends Item.Properties {
    @Override
    public T food(FoodProperties properties) {
        return (T) super.food(properties);
    }

    @Override
    public T stacksTo(int stackCount) {
        return (T) super.stacksTo(stackCount);
    }

    @Override
    public T defaultDurability(int defaultDurability) {
        return (T) super.defaultDurability(defaultDurability);
    }

    @Override
    public T durability(int durability) {
        return (T) super.durability(durability);
    }

    @Override
    public T craftRemainder(Item craftRemainder) {
        return (T) super.craftRemainder(craftRemainder);
    }

    @Override
    public T tab(CreativeModeTab tab) {
        return (T) super.tab(tab);
    }

    @Override
    public T rarity(Rarity rarity) {
        return (T) super.rarity(rarity);
    }

    @Override
    public T fireResistant() {
        return (T) super.fireResistant();
    }

    @Override
    public T setNoRepair() {
        return (T) super.setNoRepair();
    }
}
