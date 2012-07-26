package com.echoeight.tankd.entity;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.echoeight.bison.entity.BaseEntity;
import com.echoeight.bison.entity.EntityManager;
import com.echoeight.tankd.images.LoadTextures;

public class Wall extends BaseEntity {
	
	Texture wall;
	EntityManager em;
	
	public Wall(EntityManager em, double x, double y, double width, double height) {
        super(em, x, y, width, height);
        this.em = em;
        this.wall = LoadTextures.getTexture("res/wall.png");
        id = em.assignId(this);
    }
    
    @Override
    public void draw() {
    	if(!em.getIds().contains(this.getId())){
    		return;
    	}
        wall.bind();
        GL11.glLoadIdentity();
        GL11.glTranslated(x, y, 0);
        
        GL11.glBegin(GL11.GL_TRIANGLES); 

    	GL11.glTexCoord2f(0.5f, 0.0f); 
	    GL11.glVertex2d(-20/2, -20/2); 
	    	GL11.glTexCoord2f(0.5f, 0.5f);
	    GL11.glVertex2d(20/2, -20/2);
	    	GL11.glTexCoord2f(0.25f, 0.25f);
	    GL11.glVertex2i(0, 0);
	
	    	GL11.glTexCoord2f(0.5f, 0.5f); 
	    GL11.glVertex2d(20/2, -20/2); 
	    	GL11.glTexCoord2f(0.0f, 0.5f); 
	    GL11.glVertex2d(20/2, 20/2); 
	    	GL11.glTexCoord2f(0.25f, 0.25f); 
	    GL11.glVertex2i(0, 0); 
	    
	    	GL11.glTexCoord2f(0.0f, 0.5f); 
	    GL11.glVertex2d(20/2, 20/2); 
	    	GL11.glTexCoord2f(0.0f, 0.0f); 
	    GL11.glVertex2d(-20/2, 20/2); 
	    	GL11.glTexCoord2f(0.25f, 0.25f); 
	    GL11.glVertex2i(0, 0); 
	
	    	GL11.glTexCoord2f(0.0f, 0.0f); 
	    GL11.glVertex2d(-20/2, 20/2); 
	    	GL11.glTexCoord2f(0.5f, 0.0f); 
	    GL11.glVertex2d(-20/2, -20/2); 
	    	GL11.glTexCoord2f(0.25f, 0.25f); 
	    GL11.glVertex2i(0, 0); 
        
		GL11.glEnd();
		GL11.glLoadIdentity();
		
    }

	@Override
	public void update(int delta) {
	}
	
}