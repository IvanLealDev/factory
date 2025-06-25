package com.harry.factory.world;

import com.harry.factory.FactoryMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> CEILING_GLASS_PLACED_KEY = registerKey("ceiling_glass_placed");
    public static final RegistryKey<PlacedFeature> FLOOR_BEDROCK_PLACED_KEY = registerKey("floor_bedrock_placed");
    public static final RegistryKey<PlacedFeature> FLOOR_ANDESITE_1_PLACED_KEY = registerKey("floor_andesite_1_placed");
    public static final RegistryKey<PlacedFeature> FLOOR_ANDESITE_2_PLACED_KEY = registerKey("floor_andesite_2_placed");
    public static final RegistryKey<PlacedFeature> FLOOR_BLACKSTONE_PLACED_KEY = registerKey("floor_blackstone_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, CEILING_GLASS_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CEILING_GLASS_KEY), List.of());
        register(context, FLOOR_BEDROCK_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FLOOR_BEDROCK_KEY), List.of());
        register(context, FLOOR_ANDESITE_1_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FLOOR_ANDESITE_1_KEY), List.of());
        register(context, FLOOR_ANDESITE_2_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FLOOR_ANDESITE_2_KEY), List.of());
        register(context, FLOOR_BLACKSTONE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FLOOR_BLACKSTONE_KEY), List.of());
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(FactoryMod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(feature, modifiers));
    }
}