package opticraft.blocks.containers;

import opticraft.entitys.TileEntityItemLaser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ItemLaserContainer extends Container{

	protected TileEntityItemLaser tile_entity;
	
	public ItemLaserContainer(TileEntityItemLaser tile_entity, InventoryPlayer inv){
		this.tile_entity = tile_entity;
		addSlotToContainer(new Slot(tile_entity, 0, 76, 27));
		bindPlayerInventory(inv);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tile_entity.isUseableByPlayer(player);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inv){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				addSlotToContainer(new Slot(inv, j + i * 9 + 9, 9 + j * 18, 64 + i * 16));
			}
		}

		for(int i = 0; i < 9; i++){
			addSlotToContainer(new Slot(inv, i, 6 + i * 16, 142));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2){
		
		return null;		
	}
}
