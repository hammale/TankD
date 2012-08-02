package com.echoeight.tankd.entity;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.echoeight.bison.entity.BaseMoveableEntity;
import com.echoeight.bison.entity.EntityManager;
import com.echoeight.tankd.images.LoadTextures;
	
public class Shell extends BaseMoveableEntity {
		
	Texture shell;
	protected double angle;
	EntityManager em;
	Rectangle hitbox = new Rectangle();
	Tank tank;
	boolean bounced;
	Smoke smoke;
	
        public Shell(EntityManager em, Tank tank, double x, double y, double width, double height, int angle) {
            super(em, x, y, width, height);
            this.angle = angle;
            this.em = em;
            this.tank = tank;
            this.shell = LoadTextures.getTexture("res/shell.png");
            id = em.assignId(this);
            //this.smoke = new Smoke(em,10,x,y);
        }
        
        public Smoke getSmoke(){
        	return this.smoke;
        }
        
        public Tank getTank(){
        	return this.tank;
        }
        
        public boolean hasBounced(){
        	return this.bounced;
        }
        
        public void bounce(){
			dx = dx*-1;
			dy = dy*-1;
        	this.bounced = true;
        }
        
        public double getAngle(){
        	return this.angle;
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
        	shell.bind();
            GL11.glLoadIdentity();
            GL11.glTranslated(x, y, 0);
            drawQuad();
            //smoke.draw(x,y);
        }
        
        public void drawQuad(){      	
        	GL11.glBegin(GL11.GL_TRIANGLES); 

	        	GL11.glTexCoord2f(0.5f, 0.0f); 
	        GL11.glVertex2d(-shell.getTextureHeight()/1, -shell.getTextureWidth()/1); 
	        	GL11.glTexCoord2f(0.5f, 0.5f); 
	        GL11.glVertex2d(shell.getTextureHeight()/1, -shell.getTextureWidth()/1); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 
	
	        	GL11.glTexCoord2f(0.5f, 0.5f); 
	        GL11.glVertex2d(shell.getTextureHeight()/1, -shell.getTextureWidth()/1); 
	        	GL11.glTexCoord2f(0.0f, 0.5f); 
	        GL11.glVertex2d(shell.getTextureHeight()/1, shell.getTextureWidth()/1); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 
	        
	        	GL11.glTexCoord2f(0.0f, 0.5f); 
	        GL11.glVertex2d(shell.getTextureHeight()/1, shell.getTextureWidth()/1); 
	        	GL11.glTexCoord2f(0.0f, 0.0f); 
	        GL11.glVertex2d(-shell.getTextureHeight()/1, shell.getTextureWidth()/1); 
	        	GL11.glTexCoord2f(0.25f, 0.25f); 
	        GL11.glVertex2i(0, 0); 
	
	        	GL11.glTexCoord2f(0.0f, 0.0f); 
	        GL11.glVertex2d(-shell.getTextureHeight()/1, shell.getTextureWidth()/1); 
	        	GL11.glTexCoord2f(0.5f, 0.0f); 
	        GL11.glVertex2d(-shell.getTextureHeight()/1, -shell.getTextureWidth()/1); 
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
        	shell.bind();
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