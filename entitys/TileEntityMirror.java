package opticraft.entitys;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import opticraft.energy.LuxContainerTileEntity;
import opticraft.lib.DirectionalTileEntity;
import opticraft.lib.ModInfo;
import opticraft.lib.Position;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
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
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityMirror extends DirectionalTileEntity{	
	
	public ForgeDirection direction;
	
	public TileEntityMirror(){
		
	}
	
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
            outputStream.writeBoolean(false);
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
            }
    }

	public ForgeDirection getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

	public void setDirection(ForgeDirection direction) {
		// TODO Auto-generated method stub
		this.direction = direction;
	}
}
