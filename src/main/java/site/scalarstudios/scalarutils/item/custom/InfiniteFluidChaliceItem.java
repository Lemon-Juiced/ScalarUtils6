package site.scalarstudios.scalarutils.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class InfiniteFluidChaliceItem extends BucketItem {

    public InfiniteFluidChaliceItem(Fluid content, Properties properties) {
        super(content, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // Call super to handle placement logic
        InteractionResultHolder<ItemStack> result = super.use(level, player, hand);

        // If placement succeeded, return the original stack (do not consume)
        if (result.getResult().consumesAction()) {
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
        }
        // Otherwise, return the original result
        return result;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        String fluidName;
        if (content.equals(Fluids.WATER)){
            fluidName = ".water";
        } else if (content.equals(Fluids.LAVA)) {
            fluidName = ".lava";
        } else {
            fluidName = ""; // Fallback for other fluids, if needed
        }

        tooltipComponents.add(Component.translatable("tooltip.scalarutils.infinite_fluid_chalice" + fluidName));
    }
}
