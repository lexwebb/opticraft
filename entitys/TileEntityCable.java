package opticraft.entitys;

import opticraft.energy.LuxContainerTileEntity;
import opticraft.lib.DirectionalTileEntity;
import opticraft.lib.Position;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

public class TileEntityCable extends LuxContainerTileEntity{
	
	public boolean DOWN;
		public boolean DOWNNorth;
		public boolean DOWNSouth;
		public boolean DOWNEast;
		public boolean DOWNWest;
	public boolean UP;
		public boolean UPNorth;
		public boolean UPSouth;
		public boolean UPEast;
		public boolean UPWest;
	public boolean NORTH;
		public boolean NORTHUp;
		public boolean NORTHDown;
		public boolean NORTHEast;
		public boolean NORTHWest;
	public boolean SOUTH;
		public boolean SOUTHUp;
		public boolean SOUTHDown;
		public boolean SOUTHEast;
		public boolean SOUTHWest;
	public boolean EAST;
		public boolean EASTUp;
		public boolean EASTDown;
		public boolean EASTNorth;
		public boolean EASTSouth;
	public boolean WEST;
		public boolean WESTUp;
		public boolean WESTDown	;
		public boolean WESTNorth;
		public boolean WESTSouth;
	
	public TileEntityCable(){
		this.maxCharge = 0;
		this.maxOutput = 0;
		this.producer = false;
		this.consumer = false;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
//		if(DOWN){		
//			if(worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord) instanceof LuxContainerTileEntity 
//					|| worldObj.getBlockTileEntity(xCoord + 1, yCoord - 1, zCoord) instanceof LuxContainerTileEntity 
//					|| EAST)
//				DOWNEast = false;		
//			else 
//				DOWNEast = true;
//			if(worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord) instanceof LuxContainerTileEntity 
//					|| worldObj.getBlockTileEntity(xCoord - 1, yCoord - 1, zCoord) instanceof LuxContainerTileEntity 
//					|| EAST)
//				DOWNWest = false;		
//			else 
//				DOWNWest= true;
//			if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1) instanceof LuxContainerTileEntity)
//				southHidden = false;		
//			else 
//				southHidden = true;
//			if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1) instanceof LuxContainerTileEntity)
//				northHidden = false;		
//			else 
//				northHidden = true;
//		}
	}
	
}
