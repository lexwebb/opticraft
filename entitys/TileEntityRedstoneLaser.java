package opticraft.entitys;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.lwjgl.Sys;

import opticraft.blocks.Blocks;
import opticraft.energy.LuxContainerTileEntity;
import opticraft.items.Items;
import opticraft.lib.DirectionalTileEntity;
import opticraft.lib.ModInfo;
import opticraft.lib.Position;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.Hopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityRedstoneLaser extends DirectionalTileEntity{
	
	Position linkedDetector;
	public ForgeDirection direction;
	public int providedPower, recievedPower;
	public boolean reciever;
	public long tickUpdated;
	
	public TileEntityRedstoneLaser(){
		this.reciever = false;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		if(this.worldObj.getWorldTime() % 20 == 0 && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
			sendUpdatePacket();
		}
		
		if(direction == null){
			if(getOrientation() == ForgeDirection.UP || getOrientation() == ForgeDirection.DOWN)
				this.direction = ForgeDirection.NORTH;
			else if(getOrientation() == ForgeDirection.EAST || getOrientation() == ForgeDirection.WEST)
				this.direction = ForgeDirection.UP;
			else if(getOrientation() == ForgeDirection.NORTH || getOrientation() == ForgeDirection.SOUTH)
				this.direction = ForgeDirection.UP;
		}
		
//		System.out.println(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord));
		
		if(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0){
			if(!reciever){
	            this.recievedPower = worldObj.getBlockPowerInput(xCoord, yCoord, zCoord);
	            findDetector();
	            sendSignal();
	            sendBlockUpdates();
			}
		} else {
			if(!reciever){
				this.recievedPower = 0;
				this.providedPower = 0;
				findDetector();
				sendBlockUpdates();
			}
		}
		
		if(tickUpdated != 0)
		if(reciever){
			if(tickUpdated + 1 < worldObj.getWorldTime()){
				this.recievedPower = 0;
				this.providedPower = 0;
				tickUpdated = 0;
				sendBlockUpdates();
			}
			sendBlockUpdates();
		}
	}
	
	void sendBlockUpdates(){
		worldObj.notifyBlockChange(xCoord, yCoord, zCoord, Blocks.redstoneLaserTileBlock.blockID);
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
				for(int i = this.yCoord + 1; i < linkedDetector.y; i++){
					if (!worldObj.isRemote){
						EntityBeamY entity = new EntityBeamY(worldObj);
						entity.setPosition(xCoord, i, zCoord);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}
			} else if(this.getDirection() == ForgeDirection.DOWN){
				for(int i = this.yCoord - 1; i > linkedDetector.y; i--){
					if (!worldObj.isRemote){
						EntityBeamY entity = new EntityBeamY(worldObj);
						entity.setPosition(xCoord, i, zCoord);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}	
			} else if(this.getDirection() == ForgeDirection.NORTH){
				for(int i = this.zCoord - 1; i > linkedDetector.z; i--){
					if (!worldObj.isRemote){
						EntityBeamZ entity = new EntityBeamZ(worldObj);
						entity.setPosition(xCoord, yCoord, i);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}	
			} else if(this.getDirection() == ForgeDirection.SOUTH){
				for(int i = this.zCoord + 1; i < linkedDetector.z; i++){
					if (!worldObj.isRemote){
						EntityBeamZ entity = new EntityBeamZ(worldObj);
						entity.setPosition(xCoord, yCoord, i);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}	
			} else if(this.getDirection() == ForgeDirection.EAST){
				for(int i = this.xCoord + 1; i < linkedDetector.x; i++){
					if (!worldObj.isRemote){
						EntityBeamX entity = new EntityBeamX(worldObj);
						entity.setPosition(i, yCoord, zCoord);							
						worldObj.spawnEntityInWorld(entity);
					}		
				}	
			} else if(this.getDirection() == ForgeDirection.WEST){
				for(int i = this.xCoord - 1; i > linkedDetector.x; i--){
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
            
            if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){	
	            if(tagCompound.getString("direction").equals("UP"))
	            	this.direction = ForgeDirection.UP;
	            else if(tagCompound.getString("direction").equals("DOWN"))
	            	this.direction = ForgeDirection.DOWN;
	            else if(tagCompound.getString("direction").equals("NORTH"))
	            	this.direction = ForgeDirection.NORTH;
	            else if(tagCompound.getString("direction").equals("EAST"))
	            	this.direction = ForgeDirection.EAST;
	            else if(tagCompound.getString("direction").equals("SOUTH"))
	            	this.direction = ForgeDirection.SOUTH;
	            else if(tagCompound.getString("direction").equals("WEST"))
	            	this.direction = ForgeDirection.WEST;
	            else
	            	this.direction = ForgeDirection.NORTH;
	            
	            reciever = tagCompound.getBoolean("receiver");
            }
    }
    
    void sendUpdatePacket(){
    	ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        
        try {
        	outputStream.writeUTF("DirSync");
            outputStream.writeInt(xCoord);
            outputStream.writeInt(yCoord);
            outputStream.writeInt(zCoord);
            outputStream.writeUTF(this.direction.toString());
            outputStream.writeBoolean(reciever);
        } catch (Exception ex) {
                ex.printStackTrace();
        }
        
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = (ModInfo.CHANNEL + "GuiSync");
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		
		PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 50, 0, packet);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);

            if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){	
	            tagCompound.setString("direction", this.direction.name());
	            tagCompound.setBoolean("receiver", reciever);
            }
    }
}
