package opticraft.energy;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import opticraft.Opticraft;
import opticraft.lib.DirectionalBlock;
import opticraft.lib.DirectionalTileEntity;
import opticraft.lib.ModInfo;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class LuxContainerTileEntity extends DirectionalTileEntity{
		
	public List<LuxContainerTileEntity> linkedContainers = new ArrayList<LuxContainerTileEntity>();
	
	public Lux lux = new Lux();
	public int maxOutput = 1;
	public int maxCharge = 0;
	
	public boolean producer = false;
	public boolean consumer = false;
	
	public boolean faceOneUsable;
	public boolean faceTwoUsable;
	public boolean faceThreeUsable;
	public boolean faceFourUsable;
	public boolean faceFiveUsable;
	public boolean faceSixUsable;
	
	
	public LuxContainerTileEntity(){
		super();
		
		
		this.faceOneUsable = true;
		this.faceTwoUsable = true;
		this.faceThreeUsable = true;
		this.faceFourUsable = true;
		this.faceFiveUsable = true;
		this.faceSixUsable = true;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){			
			ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	        DataOutputStream outputStream = new DataOutputStream(bos);
	        
	        try {
	        	outputStream.writeUTF("GuiSync");
                outputStream.writeInt(xCoord);
                outputStream.writeInt(yCoord);
                outputStream.writeInt(zCoord);
                outputStream.writeInt(lux.value);
	        } catch (Exception ex) {
	                ex.printStackTrace();
	        }
	        
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = (ModInfo.CHANNEL + "GuiSync");
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			
			PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 10, 0, packet);
		}
		
		if(producer && lux.value >= maxOutput){
			transfer(maxOutput);
		}
	}
	
	public boolean increaseBySource(int value){
		if(this.consumer){
			if(this.maxCharge == 0){
				return false;
			} else {
				return lux.increaseBy(value, maxCharge);
			}
		}
		else return false;
	}
	
	public boolean transfer(int value){
		linkedContainers.clear();
		List<LuxContainerTileEntity> adjacentContainers = getAdjacentContainers(this);
		boolean transfered = false;
		int consumerCount = 0;
		
		recursiveFind(this);
		
		List<LuxContainerTileEntity> consumers = new ArrayList<LuxContainerTileEntity>();
		
		for(LuxContainerTileEntity ent : linkedContainers){
			if(ent.consumer && ent != this){
				consumerCount++;
				consumers.add(ent);
			}
			
		}
		
		if(consumerCount !=0){
			int ammnountPer = value / consumerCount;
			
			boolean consumersSet = false;
			while(!consumersSet){
				if(consumerCount == 0)
					break;
				for(LuxContainerTileEntity ent : consumers){
					if(!ent.lux.canAcceptValue(ammnountPer, ent.maxCharge)){
						consumers.remove(ent);
						consumerCount--;
						if(consumerCount !=0)
							ammnountPer = value / consumerCount;
						break;
					}		
				}
				consumersSet = true;
			}
			
			if(consumersSet){
				for(LuxContainerTileEntity ent : consumers){
					ent.lux.increaseBy(ammnountPer, ent.maxCharge);
					transfered = true;
				}
				
				if(transfered)
					lux.decreaseBy(ammnountPer * consumerCount, 0);
			}
		}
		
		return transfered;	
	}
	
	public void recursiveFind(LuxContainerTileEntity entity){
		List<LuxContainerTileEntity> adjacentContainers = getAdjacentContainers(entity);
		
		for(LuxContainerTileEntity ent : adjacentContainers){
			if(!linkedContainers.contains(ent)){
				linkedContainers.add(ent);
				recursiveFind(ent);
			}
		}
	}
	
	public List<LuxContainerTileEntity> getAdjacentContainers(LuxContainerTileEntity ent){
		List<LuxContainerTileEntity> adjacentContainers = new ArrayList<LuxContainerTileEntity>();
		if(worldObj.getBlockTileEntity(ent.xCoord, ent.yCoord + 1, ent.zCoord) instanceof LuxContainerTileEntity){
			adjacentContainers.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(ent.xCoord, ent.yCoord + 1, ent.zCoord));		
		}
		if(worldObj.getBlockTileEntity(ent.xCoord, ent.yCoord - 1, ent. zCoord) instanceof LuxContainerTileEntity){
			adjacentContainers.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(ent.xCoord, ent.yCoord - 1, ent.zCoord));		
		}
		if(worldObj.getBlockTileEntity(ent.xCoord + 1, ent.yCoord, ent.zCoord) instanceof LuxContainerTileEntity){
			adjacentContainers.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(ent.xCoord + 1, ent.yCoord, ent.zCoord));		
		}
		if(worldObj.getBlockTileEntity(ent.xCoord - 1, ent.yCoord, ent.zCoord) instanceof LuxContainerTileEntity){
			adjacentContainers.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(ent.xCoord - 1, ent.yCoord, ent.zCoord));		
		}
		if(worldObj.getBlockTileEntity(ent.xCoord, ent.yCoord, ent.zCoord + 1) instanceof LuxContainerTileEntity){
			adjacentContainers.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(ent.xCoord, ent.yCoord, ent.zCoord + 1));		
		}
		if(worldObj.getBlockTileEntity(ent.xCoord, ent.yCoord, ent.zCoord - 1) instanceof LuxContainerTileEntity){
			adjacentContainers.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(ent.xCoord, ent.yCoord, ent.zCoord - 1));		
		}
		
		return adjacentContainers;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            
            int storedLux = tagCompound.getInteger("Lux");
            this.lux.value = storedLux;
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
                            
            tagCompound.setInteger("Lux", lux.value);
    }

}
