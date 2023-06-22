package ru.kao.titanforgedpickaxe.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;
import ru.kao.titanforgedpickaxe.networking.packet.SwitchAutoSmeltC2SPacket;

import java.util.concurrent.atomic.AtomicInteger;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static AtomicInteger packetId = new AtomicInteger(0);
    private static int id() {
        return packetId.incrementAndGet();
    }

    public static void register() {
        INSTANCE = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(TitanForgedPickaxe.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE.messageBuilder(SwitchAutoSmeltC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SwitchAutoSmeltC2SPacket::new)
                .encoder(SwitchAutoSmeltC2SPacket::toBytes)
                .consumerMainThread(SwitchAutoSmeltC2SPacket::handle)
                .add();
    }

    public static <T> void sendToServer(T message) {
        INSTANCE.sendToServer(message);
    }

    public static <T> void sendToPlayer(T message, ServerPlayer serverPlayer) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), message);
    }

}
