package ru.kao.titanforgedpickaxe.api.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface ForgedTool {
    void breakBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity entity);
}
