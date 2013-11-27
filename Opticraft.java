package opticraft;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import opticraft.blocks.Blocks;
import opticraft.client.gui.GuiHandler;
import opticraft.entitys.EntityBeamX;
import opticraft.entitys.EntityBeamY;
import opticraft.entitys.EntityBeamZ;
import opticraft.entitys.TileEntityFiberCable;
import opticraft.entitys.TileEntityItemLaser;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntitySolarCollector;
import opticraft.items.Items;
import opticraft.items.ItemLaserWrench;
import opticraft.lib.Ids;
import opticraft.lib.ModInfo;
import opticraft.proxies.CommonProxy;
import opticraft.render.LaserWrenchRenderer;
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
import cpw.mods.fml.common.registry.EntityRegistry;
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
		
		Blocks.init();
		Blocks.addNames();
		
		Items.init();
		Items.addNames();
		
		Items.initRecipes();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
	
		EntityRegistry.registerModEntity(EntityBeamY.class, "EntityBeamY", 20, this, 128, 1, false);
		EntityRegistry.registerModEntity(EntityBeamX.class, "EntityBeamX", 21, this, 128, 1, false);
		EntityRegistry.registerModEntity(EntityBeamZ.class, "EntityBeamZ", 22, this, 128, 1, false);
		proxy.registerRenderThings();
		
        GameRegistry.registerTileEntity(TileEntityItemLaser.class, "tileEntityItemLaser");
        GameRegistry.registerTileEntity(TileEntitySolarCollector.class, "tileEntitySolarCollector");
        GameRegistry.registerTileEntity(TileEntityLaserDetector.class, "tileEntityLaserDetector");
        GameRegistry.registerTileEntity(TileEntityFiberCable.class, "tileEntityFiberCable");
              
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
