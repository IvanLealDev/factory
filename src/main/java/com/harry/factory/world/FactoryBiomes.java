package com.harry.factory.world;

import com.harry.factory.FactoryMod;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class FactoryBiomes {
    public static final RegistryKey<Biome> METAL_FORGE = register("metal_forge");
    public static final RegistryKey<Biome> COPPER_FOUNDRY = register("copper_foundry");
    public static final RegistryKey<Biome> SMOKE_CHIMNEY = register("smoke_chimney");
    public static final RegistryKey<Biome> GEAR_VALLEY = register("gear_valley");

    public static void bootstrap(Registerable<Biome> context) {
        RegistryEntryLookup<PlacedFeature> placedFeatures = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        context.register(METAL_FORGE, createMetalForgeBiome(placedFeatures));
        context.register(COPPER_FOUNDRY, createCopperFoundryBiome(placedFeatures));
        context.register(SMOKE_CHIMNEY, createSmokeChimneyBiome(placedFeatures));
        context.register(GEAR_VALLEY, createGearValleyBiome(placedFeatures));
    }

    private static RegistryKey<Biome> register(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(FactoryMod.MOD_ID, name));
    }

    private static void addFactoryBaseFeatures(GenerationSettings.Builder builder, RegistryEntryLookup<PlacedFeature> placedFeatures) {
        builder.feature(GenerationStep.Feature.RAW_GENERATION, placedFeatures.getOrThrow(ModPlacedFeatures.FLOOR_BEDROCK_PLACED_KEY));
        builder.feature(GenerationStep.Feature.RAW_GENERATION, placedFeatures.getOrThrow(ModPlacedFeatures.FLOOR_ANDESITE_1_PLACED_KEY));
        builder.feature(GenerationStep.Feature.RAW_GENERATION, placedFeatures.getOrThrow(ModPlacedFeatures.FLOOR_ANDESITE_2_PLACED_KEY));
        builder.feature(GenerationStep.Feature.RAW_GENERATION, placedFeatures.getOrThrow(ModPlacedFeatures.FLOOR_BLACKSTONE_PLACED_KEY));
        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, placedFeatures.getOrThrow(ModPlacedFeatures.CEILING_GLASS_PLACED_KEY));
        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, placedFeatures.getOrThrow(ModPlacedFeatures.RAIL_PLACED_KEY));
        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, placedFeatures.getOrThrow(ModPlacedFeatures.ANVIL_PLACED_KEY));
        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, placedFeatures.getOrThrow(ModPlacedFeatures.LEAF_BUSH_PLACED_KEY));
        builder.feature(GenerationStep.Feature.LAKES, placedFeatures.getOrThrow(ModPlacedFeatures.LAVA_PUDDLE_PLACED_KEY));
    }

    public static Biome createMetalForgeBiome(RegistryEntryLookup<PlacedFeature> placedFeatures) {
        GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
        addFactoryBaseFeatures(generationBuilder, placedFeatures);

        return new Biome.Builder()
                .precipitation(false).temperature(0.8f).downfall(0.0f)
                .effects(new BiomeEffects.Builder()
                        .skyColor(3342387).fogColor(1118481)
                        .waterColor(4159204).waterFogColor(329011)
                        .particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.025f)).build())
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings(generationBuilder.build())
                .build();
    }

    public static Biome createCopperFoundryBiome(RegistryEntryLookup<PlacedFeature> placedFeatures) {
        GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
        addFactoryBaseFeatures(generationBuilder, placedFeatures);
        generationBuilder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, placedFeatures.getOrThrow(ModPlacedFeatures.COPPER_ORE_PLACED_KEY));

        return new Biome.Builder()
                .precipitation(false).temperature(1.0f).downfall(0.0f)
                .effects(new BiomeEffects.Builder()
                        .skyColor(7887200).fogColor(10519427)
                        .waterColor(8617336).waterFogColor(5393229)
                        .particleConfig(new BiomeParticleConfig(ParticleTypes.WAX_ON, 0.015f)).build())
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings(generationBuilder.build())
                .build();
    }

    public static Biome createSmokeChimneyBiome(RegistryEntryLookup<PlacedFeature> placedFeatures) {
        GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
        addFactoryBaseFeatures(generationBuilder, placedFeatures);
        generationBuilder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, placedFeatures.getOrThrow(ModPlacedFeatures.COAL_ORE_PLACED_KEY));

        return new Biome.Builder()
                .precipitation(false).temperature(0.5f).downfall(0.0f)
                .effects(new BiomeEffects.Builder()
                        .skyColor(2236962).fogColor(4605510)
                        .waterColor(3815994).waterFogColor(1842204)
                        .particleConfig(new BiomeParticleConfig(ParticleTypes.SMOKE, 0.03f)).build())
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings(generationBuilder.build())
                .build();
    }

    public static Biome createGearValleyBiome(RegistryEntryLookup<PlacedFeature> placedFeatures) {
        GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
        addFactoryBaseFeatures(generationBuilder, placedFeatures);
        generationBuilder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, placedFeatures.getOrThrow(ModPlacedFeatures.GEAR_ORE_PLACED_KEY));
        generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, placedFeatures.getOrThrow(ModPlacedFeatures.SMOOTH_BASALT_PATCH_PLACED_KEY));

        return new Biome.Builder()
                .precipitation(false).temperature(0.7f).downfall(0.0f)
                .effects(new BiomeEffects.Builder()
                        .skyColor(8421504).fogColor(12632256)
                        .waterColor(6388580).waterFogColor(4345703).build())
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings(generationBuilder.build())
                .build();
    }
}