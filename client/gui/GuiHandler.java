package opticraft.client.gui;

import opticraft.blocks.containers.LaserContainer;
import opticraft.blocks.containers.LaserDetectorContainer;
import opticraft.blocks.containers.LuxCapacitorContainer;
import opticraft.entitys.TileEntityLaser;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntityLuxCapacitor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityLaser){
			return new LaserContainer((TileEntityLaser)tileEntity, player.inventory); // your Containers go here			
		} else if(tileEntity instanceof TileEntityLaserDetector){
			return new LaserDetectorContainer((TileEntityLaserDetector)tileEntity, player.inventory);
		} else if(tileEntity instanceof TileEntityLuxCapacitor){
			return new LuxCapacitorContainer((TileEntityLuxCapacitor)tileEntity, player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		if(tile_entity instanceof TileEntityLaser){
			return new GuiLaser(player.inventory, (TileEntityLaser)tile_entity);
		} else if(tile_entity instanceof TileEntityLaserDetector){
			return new GuiLaserDetector(player.inventory, (TileEntityLaserDetector)tile_entity);
		} else if(tile_entity instanceof TileEntityLuxCapacitor){
			return new GuiLuxCapacitor(player.inventory, (TileEntityLuxCapacitor)tile_entity);
		} 
		return null;
		
	}

}
