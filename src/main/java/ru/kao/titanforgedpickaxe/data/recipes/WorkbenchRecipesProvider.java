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
        fortuneUpgradeShape(consumer);
        tierUpgradeShape(consumer);
        autoSmeltUpgradeShape(consumer);
        aoeUpgradeShape(consumer);
    }

    private void aoeUpgradeShape(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.AOE_UPGRADE_LEVEL_1.get())
                .define('N', Items.NETHERITE_SCRAP)
                .define('D', Items.DIAMOND)
                .define('B', ModItems.UPGRADE_BASE.get())
                .pattern("DND")
                .pattern("NBN")
                .pattern("DND")
                .unlockedBy("has_upgrade_base", has(ModItems.UPGRADE_BASE.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "aoe_upgrade_level_1"));

        ShapedRecipeBuilder.shaped(ModItems.AOE_UPGRADE_LEVEL_2.get())
                .define('N', Items.NETHERITE_INGOT)
                .define('D', Items.DIAMOND_BLOCK)
                .define('B', ModItems.AOE_UPGRADE_LEVEL_1.get())
                .pattern("DND")
                .pattern("NBN")
                .pattern("DND")
                .unlockedBy("has_aoe_upgrade_level_1", has(ModItems.AOE_UPGRADE_LEVEL_1.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "aoe_upgrade_level_2"));
    }

    private void autoSmeltUpgradeShape(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.AUTO_SMELT_UPGRADE.get())
                .define('N', Items.NETHERITE_INGOT)
                .define('D', Items.DIAMOND)
                .define('M', Items.MAGMA_BLOCK)
                .define('B', ModItems.UPGRADE_BASE.get())
                .pattern("DND")
                .pattern("MBM")
                .pattern("DMD")
                .unlockedBy("has_upgrade_base", has(ModItems.UPGRADE_BASE.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "auto_smelt_upgrade"));
    }

    private void tierUpgradeShape(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.DIAMOND_TIER_UPGRADE.get())
                .define('I', Items.IRON_INGOT)
                .define('D', Items.DIAMOND)
                .define('B', ModItems.UPGRADE_BASE.get())
                .pattern("DID")
                .pattern("IBI")
                .pattern("DID")
                .unlockedBy("has_upgrade_base", has(ModItems.UPGRADE_BASE.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "diamond_tier_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.NETHERITE_TIER_UPGRADE.get())
                .define('D', Items.DIAMOND)
                .define('N', Items.NETHERITE_SCRAP)
                .define('B', ModItems.DIAMOND_TIER_UPGRADE.get())
                .pattern("NDN")
                .pattern("DBD")
                .pattern("NDN")
                .unlockedBy("had_diamond_upgrade", has(ModItems.DIAMOND_TIER_UPGRADE.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "netherite_tier_upgrade"));
    }

    private void fortuneUpgradeShape(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.FORTUNE_UPGRADE_TIER_1.get())
                .define('O', Items.OBSIDIAN)
                .define('L', Items.LAPIS_BLOCK)
                .define('B', ModItems.UPGRADE_BASE.get())
                .pattern("OLO")
                .pattern("LBL")
                .pattern("OLO")
                .unlockedBy("has_upgrade_base", has(ModItems.UPGRADE_BASE.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "fortune_upgrade_tier_1"));

        ShapedRecipeBuilder.shaped(ModItems.FORTUNE_UPGRADE_TIER_2.get())
                .define('L', Items.LAPIS_BLOCK)
                .define('D', Items.DIAMOND)
                .define('B', ModItems.FORTUNE_UPGRADE_TIER_1.get())
                .pattern("LDL")
                .pattern("DBD")
                .pattern("LDL")
                .unlockedBy("has_fortune_upgrade_tier_1", has(ModItems.FORTUNE_UPGRADE_TIER_1.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "fortune_upgrade_tier_2"));

        ShapedRecipeBuilder.shaped(ModItems.FORTUNE_UPGRADE_TIER_3.get())
                .define('T', ModItems.FORTUNE_UPGRADE_TIER_1.get())
                .define('D', Items.DIAMOND_BLOCK)
                .define('B', ModItems.FORTUNE_UPGRADE_TIER_2.get())
                .pattern("DTD")
                .pattern("TBT")
                .pattern("DTD")
                .unlockedBy("has_fortune_upgrade_tier_2", has(ModItems.FORTUNE_UPGRADE_TIER_2.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "fortune_upgrade_tier_3"));

        ShapedRecipeBuilder.shaped(ModItems.FORTUNE_UPGRADE_TIER_4.get())
                .define('T', ModItems.FORTUNE_UPGRADE_TIER_2.get())
                .define('E', Items.EMERALD_BLOCK)
                .define('B', ModItems.FORTUNE_UPGRADE_TIER_3.get())
                .pattern("ETE")
                .pattern("TBT")
                .pattern("ETE")
                .unlockedBy("has_fortune_upgrade_tier_3", has(ModItems.FORTUNE_UPGRADE_TIER_3.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "fortune_upgrade_tier_4"));

        ShapedRecipeBuilder.shaped(ModItems.FORTUNE_UPGRADE_TIER_5.get())
                .define('T', ModItems.FORTUNE_UPGRADE_TIER_3.get())
                .define('N', Items.NETHERITE_INGOT)
                .define('B', ModItems.FORTUNE_UPGRADE_TIER_4.get())
                .pattern("NTN")
                .pattern("TBT")
                .pattern("NTN")
                .unlockedBy("has_fortune_upgrade_tier_4", has(ModItems.FORTUNE_UPGRADE_TIER_4.get()))
                .save(consumer, new ResourceLocation(TitanForgedPickaxe.MODID, "fortune_upgrade_tier_5"));
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
                .define('B', ModItems.EFFICIENCY_UPGRADE_TIER_1.get())
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
                .define('N', Items.NETHERITE_SCRAP)
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
