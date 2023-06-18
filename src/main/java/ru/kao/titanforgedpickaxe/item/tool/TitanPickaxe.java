package ru.kao.titanforgedpickaxe.item.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import ru.kao.titanforgedpickaxe.api.tool.IImproving;

public class TitanPickaxe extends DiggerItem implements IImproving {
    private float speedUp = 0;

    public TitanPickaxe(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super((float)p_42962_, p_42963_, p_42961_, BlockTags.MINEABLE_WITH_PICKAXE, p_42964_);
        this.blocks = BlockTags.MINEABLE_WITH_PICKAXE;
    }

    private final TagKey<Block> blocks;

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState p_41005_) {
        System.out.println("getDestroySpeed");
//        System.out.println(speedUp);
//        System.out.println(p_41005_.is(this.blocks) ? this.speed + speedUp : 1.0F);
        var nbt = itemStack.getTag();
        if (nbt != null) {
            Tag test = nbt.get("TEST");
            if (test != null)
                System.out.println(test.getAsString());
        }

        return p_41005_.is(this.blocks) ? this.speed + speedUp : 1.0F;
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        System.out.println("mineBlock");
//        System.out.println(itemStack.toString());
//        System.out.println(level.toString());
//        System.out.println(blockState);
//        System.out.println(blockPos);
//        System.out.println(livingEntity);
        var nbt = itemStack.getTag();
        if (nbt == null) {
            nbt = new CompoundTag();
            itemStack.setTag(nbt);
        }
        nbt.putString("TEST", "TEST_NAME");
        return super.mineBlock(itemStack, level, blockState, blockPos, livingEntity);
    }

    public void speedUp(long value) {
        this.speedUp = speedUp + value;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
