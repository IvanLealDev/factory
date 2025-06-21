package com.harry.factorydim;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryDim implements ModInitializer {
    public static final String MOD_ID = "factory_overworld";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // 1. Clave para la DIMENSION como un todo (agrupa tipo y generador)
    public static final RegistryKey<DimensionOptions> DIMENSION_KEY =
            RegistryKey.of(RegistryKeys.DIMENSION, new Identifier(MOD_ID, "factory_overworld"));

    // 2. Clave para el MUNDO (lo que usas en los comandos)
    public static final RegistryKey<World> WORLD_KEY =
            RegistryKey.of(RegistryKeys.WORLD, new Identifier(MOD_ID, "factory_overworld"));

    // 3. Claves para cada BIOMA
    public static final RegistryKey<Biome> METAL_FORGE_KEY = registerBiomeKey("metal_forge");
    public static final RegistryKey<Biome> COPPER_FOUNDRY_KEY = registerBiomeKey("copper_foundry");
    public static final RegistryKey<Biome> SMOKE_CHIMNEY_KEY = registerBiomeKey("smoke_chimney");
    public static final RegistryKey<Biome> GEAR_VALLEY_KEY = registerBiomeKey("gear_valley");


    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Factory Overworld dimension...");
        // Gracias al sistema de data-driven, la mayoría del trabajo está en los JSON.
        // El simple hecho de declarar las RegistryKey estáticas asegura que se carguen
        // y estén disponibles para que el juego las encuentre en el Data Pack.
        // No se necesita más registro explícito aquí para este caso.
    }

    private static RegistryKey<Biome> registerBiomeKey(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(MOD_ID, name));
    }
}