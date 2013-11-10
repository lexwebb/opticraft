package opticraft.proxies;

import opticraft.blocks.tileentity.ItemLaserEntity;
import opticraft.blocks.tileentity.ItemLaserRenderer;
import opticraft.blocks.tileentity.SolarCollectorEntity;
import opticraft.blocks.tileentity.SolarCollectorRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void initRenderers() {

	}

	@Override
	public void initSounds() {

	}
	
	public void registerRenderThings() {
    	ClientRegistry.bindTileEntitySpecialRenderer(ItemLaserEntity.class, new ItemLaserRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(SolarCollectorEntity.class, new SolarCollectorRenderer());
	}
	
}
