package opticraft.proxies;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import opticraft.entitys.TileEntityBeam;
import opticraft.entitys.TileEntityItemLaser;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntitySolarCollector;
import opticraft.items.LaserWrench;
import opticraft.items.LaserWrenchRenderer;
import opticraft.lib.Ids;
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
    	
    	MinecraftForgeClient.registerItemRenderer(Ids.laserWrench + 256, (IItemRenderer)new LaserWrenchRenderer());
    	System.out.println(String.valueOf(Ids.laserWrench));
	}
	
}
