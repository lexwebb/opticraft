package opticraft.client.gui;

import opticraft.blocks.containers.ItemLaserContainer;
import opticraft.entitys.TileEntityItemLaser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		if(tile_entity instanceof TileEntityItemLaser){
			return new GuiItemLaser(player.inventory, (TileEntityItemLaser)tile_entity);
			//return new ItemLaserContainer((ItemLaserEntity) tile_entity, player.inventory);
		}		
		return null;
		
	}

}
