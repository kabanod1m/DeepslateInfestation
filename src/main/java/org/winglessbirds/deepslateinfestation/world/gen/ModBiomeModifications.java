package org.winglessbirds.deepslateinfestation.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import org.winglessbirds.deepslateinfestation.world.gen.feature.OrePlacedFeatures;

public class ModBiomeModifications {
    public static boolean init () {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DEEPSLATE_INFESTED_DEEPSLATE_SMALL);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DEEPSLATE_INFESTED_DEEPSLATE_MEDIUM);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DEEPSLATE_INFESTED_DEEPSLATE_LARGE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_SMALL);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_MEDIUM);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DEEPSLATE_INFESTED_COBBLED_DEEPSLATE_LARGE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COBBLED_DEEPSLATE_SMALL);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COBBLED_DEEPSLATE_MEDIUM);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COBBLED_DEEPSLATE_LARGE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES).negate(), GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_AIR);

        return true;
    }
}
