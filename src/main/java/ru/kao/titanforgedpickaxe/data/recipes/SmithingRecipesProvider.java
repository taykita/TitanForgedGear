package ru.kao.titanforgedpickaxe.data.recipes;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import org.jetbrains.annotations.NotNull;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;
import ru.kao.titanforgedpickaxe.init.ModItems;

import java.util.function.Consumer;

public class SmithingRecipesProvider extends RecipeProvider implements IConditionBuilder {
    public SmithingRecipesProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        UpgradeRecipeBuilder.smithing(
                Ingredient.of(Items.DIAMOND_PICKAXE),
                Ingredient.of(Items.OBSIDIAN),
                ModItems.TF_PICKAXE.get())
                .unlocks("has_diamond_pickaxe", has(Items.DIAMOND_PICKAXE))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "titan_forged_smithing"));

    }

    @NotNull
    @Override
    public String getName() {
        return "Smithing Recipes";
    }
}
