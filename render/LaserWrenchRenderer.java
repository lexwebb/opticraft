package opticraft.render;

import opticraft.lib.ModInfo;
import opticraft.models.LaserModel;
import opticraft.models.LaserWrenchModel;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class LaserWrenchRenderer implements IItemRenderer {
	
	protected LaserWrenchModel model;

	public LaserWrenchRenderer() {
		model = new LaserWrenchModel();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.ENTITY || type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		if(helper == ItemRendererHelper.INVENTORY_BLOCK){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			RenderBlocks blockRenderer = (RenderBlocks)data[0];
			Entity entity = (Entity)data[1]; // entity holding the item
			
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/items/LaserWrench.png"));
			
			float scale = 0.7F;
			GL11.glScalef(scale, scale, scale);
			GL11.glRotatef(90, -1, 0, 0);
			GL11.glRotatef(85, 0, 0, 1);
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glRotatef(120, 1, 0, 0);
			GL11.glTranslatef(0.4F, -0.5F, 1.5F); // Left-Right
			// Forward-Backwards Up-Down
			model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
					0.0625F);
			
			GL11.glPopMatrix();
			
		} else if(type == ItemRenderType.ENTITY){
			RenderBlocks blockRenderer = (RenderBlocks)data[0];
			//Entity entity = (Entity)data[1]; // entity holding the item
			
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/items/LaserWrench.png"));
			
			float scale = 0.7F;
			GL11.glScalef(scale, scale, scale);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glTranslatef(0.0F, -1.4F, 0.0F); // Left-Right
			// Forward-Backwards Up-Down
			model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
					0.0625F);
			
			GL11.glPopMatrix();			
		} else if(type == ItemRenderType.EQUIPPED){
			RenderBlocks blockRenderer = (RenderBlocks)data[0];
			Entity entity = (Entity)data[1]; // entity holding the item
			
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/items/LaserWrench.png"));
			
			float scale = 0.7F;
			GL11.glScalef(scale, scale, scale);
			GL11.glRotatef(80, -1, 0, 0);
			GL11.glRotatef(60, 0, 0, 1);
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glRotatef(100, 1, 0, 0);
			GL11.glTranslatef(-0.5F, -1.5F, 0.5F); // Left-Right
			// Forward-Backwards Up-Down
			model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
					0.0625F);
			
			GL11.glPopMatrix();
		} else if(type == ItemRenderType.INVENTORY){
			RenderBlocks blockRenderer = (RenderBlocks)data[0];
			//Entity entity = (Entity)data[1]; // entity holding the item
			
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/items/LaserWrench.png"));
			
			float scale = 0.8F;
			GL11.glScalef(scale, scale, scale);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(-30, 0, 0, 1);
			// Forward-Backwards Up-Down
			GL11.glTranslatef(-0.1F, -0.9F, 0.0F); // Left-Right
			model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
					0.0625F);
			
			GL11.glPopMatrix();
		}

	}

}
