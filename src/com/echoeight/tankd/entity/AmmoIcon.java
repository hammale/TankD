package com.echoeight.tankd.entity;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.echoeight.bison.entity.BaseMoveableEntity;
import com.echoeight.bison.entity.EntityManager;
import com.echoeight.tankd.images.LoadTextures;
	
public class AmmoIcon extends BaseMoveableEntity {
		
	Texture ammoFull, ammoOut, ammo;
	protected double angle;
	EntityManager em;
	boolean out = false;
	
        public AmmoIcon(EntityManager em, double x, double y, double width, double height, int angle) {
            super(em, x, y, width, height);
            this.angle = angle;
            this.em = em;
            this.ammoFull = LoadTextures.getTexture("res/ammo-full.png");
            this.ammoOut = LoadTextures.getTexture("res/ammo-out.png");
            id = em.assignId(this);
        }

        public double getAngle(){
        	return this.angle;
        }
        
        public void change(boolean toggle){
        	out = toggle;
        }
        
        public void setAngle(double angle){
        	if(angle > 360){
            	this.angle = angle-360;
        	}else{
        		this.angle = angle;
        	}
        }
        
        @Override
        public void draw() {
        	if(!em.getIds().contains(this.getId())){
        		return;
        	}
        	if(out){
        		ammo = ammoOut;
        	}else{
        		ammo = ammoFull;
        	}
        	ammo.bind();
            GL11.glLoadIdentity();
            GL11.glTranslated(x, y, 0);
            drawQuad();
            //smoke.draw(x,y);
        }
        
        public void drawQuad(){      	
        	GL11.glBegin(GL11.GL_TRIANGLES); 

	        	GL11.glTexCoord2f(0.5f, 0.0f); 
	        GL11.glVertex2d(-ammo.getTextureHeight()/1.6, -ammo.getTextureWidth()/1.6); 
	        	GL11.glTexCoord2f(0.5f, 0.5f); 
	        GL11.glVertex2d(ammo.getTextureHeight()/1.6, -ammo.getTextureWidth()/1.6); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 
	
	        	GL11.glTexCoord2f(0.5f, 0.5f); 
	        GL11.glVertex2d(ammo.getTextureHeight()/1.6, -ammo.getTextureWidth()/1.6); 
	        	GL11.glTexCoord2f(0.0f, 0.5f); 
	        GL11.glVertex2d(ammo.getTextureHeight()/1.6, ammo.getTextureWidth()/1.6); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 
	        
	        	GL11.glTexCoord2f(0.0f, 0.5f); 
	        GL11.glVertex2d(ammo.getTextureHeight()/1.6, ammo.getTextureWidth()/1.6); 
	        	GL11.glTexCoord2f(0.0f, 0.0f); 
	        GL11.glVertex2d(-ammo.getTextureHeight()/1.6, ammo.getTextureWidth()/1.6); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 
	
	        	GL11.glTexCoord2f(0.0f, 0.0f); 
	        GL11.glVertex2d(-ammo.getTextureHeight()/1.6, ammo.getTextureWidth()/1.6); 
	        	GL11.glTexCoord2f(0.5f, 0.0f); 
	        GL11.glVertex2d(-ammo.getTextureHeight()/1.6, -ammo.getTextureWidth()/1.6); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 

        GL11.glEnd();
        	
        }
        
        public void rotate(double angle){
        	if(!em.getIds().contains(this.getId())){
        		return;
        	}
        	if(angle > 360){
        		this.angle = 0;
        	}else if(angle < 0){
        		this.angle = 360;
        	}else{
        		this.angle = angle;
        	}
        	ammo.bind();
            GL11.glLoadIdentity();
            GL11.glTranslated(x, y, 0);
            GL11.glPushMatrix();
            GL11.glRotated(angle, 0, 0, 1);
            drawQuad();
    		GL11.glPopMatrix();
    		//smoke.draw(x,y);
        }
        
        @Override
        public void update(int delta) {
        	cuboid.rotate(angle);
            this.x += delta * dx;
            this.y += delta * dy;
            //smoke.update(delta);
        }       
        
}