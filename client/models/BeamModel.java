// Date: 11/11/2013 18:20:10
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package opticraft.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BeamModel extends ModelBase
{
  //fields
    public ModelRenderer LR;
    public ModelRenderer FB;
    public ModelRenderer TB;
  
  public BeamModel()
  {
    textureWidth = 40;
    textureHeight = 32;
    
      LR = new ModelRenderer(this, 0, 0);
      LR.addBox(0F, 0F, 0F, 16, 1, 1);
      LR.setRotationPoint(-8F, 15.5F, -0.5F);
      LR.setTextureSize(40, 32);
      LR.mirror = true;
      setRotation(LR, 0F, 0F, 0F);
      FB = new ModelRenderer(this, 0, 2);
      FB.addBox(0F, 0F, 0F, 1, 1, 16);
      FB.setRotationPoint(-0.5F, 15.5F, -8F);
      FB.setTextureSize(40, 32);
      FB.mirror = true;
      setRotation(FB, 0F, 0F, 0F);
      TB = new ModelRenderer(this, 34, 2);
      TB.addBox(0F, 0F, 0F, 1, 16, 1);
      TB.setRotationPoint(-0.5F, 8F, -0.5F);
      TB.setTextureSize(40, 32);
      TB.mirror = true;
      setRotation(TB, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    GL11.glEnable(GL11.GL_BLEND);
	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    LR.render(f5);
    FB.render(f5);
    TB.render(f5);
    GL11.glDisable(GL11.GL_BLEND);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
