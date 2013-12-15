package opticraft.lib;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class DirectionalTileEntity extends TileEntity{
	
	private ForgeDirection orientation;
	
	public DirectionalTileEntity(){
		
	}
	
	public void setOrientation(ForgeDirection ori){
		orientation = ori;		
	}
	
	public ForgeDirection getOrientation(){
		return orientation;
	}
	
	@Override
	public void updateEntity(){
		if(orientation == null){
			int meta = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			
			if(meta == 1){
    			setOrientation(ForgeDirection.UP);
    		} else if(meta == 2){  			
				setOrientation(ForgeDirection.NORTH);
    		} else if(meta == 3){    			
				setOrientation(ForgeDirection.SOUTH);
			} else if(meta == 4){    			
				setOrientation(ForgeDirection.WEST);
			} else if(meta == 5){   			
				setOrientation(ForgeDirection.EAST);
			} else if(meta == 0){   			
				setOrientation(ForgeDirection.DOWN);
			} else
				setOrientation(ForgeDirection.UP);
		}
	}
}
