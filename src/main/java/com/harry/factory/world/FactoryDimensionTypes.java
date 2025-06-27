package com.harry.factory.world;

import com.harry.factory.FactoryMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class FactoryDimensionTypes {
    public static final RegistryKey<DimensionType> FACTORY_OVERWORLD_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(FactoryMod.MOD_ID, "factory_overworld"));

    public static void bootstrap(Registerable<DimensionType> context) {
        context.register(FACTORY_OVERWORLD_TYPE_KEY, new DimensionType(
                OptionalLong.of(18000), // fixedTime
                true,  // hasSkylight
                true,  // hasCeiling
                true,  // ultrawarm
                false, // natural
                1.0,   // coordinateScale
                false, // bedWorks
                true,  // respawnAnchorWorks
                0,     // minY
                256,   // height
                256,   // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.THE_END_ID, // effectsLocation
                0.1f,  // ambientLight
                new DimensionType.MonsterSettings(false, // no genera monstruos
                        false, // no tiene un budget de spawneo especial
                        UniformIntProvider.create(0, 7), // rango de luz para spawnear
                        0) // block light limit
        ));
    }
}