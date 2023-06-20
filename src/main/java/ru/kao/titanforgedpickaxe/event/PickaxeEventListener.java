package ru.kao.titanforgedpickaxe.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.kao.titanforgedpickaxe.TitanForgedPickaxe;

import static ru.kao.titanforgedpickaxe.init.ModItems.TF_PICKAXE;
import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.EXP_TAG_NAME;

@Mod.EventBusSubscriber(modid = TitanForgedPickaxe.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PickaxeEventListener {

    @SubscribeEvent
    public static void breakEvent(BlockEvent.BreakEvent event) {
        ItemStack mainHandItem = event.getPlayer().getMainHandItem();
        if (mainHandItem.is(TF_PICKAXE.get())) {
            CompoundTag tag = mainHandItem.getTag();
            if (tag == null) {
                tag = new CompoundTag();
                tag.putInt(EXP_TAG_NAME, 1);
                mainHandItem.setTag(tag);
            }
            tag.putInt(EXP_TAG_NAME, tag.getInt(EXP_TAG_NAME) + 100000);
        }
    }
}
