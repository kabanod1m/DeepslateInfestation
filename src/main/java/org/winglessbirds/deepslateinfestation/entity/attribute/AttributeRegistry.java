package org.winglessbirds.deepslateinfestation.entity.attribute;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.winglessbirds.deepslateinfestation.entity.EntityType;
import org.winglessbirds.deepslateinfestation.entity.mob.DeepslateSilverfishEntity;

public class AttributeRegistry {

    public static boolean init () {
        FabricDefaultAttributeRegistry.register(EntityType.DEEPSLATE_SILVERFISH, DeepslateSilverfishEntity.createDeepslateSilverfishAttributes());

        return true;
    }

}
