package ru.kao.titanforgedpickaxe.networking.packet;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import ru.kao.titanforgedpickaxe.item.util.TagUtil;

import java.util.function.Supplier;

import static ru.kao.titanforgedpickaxe.init.ModItems.TF_PICKAXE;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.AUTO_SMELT_ENABLED_TAG_NAME;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.AUTO_SMELT_TAG_NAME;

public class SwitchAutoSmeltC2SPacket {
    public SwitchAutoSmeltC2SPacket() {

    }

    public SwitchAutoSmeltC2SPacket(FriendlyByteBuf friendlyByteBuf) {

    }

    public void toBytes(FriendlyByteBuf byteBuf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer sender = context.getSender();
            if (sender == null) {
                return;
            }
            ItemStack itemInHand = sender.getMainHandItem();
            if (itemInHand.is(TF_PICKAXE.get())) {
                CompoundTag nbt = TagUtil.getNBT(itemInHand);
                if (nbt.getBoolean(AUTO_SMELT_TAG_NAME)) {
                    nbt.putBoolean(AUTO_SMELT_ENABLED_TAG_NAME, !nbt.getBoolean(AUTO_SMELT_ENABLED_TAG_NAME));
                }
            }
        });
        return true;
    }
}
