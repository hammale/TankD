package com.echoeight.tankd.entity;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.echoeight.bison.entity.BaseMoveableEntity;
import com.echoeight.bison.entity.EntityManager;
import com.echoeight.tankd.images.LoadTextures;
	
public class Turret extends BaseMoveableEntity {
		
	Texture turret;
	protected double angle;
	EntityManager em;
	
        public Turret(EntityManager em, double x, double y, double width, double height, int angle) {
            super(em, x, y, width, height);
            turret = LoadTextures.getTexture("res/turret.png");
            this.angle = angle;
            this.em = em;
            id = em.assignId(this);
        }

        public double getAngle(){
        	return this.angle;
        }
        
        public void setAngle(double angle){
        	this.angle = angle;
        }
        
        @Override
        public void draw() {
        	if(!em.getIds().contains(this.getId())){
        		return;
        	}
        	turret.bind();
            drawQuad();
        }
        
        public void drawQuad(){
        	
        	GL11.glBegin(GL11.GL_TRIANGLES); 
        	
        	GL11.glTexCoord2f(1.0f, 0.0f); 
            GL11.glVertex2d(-turret.getTextureHeight()/1.6, -turret.getTextureWidth()/1.6); 
            	GL11.glTexCoord2f(1.0f, 1.0f); 
            GL11.glVertex2d(turret.getTextureHeight()/1.6, -turret.getTextureWidth()/1.6); 
            	GL11.glTexCoord2f(0.5f, 0.5f); 
            GL11.glVertex2i(0, 0); 

            	GL11.glTexCoord2f(1.0f, 1.0f); 
            GL11.glVertex2d(turret.getTextureHeight()/1.6, -turret.getTextureWidth()/1.6); 
            	GL11.glTexCoord2f(0.0f, 1.0f); 
            GL11.glVertex2d(turret.getTextureHeight()/1.6, turret.getTextureWidth()/1.6); 
            	GL11.glTexCoord2f(0.5f, 0.5f); 
            GL11.glVertex2i(0, 0); 

            	GL11.glTexCoord2f(0.0f, 1.0f); 
            GL11.glVertex2d(turret.getTextureHeight()/1.6, turret.getTextureWidth()/1.6); 
            	GL11.glTexCoord2f(0.0f, 0.0f); 
            GL11.glVertex2d(-turret.getTextureHeight()/1.6, turret.getTextureWidth()/1.6); 
            	GL11.glTexCoord2f(0.5f, 0.5f); 
            GL11.glVertex2i(0, 0); 

            	GL11.glTexCoord2f(0.0f, 0.0f); 
            GL11.glVertex2d(-turret.getTextureHeight()/1.6, turret.getTextureWidth()/1.6); 
            	GL11.glTexCoord2f(1.0f, 0.0f); 
            GL11.glVertex2d(-turret.getTextureHeight()/1.6, -turret.getTextureWidth()/1.6); 
            	GL11.glTexCoord2f(0.5f, 0.5f); 
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
        	turret.bind();
            GL11.glLoadIdentity();
            GL11.glTranslated(x, y, 0);   
            GL11.glPushMatrix();
            GL11.glRotated(angle, 0, 0, 1);
            drawQuad();
    		GL11.glPopMatrix();
        }
}