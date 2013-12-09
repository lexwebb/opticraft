package opticraft.entitys;

import opticraft.energy.LuxContainerTileEntity;
import opticraft.items.Items;
import opticraft.lib.DirectionalTileEntity;
import opticraft.lib.Position;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
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
import net.minecraft.tileentity.Hopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityLaser extends LuxContainerTileEntity implements ISidedInventory{
	
	private ItemStack[] inv;
	Position linkedDetector;
	
	public TileEntityLaser(){
		inv = new ItemStack[2];
		this.maxCharge = 128;
		this.consumer = true;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		
		
		if(inv[1] != null){
			if(inv[1].itemID == Items.basicMatterCrystal.itemID){
				suckItemsIn();
				findDetector();
				itemLaser(20);
			}
			if(inv[1].itemID == Items.matterCrystal.itemID){
				suckItemsIn();
				findDetector();
				itemLaser(10);
			}
			if(inv[1].itemID == Items.advancedMatterCrystal.itemID){
				suckItemsIn();
				findDetector();
				itemLaser(2);
			}
		}
	}
	
	public void findDetector(){		
		TileEntity tile_entity;
		linkedDetector = null;
		boolean solidInWay = false;
		
		if(this.getOrientation() == "U"){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord + i, zCoord).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord + i, zCoord);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == "D"){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}
		} else if(this.getOrientation() == "D"){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord - i, zCoord).isOpaque())
					solidInWay = true;
				
				System.out.println(solidInWay);
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord - i, zCoord);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == "U"){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getOrientation() == "N"){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord, zCoord - i).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - i);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == "S"){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getOrientation() == "S"){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord, zCoord + i).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + i);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == "N"){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getOrientation() == "W"){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord - i, yCoord, zCoord).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord - i, yCoord, zCoord);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == "E"){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getOrientation() == "E"){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord + i, yCoord, zCoord).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord + i, yCoord, zCoord);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == "W"){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		}
	}
	
	public void itemLaser(int frequency){
		//check for Detector
		if(this.worldObj.getWorldTime() % frequency == 0){
			if(linkedDetector != null && this.lux.get() > 0){
				TileEntityLaserDetector ent = (TileEntityLaserDetector) worldObj.getBlockTileEntity(
						(int) Math.floor(linkedDetector.x), (int) Math.floor(linkedDetector.y), (int) Math.floor(linkedDetector.z));
				if(ent.getStackInSlot(0) == null && getStackInSlot(0) != null){
					ent.setInventorySlotContents(0, this.getStackInSlot(0));
					this.setInventorySlotContents(0, null);					
					if(this.getOrientation() == "U"){
						for(int i = this.yCoord + 1; i <= linkedDetector.y; i++){
							if (!worldObj.isRemote){
								EntityBeamY entity = new EntityBeamY(worldObj);
								entity.setPosition(xCoord, i, zCoord);							
								worldObj.spawnEntityInWorld(entity);
							}		
						}
					} else if(this.getOrientation() == "D"){
						for(int i = this.yCoord - 1; i >= linkedDetector.y; i--){
							if (!worldObj.isRemote){
								EntityBeamY entity = new EntityBeamY(worldObj);
								entity.setPosition(xCoord, i, zCoord);							
								worldObj.spawnEntityInWorld(entity);
							}		
						}	
					} else if(this.getOrientation() == "N"){
						for(int i = this.zCoord - 1; i >= linkedDetector.z; i--){
							if (!worldObj.isRemote){
								EntityBeamZ entity = new EntityBeamZ(worldObj);
								entity.setPosition(xCoord, yCoord, i);							
								worldObj.spawnEntityInWorld(entity);
							}		
						}	
					} else if(this.getOrientation() == "S"){
						for(int i = this.zCoord + 1; i <= linkedDetector.z; i++){
							if (!worldObj.isRemote){
								EntityBeamZ entity = new EntityBeamZ(worldObj);
								entity.setPosition(xCoord, yCoord, i);							
								worldObj.spawnEntityInWorld(entity);
							}		
						}	
					} else if(this.getOrientation() == "E"){
						for(int i = this.xCoord + 1; i <= linkedDetector.x; i++){
							if (!worldObj.isRemote){
								EntityBeamX entity = new EntityBeamX(worldObj);
								entity.setPosition(i, yCoord, zCoord);							
								worldObj.spawnEntityInWorld(entity);
							}		
						}	
					} else if(this.getOrientation() == "W"){
						for(int i = this.xCoord - 1; i >= linkedDetector.x; i--){
							if (!worldObj.isRemote){
								EntityBeamX entity = new EntityBeamX(worldObj);
								entity.setPosition(i, yCoord, zCoord);							
								worldObj.spawnEntityInWorld(entity);
							}		
						}
					}
					
					this.lux.decreaseBy(1, 0);
					//System.out.println(this.lux.get());
					worldObj.playSoundEffect((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D, "optcrft:buzz",  0.1F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
				}
			}
		}
	}

	public void suckItemsIn() {
		IInventory inv;
		if(getStackInSlot(0) == null){	
			TileEntity ent = null;
			if(this.getOrientation() == "U"){
				ent = worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord);					
			} else if(this.getOrientation() == "D"){
				ent = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
			} else if(this.getOrientation() == "N"){
				ent = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1);
			} else if(this.getOrientation() == "S"){
				ent = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1);
			} else if(this.getOrientation() == "W"){
				ent = worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
			} else if(this.getOrientation() == "E"){
				ent = worldObj.getBlockTileEntity(xCoord - 1, yCoord , zCoord);
			}
			
			if(ent instanceof ISidedInventory){
				int slots[];
				int side;
				ISidedInventory iInv = (ISidedInventory)ent;
				
				if(this.getOrientation() == "U"){
					slots = iInv.getAccessibleSlotsFromSide(1);	
					side = 1;
				} else if(this.getOrientation() == "D"){
					slots = iInv.getAccessibleSlotsFromSide(0);	
					side = 0;
				} else if(this.getOrientation() == "N"){
					slots = iInv.getAccessibleSlotsFromSide(2);	
					side = 2;
				} else if(this.getOrientation() == "S"){
					slots = iInv.getAccessibleSlotsFromSide(3);	
					side = 3;
				} else if(this.getOrientation() == "W"){
					slots = iInv.getAccessibleSlotsFromSide(4);	
					side = 4;
				} else if(this.getOrientation() == "E"){
					slots = iInv.getAccessibleSlotsFromSide(5);	
					side = 5;
				} else
					slots = null;
					side = 0;
				
				for(int slot : slots){
					if(iInv.getStackInSlot(slot) != null && canExtractItemFromInventory((IInventory)ent, iInv.getStackInSlot(slot), slot, side)){
						
						ItemStack tempStack = iInv.getStackInSlot(slot).copy();
						tempStack.stackSize = 1;
						setInventorySlotContents(0, tempStack);
						
						if(iInv.getStackInSlot(slot).stackSize == 1){
							iInv.setInventorySlotContents(slot, null);
						} else {					
							iInv.decrStackSize(slot, 1);
						}
	
						iInv.onInventoryChanged();
						this.onInventoryChanged();			
					}
				}
				
			} else if(ent instanceof IInventory){
				inv = (IInventory)ent;
			
				boolean foundItem = false;
				for(int i = 0; i < inv.getSizeInventory(); i++){
					if(inv.getStackInSlot(i) != null){
						
						ItemStack tempStack = inv.getStackInSlot(i).copy();
						tempStack.stackSize = 1;
						setInventorySlotContents(0, tempStack);
						
						if(inv.getStackInSlot(i).stackSize == 1){
							inv.setInventorySlotContents(i, null);
						} else {					
							inv.decrStackSize(i, 1);
						}
	
						inv.onInventoryChanged();
						this.onInventoryChanged();
						
						foundItem = true;				
					}
					if(foundItem)
						break;
				}
			}
		}	
	}
	
	private static boolean canExtractItemFromInventory(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3)
    {
        return !(par0IInventory instanceof ISidedInventory) || ((ISidedInventory)par0IInventory).canExtractItem(par2, par1ItemStack, par3);
    }

    @Override
    public int getSizeInventory() {
            return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
    	if(slot > inv.length)
            return inv[0];
    	else
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
		if(i == 0){
			return true;
		} else
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		if(this.getOrientation() == "U"){
			return new int[] {0};
		} else if(this.getOrientation() == "D"){
			return new int[] {0};
		} else if(this.getOrientation() == "N"){
			return new int[] {0};
		} else if(this.getOrientation() == "S"){
			return new int[] {0};
		} else if(this.getOrientation() == "W"){
			return new int[] {0};
		} else if(this.getOrientation() == "E"){
			return new int[] {0};
		} 
		return new int[] {0};
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
