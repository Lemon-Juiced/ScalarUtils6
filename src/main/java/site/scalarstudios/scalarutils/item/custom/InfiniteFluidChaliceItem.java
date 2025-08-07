package site.scalarstudios.scalarutils.item.custom;

import dev.lemonjuice.scalar_core.item.InfiniteFluidBucketItem;
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

public class InfiniteFluidChaliceItem extends InfiniteFluidBucketItem {

    public InfiniteFluidChaliceItem(Fluid content, Properties properties) {
        super(content, properties);
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
