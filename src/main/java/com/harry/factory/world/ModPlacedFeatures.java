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
    public static final RegistryKey<PlacedFeature> SMOOTH_BASALT_PATCH_PLACED_KEY = registerKey("smooth_basalt_patch_placed");
    public static final RegistryKey<PlacedFeature> RAIL_PLACED_KEY = registerKey("rail_placed");
    public static final RegistryKey<PlacedFeature> ANVIL_PLACED_KEY = registerKey("anvil_placed");
    public static final RegistryKey<PlacedFeature> LEAF_BUSH_PLACED_KEY = registerKey("leaf_bush_placed");
    public static final RegistryKey<PlacedFeature> LAVA_PUDDLE_PLACED_KEY = registerKey("lava_puddle_placed");

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
        register(context, SMOOTH_BASALT_PATCH_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.SMOOTH_BASALT_PATCH_KEY), List.of(CountPlacementModifier.of(8), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(3), YOffset.fixed(3)), BiomePlacementModifier.of()));
        register(context, RAIL_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.RAIL_PATCH_KEY),
                List.of(
                        CountPlacementModifier.of(8),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.fixed(4), YOffset.fixed(4)),
                        BiomePlacementModifier.of()
                ));

        register(context, ANVIL_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.ANVIL_PATCH_KEY),
                List.of(
                        RarityFilterPlacementModifier.of(64),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.fixed(4), YOffset.fixed(4)),
                        BiomePlacementModifier.of()
                ));


        register(context, LEAF_BUSH_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.LEAF_BUSH_KEY),
                List.of(
                        RarityFilterPlacementModifier.of(8),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.fixed(4), YOffset.fixed(4)),
                        BiomePlacementModifier.of()
                ));

        register(context, LAVA_PUDDLE_PLACED_KEY, configuredFeatureRegistryLookup.getOrThrow(ModConfiguredFeatures.LAVA_PUDDLE_KEY),
                List.of(
                        RarityFilterPlacementModifier.of(20),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.fixed(4), YOffset.fixed(4)),
                        BiomePlacementModifier.of()
                ));
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(FactoryMod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(feature, modifiers));
    }
}