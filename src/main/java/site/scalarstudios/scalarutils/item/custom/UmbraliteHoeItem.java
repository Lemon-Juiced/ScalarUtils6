package site.scalarstudios.scalarutils.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.List;
import site.scalarstudios.scalarutils.datacomponent.ScalarDataComponents;
import site.scalarstudios.scalarutils.item.tier.ScalarTiers;

import static net.minecraft.world.item.ShearsItem.createToolProperties;

public class UmbraliteHoeItem extends HoeItem {
    private static final int[] BREAK_THRESHOLDS = {64, 192, 448, 960, 1984};

    public UmbraliteHoeItem(Properties properties) {
        super(ScalarTiers.UMBRALITE, properties.component(DataComponents.TOOL, createToolProperties()));
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if (!level.isClientSide && !state.isAir()) {
            int breaks = stack.get(ScalarDataComponents.UMBRALITE_HOE_BREAKS.get()) != null ? stack.get(ScalarDataComponents.UMBRALITE_HOE_BREAKS.get()) : 0;
            stack.set(ScalarDataComponents.UMBRALITE_HOE_BREAKS.get(), breaks + 1);
        }
        return super.mineBlock(stack, level, state, pos, miningEntity);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (level.isClientSide) return;
        if (!(entity instanceof Player player)) return;
        boolean isHeld = selected || (player.getMainHandItem() == stack || player.getOffhandItem() == stack);
        int breaks = stack.get(ScalarDataComponents.UMBRALITE_HOE_BREAKS.get()) != null ? stack.get(ScalarDataComponents.UMBRALITE_HOE_BREAKS.get()) : 0;
        int hasteLevel = getHasteLevel(breaks);
        if (isHeld && hasteLevel > 0) {
            MobEffectInstance effect = new MobEffectInstance(MobEffects.DIG_SPEED, 40, hasteLevel - 1, false, false, true);
            player.addEffect(effect);
        }
    }

    private int getHasteLevel(int breaks) {
        for (int i = BREAK_THRESHOLDS.length - 1; i >= 0; i--) {
            if (breaks >= BREAK_THRESHOLDS[i]) return i + 1;
        }
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        int breaks = stack.get(ScalarDataComponents.UMBRALITE_HOE_BREAKS.get()) != null ? stack.get(ScalarDataComponents.UMBRALITE_HOE_BREAKS.get()) : 0;
        int hasteLevel = getHasteLevel(breaks);
        int nextLevel = hasteLevel < BREAK_THRESHOLDS.length ? BREAK_THRESHOLDS[hasteLevel] : -1;
        if (hasteLevel > 0) {
            tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_hoe.haste", hasteLevel).withStyle(ChatFormatting.AQUA));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_hoe.no_haste").withStyle(ChatFormatting.GRAY));
        }
        if (nextLevel != -1) {
            tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_hoe.breaks_to_next", nextLevel - breaks).withStyle(ChatFormatting.YELLOW));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_tool.max_level").withStyle(ChatFormatting.GREEN));
        }
        tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_hoe.total_breaks", breaks).withStyle(ChatFormatting.DARK_PURPLE));
    }
}