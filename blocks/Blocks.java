package opticraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import opticraft.lib.Ids;
import opticraft.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.src.ModLoader;

public class Blocks {
	public static Block itemLaserTileBlock;
	public static Block solarCollectorTileBlock;
	public static Block laserDetectorTileBlock;

	public static void init() {
		itemLaserTileBlock = new ItemLaserBlock(Ids.TileEntityItemLaserBlock);
		solarCollectorTileBlock = new SolarCollectorBlock(Ids.TileEntitySolarCollectorBlock);
		laserDetectorTileBlock = new LaserDetectorBlock(Ids.TileEntityLaserDetectorBlock);
		
		
		GameRegistry.registerBlock(itemLaserTileBlock);
		GameRegistry.registerBlock(solarCollectorTileBlock);
		GameRegistry.registerBlock(laserDetectorTileBlock);
				
		itemLaserTileBlock.setUnlocalizedName(Names.itemLaserTile_unlocalizedName);
		solarCollectorTileBlock.setUnlocalizedName(Names.solarCollectorTile_unlocalizedName);
		laserDetectorTileBlock.setUnlocalizedName(Names.laserDetectorTile_unlocalizedName);

	}

	public static void addNames() {		
		LanguageRegistry.addName(itemLaserTileBlock, Names.itemLaserTile_name);
		LanguageRegistry.addName(solarCollectorTileBlock, Names.solarCollectorTile_name);
		LanguageRegistry.addName(laserDetectorTileBlock, Names.laserDetectorTile_name);
	}
}
