package opticraft.proxies;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import opticraft.Opticraft;
import opticraft.entitys.EntityBeam;
import opticraft.entitys.TileEntityItemLaser;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntitySolarCollector;
import opticraft.items.ItemLaserWrench;
import opticraft.lib.Ids;
import opticraft.render.BeamRenderer;
import opticraft.render.ItemLaserRenderer;
import opticraft.render.LaserDetectorRenderer;
import opticraft.render.LaserWrenchRenderer;
import opticraft.render.SolarCollectorRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void initRenderers() {
		EntityRegistry.registerModEntity(EntityBeam.class, "Entity Beam", 20, Opticraft.instance, 64, 1, false);
	}

	@Override
	public void initSounds() {

	}
	
	public void registerRenderThings() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemLaser.class, new ItemLaserRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySolarCollector.class, new SolarCollectorRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaserDetector.class, new LaserDetectorRenderer());
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityBeam.class, new BeamRenderer());
    	
    	MinecraftForgeClient.registerItemRenderer(Ids.laserWrench + 256, (IItemRenderer)new LaserWrenchRenderer());
	}	
}
