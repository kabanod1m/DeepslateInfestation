package org.winglessbirds.deepslateinfestation;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.winglessbirds.deepslateinfestation.block.Blocks;
import org.winglessbirds.deepslateinfestation.config.ModConfig;
import org.winglessbirds.deepslateinfestation.entity.EntityType;
import org.winglessbirds.deepslateinfestation.entity.SpawnRestriction;
import org.winglessbirds.deepslateinfestation.entity.attribute.AttributeRegistry;
import org.winglessbirds.deepslateinfestation.item.Items;
import org.winglessbirds.deepslateinfestation.world.gen.ModBiomeModifications;

public class DeepslateInfestation implements ModInitializer {

    public static final String MODID = "deepslateinfestation";
    public static final Logger LOG = LoggerFactory.getLogger(MODID);
    public static ModConfig CFG = new ModConfig();

    private static final String loginit = "Initializing " + MODID + ": ";

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        CFG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        LOG.debug(loginit + "Blocks: " + Blocks.init());
        LOG.debug(loginit + "Items: " + Items.init());
        LOG.debug(loginit + "Entities: " + EntityType.init());
        LOG.debug(loginit + "EntityAttributes: " + AttributeRegistry.init());
        LOG.debug(loginit + "SpawnRestrictions: " + SpawnRestriction.init());
        LOG.debug(loginit + "BiomeModifications: " + ModBiomeModifications.init());
    }

}
