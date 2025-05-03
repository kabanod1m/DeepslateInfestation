package org.winglessbirds.deepslateinfestation.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.winglessbirds.deepslateinfestation.DeepslateInfestation;
import org.winglessbirds.deepslateinfestation.entity.EntityType;

public class Items { // Block Items are registered separately, right away in the block.Blocks class!

    public static final Item SILVERFISH_SPAWN_EGG = registerItem("deepslate_silverfish_spawn_egg", new SpawnEggItem(EntityType.DEEPSLATE_SILVERFISH, 0x303030, 0x202020, new Item.Settings()), ItemGroups.SPAWN_EGGS);

    private static Item registerItem (String name, Item item, RegistryKey<ItemGroup> group) {
        Item toReturn = Registry.register(
                Registries.ITEM,
                new Identifier(DeepslateInfestation.MODID, name),
                item
        );
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(toReturn));
        return toReturn;
    }

    private static Item registerItem (String name, Item item) {
        return Registry.register(
                Registries.ITEM,
                new Identifier(DeepslateInfestation.MODID, name),
                item
        );
    }

    public static boolean init () {
        return true;
    }

}
