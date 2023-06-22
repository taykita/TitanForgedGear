package ru.kao.titanforgedpickaxe.data.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;
import ru.kao.titanforgedpickaxe.item.util.TagUtil;

import static net.minecraft.world.level.storage.loot.parameters.LootContextParams.TOOL;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.AUTO_SMELT_ENABLED_TAG_NAME;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.AUTO_SMELT_TAG_NAME;

public class AutoSmeltModifier extends LootModifier {
    public static final Codec<AutoSmeltModifier> CODEC =
            RecordCodecBuilder.create(inst -> LootModifier.codecStart(inst).apply(inst, AutoSmeltModifier::new));

    public AutoSmeltModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        CompoundTag toolNBT = TagUtil.getNBT(context.getParam(TOOL));
        if (toolNBT.getBoolean(AUTO_SMELT_TAG_NAME) && toolNBT.getBoolean(AUTO_SMELT_ENABLED_TAG_NAME)) {
            ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();
            generatedLoot.forEach((stack) -> newLoot.add(
                    context.getLevel().getRecipeManager()
                            .getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel())
                            .map(SmeltingRecipe::getResultItem)
                            .filter(itemStack -> !itemStack.isEmpty())
                            .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                            .orElse(stack)));
            return newLoot;
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return AutoSmeltModifier.CODEC;
    }
}
