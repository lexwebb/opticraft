package opticraft.entitys;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
import opticraft.lib.ModInfo;
import opticraft.lib.Position;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityBeam extends Entity {
	
	public List<Position> laserToList;
	public World world;
	
	public EntityBeam(World par1World) {
		super(par1World);
		this.world = par1World;
	}
	
	public EntityBeam(World par1World, List<Position> laserToList) {
		super(par1World);
		this.laserToList = laserToList;
	}
	
	@Override
	public void onEntityUpdate(){
		if(this.ticksExisted > 5){
			this.setDead();
		}
		
//		if (!worldObj.isRemote)
//			sendUpdatePacket(entityId);
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}
	
	void sendUpdatePacket(int entID){
    	ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        
        String parseString = "";
        
        for(Position pos : laserToList){
        	parseString = parseString + pos.x + "," + pos.y + "," + pos.z + ";";
        }
        
        try {
        	outputStream.writeUTF("DirSync");
            outputStream.writeInt(entID);
            outputStream.writeUTF(parseString);      
        } catch (Exception ex) {
                ex.printStackTrace();
        }
        
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = (ModInfo.CHANNEL + "GuiSync");
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		
		System.out.println("Test");
		
		PacketDispatcher.sendPacketToAllAround(this.posX, this.posY, this.posZ, 50, 0, packet);
    }

}
