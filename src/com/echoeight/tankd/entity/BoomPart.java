package com.echoeight.tankd.entity;

import java.util.Random;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.echoeight.bison.entity.BaseMoveableEntity;
import com.echoeight.bison.entity.EntityManager;
import com.echoeight.tankd.images.LoadTextures;
	
public class BoomPart extends BaseMoveableEntity {
		
	EntityManager em;
	Random ran = new Random();
	long initial;
	float angle;
	Texture boom;
	
        public BoomPart(EntityManager em, double x, double y) {
            super(em, x, y, 1, 1);          
            this.em = em;
            id = em.assignId(this);
            this.initial = Sys.getTime() / Sys.getTimerResolution();
            this.angle = getMouseAngle();         
            dx = .05 * ran.nextDouble();
            dy = .05 * ran.nextDouble();
            this.boom = LoadTextures.getTexture("res/boom.png");
        }
        
        @Override
        public void draw() {
        	if(!em.getIds().contains(this.getId())){
        		return;
        	}
        	boom.bind();
        	GL11.glLoadIdentity();
        	GL11.glTranslated(x, y, 0);
            
            
        	GL11.glBegin(GL11.GL_TRIANGLES); 

	        	GL11.glTexCoord2f(0.5f, 0.0f); 
	        GL11.glVertex2d(-3/1, -3/1); 
	        	GL11.glTexCoord2f(0.5f, 0.5f); 
	        GL11.glVertex2d(3/1, -3/1); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 
	
	        	GL11.glTexCoord2f(0.5f, 0.5f); 
	        GL11.glVertex2d(3/1, -3/1); 
	        	GL11.glTexCoord2f(0.0f, 0.5f); 
	        GL11.glVertex2d(3/1, 3/1); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 
	        
	        	GL11.glTexCoord2f(0.0f, 0.5f); 
	        GL11.glVertex2d(3/1, 3/1); 
	        	GL11.glTexCoord2f(0.0f, 0.0f); 
	        GL11.glVertex2d(-3/1, 3/1); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 
	
	        	GL11.glTexCoord2f(0.0f, 0.0f); 
	        GL11.glVertex2d(-3/1, 3/1); 
	        	GL11.glTexCoord2f(0.5f, 0.0f); 
	        GL11.glVertex2d(-3/1, -3/1); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 

        GL11.glEnd();
        			
        }
        
        @Override
        public void update(int delta) {
                this.x += delta * dx;
                this.y += delta * dy;
        }
}