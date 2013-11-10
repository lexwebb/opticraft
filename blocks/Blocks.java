package opticraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import opticraft.blocks.tileentity.ItemLaserBlock;
import opticraft.blocks.tileentity.SolarCollectorBlock;
import opticraft.lib.Ids;
import opticraft.lib.Names;
import net.minecraft.block.Block;

public class Blocks {
	public static Block itemLaserBlock;
	public static Block itemLaserTileBlock;
	public static Block solarCollectorTileBlock;

	public static void init() {
		itemLaserTileBlock = new ItemLaserBlock(Ids.TileEntityItemLaserBlock);
		solarCollectorTileBlock = new SolarCollectorBlock(Ids.TileEntitySolarCollectorBlock);
		
		GameRegistry.registerBlock(itemLaserTileBlock, Names.itemLaserTile_name);
		GameRegistry.registerBlock(solarCollectorTileBlock, Names.solarCollectorTile_name);
	}

	public static void addNames() {
		LanguageRegistry.addName(itemLaserTileBlock, Names.itemLaserTile_name);
		LanguageRegistry.addName(solarCollectorTileBlock, Names.solarCollectorTile_name);
	}
}
