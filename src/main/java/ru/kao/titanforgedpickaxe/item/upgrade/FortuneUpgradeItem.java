package ru.kao.titanforgedpickaxe.item.upgrade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import ru.kao.titanforgedpickaxe.item.upgrade.util.UpgradeUtil;

import static ru.kao.titanforgedpickaxe.item.util.PickaxeTagConstant.FORTUNE_TAG_NAME;

public class FortuneUpgradeItem extends LevelingUpgradeItem {
    public FortuneUpgradeItem(Properties properties) {
        super(properties);
        NEED_EXP = 2000;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return UpgradeUtil.tryToUpgrade(player,
                interactionHand, LEVEL*NEED_EXP,
                FORTUNE_TAG_NAME, LEVEL,
                (ItemStack itemStack) -> {
                    itemStack.isEnchanted();
                    int fortuneLevel = itemStack.getTag().getInt(FORTUNE_TAG_NAME);
                    if (fortuneLevel < LEVEL) {
                        if (itemStack.getTag().get("Enchantments") instanceof ListTag enchantments) {
                            for (Tag tag: enchantments) {
                                if (tag instanceof CompoundTag compoundTag) {
                                    String id = compoundTag.getString("id");
                                    if (id.equals("minecraft:fortune")) {
                                        compoundTag.putShort("lvl", (short) LEVEL);
                                        itemStack.getTag().putInt(FORTUNE_TAG_NAME, LEVEL);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    itemStack.enchant(Enchantments.BLOCK_FORTUNE, LEVEL);
                    itemStack.getTag().putInt(FORTUNE_TAG_NAME, LEVEL);
                });
    }

}
