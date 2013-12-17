// Date: 12/12/2013 19:23:07
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package opticraft.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MirrorModel extends ModelBase
{
  //fields
    public ModelRenderer Down;
    public ModelRenderer DownBlock;
    public ModelRenderer Up;
    public ModelRenderer UpBlock;
    public ModelRenderer Back;
    public ModelRenderer BackBlock;
    public ModelRenderer Front;
    public ModelRenderer FrontBlock;
    public ModelRenderer Left;
    public ModelRenderer LeftBlock;
    public ModelRenderer Right;
    public ModelRenderer RightBlock;
    ModelRenderer MirrorBlock;
    public ModelRenderer Mirror;
  
  public MirrorModel()
  {
    textureWidth = 30;
    textureHeight = 32;
    
      Down = new ModelRenderer(this, 0, 11);
      Down.addBox(0F, 0F, 0F, 1, 8, 1);
      Down.setRotationPoint(-0.5F, 16F, -0.5F);
      Down.setTextureSize(30, 32);
      Down.mirror = true;
      setRotation(Down, 0F, 0F, 0F);
      DownBlock = new ModelRenderer(this, 0, 0);
      DownBlock.addBox(0F, 0F, 0F, 2, 1, 2);
      DownBlock.setRotationPoint(-1F, 23F, -1F);
      DownBlock.setTextureSize(30, 32);
      DownBlock.mirror = true;
      setRotation(DownBlock, 0F, 0F, 0F);
      Up = new ModelRenderer(this, 0, 11);
      Up.addBox(0F, 0F, 0F, 1, 8, 1);
      Up.setRotationPoint(-0.5F, 8F, -0.5F);
      Up.setTextureSize(30, 32);
      Up.mirror = true;
      setRotation(Up, 0F, 0F, 0F);
      UpBlock = new ModelRenderer(this, 0, 0);
      UpBlock.addBox(0F, 0F, 0F, 2, 1, 2);
      UpBlock.setRotationPoint(-1F, 8F, -1F);
      UpBlock.setTextureSize(30, 32);
      UpBlock.mirror = true;
      setRotation(UpBlock, 0F, 0F, 0F);
      Back = new ModelRenderer(this, 0, 0);
      Back.addBox(0F, 0F, 0F, 1, 1, 8);
      Back.setRotationPoint(-0.5F, 15.5F, 0F);
      Back.setTextureSize(30, 32);
      Back.mirror = true;
      setRotation(Back, 0F, 0F, 0F);
      BackBlock = new ModelRenderer(this, 18, 0);
      BackBlock.addBox(0F, 0F, 0F, 2, 2, 1);
      BackBlock.setRotationPoint(-1F, 15F, 7F);
      BackBlock.setTextureSize(30, 32);
      BackBlock.mirror = true;
      setRotation(BackBlock, 0F, 0F, 0F);
      Front = new ModelRenderer(this, 0, 0);
      Front.addBox(0F, 0F, 0F, 1, 1, 8);
      Front.setRotationPoint(-0.5F, 15.5F, -8F);
      Front.setTextureSize(30, 32);
      Front.mirror = true;
      setRotation(Front, 0F, 0F, 0F);
      FrontBlock = new ModelRenderer(this, 18, 0);
      FrontBlock.addBox(0F, 0F, 0F, 2, 2, 1);
      FrontBlock.setRotationPoint(-1F, 15F, -8F);
      FrontBlock.setTextureSize(30, 32);
      FrontBlock.mirror = true;
      setRotation(FrontBlock, 0F, 0F, 0F);
      Left = new ModelRenderer(this, 0, 9);
      Left.addBox(0F, 0F, 0F, 8, 1, 1);
      Left.setRotationPoint(0F, 15.5F, -0.5F);
      Left.setTextureSize(30, 32);
      Left.mirror = true;
      setRotation(Left, 0F, 0F, 0F);
      LeftBlock = new ModelRenderer(this, 24, 0);
      LeftBlock.addBox(0F, 0F, 0F, 1, 2, 2);
      LeftBlock.setRotationPoint(7F, 15F, -1F);
      LeftBlock.setTextureSize(30, 32);
      LeftBlock.mirror = true;
      setRotation(LeftBlock, 0F, 0F, 0F);
      Right = new ModelRenderer(this, 0, 9);
      Right.addBox(0F, 0F, 0F, 8, 1, 1);
      Right.setRotationPoint(-8F, 15.5F, -0.5333334F);
      Right.setTextureSize(30, 32);
      Right.mirror = true;
      setRotation(Right, 0F, 0F, 0F);
      RightBlock = new ModelRenderer(this, 24, 0);
      RightBlock.addBox(0F, 0F, 0F, 1, 2, 2);
      RightBlock.setRotationPoint(-8F, 15F, -1F);
      RightBlock.setTextureSize(30, 32);
      RightBlock.mirror = true;
      setRotation(RightBlock, 0F, 0F, 0F);
      MirrorBlock = new ModelRenderer(this, 18, 3);
      MirrorBlock.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
      MirrorBlock.setRotationPoint(0F, 16F, 0F);
      MirrorBlock.setTextureSize(30, 32);
      MirrorBlock.mirror = true;
      setRotation(MirrorBlock, -0.7853982F, 0F, 0F);
      Mirror = new ModelRenderer(this, 18, 9);
      Mirror.addBox(-1.5F, -2.5F, -0.5F, 3, 5, 0);
      Mirror.setRotationPoint(0F, 16F, 0F);
      Mirror.setTextureSize(30, 32);
      Mirror.mirror = true;
      setRotation(Mirror, -0.7853982F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Down.render(f5);
    DownBlock.render(f5);
    Up.render(f5);
    UpBlock.render(f5);
    Back.render(f5);
    BackBlock.render(f5);
    Front.render(f5);
    FrontBlock.render(f5);
    Left.render(f5);
    LeftBlock.render(f5);
    Right.render(f5);
    RightBlock.render(f5);
    MirrorBlock.render(f5);
    Mirror.render(f5);
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
