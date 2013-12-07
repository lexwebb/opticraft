package opticraft.lib;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import opticraft.Opticraft;
import opticraft.energy.LuxContainerTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketHandler implements IPacketHandler{
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player){
		
		if(payload.channel.equals(ModInfo.CHANNEL + "GuiSync")){
			if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
				handleGuiSyncPacket(payload);
		}
//		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
	}
	
	public void handleGuiSyncPacket(Packet250CustomPayload payload){
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		int dimID;
		int entityX, entityY, entityZ;
		int luxValue;
		
		try {
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
}
