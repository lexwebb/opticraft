package opticraft.energy;

import java.util.ArrayList;
import java.util.List;

import opticraft.lib.DirectionalBlock;
import opticraft.lib.DirectionalTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class LuxContainerTileEntity extends DirectionalTileEntity{
	
	public Lux lux = new Lux();
	public int maxOutput = 1;
	public int maxCharge = 0;
	
	public boolean canExport = true;;
	public boolean canImport = true;;
	
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
		
		if(canExport && maxCharge !=0){
			transfer(2, this);
		}
	}
	
	public boolean increaseBySource(double value, LuxContainerTileEntity source){
		if(this.canImport){
			if(this.maxCharge == 0){
				if(transfer(value, source))
					return true;
				else
					return false;
			} else {
				return lux.increaseBy(value, maxCharge);
			}
		}
		else return false;
	}
	
	public boolean transfer(double value, LuxContainerTileEntity source){
		List<LuxContainerTileEntity> transporters = getAdjacantTransporters();
		boolean transfered = false;
		
		for(LuxContainerTileEntity trans : transporters){	
			if(trans != source || source == this){
				if(this.maxCharge != 0){
					if(lux.decreaseBy(value, 0)){
						if(trans.increaseBySource(value / (transporters.size()), this)){
							transfered = true;
						} else {
							lux.increaseBy(value, maxCharge);
						}
							
					}
				} else {
					if(trans.increaseBySource(value / (transporters.size() - 1), this)){
						transfered = true;
					} else {
						lux.increaseBy(value, maxCharge);
					}
				}
					
			}
		}
		
		return transfered;
	}
	
	public List<LuxContainerTileEntity> getAdjacantTransporters(){
		List<LuxContainerTileEntity> transporters = new ArrayList<LuxContainerTileEntity>();
		if(worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof LuxContainerTileEntity){
			transporters.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord));		
		}
		if(worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord) instanceof LuxContainerTileEntity){
			transporters.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord));		
		}
		if(worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord) instanceof LuxContainerTileEntity){
			transporters.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord));		
		}
		if(worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord) instanceof LuxContainerTileEntity){
			transporters.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord));		
		}
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1) instanceof LuxContainerTileEntity){
			transporters.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1));		
		}
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1) instanceof LuxContainerTileEntity){
			transporters.add((LuxContainerTileEntity)worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1));		
		}
		
		return transporters;
	}

}
