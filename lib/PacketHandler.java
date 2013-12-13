package opticraft.lib;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import opticraft.Opticraft;
import opticraft.energy.LuxContainerTileEntity;
import opticraft.entitys.TileEntityRedstoneLaser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.common.ForgeDirection;
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
		}
		((TileEntityRedstoneLaser) ent).reciever = receiver;
	}
}
