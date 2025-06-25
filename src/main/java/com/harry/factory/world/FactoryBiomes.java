package com.harry.factory.world;

import com.harry.factory.FactoryMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class FactoryBiomes {
    public static final RegistryKey<Biome> METAL_FORGE = RegistryKey.of(RegistryKeys.BIOME, new Identifier(FactoryMod.MOD_ID, "metal_forge"));
}