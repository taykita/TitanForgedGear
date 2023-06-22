package ru.kao.titanforgedpickaxe.key.binding;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;
import ru.kao.titanforgedpickaxe.networking.ModMessages;
import ru.kao.titanforgedpickaxe.networking.packet.SwitchAutoSmeltC2SPacket;

import static ru.kao.titanforgedpickaxe.key.binding.ModKeyBindings.SWITCH_AUTO_SMELT;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = TitanForgedPickaxe.MODID)
public class KeyHandler {
    private static final Minecraft MINECRAFT = Minecraft.getInstance();
    @SubscribeEvent
    public static void keyEvent(final InputEvent.Key event) {
        if (SWITCH_AUTO_SMELT.consumeClick()) {
            ModMessages.sendToServer(new SwitchAutoSmeltC2SPacket());
        }
    }
}
