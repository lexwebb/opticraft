package opticraft.proxies;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import opticraft.Opticraft;
import opticraft.entitys.EntityBeamX;
import opticraft.entitys.EntityBeamY;
import opticraft.entitys.EntityBeamZ;
import opticraft.entitys.TileEntityFiberCable;
import opticraft.entitys.TileEntityItemLaser;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntitySolarCollector;
import opticraft.items.ItemLaserWrench;
import opticraft.lib.Ids;
import opticraft.render.BeamRendererX;
import opticraft.render.BeamRendererY;
import opticraft.render.BeamRendererZ;
import opticraft.render.FiberCableRenderer;
import opticraft.render.ItemLaserRenderer;
import opticraft.render.LaserDetectorRenderer;
import opticraft.render.LaserWrenchRenderer;
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
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemLaser.class, new ItemLaserRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySolarCollector.class, new SolarCollectorRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaserDetector.class, new LaserDetectorRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFiberCable.class, new FiberCableRenderer());
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityBeamY.class, new BeamRendererY());
    	RenderingRegistry.registerEntityRenderingHandler(EntityBeamZ.class, new BeamRendererZ());
    	RenderingRegistry.registerEntityRenderingHandler(EntityBeamX.class, new BeamRendererX());
    	
    	MinecraftForgeClient.registerItemRenderer(Ids.laserWrench + 256, (IItemRenderer)new LaserWrenchRenderer());
	}	
}
