package opticraft.render;

import java.util.List;

import opticraft.entitys.EntityBeam;
import opticraft.entitys.EntityBeamY;
import opticraft.entitys.TileEntityLaser;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import opticraft.lib.Position;
import opticraft.models.BeamModel;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BeamRenderer extends Render{
    
    public BeamRenderer() {
            
    }
    
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            GL11.glPushMatrix();
            GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
            GL11.glPopMatrix();
    }

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float f, float f1) {
		
//		List<Position> laserToList = ((TileEntityLaser)((EntityBeam) entity).worldObj.getBlockTileEntity((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z))).laserToList;
		List<Position> laserToList = ((EntityBeam) entity).laserToList;
		if(laserToList != null){
			System.out.println("LOL");
			for(int i = 0; i < laserToList.size(); i++){
				GL11.glPushMatrix();
				GL11.glTranslated(-laserToList.get(i).x, -laserToList.get(i).y, -laserToList.get(i).z);
				GL11.glColor3ub((byte)255,(byte)0,(byte)0);
				float mx = (float) laserToList.get(i + 1).x;
				float my = (float) laserToList.get(i + 1).y;
				float mz = (float) laserToList.get(i + 1).z;
				GL11.glBegin(GL11.GL_LINES);
				GL11.glVertex3f(mx,my,mz);
				GL11.glEnd();
				GL11.glPopMatrix();
			}	
		} else {
			System.out.println("KJHIUGH");
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
