package ru.kao.titanforgedpickaxe.item.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class TagUtil {
    public static @NotNull CompoundTag getNBT(ItemStack itemStack) {
        CompoundTag tag = itemStack.getTag();
        if (tag != null) {
            return tag;
        }
        tag = new CompoundTag();
        itemStack.setTag(tag);
        return tag;
    }
}
