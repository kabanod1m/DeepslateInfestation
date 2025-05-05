package org.winglessbirds.deepslateinfestation.client.render.entity.model;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.winglessbirds.deepslateinfestation.DeepslateInfestation;

public class EntityModelLayers {

    private static final String MAIN = "main";

    public static final EntityModelLayer DEEPSLATE_SILVERFISH = new EntityModelLayer(new Identifier(DeepslateInfestation.MODID, "deepslate_silverfish"), MAIN);

    public static boolean init () {
        EntityModelLayerRegistry.registerModelLayer(DEEPSLATE_SILVERFISH, DeepslateSilverfishEntityModel::getTexturedModelData);

        return true;
    }

}
