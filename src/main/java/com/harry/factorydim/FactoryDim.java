package com.harry.factorydim;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryDim implements ModInitializer {
    public static final String MOD_ID = "factorydim";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Clave para registrar nuestra dimensión
    public static final RegistryKey<World> FACTORY_OVERWORLD_KEY = RegistryKey.of(
            RegistryKeys.WORLD,
            new Identifier(MOD_ID, "factory_overworld")
    );

    // Clave para el tipo de dimensión
    public static final RegistryKey<DimensionType> FACTORY_DIMENSION_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE,
            new Identifier(MOD_ID, "factory_overworld")
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Factory Dimension...");

        // Registramos los biomas para que /locate y el autocompletado funcionen
        ModBiomes.registerBiomes();

        LOGGER.info("Factory Dimension Initialized!");
    }
}