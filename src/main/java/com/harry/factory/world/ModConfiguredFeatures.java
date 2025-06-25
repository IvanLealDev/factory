package com.harry.factory.world;

import com.harry.factory.FactoryMod;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.FillLayerFeatureConfig;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> CEILING_GLASS_KEY = registerKey("ceiling_glass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_BEDROCK_KEY = registerKey("floor_bedrock");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_ANDESITE_1_KEY = registerKey("floor_andesite_1");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_ANDESITE_2_KEY = registerKey("floor_andesite_2");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_BLACKSTONE_KEY = registerKey("floor_blackstone");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, CEILING_GLASS_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(128, Blocks.TINTED_GLASS.getDefaultState()));
        register(context, FLOOR_BEDROCK_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(0, Blocks.BEDROCK.getDefaultState()));
        register(context, FLOOR_ANDESITE_1_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(1, Blocks.ANDESITE.getDefaultState()));
        register(context, FLOOR_ANDESITE_2_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(2, Blocks.ANDESITE.getDefaultState()));
        register(context, FLOOR_BLACKSTONE_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(3, Blocks.BLACKSTONE.getDefaultState()));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(FactoryMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}