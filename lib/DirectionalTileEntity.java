package opticraft.lib;

import net.minecraft.tileentity.TileEntity;

public class DirectionalTileEntity extends TileEntity{
	
	private String orientation;
	
	public DirectionalTileEntity(){
		
	}
	
	public void setOrientation(String ori){
		orientation = ori;		
	}
	
	public String getOrientation(){
		return orientation;
	}
	
	@Override
	public void updateEntity(){
		if(orientation == null){
			int meta = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			
			if(meta == 1){
    			setOrientation("U");
    		} else if(meta == 2){  			
				setOrientation("N");
    		} else if(meta == 3){    			
				setOrientation("S");
			} else if(meta == 4){    			
				setOrientation("W");
			} else if(meta == 5){   			
				setOrientation("E");
			} else if(meta == 0){   			
				setOrientation("D");
			} else
				setOrientation("U");
		}
	}
}
