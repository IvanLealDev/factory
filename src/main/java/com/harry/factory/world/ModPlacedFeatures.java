package com.harry.factory.world;

import com.harry.factory.FactoryMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.YOffset;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> CEILING_GLASS_PLACED_KEY = registerKey("ceiling_glass_placed");
    public static final RegistryKey<PlacedFeature> FLOOR_BEDROCK_PLACED_KEY = registerKey("floor_bedrock_placed");
    public static final RegistryKey<PlacedFeature> FLOOR_ANDESITE_1_PLACED_KEY = registerKey("floor_andesite_1_placed");
    public static final RegistryKey<PlacedFeature> FLOOR_ANDESITE_2_PLACED_KEY = registerKey("floor_andesite_2_placed");
    public static final RegistryKey<PlacedFeature> FLOOR_BLACKSTONE_PLACED_KEY = registerKey("floor_blackstone_placed");
    public static final RegistryKey<PlacedFeature> COPPER_ORE_PLACED_KEY = registerKey("copper_ore_patches_placed");
    public static final RegistryKey<PlacedFeature> COAL_ORE_PLACED_KEY = registerKey("coal_ore_patches_placed");
    public static final RegistryKey<PlacedFeature> GEAR_ORE_PLACED_KEY = registerKey("gear_ore_patches_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatureRegistryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, CEILING_GLASS_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.CEILING_GLASS_KEY), List.of());
        register(context, FLOOR_BEDROCK_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.FLOOR_BEDROCK_KEY), List.of());
        register(context, FLOOR_ANDESITE_1_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.FLOOR_ANDESITE_1_KEY), List.of());
        register(context, FLOOR_ANDESITE_2_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.FLOOR_ANDESITE_2_KEY), List.of());
        register(context, FLOOR_BLACKSTONE_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.FLOOR_BLACKSTONE_KEY), List.of());

        List<PlacementModifier> orePlacementModifiers = List.of(
                CountPlacementModifier.of(4),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(4), YOffset.fixed(4))
        );
        register(context, COPPER_ORE_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.COPPER_ORE_KEY), orePlacementModifiers);
        register(context, COAL_ORE_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.COAL_ORE_KEY), orePlacementModifiers);
        register(context, GEAR_ORE_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.GEAR_ORE_KEY), orePlacementModifiers);
    }


    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(FactoryMod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(feature, modifiers));
    }
}