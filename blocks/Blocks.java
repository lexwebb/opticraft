package opticraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import opticraft.blocks.tileentity.BeamBlock;
import opticraft.blocks.tileentity.ItemLaserBlock;
import opticraft.blocks.tileentity.SolarCollectorBlock;
import opticraft.lib.Ids;
import opticraft.lib.Names;
import net.minecraft.block.Block;

public class Blocks {
	public static Block itemLaserTileBlock;
	public static Block solarCollectorTileBlock;
	public static Block beamTileBlock;

	public static void init() {
		itemLaserTileBlock = new ItemLaserBlock(Ids.TileEntityItemLaserBlock);
		solarCollectorTileBlock = new SolarCollectorBlock(Ids.TileEntitySolarCollectorBlock);
		beamTileBlock = new BeamBlock(Ids.TileEntityBeamBlock);
		
		GameRegistry.registerBlock(itemLaserTileBlock, Names.itemLaserTile_unlocalizedName);
		GameRegistry.registerBlock(solarCollectorTileBlock, Names.solarCollectorTile_unlocalizedName);
		GameRegistry.registerBlock(beamTileBlock, Names.beamTile_unlocalizedName);
	}

	public static void addNames() {
		LanguageRegistry.addName(itemLaserTileBlock, Names.itemLaserTile_name);
		LanguageRegistry.addName(solarCollectorTileBlock, Names.solarCollectorTile_name);
		LanguageRegistry.addName(beamTileBlock, Names.beamTile_name);
	}
}
