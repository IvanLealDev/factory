package com.harry.factory.datagen;

import com.harry.factory.FactoryMod;
import com.harry.factory.world.FactoryBiomes;
import com.harry.factory.world.FactoryDimensionTypes;
import com.harry.factory.world.ModConfiguredFeatures;
import com.harry.factory.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class FactoryDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(FactoryWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        FactoryMod.LOGGER.info("Building registry for Factory Mod");
        registryBuilder.addRegistry(RegistryKeys.DIMENSION_TYPE, FactoryDimensionTypes::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.BIOME, FactoryBiomes::bootstrap);
    }
}