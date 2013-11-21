package opticraft.blocks.tileentity;

import opticraft.entitys.EntityEnergyLaser;
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
import net.minecraft.world.World;

public class ItemLaserEntity extends TileEntity implements IInventory{
	
	private String orientation;
	private EntityEnergyLaser laser = null;
	private ItemStack[] inv;
	
	public ItemLaserEntity(){
		inv = new ItemStack[1];
	}
	
	public void setOrientation(String ori){
		System.out.println("SET ORIENTATION");
		orientation = ori;		
	}
	
	public String getOrientation(){
		return orientation;
	}
	
//	@Override
//	public Packet getDescriptionPacket(){
//		NBTTagCompound titleTag = new NBTTagCompound();
//		this.writeToNBT(titleTag);
//		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, titleTag);
//	}
//	
//	@Override
//	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt){
//		this.readFromNBT(pkt.data);
//	}
	
	@Override
	public void updateEntity(){
		if(orientation == null){
			int meta = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			
			if(meta == 1){
    			setOrientation("U");
    		} else if(meta == 2){  			
				setOrientation("N");
    		} else if(meta == 3){    			
				setOrientation("S");
			} else if(meta == 4){    			
				setOrientation("W");
			} else if(meta == 5){   			
				setOrientation("E");
			} else if(meta == 0){   			
				setOrientation("D");
			} else
				setOrientation("U");
		}
	}

	//inventory
	
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
            return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
//            return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
//            player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    	return false;
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
