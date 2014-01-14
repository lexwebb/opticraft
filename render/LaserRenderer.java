package opticraft.render;

import java.util.List;

import opticraft.entitys.EntityBeam;
import opticraft.entitys.TileEntityLaser;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import opticraft.lib.Position;
import opticraft.models.LaserModel;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class LaserRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	private final LaserModel model;
	protected int renderID;
    
    public LaserRenderer(int renderID) {
            this.model = new LaserModel();
            this.renderID = renderID;
    }
    
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            GL11.glPushMatrix();
            GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
            GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
    
    		TileEntityLaser ent = (TileEntityLaser) te;
    		ForgeDirection or = ent.getOrientation();
    		
    //The PushMatrix tells the renderer to "start" doing something.    
            GL11.glPushMatrix();
    //This is setting the initial location.
            
            if(or == ForgeDirection.UP)
            	GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            else if(or == ForgeDirection.DOWN)
            	GL11.glTranslatef((float) x + 0.5F, (float) y - 0.5F, (float) z + 0.5F);
            else if(or == ForgeDirection.EAST)
            	GL11.glTranslatef((float) x + 1.5F, (float) y + 0.5F, (float) z + 0.5F);
            else if(or == ForgeDirection.WEST)
            	GL11.glTranslatef((float) x - 0.5F, (float) y + 0.5F, (float) z + 0.5F);
            else if(or == ForgeDirection.NORTH)
            	GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z - 0.5F);
            else if(or == ForgeDirection.SOUTH)
            	GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 1.5F);
            
            
    //This is the texture of your block. It's pathed to be the same place as your other blocks here.
            //Outdated bindTextureByName("/mods/roads/textures/blocks/TrafficLightPoleRed.png");
   //Use in 1.6.2  this
            ResourceLocation textures = (new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/blocks/itemLaserTile.png")); 
    //the ':' is very important
    //binding the textures
            Minecraft.getMinecraft().renderEngine.bindTexture(textures);

    //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
            GL11.glPushMatrix();
            
            
            if(or == ForgeDirection.UP)
            	GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            else if(or == ForgeDirection.DOWN)
            	GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            else if(or == ForgeDirection.EAST)
            	GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
            else if(or == ForgeDirection.WEST)
            	GL11.glRotatef(270F, 0.0F, 0.0F, 1.0F);
            else if(or == ForgeDirection.NORTH)
            	GL11.glRotatef(90F, 90F, 0.00F, 1.0F);
            else if(or == ForgeDirection.SOUTH)
            	GL11.glRotatef(270F, 90F, 0.00F, 1.0F);
            
    //A reference to your Model file. Again, very important.
            this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    //Tell it to stop rendering for both the PushMatrix's
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            
//            List<Position> laserToList = ent.laserToList;
//    		if(laserToList != null && laserToList.size() > 1){
////    			System.out.println("LOL");
//    			for(int i = 0; i < laserToList.size() - 1; i++){
//    				System.out.println(laserToList.get(i).toString());
//    				System.out.println(laserToList.get(i + 1).toString());
//    				GL11.glPushMatrix();
//    				GL11.glTranslated(laserToList.get(i).x, laserToList.get(i).y, laserToList.get(i).z);
//    				GL11.glColor3ub((byte)255,(byte)0,(byte)0);
//    				float mx = (float) laserToList.get(i + 1).x;
//    				float my = (float) laserToList.get(i + 1).y;
//    				float mz = (float) laserToList.get(i + 1).z;
//    				GL11.glBegin(GL11.GL_LINES);
//    				GL11.glVertex3f(mx,my,mz);
//    				GL11.glEnd();
//    				GL11.glPopMatrix();
//    			}	
//    		} else {
////    			System.out.println(laserToList.size());
//    		}
    }
    
    

    //Set the lighting stuff, so it changes it's brightness properly.       
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
            Tessellator tess = Tessellator.instance;
            float brightness = block.getBlockBrightness(world, i, j, k);
            int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
            int modulousModifier = skyLight % 65536;
            int divModifier = skyLight / 65536;
            tess.setColorOpaque_F(brightness, brightness, brightness);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  (float) modulousModifier,  divModifier);
    }

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
		GL11.glPushMatrix();
		
		GL11.glTranslatef(0.0f, 1.2f, 0.0f);
		
		ResourceLocation textures = (new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/blocks/itemLaserTile.png")); 
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
                   
     	GL11.glPushMatrix();

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		return this.renderID;
	}
	
}
