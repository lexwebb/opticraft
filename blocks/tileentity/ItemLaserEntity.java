package opticraft.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ItemLaserEntity extends TileEntity {
	
	private String orientation;

	@Override
    public void readFromNBT(NBTTagCompound nbt){		
		super.readFromNBT(nbt);
		this.orientation = nbt.getString("orientation");
    }
	
	@Override
    public void writeToNBT(NBTTagCompound nbt){
		nbt.setString("orientation", orientation);
		super.writeToNBT(nbt);		
    }
	
	public void setOrientation(String ori){
		orientation = ori;
		writeToNBT(new NBTTagCompound());
	}
	
	public String getOrientation(){
		return orientation;
	}
}
