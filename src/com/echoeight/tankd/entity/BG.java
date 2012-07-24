package com.echoeight.tankd.entity;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.echoeight.bison.entity.BaseEntity;
import com.echoeight.bison.entity.EntityManager;
import com.echoeight.tankd.images.LoadTextures;

public class BG extends BaseEntity {
	
	Texture bg;
	EntityManager em;
	
	public BG(EntityManager em, double x, double y, double width, double height) {
        super(em, x, y, width, height);
        this.em = em;
        id = em.assignId(this);
    	bg = LoadTextures.getTexture("res/bg.png");
    }
    
    @Override
    public void draw() {
    	if(!em.getIds().contains(this.getId())){
    		return;
    	}
        bg.bind();
        GL11.glLoadIdentity();
        GL11.glTranslated(x, y, 0);
    	GL11.glBegin(GL11.GL_QUADS);
    	
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(0,0);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(bg.getTextureWidth(),0);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(bg.getTextureWidth(),bg.getTextureHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(0,bg.getTextureHeight());
		GL11.glEnd();
		GL11.glLoadIdentity();
		
    }

	@Override
	public void update(int delta) {	
	}
	
}