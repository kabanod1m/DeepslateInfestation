package org.winglessbirds.deepslateinfestation.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import org.winglessbirds.deepslateinfestation.entity.mob.DeepslateSilverfishEntity;

public class SpawnRestriction {

    public static boolean init () {
        net.minecraft.entity.SpawnRestriction.register(EntityType.DEEPSLATE_SILVERFISH, net.minecraft.entity.SpawnRestriction.Location.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DeepslateSilverfishEntity::canSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES, BiomeKeys.DEEP_DARK).negate(), SpawnGroup.MONSTER, EntityType.DEEPSLATE_SILVERFISH, 50, 1, 5);

        return true;
    }

}
