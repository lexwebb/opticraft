package opticraft.entitys;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
import opticraft.lib.ModInfo;
import opticraft.lib.Position;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityBeam extends Entity {

	public List<Position> laserToList;
	public World world;
	public Position start, end;
	private DataWatcher dw;

	public EntityBeam(World par1World) {
		super(par1World);
		this.world = par1World;
	}

	public EntityBeam(World par1World, Position start, Position end) {
		super(par1World);
		this.start = start;
		this.end = end;
		
		this.dw = this.getDataWatcher();
		this.dw.addObject(8, 0);
		this.dw.addObject(9, 0);
		this.dw.addObject(10, 0);
		this.dw.addObject(11, 0);
		this.dw.addObject(12, 0);
		this.dw.addObject(13, 0);
		
		System.out.println("EntityBeamInitialised, start: " + start.toString() + " - " + end.toString());
	}

	@Override
	public void onUpdate() {

		if (!worldObj.isRemote) {
			updateDataServer();
			System.out.println("UpdatedServer");
		}

		if (worldObj.isRemote) {
			updateDataClient();
			System.out.println("UpdatedClient");
		}

		if (this.ticksExisted > 5) {
			this.setDead();
		}
		
		boundingBox.minX = Math.min(start.x, end.x);
        boundingBox.minY = Math.min(start.y, end.y);
        boundingBox.minZ = Math.min(start.z, end.z);

        boundingBox.maxX = Math.max(start.x, end.x);
        boundingBox.maxY = Math.max(start.y, end.y);
        boundingBox.maxZ = Math.max(start.z, end.z);

        boundingBox.minX--;
        boundingBox.minY--;
        boundingBox.minZ--;

        boundingBox.maxX++;
        boundingBox.maxY++;
        boundingBox.maxZ++;

		// if (!worldObj.isRemote)
		// sendUpdatePacket(entityId);
        super.onUpdate();
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub

	}

	void sendUpdatePacket(int entID) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);

		String parseString = "";

		for (Position pos : laserToList) {
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

		PacketDispatcher.sendPacketToAllAround(this.posX, this.posY, this.posZ,
				50, 0, packet);
	}

	protected void updateDataClient() {
		start.x = decodeDouble(dw.getWatchableObjectInt(8));
		start.y = decodeDouble(dw.getWatchableObjectInt(9));
		start.z = decodeDouble(dw.getWatchableObjectInt(10));
		end.x = decodeDouble(dw.getWatchableObjectInt(11));
		end.y = decodeDouble(dw.getWatchableObjectInt(12));
		end.z = decodeDouble(dw.getWatchableObjectInt(13));
	}

	protected void updateDataServer() {
		if (!worldObj.isRemote) {
			System.out.println("lolwut");
		}
		dw.updateObject(8, Integer.valueOf(encodeDouble(start.x)));
		dw.updateObject(9, Integer.valueOf(encodeDouble(start.y)));
		dw.updateObject(10, Integer.valueOf(encodeDouble(start.z)));
		dw.updateObject(11, Integer.valueOf(encodeDouble(end.x)));
		dw.updateObject(12, Integer.valueOf(encodeDouble(end.y)));
		dw.updateObject(13, Integer.valueOf(encodeDouble(end.z)));
	}

	protected int encodeDouble(double d) {
		return (int) (d * 8192);
	}

	protected double decodeDouble(int i) {
		return (i / 8192D);
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

}
