package ru.kao.titanforgedpickaxe.key.binding;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;

@Mod.EventBusSubscriber(modid = TitanForgedPickaxe.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModKeyBindings {
    private static final String CATEGORY = "key.category.titan_forged_pickaxe.general";
    public static final KeyMapping SWITCH_AUTO_SMELT = new KeyMapping("key.titan_forged_pickaxe.switch.auto_smelt",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM,GLFW.GLFW_KEY_C, CATEGORY);

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(SWITCH_AUTO_SMELT);
    }
}
