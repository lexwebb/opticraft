package opticraft.proxies;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import opticraft.Opticraft;
import opticraft.blocks.Blocks;
import opticraft.entitys.EntityBeamX;
import opticraft.entitys.EntityBeamY;
import opticraft.entitys.EntityBeamZ;
import opticraft.entitys.TileEntityFiberCable;
import opticraft.entitys.TileEntityLaser;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntityLuxCapacitor;
import opticraft.entitys.TileEntityRedstoneLaser;
import opticraft.entitys.TileEntitySolarCollector;
import opticraft.items.ItemLaserWrench;
import opticraft.lib.Ids;
import opticraft.render.BeamRendererX;
import opticraft.render.BeamRendererY;
import opticraft.render.BeamRendererZ;
import opticraft.render.FiberCableRenderer;
import opticraft.render.LaserRenderer;
import opticraft.render.LaserDetectorRenderer;
import opticraft.render.LaserWrenchRenderer;
import opticraft.render.LuxCapacitorRenderer;
import opticraft.render.RedstoneLaserRenderer;
import opticraft.render.SolarCollectorRenderer;
import opticraft.sounds.BuzzSound;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void initRenderers() {
		
	}

	@Override
	public void initSounds() {
		MinecraftForge.EVENT_BUS.register(new BuzzSound());
	}
	
	public void registerRenderThings() {
		
		//itemLaser
		int ItemLaserRenderID = RenderingRegistry.getNextAvailableRenderId();
		LaserRenderer laserRenderer = new LaserRenderer(ItemLaserRenderID);
		Blocks.itemLaserTileBlock.setRenderType(ItemLaserRenderID);
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaser.class, laserRenderer);
    	RenderingRegistry.registerBlockHandler(laserRenderer);
    	
    	//itemLaser
		int RedstoneLaserRenderID = RenderingRegistry.getNextAvailableRenderId();
		RedstoneLaserRenderer redstoneLaserRenderer = new RedstoneLaserRenderer(RedstoneLaserRenderID);
		Blocks.redstoneLaserTileBlock.setRenderType(RedstoneLaserRenderID);
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRedstoneLaser.class, redstoneLaserRenderer);
    	RenderingRegistry.registerBlockHandler(redstoneLaserRenderer);
    	
    	//solarCollecter
    	int solarCollecterRenderID = RenderingRegistry.getNextAvailableRenderId();
		SolarCollectorRenderer solarCollecterRenderer = new SolarCollectorRenderer(solarCollecterRenderID);
		Blocks.solarCollectorTileBlock.setRenderType(solarCollecterRenderID);
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySolarCollector.class, solarCollecterRenderer);
    	RenderingRegistry.registerBlockHandler(solarCollecterRenderer);
    	
    	//LaserDetecter
    	int LaserDetectorRenderID = RenderingRegistry.getNextAvailableRenderId();
    	LaserDetectorRenderer LaserDetectorRenderer = new  LaserDetectorRenderer(LaserDetectorRenderID);
		Blocks.laserDetectorTileBlock.setRenderType(LaserDetectorRenderID);
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaserDetector.class, LaserDetectorRenderer);
    	RenderingRegistry.registerBlockHandler(LaserDetectorRenderer);
    	
    	int FiberCableRenderID = RenderingRegistry.getNextAvailableRenderId();
    	FiberCableRenderer FiberCableRenderer = new FiberCableRenderer(FiberCableRenderID);
		Blocks.fiberCableTileBlock.setRenderType(FiberCableRenderID);
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFiberCable.class, FiberCableRenderer);
    	RenderingRegistry.registerBlockHandler(FiberCableRenderer);
    	
    	int LuxBatteryRenderID = RenderingRegistry.getNextAvailableRenderId();
    	LuxCapacitorRenderer LuxCapacitorRenderer = new LuxCapacitorRenderer(LuxBatteryRenderID);
		Blocks.luxBatteryTileBlock.setRenderType(LuxBatteryRenderID);
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLuxCapacitor.class, LuxCapacitorRenderer);
    	RenderingRegistry.registerBlockHandler(LuxCapacitorRenderer);
    	
    	
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityBeamY.class, new BeamRendererY());
    	RenderingRegistry.registerEntityRenderingHandler(EntityBeamZ.class, new BeamRendererZ());
    	RenderingRegistry.registerEntityRenderingHandler(EntityBeamX.class, new BeamRendererX());
    	
    	MinecraftForgeClient.registerItemRenderer(Ids.laserWrench + 256, (IItemRenderer)new LaserWrenchRenderer());
    	
	}	
}
