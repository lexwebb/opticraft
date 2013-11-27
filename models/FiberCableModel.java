// Date: 26/11/2013 23:14:58
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package opticraft.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class FiberCableModel extends ModelBase
{
  //fields
    ModelRenderer Center;
    ModelRenderer CenterInner;
    ModelRenderer Top;
    ModelRenderer TopInner;
    ModelRenderer Bottom;
    ModelRenderer BottomInner;
    ModelRenderer Front;
    ModelRenderer FrontInner;
    ModelRenderer Back;
    ModelRenderer BackInner;
    ModelRenderer Left;
    ModelRenderer LeftInner;
    ModelRenderer Right;
    ModelRenderer RightInner;
  
  public FiberCableModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Center = new ModelRenderer(this, 8, 17);
      Center.addBox(0F, 0F, 0F, 2, 2, 2);
      Center.setRotationPoint(-1F, 15F, -1F);
      Center.setTextureSize(64, 32);
      Center.mirror = true;
      setRotation(Center, 0F, 0F, 0F);
      CenterInner = new ModelRenderer(this, 8, 21);
      CenterInner.addBox(0F, 0F, 0F, 1, 1, 1);
      CenterInner.setRotationPoint(-0.5F, 15.5F, -0.5F);
      CenterInner.setTextureSize(64, 32);
      CenterInner.mirror = true;
      setRotation(CenterInner, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 8);
      Top.addBox(0F, 0F, 0F, 2, 7, 2);
      Top.setRotationPoint(-1F, 8F, -1F);
      Top.setTextureSize(64, 32);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
      TopInner = new ModelRenderer(this, 4, 17);
      TopInner.addBox(0F, 0F, 0F, 1, 7, 1);
      TopInner.setRotationPoint(-0.5F, 8F, -0.5F);
      TopInner.setTextureSize(64, 32);
      TopInner.mirror = true;
      setRotation(TopInner, 0F, 0F, 0F);
      Bottom = new ModelRenderer(this, 8, 8);
      Bottom.addBox(0F, 0F, 0F, 2, 7, 2);
      Bottom.setRotationPoint(-1F, 17F, -1F);
      Bottom.setTextureSize(64, 32);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, 0F);
      BottomInner = new ModelRenderer(this, 0, 17);
      BottomInner.addBox(0F, 0F, 0F, 1, 7, 1);
      BottomInner.setRotationPoint(-0.5F, 17F, -0.5F);
      BottomInner.setTextureSize(64, 32);
      BottomInner.mirror = true;
      setRotation(BottomInner, 0F, 0F, 0F);
      Front = new ModelRenderer(this, 16, 0);
      Front.addBox(0F, 0F, 0F, 2, 2, 7);
      Front.setRotationPoint(-1F, 15F, -8F);
      Front.setTextureSize(64, 32);
      Front.mirror = true;
      setRotation(Front, 0F, 0F, 0F);
      FrontInner = new ModelRenderer(this, 0, 0);
      FrontInner.addBox(0F, 0F, 0F, 1, 1, 7);
      FrontInner.setRotationPoint(-0.5F, 15.5F, -8F);
      FrontInner.setTextureSize(64, 32);
      FrontInner.mirror = true;
      setRotation(FrontInner, 0F, 0F, 0F);
      Back = new ModelRenderer(this, 16, 9);
      Back.addBox(0F, 0F, 0F, 2, 2, 7);
      Back.setRotationPoint(-1F, 15F, 1F);
      Back.setTextureSize(64, 32);
      Back.mirror = true;
      setRotation(Back, 0F, 0F, 0F);
      BackInner = new ModelRenderer(this, 34, 0);
      BackInner.addBox(0F, 0F, 0F, 1, 1, 7);
      BackInner.setRotationPoint(-0.5F, 15.5F, 1F);
      BackInner.setTextureSize(64, 32);
      BackInner.mirror = true;
      setRotation(BackInner, 0F, 0F, 0F);
      Left = new ModelRenderer(this, 34, 8);
      Left.addBox(0F, 0F, 0F, 7, 2, 2);
      Left.setRotationPoint(1F, 15F, -1F);
      Left.setTextureSize(64, 32);
      Left.mirror = true;
      setRotation(Left, 0F, 0F, 0F);
      LeftInner = new ModelRenderer(this, 18, 18);
      LeftInner.addBox(0F, 0F, 0F, 7, 1, 1);
      LeftInner.setRotationPoint(1F, 15.5F, -0.5F);
      LeftInner.setTextureSize(64, 32);
      LeftInner.mirror = true;
      setRotation(LeftInner, 0F, 0F, 0F);
      Right = new ModelRenderer(this, 34, 12);
      Right.addBox(0F, 0F, 0F, 7, 2, 2);
      Right.setRotationPoint(-8F, 15F, -1F);
      Right.setTextureSize(64, 32);
      Right.mirror = true;
      setRotation(Right, 0F, 0F, 0F);
      RightInner = new ModelRenderer(this, 18, 20);
      RightInner.addBox(0F, 0F, 0F, 7, 1, 1);
      RightInner.setRotationPoint(-8F, 15.5F, -0.5F);
      RightInner.setTextureSize(64, 32);
      RightInner.mirror = true;
      setRotation(RightInner, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Center.render(f5);
    CenterInner.render(f5);
    Top.render(f5);
    TopInner.render(f5);
    Bottom.render(f5);
    BottomInner.render(f5);
    Front.render(f5);
    FrontInner.render(f5);
    Back.render(f5);
    BackInner.render(f5);
    Left.render(f5);
    LeftInner.render(f5);
    Right.render(f5);
    RightInner.render(f5);
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
