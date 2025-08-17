package site.scalarstudios.scalarutils.datacomponent;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;

import java.util.function.Supplier;

public class ScalarDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, ScalarUtils.MODID);

    public static final Supplier<DataComponentType<Integer>> UMBRALITE_AXE_BREAKS = DATA_COMPONENT_TYPES.register(
            "umbralite_axe_breaks",
            () -> DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static final Supplier<DataComponentType<Integer>> UMBRALITE_PICKAXE_BREAKS = DATA_COMPONENT_TYPES.register(
            "umbralite_pickaxe_breaks",
            () -> DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static final Supplier<DataComponentType<Integer>> UMBRALITE_HOE_BREAKS = DATA_COMPONENT_TYPES.register(
            "umbralite_hoe_breaks",
            () -> DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static final Supplier<DataComponentType<Integer>> UMBRALITE_SHOVEL_BREAKS = DATA_COMPONENT_TYPES.register(
            "umbralite_shovel_breaks",
            () -> DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static final Supplier<DataComponentType<Integer>> UMBRALITE_SWORD_KILLS = DATA_COMPONENT_TYPES.register(
            "umbralite_sword_kills",
            () -> DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
