package opticraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import opticraft.lib.Ids;
import opticraft.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.src.ModLoader;

public class Blocks {
	public static LaserBlock laserTileBlock;
	public static SolarCollectorBlock solarCollectorTileBlock;
	public static LaserDetectorBlock laserDetectorTileBlock;
	public static FiberCableBlock fiberCableTileBlock;
	public static LuxCapacitorBlock luxBatteryTileBlock;
	public static RedstoneLaserBlock redstoneLaserTileBlock;
	public static MirrorBlock mirrorBlock;
	public static ColliderPipeBlock colliderPipeBlock;
	public static ColliderPipeCornerBlock colliderPipeCornerBlock;
	public static PrinterBlock printerBlock;

	public static void init() {
		laserTileBlock = new LaserBlock(Ids.TileEntityLaserBlock);
		solarCollectorTileBlock = new SolarCollectorBlock(Ids.TileEntitySolarCollectorBlock);
		laserDetectorTileBlock = new LaserDetectorBlock(Ids.TileEntityLaserDetectorBlock);
		fiberCableTileBlock = new FiberCableBlock(Ids.TileEntityFiberCable);
		luxBatteryTileBlock = new LuxCapacitorBlock(Ids.TileEntityLuxCapacitorBlock);
		redstoneLaserTileBlock = new RedstoneLaserBlock(Ids.TileEntityRedstoneLaserBlock);
		mirrorBlock = new MirrorBlock(Ids.TileEntityMirrorBlock);
		colliderPipeBlock = new ColliderPipeBlock(Ids.TileEntityColliderPipeBlock);
		colliderPipeCornerBlock = new ColliderPipeCornerBlock(Ids.TileEntityColliderPipeCornerBlock);
		printerBlock = new PrinterBlock(Ids.TileEntityPrinterBlock);
		
		
		GameRegistry.registerBlock(laserTileBlock);
		GameRegistry.registerBlock(solarCollectorTileBlock);
		GameRegistry.registerBlock(laserDetectorTileBlock);
		GameRegistry.registerBlock(fiberCableTileBlock);
		GameRegistry.registerBlock(luxBatteryTileBlock);
		GameRegistry.registerBlock(redstoneLaserTileBlock);
		GameRegistry.registerBlock(mirrorBlock);
		GameRegistry.registerBlock(colliderPipeBlock);
		GameRegistry.registerBlock(colliderPipeCornerBlock);
		GameRegistry.registerBlock(printerBlock);
				
		laserTileBlock.setUnlocalizedName(Names.laserTile_u);
		solarCollectorTileBlock.setUnlocalizedName(Names.solarCollectorTile_u);
		laserDetectorTileBlock.setUnlocalizedName(Names.laserDetectorTile_u);
		fiberCableTileBlock.setUnlocalizedName(Names.fiberCableTile_u);
		luxBatteryTileBlock.setUnlocalizedName(Names.luxCapacitorTile_u);
		redstoneLaserTileBlock.setUnlocalizedName(Names.redstoneLaserTile_u);
		mirrorBlock.setUnlocalizedName(Names.mirrorTile_u);
		colliderPipeBlock.setUnlocalizedName(Names.colliderPipeTile_u);
		colliderPipeCornerBlock.setUnlocalizedName(Names.colliderPipeCornerTile_u);
		printerBlock.setUnlocalizedName(Names.printerTile_u);

	}

	public static void addNames() {		
		LanguageRegistry.addName(laserTileBlock, Names.laserTile_n);
		LanguageRegistry.addName(solarCollectorTileBlock, Names.solarCollectorTile_n);
		LanguageRegistry.addName(laserDetectorTileBlock, Names.laserDetectorTile_n);
		LanguageRegistry.addName(fiberCableTileBlock, Names.fiberCableTile_n);
		LanguageRegistry.addName(luxBatteryTileBlock, Names.luxCapacitorTile_n);
		LanguageRegistry.addName(redstoneLaserTileBlock, Names.redstoneLaserTile_n);
		LanguageRegistry.addName(mirrorBlock, Names.mirrorTile_n);
		LanguageRegistry.addName(colliderPipeBlock, Names.colliderPipeTile_n);
		LanguageRegistry.addName(colliderPipeCornerBlock, Names.colliderPipeCornerTile_n);
		LanguageRegistry.addName(printerBlock, Names.printerTile_n);
	}
}
