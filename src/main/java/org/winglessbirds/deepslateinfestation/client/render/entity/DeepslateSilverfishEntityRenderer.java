package org.winglessbirds.deepslateinfestation.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.winglessbirds.deepslateinfestation.DeepslateInfestation;
import org.winglessbirds.deepslateinfestation.client.render.entity.model.DeepslateSilverfishEntityModel;
import org.winglessbirds.deepslateinfestation.client.render.entity.model.EntityModelLayers;
import org.winglessbirds.deepslateinfestation.entity.mob.DeepslateSilverfishEntity;

@Environment(EnvType.CLIENT)
public class DeepslateSilverfishEntityRenderer extends MobEntityRenderer<DeepslateSilverfishEntity, DeepslateSilverfishEntityModel<DeepslateSilverfishEntity>> {

    private static final Identifier TEXTURE = new Identifier(DeepslateInfestation.MODID, "textures/entity/deepslate_silverfish/deepslate_silverfish.png");

    public DeepslateSilverfishEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DeepslateSilverfishEntityModel<>(context.getPart(EntityModelLayers.DEEPSLATE_SILVERFISH)), 0.25F);
    }

    @Override
    public Identifier getTexture(DeepslateSilverfishEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render (DeepslateSilverfishEntity entity, float f, float g, MatrixStack matrix, VertexConsumerProvider vertex, int i) {
        matrix.scale(0.6f, 0.6f, 0.6f);

        super.render(entity, f, g, matrix, vertex, i);
    }

}
