package org.winglessbirds.deepslateinfestation.world.gen.feature;

import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;
import org.winglessbirds.deepslateinfestation.DeepslateInfestation;

import java.util.List;

public class OrePlacedFeatures {
    // vein per chunk TODO: this can't be THIS stupid, why do I even write the same info in those jsons if I end up writing the same thing here?
    static final int size_normal_small = 24;
    static final int size_normal_medium = 8;
    static final int size_normal_large = 4;
    static final int size_cobbled_small = 16;
    static final int size_cobbled_medium = 6;
    static final int size_cobbled_large = 3;
    static final int size_air = 16;
    static final int maxheight = 2;

    public static final RegistryKey<PlacedFeature> ORE_DEEPSLATE_INFESTED_DEEPSLATE_SMALL = registerKey("ore_deepslate_infested_deepslate_small");
    public static final RegistryKey<PlacedFeature> ORE_DEEPSLATE_INFESTED_DEEPSLATE_MEDIUM = registerKey("ore_deepslate_infested_deepslate_medium");
    public static final RegistryKey<PlacedFeature> ORE_DEEPSLATE_INFESTED_DEEPSLATE_LARGE = registerKey("ore_deepslate_infested_deepslate_large");
    public static final RegistryKey<PlacedFeature> ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_SMALL = registerKey("ore_deepslate_infested_cobbled_deepslate_small");
    public static final RegistryKey<PlacedFeature> ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_MEDIUM = registerKey("ore_deepslate_infested_cobbled_deepslate_medium");
    public static final RegistryKey<PlacedFeature> ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_LARGE = registerKey("ore_deepslate_infested_cobbled_deepslate_large");
    public static final RegistryKey<PlacedFeature> ORE_COBBLED_DEEPSLATE_SMALL = registerKey("ore_cobbled_deepslate_small");
    public static final RegistryKey<PlacedFeature> ORE_COBBLED_DEEPSLATE_MEDIUM = registerKey("ore_cobbled_deepslate_medium");
    public static final RegistryKey<PlacedFeature> ORE_COBBLED_DEEPSLATE_LARGE = registerKey("ore_cobbled_deepslate_large");
    public static final RegistryKey<PlacedFeature> ORE_AIR = registerKey("ore_air");

    public static void bootstrap (Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_DEEPSLATE_SMALL, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_DEEPSLATE_INFESTED_DEEPSLATE_SMALL),
                modifiersWithCount(size_normal_small, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_DEEPSLATE_MEDIUM, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_DEEPSLATE_INFESTED_DEEPSLATE_SMALL),
                modifiersWithCount(size_normal_medium, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_DEEPSLATE_LARGE, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_DEEPSLATE_INFESTED_DEEPSLATE_SMALL),
                modifiersWithCount(size_normal_large, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_SMALL, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_SMALL),
                modifiersWithCount(size_cobbled_small, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_MEDIUM, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_SMALL),
                modifiersWithCount(size_cobbled_medium, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
        register(featureRegisterable, ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_LARGE, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_SMALL),
                modifiersWithCount(size_cobbled_large, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
        register(featureRegisterable, ORE_COBBLED_DEEPSLATE_SMALL, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_COBBLED_DEEPSLATE_SMALL),
                modifiersWithCount(size_cobbled_small, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
        register(featureRegisterable, ORE_COBBLED_DEEPSLATE_MEDIUM, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_COBBLED_DEEPSLATE_SMALL),
                modifiersWithCount(size_cobbled_medium, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
        register(featureRegisterable, ORE_COBBLED_DEEPSLATE_LARGE, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_COBBLED_DEEPSLATE_SMALL),
                modifiersWithCount(size_cobbled_large, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
        register(featureRegisterable, ORE_AIR, configuredFeatureRegistryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_AIR),
                modifiersWithCount(size_air, HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(maxheight)))
        );
    }

    public static RegistryKey<PlacedFeature> registerKey (String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(DeepslateInfestation.MODID, name));
    }

    private static void register (Registerable<PlacedFeature> context,
                                  RegistryKey<PlacedFeature> key,
                                  RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                  List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static List<PlacementModifier> modifiers (PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> modifiersWithCount (int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static List<PlacementModifier> modifiersWithRarity (int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
