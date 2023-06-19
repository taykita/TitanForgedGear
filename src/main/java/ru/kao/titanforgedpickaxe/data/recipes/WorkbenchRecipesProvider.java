package ru.kao.titanforgedpickaxe.data.recipes;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;
import ru.kao.titanforgedpickaxe.init.ModItems;

import java.util.function.Consumer;

public class WorkbenchRecipesProvider extends RecipeProvider implements IConditionBuilder {
    public WorkbenchRecipesProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.UPGRADE_BASE.get())
                .define('P', ItemTags.PLANKS)
                .define('I', Items.IRON_INGOT)
                .pattern("IPI")
                .pattern("PIP")
                .pattern("IPI")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "upgrade_base"));

        efficiencyUpgradeShape(consumer);
    }

    private void efficiencyUpgradeShape(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.EFFICIENCY_UPGRADE_TIER_1.get())
                .define('O', Items.OBSIDIAN)
                .define('I', Items.IRON_INGOT)
                .define('B', ModItems.UPGRADE_BASE.get())
                .pattern("OIO")
                .pattern("IBI")
                .pattern("OIO")
                .unlockedBy("has_upgrade_base", has(ModItems.UPGRADE_BASE.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "efficiency_upgrade_tier_1"));

        ShapedRecipeBuilder.shaped(ModItems.EFFICIENCY_UPGRADE_TIER_2.get())
                .define('O', Items.OBSIDIAN)
                .define('D', Items.DIAMOND)
                .define('B', ModItems.EFFICIENCY_UPGRADE_TIER_2.get())
                .pattern("DOD")
                .pattern("OBO")
                .pattern("DOD")
                .unlockedBy("has_efficiency_upgrade_tier_1", has(ModItems.EFFICIENCY_UPGRADE_TIER_1.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "efficiency_upgrade_tier_2"));

        ShapedRecipeBuilder.shaped(ModItems.EFFICIENCY_UPGRADE_TIER_3.get())
                .define('T', ModItems.EFFICIENCY_UPGRADE_TIER_1.get())
                .define('D', Items.DIAMOND)
                .define('B', ModItems.EFFICIENCY_UPGRADE_TIER_2.get())
                .pattern("TDT")
                .pattern("DBD")
                .pattern("TDT")
                .unlockedBy("has_efficiency_upgrade_tier_2", has(ModItems.EFFICIENCY_UPGRADE_TIER_2.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "efficiency_upgrade_tier_3"));

        ShapedRecipeBuilder.shaped(ModItems.EFFICIENCY_UPGRADE_TIER_4.get())
                .define('T', ModItems.EFFICIENCY_UPGRADE_TIER_2.get())
                .define('E', Items.EMERALD)
                .define('B', ModItems.EFFICIENCY_UPGRADE_TIER_3.get())
                .pattern("TET")
                .pattern("EBE")
                .pattern("TET")
                .unlockedBy("has_efficiency_upgrade_tier_3", has(ModItems.EFFICIENCY_UPGRADE_TIER_3.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "efficiency_upgrade_tier_4"));

        ShapedRecipeBuilder.shaped(ModItems.EFFICIENCY_UPGRADE_TIER_5.get())
                .define('T', ModItems.EFFICIENCY_UPGRADE_TIER_3.get())
                .define('N', Items.NETHERITE_INGOT)
                .define('B', ModItems.EFFICIENCY_UPGRADE_TIER_4.get())
                .pattern("TNT")
                .pattern("NBN")
                .pattern("TNT")
                .unlockedBy("has_efficiency_upgrade_tier_4", has(ModItems.EFFICIENCY_UPGRADE_TIER_4.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "efficiency_upgrade_tier_5"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Smithing Recipes";
    }
}
