package com.harry.factory.world;

import com.harry.factory.FactoryMod;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> CEILING_GLASS_KEY = registerKey("ceiling_glass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_BEDROCK_KEY = registerKey("floor_bedrock");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_ANDESITE_1_KEY = registerKey("floor_andesite_1");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_ANDESITE_2_KEY = registerKey("floor_andesite_2");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOOR_BLACKSTONE_KEY = registerKey("floor_blackstone");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COPPER_ORE_KEY = registerKey("copper_ore_patches");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COAL_ORE_KEY = registerKey("coal_ore_patches");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GEAR_ORE_KEY = registerKey("gear_ore_patches");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMOOTH_BASALT_PATCH_KEY = registerKey("smooth_basalt_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RAIL_PATCH_KEY = registerKey("rail_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ANVIL_PATCH_KEY = registerKey("anvil_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LEAF_BUSH_KEY = registerKey("leaf_bush");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LAVA_PUDDLE_KEY = registerKey("lava_puddle");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var blackstoneRule = new BlockMatchRuleTest(Blocks.BLACKSTONE);

        register(context, CEILING_GLASS_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(128, Blocks.TINTED_GLASS.getDefaultState()));
        register(context, FLOOR_BEDROCK_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(0, Blocks.BEDROCK.getDefaultState()));
        register(context, FLOOR_ANDESITE_1_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(1, Blocks.ANDESITE.getDefaultState()));
        register(context, FLOOR_ANDESITE_2_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(2, Blocks.ANDESITE.getDefaultState()));
        register(context, FLOOR_BLACKSTONE_KEY, Feature.FILL_LAYER, new FillLayerFeatureConfig(3, Blocks.BLACKSTONE.getDefaultState()));
        register(context, COPPER_ORE_KEY, Feature.ORE, new OreFeatureConfig(blackstoneRule, Blocks.COPPER_BLOCK.getDefaultState(), 12));
        register(context, COAL_ORE_KEY, Feature.ORE, new OreFeatureConfig(blackstoneRule, Blocks.COAL_BLOCK.getDefaultState(), 17));
        register(context, GEAR_ORE_KEY, Feature.ORE, new OreFeatureConfig(blackstoneRule, Blocks.DEEPSLATE_IRON_ORE.getDefaultState(), 9));
        register(context, SMOOTH_BASALT_PATCH_KEY, Feature.ORE, new OreFeatureConfig(blackstoneRule, Blocks.SMOOTH_BASALT.getDefaultState(), 33));
        register(context, RAIL_PATCH_KEY, Feature.ORE, new OreFeatureConfig(new BlockMatchRuleTest(Blocks.AIR), Blocks.RAIL.getDefaultState(), 8));
        register(context, ANVIL_PATCH_KEY, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(1, 0, 0, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.ANVIL)))));
        register(context, LEAF_BUSH_KEY, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(48, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.OAK_LEAVES)))));
        register(context, LAVA_PUDDLE_KEY, Feature.ORE, new OreFeatureConfig(new BlockMatchRuleTest(Blocks.BLACKSTONE), Blocks.LAVA.getDefaultState(), 12));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(FactoryMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}