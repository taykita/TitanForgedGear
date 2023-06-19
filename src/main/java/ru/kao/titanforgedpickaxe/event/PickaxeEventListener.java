package ru.kao.titanforgedpickaxe.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;
import ru.kao.titanforgedpickaxe.item.tool.TitanPickaxeItem;

import static ru.kao.titanforgedpickaxe.init.ModItems.TF_PICKAXE;

@Mod.EventBusSubscriber(modid = TitanForgedPickaxe.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PickaxeEventListener {

    @SubscribeEvent
    public static void breakEvent(BlockEvent.BreakEvent event) {
        ItemStack mainHandItem = event.getPlayer().getMainHandItem();
        if (mainHandItem.is(TF_PICKAXE.get())) {
            CompoundTag tag = mainHandItem.getTag();
            if (tag == null) {
                tag = new CompoundTag();
                tag.putInt("mineExp", 1);
                mainHandItem.setTag(tag);
            }
            tag.putInt("mineExp", tag.getInt("mineExp") + 100);
            System.out.println(tag.getInt("mineExp"));
        }
    }
}
