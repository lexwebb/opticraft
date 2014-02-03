package opticraft.client.render;

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

public class LaserBeamRenderer{
    
    public LaserBeamRenderer() {
            
    }

	public void Render(TileEntity ent, double x, double y, double z) {
		
//		List<Position> laserToList = ((TileEntityLaser)((EntityBeam) entity).worldObj.getBlockTileEntity((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z))).laserToList;
		
		List<Position> laserToList =  ((TileEntityLaser)ent).laserToList;
		if (laserToList != null && laserToList.size() > 1 && ((TileEntityLaser)ent).shouldFire) {
			for (int i = 0; i < laserToList.size() - 1; i++) {
				System.out.println(laserToList.get(i).toString());
				System.out.println(laserToList.get(i + 1).toString());
				
				double doubleX = ent.xCoord;
				double doubleY = ent.yCoord;
				double doubleZ = ent.zCoord;
				
//				double doubleX = Minecraft.getMinecraft().thePlayer.posX - 0.5;
//				double doubleY = Minecraft.getMinecraft().thePlayer.posY + 0.1;
//				double doubleZ = Minecraft.getMinecraft().thePlayer.posZ - 0.5;

				GL11.glPushMatrix();
				
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_LINE_SMOOTH);
				GL11.glHint(GL11.GL_LINE_SMOOTH_HINT,  GL11.GL_NICEST);
	            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	            
//				GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
				
				GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
				GL11.glColor3ub((byte)255,(byte)0,(byte)0);
				
				double xd = ent.xCoord- Minecraft.getMinecraft().thePlayer.posX;
				double yd = ent.yCoord- Minecraft.getMinecraft().thePlayer.posY;
				double zd = ent.zCoord- Minecraft.getMinecraft().thePlayer.posZ;
				
				double dist = Math.sqrt(xd*xd + yd*yd + zd*zd);
				if(dist < 0) //dirty hack
					dist = dist * -1;		
				double modDist = dist - 4;
				
				if((10.0F - modDist / 2) > 1)
				GL11.glLineWidth((float) (10.0F - modDist / 2));
				else
					GL11.glLineWidth(1.0f);
				
				float mx1 = -(ent.xCoord - (float) laserToList.get(i).x);
				float my1 = -(ent.yCoord - (float) laserToList.get(i).y);
				float mz1 = -(ent.zCoord - (float) laserToList.get(i).z);
				
				float mx2 = -(ent.xCoord - (float) laserToList.get(i + 1).x);
				float my2 = -(ent.yCoord - (float) laserToList.get(i + 1).y);
				float mz2 = -(ent.zCoord - (float) laserToList.get(i + 1).z);
				
				GL11.glBegin(GL11.GL_LINES);
				GL11.glVertex3f(mx1, my1, mz1);
				GL11.glVertex3f(mx2, my2, mz2);
				GL11.glEnd();
				
//				Tessellator tessellator = Tessellator.instance;
//		        tessellator.startDrawing(GL11.GL_LINES);
//		        tessellator.addVertex(mx1, my1, mz1);
//		        tessellator.addVertex(mx2,my2, mz2);
//		        tessellator.addVertex(0, 0, 0);
//		        tessellator.draw();
		        
	            GL11.glDisable(GL11.GL_BLEND);
	            GL11.glDisable(GL11.GL_LINE_SMOOTH);
				GL11.glPopMatrix();
			}
		} else {
			// System.out.println(laserToList.size());
		}
	}
}
