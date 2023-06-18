package ru.kao.titanforgedpickaxe;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import static ru.kao.titanforgedpickaxe.init.ModItems.CORE_ITEMS;
import static ru.kao.titanforgedpickaxe.init.ModItems.UPGRADE_ITEMS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TitanForgedPickaxe.MODID)
public class TitanForgedPickaxe
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "titanforgedpickaxe";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public TitanForgedPickaxe()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
//        modEventBus.addListener(this::addCreative);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        CORE_ITEMS.register(modEventBus);
        UPGRADE_ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

//    private void addCreative(CreativeModeTab event) {
//        if (event.getTab() == ModCreativeModeTabs.ENDERITE_TAB) {
//            event.accept(ModItems.RAW_ENDERITE);
//            event.accept(ModItems.ENDERITE_SCRAP);
//            event.accept(ModItems.ENDERITE_INGOT);
//            event.accept(ModItems.RAW_ENDERITE_BLOCK_ITEM);
//            event.accept(ModItems.ENDERITE_ORE_ITEM);
//            event.accept(ModItems.ENDERITE_BLOCK_ITEM);
//            event.accept(ModItems.ENDERITE_HELMET);
//            event.accept(ModItems.ENDERITE_CHESTPLATE);
//            event.accept(ModItems.ENDERITE_LEGGINGS);
//            event.accept(ModItems.ENDERITE_BOOTS);
//            event.accept(ModItems.ENDERITE_ELYTRA_CHESTPLATE);
//            event.accept(ModItems.ENDERITE_SWORD);
//            event.accept(ModItems.ENDERITE_PICKAXE);
//            event.accept(ModItems.ENDERITE_AXE);
//            event.accept(ModItems.ENDERITE_SHOVEL);
//            event.accept(ModItems.ENDERITE_HOE);
//            //event.accept(ModItems.END_RESPAWN_ANCHOR);
//
//            event.accept(ModItems.GILDED_ENDERITE_HELMET);
//            event.accept(ModItems.GILDED_ENDERITE_CHESTPLATE);
//            event.accept(ModItems.GILDED_ENDERITE_LEGGINGS);
//            event.accept(ModItems.GILDED_ENDERITE_BOOTS);
//            event.accept(ModItems.GILDED_ENDERITE_ELYTRA_CHESTPLATE);
//            event.accept(ModItems.GILDED_ENDERITE_SWORD);
//            event.accept(ModItems.GILDED_ENDERITE_PICKAXE);
//            event.accept(ModItems.GILDED_ENDERITE_AXE);
//            event.accept(ModItems.GILDED_ENDERITE_SHOVEL);
//            event.accept(ModItems.GILDED_ENDERITE_HOE);
//            event.accept(ModItems.GILDED_ENDERITE_BLOCK_ITEM);
//        }
//    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
