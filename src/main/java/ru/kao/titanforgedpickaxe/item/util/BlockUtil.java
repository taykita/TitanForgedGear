package ru.kao.titanforgedpickaxe.item.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GameMasterBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import ru.kao.titanforgedpickaxe.api.tool.ForgedTool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.PI;

public final class BlockUtil {
    public static Direction getBlockDirection(Level level, Player player, double reachDistance) {
        double xRot = player.getXRot();
        double yRot = player.getYRot();
        Vec3 eyePosition = player.getEyePosition(1.0F);
        Vec3 vec3Block = getCrossOnBlockPos(reachDistance, xRot, yRot, eyePosition);
        return level.clip(new ClipContext(eyePosition, vec3Block, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)).getDirection();
    }

    @NotNull
    private static Vec3 getCrossOnBlockPos(double reachDistance, double xRot, double yRot, Vec3 eyePosition) {
        double PiDiv2 = PI / 180F;
        double v1 = -yRot * PiDiv2;
        double v2 = -xRot * PiDiv2;
        double f = -Mth.cos((float) v2);
        double fX = Mth.sin((float) (v1 - PI)) * f;
        double fY = Mth.sin((float) v2);
        double fZ = Mth.cos((float) (v1 - PI)) * f;
        return eyePosition.add(fX * reachDistance, fY * reachDistance, fZ * reachDistance);
    }

    public static void destroyBlocksInAOE(
            ServerLevel level, ServerPlayer player, List<BlockPos> blocksPos,
            List<BlockState> blocksState, ForgedTool forgedTool) {
        Iterator<BlockPos> blocksPosIterator = blocksPos.iterator();
        Iterator<BlockState> blockStateIterator = blocksState.iterator();
        while (blocksPosIterator.hasNext() && blockStateIterator.hasNext()) {
            BlockPos blockPos = blocksPosIterator.next();
            BlockState blockState = blockStateIterator.next();
            if (isValidBlockForHarvest(level, player, blockPos, blockState)) {
                destroyBlock(level, player, blockPos, blockState, forgedTool);
            }
        }
    }

    private static boolean isValidBlockForHarvest(ServerLevel level, ServerPlayer player, BlockPos blockPos, BlockState blockState) {
        return blockState.canHarvestBlock(level, blockPos, player) && !blockState.is(Blocks.BEDROCK);
    }

    public static boolean destroyBlock(ServerLevel level, ServerPlayer player, BlockPos blockPos, BlockState blockState, ForgedTool forgedTool) {
        int exp = net.minecraftforge.common.ForgeHooks.onBlockBreakEvent(level, player.gameMode.getGameModeForPlayer(), player, blockPos);
        if (exp == -1) {
            return false;
        }
        BlockEntity blockentity = level.getBlockEntity(blockPos);
        Block block = blockState.getBlock();
        if (block instanceof GameMasterBlock && !player.canUseGameMasterBlocks()) {
            level.sendBlockUpdated(blockPos, blockState, blockState, 3);
            return false;
        }
        if (player.getMainHandItem().onBlockStartBreak(blockPos, player)) {
            return false;
        }
        if (player.blockActionRestricted(level, blockPos, player.gameMode.getGameModeForPlayer())) {
            return false;
        }
        if (player.gameMode.isCreative()) {
            removeBlock(blockPos, false, level, player);
            return true;
        }

        ItemStack itemInMainHand = player.getMainHandItem();
        ItemStack copyItemInMainHand = itemInMainHand.copy();

        boolean canHarvestBlock = blockState.canHarvestBlock(level, blockPos, player);
        forgedTool.breakBlock(itemInMainHand, level, blockState, blockPos, player);

        if (itemInMainHand.isEmpty() && !copyItemInMainHand.isEmpty()) {
            net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, copyItemInMainHand, InteractionHand.MAIN_HAND);
        }

        boolean blockIsHarvested = removeBlock(blockPos, canHarvestBlock, level, player);
        if (blockIsHarvested && canHarvestBlock) {
            block.playerDestroy(level, player, blockPos, blockState, blockentity, copyItemInMainHand);
        }
        if (blockIsHarvested && exp > 0) {
            blockState.getBlock().popExperience(level, blockPos, exp);
        }

        return true;
    }

    public static boolean removeBlock(BlockPos blockPos, boolean canHarvest, Level level, ServerPlayer player) {
        BlockState state = level.getBlockState(blockPos);
        boolean removed = state.onDestroyedByPlayer(level, blockPos, player, canHarvest, level.getFluidState(blockPos));
        if (removed)
            state.getBlock().destroy(level, blockPos, state);
        return removed;
    }

    public static List<BlockPos> getAOEBlockPos(int xRadius, int yRadius, int zRadius, BlockPos blockPos) {
        List<BlockPos> blockPosList = new ArrayList<>();

        if (xRadius == 0)
            for (int i = zRadius; i >= -zRadius; i--)
                for (int j = yRadius; j >= -yRadius; j--)
                    blockPosList.add(new BlockPos.MutableBlockPos(blockPos.getX(), blockPos.getY() + j, blockPos.getZ() + i));
        else if (yRadius == 0)
            for (int i = zRadius; i >= -zRadius; i--)
                for (int k = xRadius; k >= -xRadius; k--)
                    blockPosList.add(new BlockPos.MutableBlockPos(blockPos.getX() + k, blockPos.getY(), blockPos.getZ() + i));
        else if (zRadius == 0)
            for (int j = yRadius; j >= -yRadius; j--)
                for (int k = xRadius; k >= -xRadius; k--)
                    blockPosList.add(new BlockPos.MutableBlockPos(blockPos.getX() + k, blockPos.getY() + j, blockPos.getZ()));

        return blockPosList;
    }

}
