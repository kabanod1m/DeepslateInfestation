package org.winglessbirds.deepslateinfestation.client;

import net.fabricmc.api.ClientModInitializer;
import org.winglessbirds.deepslateinfestation.DeepslateInfestation;
import org.winglessbirds.deepslateinfestation.client.render.entity.EntityRenderers;
import org.winglessbirds.deepslateinfestation.client.render.entity.model.EntityModelLayers;

public class DeepslateInfestationClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        DeepslateInfestation.LOG.debug("Initializing DeepslateInfestation: (Client) EntityRenderers: " + EntityRenderers.init());
        DeepslateInfestation.LOG.debug("Initializing DeepslateInfestation: (Client) ModelLayers: " + EntityModelLayers.init());
    }

}
