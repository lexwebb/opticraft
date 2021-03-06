package opticraft.entitys;

import buildcraft.api.power.IPowerEmitter;
import opticraft.energy.LuxContainerTileEntity;
import opticraft.lib.DirectionalTileEntity;
import opticraft.lib.Position;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeDirection;

@Interface(iface = "IPowerEmitter", modid = "BuildCraftAPI|power")
public class TileEntityLaserDetector extends LuxContainerTileEntity implements IInventory, IPowerEmitter{	
	
private ItemStack[] inv;
	
	public TileEntityLaserDetector(){
		inv = new ItemStack[1];
		this.maxCharge = 128;
		this.maxOutput = 2;
		this.producer = true;
	}
		
    @Override
    public void updateEntity(){
    	super.updateEntity();
    	if(this.worldObj.getWorldTime() % 10 == 0){
	    	if(getStackInSlot(0) != null){	
				TileEntity ent = null;
				int sideDirection = 0;
				
				if(this.getOrientation() == ForgeDirection.UP){
					ent = worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord);					
				} else if(this.getOrientation() == ForgeDirection.DOWN){
					ent = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
				} else if(this.getOrientation() == ForgeDirection.NORTH){
					ent = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1);
				} else if(this.getOrientation() == ForgeDirection.SOUTH){
					ent = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1);
				} else if(this.getOrientation() == ForgeDirection.WEST){
					ent = worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
				} else if(this.getOrientation() == ForgeDirection.EAST){
					ent = worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord);
				}
				
				if(ent instanceof IInventory)
					PushItemsOut((IInventory)ent);
			}
    	}
    }
    
    public int pushPowerOut(int lux){
    	TileEntity ent = null;
    	LuxContainerTileEntity entity = null;
		
		if(this.getOrientation() == ForgeDirection.UP){
			ent = worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord);					
		} else if(this.getOrientation() == ForgeDirection.DOWN){
			ent = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
		} else if(this.getOrientation() == ForgeDirection.NORTH){
			ent = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1);
		} else if(this.getOrientation() == ForgeDirection.SOUTH){
			ent = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1);
		} else if(this.getOrientation() == ForgeDirection.WEST){
			ent = worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
		} else if(this.getOrientation() == ForgeDirection.EAST){
			ent = worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord);
		}
		
		if(ent instanceof LuxContainerTileEntity)
			entity = (LuxContainerTileEntity) ent;
			
		if(entity.consumer){
			if(entity.lux.increaseBy(lux, entity.maxCharge))
				return lux;
			else{	
				for(int i = lux; i >= 0;i--){
					if(entity.lux.increaseBy(i, entity.maxCharge))
						return i;
				}
				return 0;
			}			
		} else
			return 0;
    }
    
    public void PushItemsOut(IInventory inv) {
		boolean foundItem = false;
		boolean hasInserted = false;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			
			if(inv.getStackInSlot(i) != null){
				if(areItemStacksEqualItem(inv.getStackInSlot(i), getStackInSlot(0))){
					if(inv.getStackInSlot(i).stackSize < 64){
						ItemStack tempStack = inv.getStackInSlot(i).copy();
						tempStack.stackSize = tempStack.stackSize + 1;
						inv.setInventorySlotContents(i, tempStack);
						hasInserted = true;
					}
				} 
			}
			else {
				
				inv.setInventorySlotContents(i, getStackInSlot(0));
				hasInserted = true;
			}
			
			if(hasInserted){
				if(getStackInSlot(0).stackSize == 1){
					setInventorySlotContents(0, null);
				} else {					
					decrStackSize(i, 1);
				}

				inv.onInventoryChanged();
				this.onInventoryChanged();
				
				foundItem = true;		
			}
			
			if(foundItem)
				break;
		}
		
	}
    
    private static boolean areItemStacksEqualItem(ItemStack par0ItemStack, ItemStack par1ItemStack)
    {
        return par0ItemStack.itemID != par1ItemStack.itemID ? false : (par0ItemStack.getItemDamage() != par1ItemStack.getItemDamage() ? false : (par0ItemStack.stackSize > par0ItemStack.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(par0ItemStack, par1ItemStack)));
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
            return "laserDetectorEntity";
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


	@Method(modid = "BuildCraftAPI|power")
	@Override
	public boolean canEmitPowerFrom(ForgeDirection side) {	
		if(side == getOrientation().getOpposite())
			return true;
		else
			return false;
	}
}
