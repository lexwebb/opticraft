// Date: 28/11/2013 20:04:23
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package opticraft.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LuxCapacitorModel extends ModelBase
{
  //fields
    ModelRenderer TopLeft;
    ModelRenderer TopRight;
    ModelRenderer TopFront;
    ModelRenderer TopBack;
    ModelRenderer FrontLeft;
    ModelRenderer BackRight;
    ModelRenderer BackLeft;
    ModelRenderer FrontRight;
    ModelRenderer BottomLeft;
    ModelRenderer BottomRight;
    ModelRenderer BottomFront;
    ModelRenderer BottomBack;
    ModelRenderer LeftFlat;
    ModelRenderer LeftVert;
    ModelRenderer RightFlat;
    ModelRenderer RightVert;
    ModelRenderer FrontFlat;
    ModelRenderer FrontVert;
    ModelRenderer BackFlat;
    ModelRenderer BackVert;
    ModelRenderer TopOne;
    ModelRenderer TopTwo;
    ModelRenderer BottomOne;
    ModelRenderer BottomTwo;
    ModelRenderer FrontPipe;
    ModelRenderer BackPipe;
    ModelRenderer LeftPipe;
    ModelRenderer RightPipe;
    ModelRenderer TopPipe;
    ModelRenderer BottomPipe;
    ModelRenderer Shape1;
  
  public LuxCapacitorModel()
  {
    textureWidth = 68;
    textureHeight = 32;
    
      TopLeft = new ModelRenderer(this, 0, 0);
      TopLeft.addBox(0F, 0F, 0F, 3, 3, 16);
      TopLeft.setRotationPoint(5F, 8F, -8F);
      TopLeft.setTextureSize(68, 32);
      TopLeft.mirror = true;
      setRotation(TopLeft, 0F, 0F, 0F);
      TopRight = new ModelRenderer(this, 0, 0);
      TopRight.addBox(0F, 0F, 0F, 3, 3, 16);
      TopRight.setRotationPoint(-8F, 8F, -8F);
      TopRight.setTextureSize(68, 32);
      TopRight.mirror = true;
      setRotation(TopRight, 0F, 0F, 0F);
      TopFront = new ModelRenderer(this, 38, 0);
      TopFront.addBox(0F, 0F, 0F, 10, 3, 3);
      TopFront.setRotationPoint(-5F, 8F, -8F);
      TopFront.setTextureSize(68, 32);
      TopFront.mirror = true;
      setRotation(TopFront, 0F, 0F, 0F);
      TopBack = new ModelRenderer(this, 38, 0);
      TopBack.addBox(0F, 0F, 0F, 10, 3, 3);
      TopBack.setRotationPoint(-5F, 8F, 5F);
      TopBack.setTextureSize(68, 32);
      TopBack.mirror = true;
      setRotation(TopBack, 0F, 0F, 0F);
      FrontLeft = new ModelRenderer(this, 0, 0);
      FrontLeft.addBox(0F, 0F, 0F, 3, 10, 3);
      FrontLeft.setRotationPoint(5F, 11F, -8F);
      FrontLeft.setTextureSize(68, 32);
      FrontLeft.mirror = true;
      setRotation(FrontLeft, 0F, 0F, 0F);
      BackRight = new ModelRenderer(this, 0, 0);
      BackRight.addBox(0F, 0F, 0F, 3, 10, 3);
      BackRight.setRotationPoint(-8F, 11F, 5F);
      BackRight.setTextureSize(68, 32);
      BackRight.mirror = true;
      setRotation(BackRight, 0F, 0F, 0F);
      BackLeft = new ModelRenderer(this, 0, 0);
      BackLeft.addBox(0F, 0F, 0F, 3, 10, 3);
      BackLeft.setRotationPoint(5F, 11F, 5F);
      BackLeft.setTextureSize(68, 32);
      BackLeft.mirror = true;
      setRotation(BackLeft, 0F, 0F, 0F);
      FrontRight = new ModelRenderer(this, 0, 0);
      FrontRight.addBox(0F, 0F, 0F, 3, 10, 3);
      FrontRight.setRotationPoint(-8F, 11F, -8F);
      FrontRight.setTextureSize(68, 32);
      FrontRight.mirror = true;
      setRotation(FrontRight, 0F, 0F, 0F);
      BottomLeft = new ModelRenderer(this, 0, 0);
      BottomLeft.addBox(0F, 0F, 0F, 3, 3, 16);
      BottomLeft.setRotationPoint(5F, 21F, -8F);
      BottomLeft.setTextureSize(68, 32);
      BottomLeft.mirror = true;
      setRotation(BottomLeft, 0F, 0F, 0F);
      BottomRight = new ModelRenderer(this, 0, 0);
      BottomRight.addBox(0F, 0F, 0F, 3, 3, 16);
      BottomRight.setRotationPoint(-8F, 21F, -8F);
      BottomRight.setTextureSize(68, 32);
      BottomRight.mirror = true;
      setRotation(BottomRight, 0F, 0F, 0F);
      BottomFront = new ModelRenderer(this, 38, 0);
      BottomFront.addBox(0F, 0F, 0F, 10, 3, 3);
      BottomFront.setRotationPoint(-5F, 21F, -8F);
      BottomFront.setTextureSize(68, 32);
      BottomFront.mirror = true;
      setRotation(BottomFront, 0F, 0F, 0F);
      BottomBack = new ModelRenderer(this, 38, 0);
      BottomBack.addBox(0F, 0F, 0F, 10, 3, 3);
      BottomBack.setRotationPoint(-5F, 21F, 5F);
      BottomBack.setTextureSize(68, 32);
      BottomBack.mirror = true;
      setRotation(BottomBack, 0F, 0F, 0F);
      LeftFlat = new ModelRenderer(this, 0, 21);
      LeftFlat.addBox(0F, 0F, 0F, 1, 1, 10);
      LeftFlat.setRotationPoint(6F, 15.5F, -5F);
      LeftFlat.setTextureSize(68, 32);
      LeftFlat.mirror = true;
      setRotation(LeftFlat, 0F, 0F, 0F);
      LeftVert = new ModelRenderer(this, 28, 0);
      LeftVert.addBox(0F, 0F, 0F, 1, 10, 1);
      LeftVert.setRotationPoint(6F, 11F, -0.5F);
      LeftVert.setTextureSize(68, 32);
      LeftVert.mirror = true;
      setRotation(LeftVert, 0F, 0F, 0F);
      RightFlat = new ModelRenderer(this, 0, 21);
      RightFlat.addBox(0F, 0F, 0F, 1, 1, 10);
      RightFlat.setRotationPoint(-7F, 15.5F, -5F);
      RightFlat.setTextureSize(68, 32);
      RightFlat.mirror = true;
      setRotation(RightFlat, 0F, 0F, 0F);
      RightVert = new ModelRenderer(this, 28, 0);
      RightVert.addBox(0F, 0F, 0F, 1, 10, 1);
      RightVert.setRotationPoint(-7F, 11F, -0.5F);
      RightVert.setTextureSize(68, 32);
      RightVert.mirror = true;
      setRotation(RightVert, 0F, 0F, 0F);
      FrontFlat = new ModelRenderer(this, 0, 19);
      FrontFlat.addBox(0F, 0F, 0F, 10, 1, 1);
      FrontFlat.setRotationPoint(-5F, 15.5F, -7F);
      FrontFlat.setTextureSize(68, 32);
      FrontFlat.mirror = true;
      setRotation(FrontFlat, 0F, 0F, 0F);
      FrontVert = new ModelRenderer(this, 28, 0);
      FrontVert.addBox(0F, 0F, 0F, 1, 10, 1);
      FrontVert.setRotationPoint(-0.5F, 11F, -7F);
      FrontVert.setTextureSize(68, 32);
      FrontVert.mirror = true;
      setRotation(FrontVert, 0F, 0F, 0F);
      BackFlat = new ModelRenderer(this, 0, 19);
      BackFlat.addBox(0F, 0F, 0F, 10, 1, 1);
      BackFlat.setRotationPoint(-5F, 15.5F, 6F);
      BackFlat.setTextureSize(68, 32);
      BackFlat.mirror = true;
      setRotation(BackFlat, 0F, 0F, 0F);
      BackVert = new ModelRenderer(this, 28, 0);
      BackVert.addBox(0F, 0F, 0F, 1, 10, 1);
      BackVert.setRotationPoint(-0.5F, 11F, 6F);
      BackVert.setTextureSize(68, 32);
      BackVert.mirror = true;
      setRotation(BackVert, 0F, 0F, 0F);
      TopOne = new ModelRenderer(this, 0, 21);
      TopOne.addBox(0F, 0F, 0F, 1, 1, 10);
      TopOne.setRotationPoint(-0.5F, 9F, -5F);
      TopOne.setTextureSize(68, 32);
      TopOne.mirror = true;
      setRotation(TopOne, 0F, 0F, 0F);
      TopTwo = new ModelRenderer(this, 0, 19);
      TopTwo.addBox(0F, 0F, 0F, 10, 1, 1);
      TopTwo.setRotationPoint(-5F, 9F, -0.5F);
      TopTwo.setTextureSize(68, 32);
      TopTwo.mirror = true;
      setRotation(TopTwo, 0F, 0F, 0F);
      BottomOne = new ModelRenderer(this, 0, 21);
      BottomOne.addBox(0F, 0F, 0F, 1, 1, 10);
      BottomOne.setRotationPoint(-0.5F, 22F, -5F);
      BottomOne.setTextureSize(68, 32);
      BottomOne.mirror = true;
      setRotation(BottomOne, 0F, 0F, 0F);
      BottomTwo = new ModelRenderer(this, 0, 19);
      BottomTwo.addBox(0F, 0F, 0F, 10, 1, 1);
      BottomTwo.setRotationPoint(-5F, 22F, -0.5F);
      BottomTwo.setTextureSize(68, 32);
      BottomTwo.mirror = true;
      setRotation(BottomTwo, 0F, 0F, 0F);
      FrontPipe = new ModelRenderer(this, 16, 22);
      FrontPipe.addBox(0F, 0F, 0F, 2, 2, 1);
      FrontPipe.setRotationPoint(-0.9333333F, 15F, -8F);
      FrontPipe.setTextureSize(68, 32);
      FrontPipe.mirror = true;
      setRotation(FrontPipe, 0F, 0F, 0F);
      BackPipe = new ModelRenderer(this, 16, 22);
      BackPipe.addBox(0F, 0F, 0F, 2, 2, 1);
      BackPipe.setRotationPoint(-0.9333333F, 15F, 7F);
      BackPipe.setTextureSize(68, 32);
      BackPipe.mirror = true;
      setRotation(BackPipe, 0F, 0F, 0F);
      LeftPipe = new ModelRenderer(this, 0, 26);
      LeftPipe.addBox(0F, 0F, 0F, 1, 2, 2);
      LeftPipe.setRotationPoint(7F, 15F, -1F);
      LeftPipe.setTextureSize(68, 32);
      LeftPipe.mirror = true;
      setRotation(LeftPipe, 0F, 0F, 0F);
      RightPipe = new ModelRenderer(this, 0, 26);
      RightPipe.addBox(0F, 0F, 0F, 1, 2, 2);
      RightPipe.setRotationPoint(-8F, 15F, -1F);
      RightPipe.setTextureSize(68, 32);
      RightPipe.mirror = true;
      setRotation(RightPipe, 0F, 0F, 0F);
      TopPipe = new ModelRenderer(this, 0, 22);
      TopPipe.addBox(0F, 0F, 0F, 2, 1, 2);
      TopPipe.setRotationPoint(-1F, 8F, -1F);
      TopPipe.setTextureSize(68, 32);
      TopPipe.mirror = true;
      setRotation(TopPipe, 0F, 0F, 0F);
      BottomPipe = new ModelRenderer(this, 0, 22);
      BottomPipe.addBox(0F, 0F, 0F, 2, 1, 2);
      BottomPipe.setRotationPoint(-1F, 23F, -1F);
      BottomPipe.setTextureSize(68, 32);
      BottomPipe.mirror = true;
      setRotation(BottomPipe, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 28, 12);
      Shape1.addBox(0F, 0F, 0F, 10, 10, 10);
      Shape1.setRotationPoint(-5F, 11F, -5F);
      Shape1.setTextureSize(68, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    TopLeft.render(f5);
    TopRight.render(f5);
    TopFront.render(f5);
    TopBack.render(f5);
    FrontLeft.render(f5);
    BackRight.render(f5);
    BackLeft.render(f5);
    FrontRight.render(f5);
    BottomLeft.render(f5);
    BottomRight.render(f5);
    BottomFront.render(f5);
    BottomBack.render(f5);
    LeftFlat.render(f5);
    LeftVert.render(f5);
    RightFlat.render(f5);
    RightVert.render(f5);
    FrontFlat.render(f5);
    FrontVert.render(f5);
    BackFlat.render(f5);
    BackVert.render(f5);
    TopOne.render(f5);
    TopTwo.render(f5);
    BottomOne.render(f5);
    BottomTwo.render(f5);
    FrontPipe.render(f5);
    BackPipe.render(f5);
    LeftPipe.render(f5);
    RightPipe.render(f5);
    TopPipe.render(f5);
    BottomPipe.render(f5); 
  }
  
  public void renderCenter(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
	    Shape1.render(f5);
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
