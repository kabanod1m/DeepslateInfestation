package org.winglessbirds.deepslateinfestation.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.winglessbirds.deepslateinfestation.DeepslateInfestation;

public class Blocks {

    public static final Block DEEPSLATE_INFESTED_DEEPSLATE = registerBlock("deepslate_infested_deepslate", new RotatedDeepslateInfestedBlock(net.minecraft.block.Blocks.DEEPSLATE, AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY).sounds(BlockSoundGroup.DEEPSLATE)), ItemGroups.FUNCTIONAL);
    public static final Block DEEPSLATE_INFESTED_COBBLED_DEEPSLATE = registerBlock("deepslate_infested_cobbled_deepslate", new DeepslateInfestedBlock(net.minecraft.block.Blocks.COBBLED_DEEPSLATE, AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY).sounds(BlockSoundGroup.DEEPSLATE)), ItemGroups.FUNCTIONAL);

    private static Block registerBlock (String name, Block block, RegistryKey<ItemGroup> group) {
        registerBlockItem(name, block, group);
        return Registry.register(
                Registries.BLOCK,
                new Identifier(DeepslateInfestation.MODID, name),
                block
        );
    }

    private static Block registerBlock (String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(
                Registries.BLOCK,
                new Identifier(DeepslateInfestation.MODID, name),
                block
        );
    }

    private static Block registerBlockUnobtainable (String name, Block block) {
        return Registry.register(
                Registries.BLOCK,
                new Identifier(DeepslateInfestation.MODID, name),
                block
        );
    }

    private static Item registerBlockItem (String name, Block block, RegistryKey<ItemGroup> group) {
        Item toReturn = Registry.register(
                Registries.ITEM,
                new Identifier(DeepslateInfestation.MODID, name),
                new BlockItem(block, new FabricItemSettings())
        );
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(toReturn));
        return toReturn;
    }

    private static Item registerBlockItem (String name, Block block) {
        return Registry.register(
                Registries.ITEM,
                new Identifier(DeepslateInfestation.MODID, name),
                new BlockItem(block, new FabricItemSettings())
        );
    }

    public static boolean init () {
        return true;
    }

}
