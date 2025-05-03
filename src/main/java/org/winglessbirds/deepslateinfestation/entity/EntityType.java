package org.winglessbirds.deepslateinfestation.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.winglessbirds.deepslateinfestation.DeepslateInfestation;
import org.winglessbirds.deepslateinfestation.entity.mob.DeepslateSilverfishEntity;

public class EntityType {

    public static final net.minecraft.entity.EntityType<DeepslateSilverfishEntity> DEEPSLATE_SILVERFISH = Registry.register(Registries.ENTITY_TYPE, new Identifier(DeepslateInfestation.MODID, "deepslate_silverfish"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DeepslateSilverfishEntity::new).dimensions(EntityDimensions.fixed(0.4f, 0.3f)).trackRangeBlocks(10).build());

    public static boolean init () {
        return true;
    }

}
