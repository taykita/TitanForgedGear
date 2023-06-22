package ru.kao.titanforgedpickaxe.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;
import ru.kao.titanforgedpickaxe.data.recipes.LootModifierGenerator;
import ru.kao.titanforgedpickaxe.data.recipes.SmithingRecipesProvider;
import ru.kao.titanforgedpickaxe.data.recipes.WorkbenchRecipesProvider;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = TitanForgedPickaxe.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void buildData(GatherDataEvent event) throws IOException {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeServer(), new SmithingRecipesProvider(generator));
        generator.addProvider(event.includeServer(), new WorkbenchRecipesProvider(generator));
        generator.addProvider(event.includeServer(), new LootModifierGenerator(generator));

        generator.run();
    }
}
