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
import net.minecraft.nbt.NBTTagInt;
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
import net.minecraftforge.common.ForgeDirection;

public class TileEntityRedstoneLaser extends LuxContainerTileEntity{
	
	Position linkedDetector;
	ForgeDirection direction;
	public int providedPower, recievedPower;
	public boolean reciever;
	public long tickUpdated;
	
	public TileEntityRedstoneLaser(){
		this.maxCharge = 0;
		this.reciever = false;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		if(direction == null){
			if(getOrientation() == "U" || getOrientation() == "D")
				this.direction = ForgeDirection.NORTH;
			else if(getOrientation() == "E" || getOrientation() == "W")
				this.direction = ForgeDirection.UP;
			else if(getOrientation() == "N" || getOrientation() == "S")
				this.direction = ForgeDirection.UP;
		}
		
//		System.out.println(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord));
		
		if(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0){
			if(!reciever){
				System.out.println(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord));
	            this.recievedPower = worldObj.getBlockPowerInput(xCoord, yCoord, zCoord);
	            findDetector();
	            sendSignal();
	            sendBlockUpdates();
			}
		} else {
			if(!reciever){
				this.recievedPower = 0;
				findDetector();
				sendBlockUpdates();
			}
		}
		
		if(reciever){
			if(tickUpdated + 10 < worldObj.getWorldTime()){
				this.recievedPower = 0;
				System.out.println("recieverpower: " + tickUpdated + " Gametime: " + worldObj.getWorldTime());
			}
			
			if(tickUpdated == 0){
				this.recievedPower = 0;
				sendBlockUpdates();
			}
		}
	}
	
	void sendBlockUpdates(){
		if(linkedDetector != null){
            TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) worldObj.getBlockTileEntity(
					(int) Math.floor(linkedDetector.x), (int) Math.floor(linkedDetector.y), (int) Math.floor(linkedDetector.z));
			ent.recievedPower = 0;			
			worldObj.scheduleBlockUpdate(ent.xCoord, ent.yCoord, ent.zCoord, 1, 1);
        }
		worldObj.scheduleBlockUpdate(xCoord, yCoord, zCoord, 1, 1);
	}
	
	public void findDetector(){		
		TileEntity tile_entity;
		linkedDetector = null;
		boolean solidInWay = false;
		
		if(this.getDirection() == ForgeDirection.UP){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord + i, zCoord).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord + i, zCoord);
				if(tile_entity instanceof TileEntityRedstoneLaser){
					TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) tile_entity;
					if(ent.getDirection() == ForgeDirection.DOWN && ent.reciever){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}
		} else if(this.getDirection() == ForgeDirection.DOWN){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord - i, zCoord).isOpaque())
					solidInWay = true;
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord - i, zCoord);
				if(tile_entity instanceof TileEntityRedstoneLaser){
					TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) tile_entity;
					if(ent.getDirection() == ForgeDirection.UP && ent.reciever){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getDirection() == ForgeDirection.NORTH){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord, zCoord - i).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - i);
				if(tile_entity instanceof TileEntityRedstoneLaser){
					TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) tile_entity;
					if(ent.getDirection() == ForgeDirection.SOUTH && ent.reciever){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getDirection() == ForgeDirection.SOUTH){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord, yCoord, zCoord + i).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + i);
				if(tile_entity instanceof TileEntityRedstoneLaser){
					TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) tile_entity;
					if(ent.getDirection() == ForgeDirection.NORTH && ent.reciever){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getDirection() == ForgeDirection.WEST){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord - i, yCoord, zCoord).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord - i, yCoord, zCoord);
				if(tile_entity instanceof TileEntityRedstoneLaser){
					TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) tile_entity;
					if(ent.getDirection() == ForgeDirection.EAST && ent.reciever){
						linkedDetector = new Position(ent);
					}
				}
				if(linkedDetector != null)
					break;
				else if(solidInWay)
					break;
			}	
		} else if(this.getDirection() == ForgeDirection.EAST){
			for(int i = 1; i < 64; i++){
				if(worldObj.getBlockMaterial(xCoord + i, yCoord, zCoord).isSolid())
					solidInWay = true;
				
				tile_entity = worldObj.getBlockTileEntity(xCoord + i, yCoord, zCoord);
				if(tile_entity instanceof TileEntityRedstoneLaser){
					TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) tile_entity;
					if(ent.getDirection() == ForgeDirection.WEST && ent.reciever){
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
	
	public void sendSignal(){
		//check for Detector
		if(linkedDetector != null){
			TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) worldObj.getBlockTileEntity(
					(int) Math.floor(linkedDetector.x), (int) Math.floor(linkedDetector.y), (int) Math.floor(linkedDetector.z));				
			if(this.getDirection() == ForgeDirection.UP){
				for(int i = this.yCoord; i < linkedDetector.y; i++){
					if (!worldObj.isRemote){
						EntityBeamY entity = new EntityBeamY(worldObj);
						entity.setPosition(xCoord, i, zCoord);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}
			} else if(this.getDirection() == ForgeDirection.DOWN){
				for(int i = this.yCoord; i > linkedDetector.y; i--){
					if (!worldObj.isRemote){
						EntityBeamY entity = new EntityBeamY(worldObj);
						entity.setPosition(xCoord, i, zCoord);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}	
			} else if(this.getDirection() == ForgeDirection.NORTH){
				for(int i = this.zCoord; i > linkedDetector.z; i--){
					if (!worldObj.isRemote){
						EntityBeamZ entity = new EntityBeamZ(worldObj);
						entity.setPosition(xCoord, yCoord, i);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}	
			} else if(this.getDirection() == ForgeDirection.SOUTH){
				for(int i = this.zCoord; i < linkedDetector.z; i++){
					if (!worldObj.isRemote){
						EntityBeamZ entity = new EntityBeamZ(worldObj);
						entity.setPosition(xCoord, yCoord, i);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}	
			} else if(this.getDirection() == ForgeDirection.EAST){
				for(int i = this.xCoord; i < linkedDetector.x; i++){
					if (!worldObj.isRemote){
						EntityBeamX entity = new EntityBeamX(worldObj);
						entity.setPosition(i, yCoord, zCoord);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}	
			} else if(this.getDirection() == ForgeDirection.WEST){
				for(int i = this.xCoord; i > linkedDetector.x; i--){
					if (!worldObj.isRemote){
						EntityBeamX entity = new EntityBeamX(worldObj);
						entity.setPosition(i, yCoord, zCoord);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}
			}
			
			ent.providedPower = this.recievedPower;
			ent.tickUpdated = worldObj.getWorldTime();
			worldObj.markBlockForUpdate(ent.xCoord, ent.yCoord, ent.zCoord);
			worldObj.playSoundEffect((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D, "optcrft:buzz",  0.1F, worldObj.rand.nextFloat() * 0.1F + 0.9F);		
		}
	}
	
	public void setDirection(ForgeDirection direction){
		this.direction = direction;
	}
	
	public ForgeDirection getDirection(){
		return this.direction;
	}
    
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            
            if(tagCompound.getString("direction") == "UP")
            	this.direction = ForgeDirection.UP;
            else if(tagCompound.getString("direction") == "DOWN")
            	this.direction = ForgeDirection.DOWN;
            else if(tagCompound.getString("direction") == "NORTH")
            	this.direction = ForgeDirection.NORTH;
            else if(tagCompound.getString("direction") == "EAST")
            	this.direction = ForgeDirection.EAST;
            else if(tagCompound.getString("direction") == "SOUTH")
            	this.direction = ForgeDirection.SOUTH;
            else if(tagCompound.getString("direction") == "WEST")
            	this.direction = ForgeDirection.WEST;
            else
            	this.direction = ForgeDirection.NORTH;
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
                            
            tagCompound.setString("direction", this.direction.name());
    }
}
