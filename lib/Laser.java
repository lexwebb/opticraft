package opticraft.lib;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntityMirror;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.DataWatcher;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class Laser {
	
	public Position linkedDetector;
	public List<Position> laserToList;
	public List<ForgeDirection> laserToDirection;
	public boolean shouldFire;
	public long laserFireTime;
	public AxisAlignedBB boundingRenderBox;
	public TileEntity entity;

	public Laser(World worldObj, TileEntity entity){
		this.entity = entity;
		laserToList = new ArrayList<Position>();
		laserToDirection = new ArrayList<ForgeDirection>();
	}
	
	public void updateLaser(){
		if (entity.worldObj.isRemote){
			if(Minecraft.getMinecraft().theWorld.getTotalWorldTime() >= laserFireTime + 2)
				laserToList.clear();
		}
		
		updateRenderBox();
	}
	
	public void findDetectorRecursive(ForgeDirection direction, int x, int y, int z){
		TileEntity tile_entity;
		linkedDetector = null;
		boolean solidInWay = false;
		
		laserToList.add(new Position(x, y, z));
		laserToDirection.add(direction);
		
		if(direction == ForgeDirection.UP){
			for(int i = 1; i < 64; i++){
				if(entity.worldObj.getBlockMaterial(x, y, z - i) != null && entity.worldObj.getBlockMaterial(x, y + i, z).isSolid())
					solidInWay = true;
				
				tile_entity = entity.worldObj.getBlockTileEntity(x, y + i, z);
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
				if(entity.worldObj.getBlockMaterial(x, y, z - i) != null && entity.worldObj.getBlockMaterial(x, y - i, z).isOpaque())
					solidInWay = true;
				
				System.out.println(solidInWay);
				
				tile_entity = entity.worldObj.getBlockTileEntity(x, y - i, z);
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
				if(entity.worldObj.getBlockMaterial(x, y, z - i) != null && entity.worldObj.getBlockMaterial(x, y, z - i).isSolid())
					solidInWay = true;
				
				tile_entity = entity.worldObj.getBlockTileEntity(x, y, z - i);
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
				if(entity.worldObj.getBlockMaterial(x, y, z - i) != null && entity.worldObj.getBlockMaterial(x, y, z + i).isSolid())
					solidInWay = true;
				
				tile_entity = entity.worldObj.getBlockTileEntity(x, y, z + i);
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
				if(entity.worldObj.getBlockMaterial(x, y, z - i) != null && entity.worldObj.getBlockMaterial(x - i, y, z).isSolid())
					solidInWay = true;
				
				tile_entity = entity.worldObj.getBlockTileEntity(x - i, y, z);
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
				if(entity.worldObj.getBlockMaterial(x, y, z - i) != null && entity.worldObj.getBlockMaterial(x + i, y, z).isSolid())
					solidInWay = true;
				
				tile_entity = entity.worldObj.getBlockTileEntity(x + i, y, z);
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

	public void laserBeam(){
		if(laserToList != null && laserToList.size() > 1){
			for(int j = 0; j < laserToList.size() - 1; j++){
				ForgeDirection or = laserToDirection.get(j);
				sendUpdatePacket();	
				entity.worldObj.playSoundEffect((double)entity.xCoord + 0.5D, (double)entity.yCoord + 0.5D, (double)entity.zCoord + 0.5D, "optcrft:buzz",  0.1F, entity.worldObj.rand.nextFloat() * 0.1F + 0.9F);
                laserToList.clear();
			}
		}
	}
	
	void sendUpdatePacket(){
    	ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);      
        
        try {
        	outputStream.writeUTF("LaserRenderSync");
        	outputStream.writeInt(entity.xCoord);
        	outputStream.writeInt(entity.yCoord);
        	outputStream.writeInt(entity.zCoord);
        	
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

		PacketDispatcher.sendPacketToAllAround(entity.xCoord, entity.yCoord, entity.zCoord, 50, 0, packet);
    }
	
	public void updateRenderBox(){
		if(laserToList == null || laserToList.size() == 0){
			boundingRenderBox.setBounds(entity.xCoord, entity.yCoord, entity.zCoord, entity.xCoord + 1,  entity.yCoord + 1, entity.zCoord + 1);
		} else {
			int minx, miny, minz, maxx, maxy, maxz;
			minx = entity.xCoord;
			miny = entity.yCoord;
			minz = entity.zCoord;
			maxx = entity.xCoord+1;
			maxy = entity.yCoord+1;
			maxz = entity.zCoord+1;
			
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
}
