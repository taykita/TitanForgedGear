package ru.kao.titanforgedpickaxe.data.recipes;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;
import ru.kao.titanforgedpickaxe.data.modifier.AutoSmeltModifier;
import ru.kao.titanforgedpickaxe.init.ModItems;

public class LootModifierGenerator extends GlobalLootModifierProvider {
    public LootModifierGenerator(DataGenerator gen) {
        super(gen, TitanForgedPickaxe.MODID);
    }

    @Override
    protected void start() {
        add("titan_forged_pickaxe_smelting", new AutoSmeltModifier(
                new LootItemCondition[]{MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModItems.TF_PICKAXE.get())).build()}));
    }
}
