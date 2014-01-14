package opticraft.events;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class LaserEventHandler {

	@ForgeSubscribe
	public void renderWorldLastEvent(RenderWorldLastEvent evt)
	{
	double doubleX = Minecraft.getMinecraft().thePlayer.posX - 0.5;
	double doubleY = Minecraft.getMinecraft().thePlayer.posY + 0.1;
	double doubleZ = Minecraft.getMinecraft().thePlayer.posZ - 0.5;

	GL11.glPushMatrix();
	GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
	GL11.glColor3ub((byte)255,(byte)0,(byte)0);
	float mx = 9;
	float my = 9;
	float mz = 9;
	GL11.glBegin(GL11.GL_LINES);
	GL11.glVertex3f(mx+0.4f,my,mz+0.4f);
	GL11.glVertex3f(mx-0.4f,my,mz-0.4f);
	GL11.glVertex3f(mx+0.4f,my,mz-0.4f);
	GL11.glVertex3f(mx-0.4f,my,mz+0.4f);
	GL11.glEnd();
	GL11.glPopMatrix();
	}
}
