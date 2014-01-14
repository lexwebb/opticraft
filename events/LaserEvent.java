package opticraft.events;

import org.lwjgl.opengl.GL11;

import opticraft.lib.Position;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.Event;

public class LaserEvent extends Event{ 
	
	Position pos1, pos2;
	
	public LaserEvent(Position pos1, Position pos2){
		this.pos1 = pos1;
		this.pos2 = pos2;
		renderLaser();
	}

	public void renderLaser(){
		double doubleX = Minecraft.getMinecraft().thePlayer.posX - 0.5;
		double doubleY = Minecraft.getMinecraft().thePlayer.posY + 0.1;
		double doubleZ = Minecraft.getMinecraft().thePlayer.posZ - 0.5;

		GL11.glPushMatrix();
		GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
		GL11.glColor3ub((byte)255,(byte)0,(byte)0);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3f((float) pos1.x,(float) pos1.y,(float) pos1.z);
		GL11.glVertex3f((float) pos2.x,(float) pos2.y,(float) pos2.z);
		GL11.glEnd();
		GL11.glPopMatrix();
		
		System.out.println("Pos1: " + pos1.toString() + " Pos2: " + pos2.toString());
	}
}
