package opticraft.entitys;

import opticraft.energy.LuxContainerTileEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySolarCollector extends LuxContainerTileEntity {
	
	public TileEntitySolarCollector(){
		this.producer = true;
		this.maxCharge = 128;
		this.maxOutput = 2;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		if(this.worldObj.getWorldTime() % 10 == 0){
			if(time() < 16000){
				lux.increaseBy(2, maxCharge);			
			}
			
			System.out.println(lux.get());
		}
	}
	
	private long time(){
    	long worldTime = this.worldObj.getWorldTime();
    	int days = (int) Math.floor(worldTime / 24000);
    	long baseTime = worldTime - (days * 24000);
		return baseTime;    	
    }
}
