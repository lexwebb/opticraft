package opticraft.entitys;

import opticraft.lib.Position;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityLaserDetector extends TileEntity {
	
	private String orientation;

	@Override
    public void readFromNBT(NBTTagCompound nbt){

    }
	
	@Override
    public void writeToNBT(NBTTagCompound nbt){
	
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
	
}
