package org.winglessbirds.deepslateinfestation.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = org.winglessbirds.deepslateinfestation.DeepslateInfestation.MODID)
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class ModConfig implements ConfigData {

    @Comment("Limit maximum spawn light level\nMinimum = -1 (Do not spawn Deepslate Silverfishes randomly)\nMaximum = 15 (default) (Always spawn, unless there is skylight)")
    public int spawnLightLevelMax = 15;

    @Comment("Limit maximum height where Deepslate Silverfishes might spawn randomly")
    public int yLevelMax = 0;

}
