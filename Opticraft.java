package opticraft;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import opticraft.blocks.Blocks;
import opticraft.client.gui.GuiHandler;
import opticraft.entitys.TileEntityBeam;
import opticraft.entitys.TileEntityItemLaser;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntitySolarCollector;
import opticraft.items.Items;
import opticraft.items.LaserWrench;
import opticraft.items.LaserWrenchRenderer;
import opticraft.lib.Ids;
import opticraft.lib.ModInfo;
import opticraft.proxies.CommonProxy;
import cpw.mods.fml.client.registry.ClientRegistry;
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
		
        GameRegistry.registerTileEntity(TileEntityItemLaser.class, "tileEntityItemLaser");
        GameRegistry.registerTileEntity(TileEntitySolarCollector.class, "tileEntitySolarCollector");
        GameRegistry.registerTileEntity(TileEntityBeam.class, "tileEntityBeam");
        GameRegistry.registerTileEntity(TileEntityLaserDetector.class, "tileEntityLaserDetector");
        
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
