package ru.kao.titanforgedpickaxe.init;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;
import ru.kao.titanforgedpickaxe.data.modifier.AutoSmeltModifier;

public final class LootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, TitanForgedPickaxe.MODID);

    public static final RegistryObject<Codec<AutoSmeltModifier>> SMELTING = LOOT_MODIFIERS.register("titan_forged_pickaxe_smelting", () -> AutoSmeltModifier.CODEC);

}
