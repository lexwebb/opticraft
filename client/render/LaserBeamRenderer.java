package opticraft.client.render;

import opticraft.entitys.EntityBeam;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LaserBeamRenderer extends Render{

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float f, float f1) {
		
		System.out.println("Test2");
		
		EntityBeam ent = (EntityBeam) entity;
		double doubleX = ent.start.x;
		double doubleY = ent.start.y;
		double doubleZ = ent.start.z;
		
//		double doubleX = Minecraft.getMinecraft().thePlayer.posX - 0.5;
//		double doubleY = Minecraft.getMinecraft().thePlayer.posY + 0.1;
//		double doubleZ = Minecraft.getMinecraft().thePlayer.posZ - 0.5;

		GL11.glPushMatrix();
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT,  GL11.GL_NICEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
//		GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
		
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		GL11.glColor3ub((byte)255,(byte)0,(byte)0);
		
		double xd = ent.start.x- Minecraft.getMinecraft().thePlayer.posX;
		double yd = ent.start.y- Minecraft.getMinecraft().thePlayer.posY;
		double zd = ent.start.z- Minecraft.getMinecraft().thePlayer.posZ;
		
		double dist = Math.sqrt(xd*xd + yd*yd + zd*zd);
		if(dist < 0) //dirty hack
			dist = dist * -1;		
		double modDist = dist - 4;
		
		if((10.0F - modDist / 2) > 1)
		GL11.glLineWidth((float) (10.0F - modDist / 2));
		else
			GL11.glLineWidth(1.0f);
		
		float mx1 = (float) -(ent.start.x - (float) ent.start.x);
		float my1 = (float) -(ent.start.y - (float) ent.start.y);
		float mz1 = (float) -(ent.start.z - (float) ent.start.z);
		
		float mx2 = (float) -(ent.start.x - (float) ent.start.x);
		float my2 = (float) -(ent.start.y- (float) ent.start.y);
		float mz2 = (float) -(ent.start.z - (float) ent.start.z);
		
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3f(mx1, my1, mz1);
		GL11.glVertex3f(mx2, my2, mz2);
		GL11.glEnd();
		
//		Tessellator tessellator = Tessellator.instance;
//        tessellator.startDrawing(GL11.GL_LINES);
//        tessellator.addVertex(mx1, my1, mz1);
//        tessellator.addVertex(mx2,my2, mz2);
//        tessellator.addVertex(0, 0, 0);
//        tessellator.draw();
        
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glPopMatrix();
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
