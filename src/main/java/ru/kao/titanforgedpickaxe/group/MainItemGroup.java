package ru.kao.titanforgedpickaxe.group;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MainItemGroup extends CreativeModeTab {
    public static final MainItemGroup MAIN_ITEM_GROUP = new MainItemGroup(CreativeModeTab.TABS.length, "main");

    public MainItemGroup (int length, String label) {
        super(length, label);
    }
    public static CreativeModeTab TITAN_FORGED_TAB;


    @Override
    public ItemStack makeIcon() {
        return ItemStack.EMPTY;
    }
}
