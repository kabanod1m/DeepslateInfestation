package org.winglessbirds.deepslateinfestation.client.render.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.winglessbirds.deepslateinfestation.entity.EntityType;

public class EntityRenderers {

    public static boolean init () {
        EntityRendererRegistry.register(EntityType.DEEPSLATE_SILVERFISH, DeepslateSilverfishEntityRenderer::new);

        return true;
    }

}
