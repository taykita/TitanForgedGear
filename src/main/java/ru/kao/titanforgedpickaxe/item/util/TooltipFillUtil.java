package ru.kao.titanforgedpickaxe.item.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

import java.util.List;

public class TooltipFillUtil {

    public static void fillUpgradeInfo(List<Component> components, CompoundTag tag, String tagName, String tooltipLink) {
        if (tag.getInt(tagName) > 0) {
            fillTooltipIntTextFromTag(
                    components, tag, tooltipLink, tagName);
        }
    }

    public static void fillUpgradeInfo(List<Component> components, CompoundTag tag, String tagName, String tooltipLink, List list) {
        if (tag.getInt(tagName) > 0) {
            fillTooltipTextFromList(
                    components, tag, tooltipLink, tagName, list);
        }
    }

    public static void fillTooltipNumberText(List<Component> components, String tooltipLink, Number value) {
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

    public static void fillTooltipTextFromList(List<Component> components, CompoundTag tag, String tooltipLink, String tagKey, List list) {
        components.add(MutableComponent.
                create(new TranslatableContents(
                        tooltipLink))
                .append(" ")
                .append(String.valueOf(list.get(tag.getInt(tagKey)))));
    }

    public static void fillTooltipText(List<Component> components, String tooltipLink) {
        components.add(MutableComponent.
                create(new TranslatableContents(tooltipLink)));
    }
}
