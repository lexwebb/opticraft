package opticraft.entitys;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import opticraft.energy.LuxContainerTileEntity;
import opticraft.items.Items;
import opticraft.lib.DirectionalTileEntity;
import opticraft.lib.Ids;
import opticraft.lib.ModInfo;
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
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.Hopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityLaser extends LuxContainerTileEntity implements ISidedInventory{
	
	private ItemStack[] inv;
	Position linkedDetector;
	public List<Position> laserToList;
	public List<ForgeDirection> laserToDirection;
	public boolean shouldFire;
	public long laserFireTime;
	private AxisAlignedBB boundingRenderBox;
	
	public TileEntityLaser(){
		inv = new ItemStack[2];
		this.maxCharge = 128;
		this.consumer = true;
		laserToList = new ArrayList<Position>();
		laserToDirection = new ArrayList<ForgeDirection>();
		boundingRenderBox = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1); /////noooooo
		updateRenderBox();
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		if (worldObj.isRemote){
			if(Minecraft.getMinecraft().theWorld.getTotalWorldTime() >= laserFireTime + 2)
				laserToList.clear();
		}	
		
		if(inv[1] != null){
			
			shouldFire = false;
			
//			System.out.println("invid: " + inv[1].itemID + "itemID: " + Ids.energyCrystal);
			
			switch (inv[1].itemID) {
			case Ids.basicMatterCrystal + 256:
				suckItemsIn();
				itemLaser(20);
				break;
			case Ids.matterCrystal + 256:
				suckItemsIn();
				itemLaser(10);
				break;
			case Ids.advancedMatterCrystal + 256:
				suckItemsIn();
				itemLaser(2);
				break;
			case Ids.energyCrystal + 256:
				energyLaser(10);
				break;
			}
		}
		
		updateRenderBox();
	}
	
	//legacy
	public void findDetector(){		
		TileEntity tile_entity;
		linkedDetector = null;
		boolean solidInWay = false;
		
		if(this.getOrientation() == ForgeDirection.UP){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord + i, zCoord).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord + i, zCoord);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.DOWN){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}
		} else if(this.getOrientation() == ForgeDirection.DOWN){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord - i, zCoord).isOpaque())
					solidInWay = true;
				
				System.out.println(solidInWay);
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord - i, zCoord);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.UP){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getOrientation() == ForgeDirection.NORTH){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord, zCoord - i).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - i);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.SOUTH){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getOrientation() == ForgeDirection.SOUTH){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord, zCoord + i).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + i);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.NORTH){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getOrientation() == ForgeDirection.WEST){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord - i, yCoord, zCoord).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord - i, yCoord, zCoord);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.EAST){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getOrientation() == ForgeDirection.EAST){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord + i, yCoord, zCoord).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord + i, yCoord, zCoord);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.WEST){
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
	//------
	
	public void findDetectorRecursive(ForgeDirection direction, int x, int y, int z){
		TileEntity tile_entity;
		linkedDetector = null;
		boolean solidInWay = false;
		
		laserToList.add(new Position(x, y, z));
		laserToDirection.add(direction);
		
		if(direction == ForgeDirection.UP){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(x, y + i, z).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(x, y + i, z);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.DOWN){
						linkedDetector = new Position(ent);
					}
				} else if(tile_entity instanceof TileEntityMirror){
					TileEntityMirror ent = (TileEntityMirror) tile_entity;
					if(ent.direction != null && ent.direction.getOpposite() == direction){
						findDetectorRecursive(ent.orientation, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					} else if(ent.orientation != null && ent.orientation.getOpposite() == direction){
						findDetectorRecursive(ent.direction, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}
		} else if(direction == ForgeDirection.DOWN){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(x, y - i, z).isOpaque())
					solidInWay = true;
				
				System.out.println(solidInWay);
				
				tile_entity = worldObj.getBlockTileEntity(x, y - i, z);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.UP){
						linkedDetector = new Position(ent);
					}
				} else if(tile_entity instanceof TileEntityMirror){
					TileEntityMirror ent = (TileEntityMirror) tile_entity;
					if(ent.direction != null && ent.direction.getOpposite() == direction){
						findDetectorRecursive(ent.orientation, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					} else if(ent.orientation != null && ent.orientation.getOpposite() == direction){
						findDetectorRecursive(ent.direction, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(direction == ForgeDirection.NORTH){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(x, y, z - i).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(x, y, z - i);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.SOUTH){
						linkedDetector = new Position(ent);
					}
				} else if(tile_entity instanceof TileEntityMirror){
					TileEntityMirror ent = (TileEntityMirror) tile_entity;
					if(ent.direction != null && ent.direction.getOpposite() == direction){
						findDetectorRecursive(ent.orientation, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					} else if(ent.orientation != null && ent.orientation.getOpposite() == direction){
						findDetectorRecursive(ent.direction, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(direction == ForgeDirection.SOUTH){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(x, y, z + i).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(x, y, z + i);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.NORTH){
						linkedDetector = new Position(ent);
					}
				} else if(tile_entity instanceof TileEntityMirror){
					TileEntityMirror ent = (TileEntityMirror) tile_entity;
					if(ent.direction != null && ent.direction.getOpposite() == direction){
						findDetectorRecursive(ent.orientation, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					} else if(ent.orientation != null && ent.orientation.getOpposite() == direction){
						findDetectorRecursive(ent.direction, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(direction == ForgeDirection.WEST){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(x - i, y, z).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(x - i, y, z);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.EAST){
						linkedDetector = new Position(ent);
					}
				} else if(tile_entity instanceof TileEntityMirror){
					TileEntityMirror ent = (TileEntityMirror) tile_entity;
					if(ent.direction != null && ent.direction.getOpposite() == direction){
						findDetectorRecursive(ent.orientation, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					} else if(ent.orientation != null && ent.orientation.getOpposite() == direction){
						findDetectorRecursive(ent.direction, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(direction == ForgeDirection.EAST){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(x + i, y, z).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(x + i, y, z);
				if(tile_entity instanceof TileEntityLaserDetector){
					DirectionalTileEntity ent = (DirectionalTileEntity) tile_entity;
					if(ent.getOrientation() == ForgeDirection.WEST){
						linkedDetector = new Position(ent);
					}
				} else if(tile_entity instanceof TileEntityMirror){
					TileEntityMirror ent = (TileEntityMirror) tile_entity;
					if(ent.direction != null && ent.direction.getOpposite() == direction){
						findDetectorRecursive(ent.orientation, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
					} else if(ent.orientation != null && ent.orientation.getOpposite() == direction){
						findDetectorRecursive(ent.direction, ent.xCoord, ent.yCoord, ent.zCoord);
						break;
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
			
			laserToList.clear();
			findDetectorRecursive(this.getOrientation(), xCoord, yCoord, zCoord);
			if(linkedDetector != null)
				laserToList.add(linkedDetector);
			laserToDirection.add(ForgeDirection.NORTH);
			
			if(linkedDetector != null && this.lux.get() > 0){
				TileEntityLaserDetector ent = (TileEntityLaserDetector) worldObj.getBlockTileEntity(
						(int) Math.floor(linkedDetector.x), (int) Math.floor(linkedDetector.y), (int) Math.floor(linkedDetector.z));
				if(ent.getStackInSlot(0) == null && getStackInSlot(0) != null){
					ent.setInventorySlotContents(0, this.getStackInSlot(0));
					this.setInventorySlotContents(0, null);					
					
					if (!worldObj.isRemote)
						laserBeam();

					shouldFire = true;
					this.lux.decreaseBy(1, 0);
				}
			}
		}
	}
	
	public void energyLaser(int transferable){
		if(this.worldObj.getWorldTime() % 10 == 0){
			laserToList.clear();
			findDetectorRecursive(this.getOrientation(), xCoord, yCoord, zCoord);
			if(linkedDetector != null)
				laserToList.add(linkedDetector);
			laserToDirection.add(ForgeDirection.NORTH);
			
			System.out.println(lux.get());
			
			if(linkedDetector != null && this.lux.get() > 1){
				TileEntityLaserDetector ent = (TileEntityLaserDetector) worldObj.getBlockTileEntity(
						(int) Math.floor(linkedDetector.x), (int) Math.floor(linkedDetector.y), (int) Math.floor(linkedDetector.z));
				
				if (!worldObj.isRemote)
					laserBeam();
				
				shouldFire = true;
				
				if(lux.get() <= transferable){
					lux.decreaseBy(ent.pushPowerOut((int) lux.get() - 1), 0);
				} else
					lux.decreaseBy(ent.pushPowerOut(transferable), 0);
				lux.decreaseBy(1, 0);
			}
		}
	}
	
	public void laserBeam(){
		if(laserToList != null && laserToList.size() > 1){
			for(int j = 0; j < laserToList.size() - 1; j++){
				ForgeDirection or = laserToDirection.get(j);
				sendUpdatePacket();	
				worldObj.playSoundEffect((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D, "optcrft:buzz",  0.1F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
                laserToList.clear();
			}
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox(){
		
		return boundingRenderBox;		
	}
	
	public void updateRenderBox(){
		if(laserToList == null || laserToList.size() == 0){
			boundingRenderBox.setBounds(xCoord, yCoord, zCoord, xCoord + 1,  yCoord + 1, zCoord + 1);
		} else {
			int minx, miny, minz, maxx, maxy, maxz;
			minx = xCoord;
			miny = yCoord;
			minz = zCoord;
			maxx = xCoord+1;
			maxy = yCoord+1;
			maxz = zCoord+1;
			
			for(int i = 0; i < laserToList.size();i++){
				minx = Math.min(minx, (int) laserToList.get(i).x);
				miny = Math.min(miny, (int) laserToList.get(i).y);
				minz = Math.min(minz, (int) laserToList.get(i).z);
				
				maxx = Math.max(maxx, (int) laserToList.get(i).x);
				maxy = Math.max(maxy, (int) laserToList.get(i).y);
				maxz = Math.max(maxz, (int) laserToList.get(i).z);
			}
			
			minx--; miny--; minz--;
			maxx++; maxy++; maxz++;
			
			boundingRenderBox.setBounds(minx, miny, minz, maxx, maxy, maxz);
		}
	}

	void sendUpdatePacket(){
    	ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);      
        
        try {
        	outputStream.writeUTF("LaserRenderSync");
        	outputStream.writeInt(xCoord);
        	outputStream.writeInt(yCoord);
        	outputStream.writeInt(zCoord);
        	
        	//pass size of array
        	outputStream.writeInt(laserToList.size());
        	
        	//array send loop
        	for(int i = 0; i < laserToList.size(); i++){
        		outputStream.writeDouble(laserToList.get(i).x);
                outputStream.writeDouble(laserToList.get(i).y);
                outputStream.writeDouble(laserToList.get(i).z);
        	}      	    
        } catch (Exception ex) {
                ex.printStackTrace();
        }
        
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = (ModInfo.CHANNEL + "GuiSync");
		packet.data = bos.toByteArray();
		packet.length = bos.size();

		PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 50, 0, packet);
    }

	public void suckItemsIn() {
		IInventory inv;
		if(getStackInSlot(0) == null){	
			TileEntity ent = null;
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
				ent = worldObj.getBlockTileEntity(xCoord - 1, yCoord , zCoord);
			}
			
			if(ent instanceof ISidedInventory){
				int slots[];
				int side;
				ISidedInventory iInv = (ISidedInventory)ent;
				
				if(this.getOrientation() == ForgeDirection.UP){
					slots = iInv.getAccessibleSlotsFromSide(1);	
					side = 1;
				} else if(this.getOrientation() == ForgeDirection.DOWN){
					slots = iInv.getAccessibleSlotsFromSide(0);	
					side = 0;
				} else if(this.getOrientation() == ForgeDirection.NORTH){
					slots = iInv.getAccessibleSlotsFromSide(2);	
					side = 2;
				} else if(this.getOrientation() == ForgeDirection.SOUTH){
					slots = iInv.getAccessibleSlotsFromSide(3);	
					side = 3;
				} else if(this.getOrientation() == ForgeDirection.WEST){
					slots = iInv.getAccessibleSlotsFromSide(4);	
					side = 4;
				} else if(this.getOrientation() == ForgeDirection.EAST){
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
		if(this.getOrientation() == ForgeDirection.UP){
			return new int[] {0};
		} else if(this.getOrientation() == ForgeDirection.DOWN){
			return new int[] {0};
		} else if(this.getOrientation() == ForgeDirection.NORTH){
			return new int[] {0};
		} else if(this.getOrientation() == ForgeDirection.SOUTH){
			return new int[] {0};
		} else if(this.getOrientation() == ForgeDirection.WEST){
			return new int[] {0};
		} else if(this.getOrientation() == ForgeDirection.EAST){
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