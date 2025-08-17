package site.scalarstudios.scalarutils.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;

import java.util.List;
import site.scalarstudios.scalarutils.datacomponent.ScalarDataComponents;
import site.scalarstudios.scalarutils.item.tier.ScalarTiers;

public class UmbraliteSwordItem extends SwordItem {
    // Cumulative kill thresholds: 64,  192,  448,  960,  1984
    //                             64, +128, +256, +512, +1024
    private static final int[] KILL_THRESHOLDS = {64, 192, 448, 960, 1984};

    public UmbraliteSwordItem(Properties properties) {
        super(ScalarTiers.UMBRALITE, properties.component(DataComponents.TOOL, createToolProperties()));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int kills = stack.get(ScalarDataComponents.UMBRALITE_SWORD_KILLS.get()) != null ? stack.get(ScalarDataComponents.UMBRALITE_SWORD_KILLS.get()) : 0;
        if (!target.level().isClientSide && target instanceof Mob && attacker instanceof Player && target.isDeadOrDying()) {
            stack.set(ScalarDataComponents.UMBRALITE_SWORD_KILLS.get(), kills + 1);
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (level.isClientSide) return;
        if (!(entity instanceof Player player)) return;
        boolean isHeld = selected || (player.getMainHandItem() == stack || player.getOffhandItem() == stack);
        int kills = stack.get(ScalarDataComponents.UMBRALITE_SWORD_KILLS.get()) != null ? stack.get(ScalarDataComponents.UMBRALITE_SWORD_KILLS.get()) : 0;
        int strengthLevel = getStrengthLevel(kills);
        if (isHeld && strengthLevel > 0) {
            MobEffectInstance effect = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, strengthLevel - 1, false, false, true);
            player.addEffect(effect);
        }
    }

    private int getStrengthLevel(int kills) {
        for (int i = KILL_THRESHOLDS.length - 1; i >= 0; i--) {
            if (kills >= KILL_THRESHOLDS[i]) return i + 1;
        }
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        int kills = stack.get(ScalarDataComponents.UMBRALITE_SWORD_KILLS.get()) != null ? stack.get(ScalarDataComponents.UMBRALITE_SWORD_KILLS.get()) : 0;
        int strengthLevel = getStrengthLevel(kills);
        int nextLevel = strengthLevel < KILL_THRESHOLDS.length ? KILL_THRESHOLDS[strengthLevel] : -1;
        if (strengthLevel > 0) {
            tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_sword.strength", strengthLevel).withStyle(ChatFormatting.AQUA));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_sword.no_strength").withStyle(ChatFormatting.GRAY));
        }
        if (nextLevel != -1) {
            tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_sword.kills_to_next", nextLevel - kills).withStyle(ChatFormatting.YELLOW));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_tool.max_level").withStyle(ChatFormatting.GREEN));
        }
        tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_sword.total_kills", kills).withStyle(ChatFormatting.DARK_PURPLE));
        tooltipComponents.add(Component.translatable("tooltip.scalarutils.umbralite_sword.aoe_note").withStyle(ChatFormatting.RED));
    }
}
