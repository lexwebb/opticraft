package opticraft.items;

import opticraft.lib.ModInfo;
import opticraft.models.ItemLaserModel;
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
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if (type == ItemRenderType.EQUIPPED) {
			RenderBlocks blockRenderer = (RenderBlocks)data[0];
			EntityLiving entity = (EntityLiving)data[1]; // entity holding the item

			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/items/LaserWrench.png"));

			float scale = 1.4F;
			GL11.glScalef(scale, scale, scale);
			GL11.glRotatef(90, -1, 0, 0);
			GL11.glRotatef(85, 0, 0, 1);
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glRotatef(135, 1, 0, 0);
			GL11.glTranslatef(-0.1F, -0.5F, 0.5F); // Left-Right
			// Forward-Backwards Up-Down
			model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
					0.0625F);

			GL11.glPopMatrix();
		}
	}

}
