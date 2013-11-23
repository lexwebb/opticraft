package opticraft.entitys;

import opticraft.lib.DirectionalTileEntity;
import opticraft.lib.Position;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityItemLaser extends DirectionalTileEntity implements IInventory{
	
	private ItemStack[] inv;
	Position linkedDetector;
	
	public TileEntityItemLaser(){
		inv = new ItemStack[1];
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		//check for Detector
		if(this.worldObj.getWorldTime() % 10 == 0){
			TileEntity tile_entity;
			linkedDetector = null;
			
			if(this.getOrientation() == "U"){
				for(int i = 1; i < 64; i++){
					tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord + i, zCoord);
					if(tile_entity instanceof TileEntityLaserDetector){
						DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
						if(ent.getOrientation() == "D"){
							linkedDetector = new Position(ent);
						}
					}
					if(linkedDetector != null)
						break;
				}
			} else if(this.getOrientation() == "D"){
				for(int i = 1; i < 64; i++){
					tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord - i, zCoord);
					if(tile_entity instanceof TileEntityLaserDetector){
						DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
						if(ent.getOrientation() == "U"){
							linkedDetector = new Position(ent);
						}
					}
					if(linkedDetector != null)
						break;
				}	
			} else if(this.getOrientation() == "N"){
				for(int i = 1; i < 64; i++){
					tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - i);
					if(tile_entity instanceof TileEntityLaserDetector){
						DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
						if(ent.getOrientation() == "S"){
							linkedDetector = new Position(ent);
						}
					}
					if(linkedDetector != null)
						break;
				}	
			} else if(this.getOrientation() == "S"){
				for(int i = 1; i < 64; i++){
					tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + i);
					if(tile_entity instanceof TileEntityLaserDetector){
						DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
						if(ent.getOrientation() == "N"){
							linkedDetector = new Position(ent);
						}
					}
					if(linkedDetector != null)
						break;
				}	
			} else if(this.getOrientation() == "W"){
				for(int i = 1; i < 64; i++){
					tile_entity = worldObj.getBlockTileEntity(xCoord - i, yCoord, zCoord);
					if(tile_entity instanceof TileEntityLaserDetector){
						DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
						if(ent.getOrientation() == "E"){
							linkedDetector = new Position(ent);
						}
					}
					if(linkedDetector != null)
						break;
				}	
			} else if(this.getOrientation() == "E"){
				for(int i = 1; i < 64; i++){
					tile_entity = worldObj.getBlockTileEntity(xCoord + i, yCoord, zCoord);
					if(tile_entity instanceof TileEntityLaserDetector){
						DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
						if(ent.getOrientation() == "W"){
							linkedDetector = new Position(ent);
						}
					}
					if(linkedDetector != null)
						break;
				}	
			}
			
			if(linkedDetector != null){
				TileEntityLaserDetector ent = (TileEntityLaserDetector) worldObj.getBlockTileEntity(
						(int) Math.floor(linkedDetector.x), (int) Math.floor(linkedDetector.y), (int) Math.floor(linkedDetector.z));
				if(ent.getStackInSlot(0) == null){
					ent.setInventorySlotContents(0, this.getStackInSlot(0));
					this.setInventorySlotContents(0, null);
//					
					if(this.getOrientation() == "U"){
						for(int i = this.yCoord; i <= linkedDetector.y - 1; i++){
							System.out.println(i);
							if (!worldObj.isRemote){
								worldObj.spawnEntityInWorld(new EntityBeam(worldObj, "UD"));
							}
							
						}
					} else if(this.getOrientation() == "D"){
						for(int i = this.yCoord; i <= this.yCoord + 1; i++){

						}	
					} else if(this.getOrientation() == "N"){
						for(int i = this.yCoord; i <= this.yCoord + 1; i++){

						}	
					} else if(this.getOrientation() == "S"){
						for(int i = this.yCoord; i <= this.yCoord + 1; i++){

						}	
					} else if(this.getOrientation() == "W"){
						for(int i = this.yCoord; i <= this.yCoord + 1; i++){

						}	
					} else if(this.getOrientation() == "E"){
						for(int i = this.yCoord; i <= this.yCoord + 1; i++){
							
						}

					}
				}
			}
		}
	}

    @Override
    public int getSizeInventory() {
            return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
            return inv[slot];
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
            inv[slot] = stack;
            if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                    stack.stackSize = getInventoryStackLimit();
            }               
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    if (stack.stackSize <= amt) {
                            setInventorySlotContents(slot, null);
                    } else {
                            stack = stack.splitStack(amt);
                            if (stack.stackSize == 0) {
                                    setInventorySlotContents(slot, null);
                            }
                    }
            }
            return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    setInventorySlotContents(slot, null);
            }
            return stack;
    }
    
    @Override
    public int getInventoryStackLimit() {
            return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
            return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
            player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    	//return false;
    }

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {}
    
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            
            NBTTagList tagList = tagCompound.getTagList("Inventory");
            for (int i = 0; i < tagList.tagCount(); i++) {
                    NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                    byte slot = tag.getByte("Slot");
                    if (slot >= 0 && slot < inv.length) {
                            inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                    }
            }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
                            
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < inv.length; i++) {
                    ItemStack stack = inv[i];
                    if (stack != null) {
                            NBTTagCompound tag = new NBTTagCompound();
                            tag.setByte("Slot", (byte) i);
                            stack.writeToNBT(tag);
                            itemList.appendTag(tag);
                    }
            }
            tagCompound.setTag("Inventory", itemList);
    }

    @Override
    public String getInvName() {
            return "itemLaserEntity";
    }

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
