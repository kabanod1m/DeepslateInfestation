package org.winglessbirds.deepslateinfestation.world.gen.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.winglessbirds.deepslateinfestation.DeepslateInfestation;
import org.winglessbirds.deepslateinfestation.block.Blocks;

import java.util.List;

public class OreConfiguredFeatures {
    // vein sizes TODO: this can't be THIS stupid, why do I even write the same info in those jsons if I end up writing the same thing here?
    static final int size_normal_small = 3;
    static final int size_normal_medium = 7;
    static final int size_normal_large = 15;
    static final int size_cobbled_small = 3;
    static final int size_cobbled_medium = 5;
    static final int size_cobbled_large = 8;
    static final int size_air = 3;

    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE_INFESTED_DEEPSLATE_SMALL = registerKey("ore_deepslate_infested_deepslate_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE_INFESTED_DEEPSLATE_MEDIUM = registerKey("ore_deepslate_infested_deepslate_medium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE_INFESTED_DEEPSLATE_LARGE = registerKey("ore_deepslate_infested_deepslate_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_SMALL = registerKey("ore_deepslate_infested_cobbled_deepslate_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_MEDIUM = registerKey("ore_deepslate_infested_cobbled_deepslate_medium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_LARGE = registerKey("ore_deepslate_infested_cobbled_deepslate_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_COBBLED_DEEPSLATE_SMALL = registerKey("ore_cobbled_deepslate_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_COBBLED_DEEPSLATE_MEDIUM = registerKey("ore_cobbled_deepslate_medium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_COBBLED_DEEPSLATE_LARGE = registerKey("ore_cobbled_deepslate_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_AIR = registerKey("ore_air");

    public static void bootstrap (Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<OreFeatureConfig.Target> deepslateInfestedDeepslateOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplaceables, Blocks.DEEPSLATE_INFESTED_DEEPSLATE.getDefaultState())
        );
        List<OreFeatureConfig.Target> deepslateInfestedCobbledDeepslateOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplaceables, Blocks.DEEPSLATE_INFESTED_COBBLED_DEEPSLATE.getDefaultState())
        );
        List<OreFeatureConfig.Target> cobbledDeepslateOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplaceables, net.minecraft.block.Blocks.COBBLED_DEEPSLATE.getDefaultState())
        );
        List<OreFeatureConfig.Target> airOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplaceables, net.minecraft.block.Blocks.AIR.getDefaultState())
        );
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_DEEPSLATE_SMALL, Feature.ORE, new OreFeatureConfig(deepslateInfestedDeepslateOres, size_normal_small));
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_DEEPSLATE_MEDIUM, Feature.ORE, new OreFeatureConfig(deepslateInfestedDeepslateOres, size_normal_medium));
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_DEEPSLATE_LARGE, Feature.ORE, new OreFeatureConfig(deepslateInfestedDeepslateOres, size_normal_large));
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_SMALL, Feature.ORE, new OreFeatureConfig(deepslateInfestedCobbledDeepslateOres, size_cobbled_small));
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_MEDIUM, Feature.ORE, new OreFeatureConfig(deepslateInfestedCobbledDeepslateOres, size_cobbled_medium));
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_LARGE, Feature.ORE, new OreFeatureConfig(deepslateInfestedCobbledDeepslateOres, size_cobbled_large));
        register(featureRegisterable, ORE_COBBLED_DEEPSLATE_SMALL, Feature.ORE, new OreFeatureConfig(cobbledDeepslateOres, size_cobbled_small));
        register(featureRegisterable, ORE_COBBLED_DEEPSLATE_MEDIUM, Feature.ORE, new OreFeatureConfig(cobbledDeepslateOres, size_cobbled_medium));
        register(featureRegisterable, ORE_COBBLED_DEEPSLATE_LARGE, Feature.ORE, new OreFeatureConfig(cobbledDeepslateOres, size_cobbled_large));
        register(featureRegisterable, ORE_AIR, Feature.ORE, new OreFeatureConfig(airOres, size_air));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey (String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(DeepslateInfestation.MODID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register (Registerable<ConfiguredFeature<?, ?>> context,
                                                                                    RegistryKey<ConfiguredFeature<?, ?>> key,
                                                                                    F feature,
                                                                                    FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
