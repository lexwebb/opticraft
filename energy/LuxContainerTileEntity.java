package opticraft.energy;

import java.util.ArrayList;
import java.util.List;

import opticraft.lib.DirectionalBlock;
import opticraft.lib.DirectionalTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

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

}
