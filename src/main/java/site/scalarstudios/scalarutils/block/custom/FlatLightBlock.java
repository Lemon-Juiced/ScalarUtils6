package site.scalarstudios.scalarutils.block.custom;

import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FlatLightBlock extends RedstoneLampBlock {
    public FlatLightBlock(BlockBehaviour.Properties properties, boolean defaultOn) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, defaultOn));
    }
}