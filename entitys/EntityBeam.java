package opticraft.entitys;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityBeam extends Entity {
	
	public String orientation;

	public EntityBeam(World par1World, String orientation) {
		super(par1World);
		this.orientation = orientation;
	}
	
	public EntityBeam(World par1World) {
		super(par1World);
	}

	@Override
	protected void entityInit() {

	}
	
	protected void init(){
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

}
