package com.harry.factory.world;

import com.harry.factory.FactoryMod;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> CEILING_GLASS_KEY = registerKey("ceiling_glass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_BEDROCK_KEY = registerKey("floor_bedrock");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_ANDESITE_1_KEY = registerKey("floor_andesite_1");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_ANDESITE_2_KEY = registerKey("floor_andesite_2");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_BLACKSTONE_KEY = registerKey("floor_blackstone");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COPPER_ORE_KEY = registerKey("copper_ore_patches");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COAL_ORE_KEY = registerKey("coal_ore_patches");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GEAR_ORE_KEY = registerKey("gear_ore_patches");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var blackstoneRule = new BlockMatchRuleTest(Blocks.BLACKSTONE);

        register(context, CEILING_GLASS_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(128, Blocks.TINTED_GLASS.getDefaultState()));
        register(context, FLOOR_BEDROCK_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(0, Blocks.BEDROCK.getDefaultState()));
        register(context, FLOOR_ANDESITE_1_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(1, Blocks.ANDESITE.getDefaultState()));
        register(context, FLOOR_ANDESITE_2_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(2, Blocks.ANDESITE.getDefaultState()));
        register(context, FLOOR_BLACKSTONE_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(3, Blocks.BLACKSTONE.getDefaultState()));
        register(context, COPPER_ORE_KEY, Feature.ORE, new OreFeatureConfig(blackstoneRule, Blocks.COPPER_BLOCK.getDefaultState(), 12));
        register(context, COAL_ORE_KEY, Feature.ORE, new OreFeatureConfig(blackstoneRule, Blocks.COAL_BLOCK.getDefaultState(), 17));
        register(context, GEAR_ORE_KEY, Feature.ORE, new OreFeatureConfig(blackstoneRule, Blocks.IRON_ORE.getDefaultState(), 9));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(FactoryMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}