package opticraft.proxies;

import opticraft.entitys.TileEntityBeam;
import opticraft.entitys.TileEntityItemLaser;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntitySolarCollector;
import opticraft.render.BeamRenderer;
import opticraft.render.ItemLaserRenderer;
import opticraft.render.LaserDetectorRenderer;
import opticraft.render.SolarCollectorRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void initRenderers() {

	}

	@Override
	public void initSounds() {

	}
	
	public void registerRenderThings() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemLaser.class, new ItemLaserRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySolarCollector.class, new SolarCollectorRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBeam.class, new BeamRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaserDetector.class, new LaserDetectorRenderer());
	}
	
}
