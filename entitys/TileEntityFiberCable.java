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

public class TileEntityFiberCable extends LuxContainerTileEntity{
	
	public boolean upHidden;
	public boolean downHidden;
	public boolean eastHidden;
	public boolean westHidden;
	public boolean northHidden;
	public boolean southHidden;
	
	
	public TileEntityFiberCable(){
		this.maxCharge = 0;
		this.maxOutput = 128;
		this.canImport = true;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		if(worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof LuxContainerTileEntity){
			upHidden = false;		
		} else {
			upHidden = true;
		}
		if(worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord) instanceof LuxContainerTileEntity){
			downHidden = false;		
		} else {
			downHidden = true;
		}
		if(worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord) instanceof LuxContainerTileEntity){
			eastHidden = false;		
		} else {
			eastHidden = true;
		}
		if(worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord) instanceof LuxContainerTileEntity){
			westHidden = false;		
		} else {
			westHidden = true;
		}
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1) instanceof LuxContainerTileEntity){
			southHidden = false;		
		} else {
			southHidden = true;
		}
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1) instanceof LuxContainerTileEntity){
			northHidden = false;		
		} else {
			northHidden = true;
		}
	}
	
}
