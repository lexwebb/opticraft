package opticraft;

import opticraft.blocks.Blocks;
import opticraft.blocks.tileentity.BeamEntity;
import opticraft.blocks.tileentity.ItemLaserEntity;
import opticraft.blocks.tileentity.LaserDetectorEntity;
import opticraft.blocks.tileentity.SolarCollectorEntity;
import opticraft.client.gui.GuiHandler;
import opticraft.items.Items;
import opticraft.lib.ModInfo;
import opticraft.proxies.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod (channels = {ModInfo.CHANNEL}, clientSideRequired = true, serverSideRequired = true)
public class Opticraft {
	
	private GuiHandler guiHandler = new GuiHandler();
	
	@Instance
	public static Opticraft instance = new Opticraft();

	@SidedProxy( clientSide = ModInfo.PROXY_LOCATION + ".ClientProxy", serverSide = ModInfo.PROXY_LOCATION + ".CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {	
		proxy.initRenderers();
		proxy.initSounds();
		
		Items.init();
		Items.addNames();
		
		Blocks.init();
		Blocks.addNames();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
	
		proxy.registerRenderThings();
        GameRegistry.registerTileEntity(ItemLaserEntity.class, "tileEntityItemLaser");
        GameRegistry.registerTileEntity(SolarCollectorEntity.class, "tileEntitySolarCollector");
        GameRegistry.registerTileEntity(BeamEntity.class, "tileEntityBeam");
        GameRegistry.registerTileEntity(LaserDetectorEntity.class, "tileEntityLaserDetector");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
