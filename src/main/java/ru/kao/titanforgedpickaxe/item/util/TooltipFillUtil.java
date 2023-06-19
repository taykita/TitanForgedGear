package ru.kao.titanforgedpickaxe.item.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

import java.util.List;

public class TooltipFillUtil {
    public static void fillTooltipNumberText(List<Component> components, CompoundTag tag, String tooltipLink, Number value) {
        components.add(MutableComponent.
                create(new TranslatableContents(
                        tooltipLink))
                .append(" ")
                .append(String.valueOf(value)));
    }
    public static void fillTooltipIntTextFromTag(List<Component> components, CompoundTag tag, String tooltipLink, String tagKey) {
        components.add(MutableComponent.
                create(new TranslatableContents(
                        tooltipLink))
                .append(" ")
                .append(String.valueOf(tag.getInt(tagKey))));
    }
}
