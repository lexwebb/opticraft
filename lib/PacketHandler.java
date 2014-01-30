package opticraft.lib;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opticraft.Opticraft;
import opticraft.energy.LuxContainerTileEntity;
import opticraft.entitys.EntityBeam;
import opticraft.entitys.TileEntityLaser;
import opticraft.entitys.TileEntityMirror;
import opticraft.entitys.TileEntityRedstoneLaser;
import opticraft.events.LaserEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketHandler implements IPacketHandler{
	
	@SideOnly(Side.CLIENT)
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player){
		
		if(payload.channel.equals(ModInfo.CHANNEL + "GuiSync")){
			handleSyncPacket(payload);
		}
	}
	
	public void handleSyncPacket(Packet250CustomPayload payload){
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		String temp;
		try {
			temp = data.readUTF();
		} catch (IOException e) {
            e.printStackTrace();
            return;
		}
		
		if(temp.equals("DirSync"))
			handleDirSyncPacket(payload);
		else if(temp.equals("GuiSync"))
			handleGuiSyncPacket(payload);
		else if(temp.equals("MultiDirSync"))
			handleMultiDirSyncPacket(payload);
		else if(temp.equals("LaserRenderSync"))
			handleLaserRenderSyncPacket(payload);
		else if(temp.equals("LaserRender"))
			handleLaserRenderPacket(payload);
	}
	
	private void handleLaserRenderPacket(Packet250CustomPayload payload) {
		
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		Position pos1, pos2;
		
		try {
			String temp = data.readUTF();
			pos1 = new Position(data.readDouble(), data.readDouble(), data.readDouble());
			pos2 = new Position(data.readDouble(), data.readDouble(), data.readDouble());
		} catch (IOException e) {
            e.printStackTrace();
            return;
		}
		
		System.out.println("Client: " + "Pos1: " + pos1.toString() + " Pos2: " + pos1.toString());
		
		MinecraftForge.EVENT_BUS.post(new LaserEvent(pos1, pos2));
	}

	public void handleGuiSyncPacket(Packet250CustomPayload payload){
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		int dimID;
		int entityX, entityY, entityZ;
		int luxValue;
		
		try {
			String temp = data.readUTF();
			entityX = data.readInt();
			entityY = data.readInt();
			entityZ = data.readInt();
			luxValue = data.readInt();
		} catch (IOException e) {
            e.printStackTrace();
            return;
		}
		
		TileEntity ent = Minecraft.getMinecraft().getMinecraft().theWorld.getBlockTileEntity(entityX, entityY, entityZ);
		if(ent instanceof LuxContainerTileEntity){
			((LuxContainerTileEntity) ent).lux.set(luxValue);
		}
	}
	
	public void handleDirSyncPacket(Packet250CustomPayload payload){
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		int dimID;
		int entityX, entityY, entityZ;
		String direction;
		boolean receiver;
		
		try {
			String temp = data.readUTF();
			entityX = data.readInt();
			entityY = data.readInt();
			entityZ = data.readInt();
			direction = data.readUTF();
			receiver = data.readBoolean();
		} catch (IOException e) {
            e.printStackTrace();
            return;
		}
		
		TileEntity ent = Minecraft.getMinecraft().getMinecraft().theWorld.getBlockTileEntity(entityX, entityY, entityZ);
		if(ent instanceof TileEntityRedstoneLaser){
			
			if(direction.equals("UP"))
				((TileEntityRedstoneLaser) ent).direction = ForgeDirection.UP;
            else if(direction.equals("DOWN"))
            	((TileEntityRedstoneLaser) ent).direction = ForgeDirection.DOWN;
            else if(direction.equals("NORTH"))
            	((TileEntityRedstoneLaser) ent).direction = ForgeDirection.NORTH;
            else if(direction.equals("EAST"))
            	((TileEntityRedstoneLaser) ent).direction = ForgeDirection.EAST;
            else if(direction.equals("SOUTH"))
            	((TileEntityRedstoneLaser) ent).direction = ForgeDirection.SOUTH;
            else if(direction.equals("WEST"))
            	((TileEntityRedstoneLaser) ent).direction = ForgeDirection.WEST;
            else
            	((TileEntityRedstoneLaser) ent).direction = ForgeDirection.NORTH;
		
			((TileEntityRedstoneLaser) ent).reciever = receiver;
		}
	}
	
	public void handleMultiDirSyncPacket(Packet250CustomPayload payload){
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		int dimID;
		int entityX, entityY, entityZ;
		String direction, orientation;
		boolean receiver;
		
		try {
			String temp = data.readUTF();
			entityX = data.readInt();
			entityY = data.readInt();
			entityZ = data.readInt();
			direction = data.readUTF();
			orientation = data.readUTF();
			receiver = data.readBoolean();
		} catch (IOException e) {
            e.printStackTrace();
            return;
		}
		
		TileEntity ent = Minecraft.getMinecraft().getMinecraft().theWorld.getBlockTileEntity(entityX, entityY, entityZ);
		
		if(ent instanceof TileEntityMirror){	
			if(direction.equals("UP"))
				((TileEntityMirror) ent).direction = ForgeDirection.UP;
	        else if(direction.equals("DOWN"))
	        	((TileEntityMirror) ent).direction = ForgeDirection.DOWN;
	        else if(direction.equals("NORTH"))
	        	((TileEntityMirror) ent).direction = ForgeDirection.NORTH;
	        else if(direction.equals("EAST"))
	        	((TileEntityMirror) ent).direction = ForgeDirection.EAST;
	        else if(direction.equals("SOUTH"))
	        	((TileEntityMirror) ent).direction = ForgeDirection.SOUTH;
	        else if(direction.equals("WEST"))
	        	((TileEntityMirror) ent).direction = ForgeDirection.WEST;
	        else
	        	((TileEntityMirror) ent).direction = ForgeDirection.NORTH;
			
			if(orientation.equals("UP"))
				((TileEntityMirror) ent).orientation = ForgeDirection.UP;
	        else if(orientation.equals("DOWN"))
	        	((TileEntityMirror) ent).orientation = ForgeDirection.DOWN;
	        else if(orientation.equals("NORTH"))
	        	((TileEntityMirror) ent).orientation = ForgeDirection.NORTH;
	        else if(orientation.equals("EAST"))
	        	((TileEntityMirror) ent).orientation = ForgeDirection.EAST;
	        else if(orientation.equals("SOUTH"))
	        	((TileEntityMirror) ent).orientation = ForgeDirection.SOUTH;
	        else if(orientation.equals("WEST"))
	        	((TileEntityMirror) ent).orientation = ForgeDirection.WEST;
	        else
	        	((TileEntityMirror) ent).orientation = ForgeDirection.NORTH;
		}
	}
	
	public void handleLaserRenderSyncPacket(Packet250CustomPayload payload){
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		int dimID;
		int x, y, z;
		double x1, y1, z1;
		String direction, orientation;
		boolean receiver;
		
		List<Position> laserToList = new ArrayList<Position>();
		
		try {
			String temp = data.readUTF();
			x = data.readInt();
			y = data.readInt();
			z = data.readInt();
			
			//pass size of array
        	int size = data.readInt();
        	
        	//array send loop
        	for(int i = 0; i < size; i++){
        		x1 = data.readDouble();
        		y1 = data.readDouble();
        		z1 = data.readDouble();
        		
        		laserToList.add(new Position(x1, y1, z1));
        	}
        	
		} catch (IOException e) {
            e.printStackTrace();
            return;
		}
		
		TileEntityLaser ent = (TileEntityLaser) Minecraft.getMinecraft().theWorld.getBlockTileEntity(x, y, z);
		
		ent.laserToList.clear();
		ent.laserToList = laserToList;
		
		ent.laserFireTime = Minecraft.getMinecraft().theWorld.getTotalWorldTime();
	}
}
