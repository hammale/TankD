package com.echoeight.tankd.entity;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.echoeight.bison.entity.BaseMoveableEntity;
import com.echoeight.bison.entity.Entity;
import com.echoeight.bison.entity.EntityManager;
import com.echoeight.tankd.images.LoadTextures;
	
public class Tank extends BaseMoveableEntity {
		
	Texture tank;
	protected double angle;
	EntityManager em;
	Turret turret;
	ArrayList<Shell> shells = new ArrayList<Shell>();
	ArrayList<Tread> treads = new ArrayList<Tread>();
	Tread tread;
	
        public Tank(EntityManager em, double x, double y, double width, double height, int angle) {
            super(em, x, y, width, height);
            this.tank = LoadTextures.getTexture("res/tank.png");
            this.angle = angle;
            this.em = em;
            id = em.assignId(this);
            this.turret = new Turret(em, x, y, 30, 12, 0);
        }
        
        public ArrayList<Tread> getTreads(){
        	return this.treads;
        }
        
        public void setTread(Tread tread){
        	this.tread = tread;
        	treads.add(tread);
        }
        
        public Tread getTread(){
        	return this.tread;
        }
        
        public void clearShells(){
        	this.shells.clear();
        }
        
        public void removeShell(Shell shell){
        	this.shells.remove(shell);
        }
        
        public void addShell(Shell shell){
        	this.shells.add(shell);
        }
        
        public ArrayList<Shell> getShells(){
        	return this.shells;
        }
        
        public Turret getTurret(){
        	return this.turret;
        }
        
        public double getAngle(){
        	return this.angle;
        }
        
        public void setAngle(double angle){
        	this.angle = angle;
        }
        
        @Override
        public void draw() {
        	if(!em.getIds().contains(this.getId()) || !em.getIds().contains(turret.getId())){
        		return;
        	}
        	tank.bind();
            GL11.glTranslated(x, y, 0);
            drawQuad();
        	for(Entity ent : em.getMovingEntities()){
    			if(ent instanceof Shell){
    				((Shell) ent).rotate(((Shell) ent).getAngle());
    			} 			
    		}
            turret.draw();
        }
        
        public void drawQuad(){
//        	GL11.glBegin(GL11.GL_QUADS);
//    				GL11.glTexCoord2f(0,0);
//    			GL11.glVertex2f(0,0);
//    				GL11.glTexCoord2f(1,0);
//    			GL11.glVertex2f(34,0);
//    				GL11.glTexCoord2f(1,1);
//    			GL11.glVertex2f(34,34);
//    				GL11.glTexCoord2f(0,1);
//    			GL11.glVertex2f(0,34);  			
//    		GL11.glEnd();
        	
        	GL11.glBegin(GL11.GL_TRIANGLES); 

	            	GL11.glTexCoord2f(0.5f, 0.0f); 
	            GL11.glVertex2d(-68/3.7, -68/3.7); 
	            	GL11.glTexCoord2f(0.5f, 0.5f); 
	            GL11.glVertex2d(68/3.7, -68/3.7); 
	            	GL11.glTexCoord2f(0.25f, 0.25f); 
	            GL11.glVertex2i(0, 0); 
	
	            	GL11.glTexCoord2f(0.5f, 0.5f); 
	            GL11.glVertex2d(68/3.7, -68/3.7); 
	            	GL11.glTexCoord2f(0.0f, 0.5f); 
	            GL11.glVertex2d(68/3.7, 68/3.7); 
	            	GL11.glTexCoord2f(0.25f, 0.25f); 
	            GL11.glVertex2i(0, 0); 
	            
	            	GL11.glTexCoord2f(0.0f, 0.5f); 
	            GL11.glVertex2d(68/3.7, 68/3.7); 
	            	GL11.glTexCoord2f(0.0f, 0.0f); 
	            GL11.glVertex2d(-68/3.7, 68/3.7); 
	            	GL11.glTexCoord2f(0.25f, 0.25f); 
	            GL11.glVertex2i(0, 0); 
	
	            	GL11.glTexCoord2f(0.0f, 0.0f); 
	            GL11.glVertex2d(-68/3.7, 68/3.7); 
	            	GL11.glTexCoord2f(0.5f, 0.0f); 
	            GL11.glVertex2d(-68/3.7, -68/3.7); 
	            	GL11.glTexCoord2f(0.25f, 0.25f); 
	            GL11.glVertex2i(0, 0); 

            GL11.glEnd();
        	
        }
        
        public void rotate(double angle){
        	if(!em.getIds().contains(this.getId())){
        		return;
        	}
        	if(angle > 360){
        		this.angle = angle-360;
        	}else if(angle < 0){
        		this.angle = 360-(Math.abs(angle));
        	}else{
        		this.angle = angle;
        	}
    		syncTurret();
        	tank.bind();
            GL11.glLoadIdentity();
            GL11.glTranslated(x, y, 0);
            GL11.glPushMatrix();
            GL11.glRotated(angle, 0, 0, 1);
            drawQuad();
    		GL11.glPopMatrix();
        	for(Shell shell : getShells()){
    				shell.rotate(shell.getAngle());		
    		}
            turret.rotate(turret.getAngle());
        }
        
        public void syncTurret(){
    		turret.setX(x);
    		turret.setY(y);
    		turret.setDX(dx);
    		turret.setDY(dy);
        }
        
        @Override
        public void update(int delta) {
        	cuboid.rotate(dx, dy, angle);
         	for(Entity ent : em.getMovingEntities()){
    			if(ent instanceof Shell){
    				ent.update(delta);
    			} 			
    		}
            	turret.update(delta);
                x += delta * dx;
                y += delta * dy;
        }
        
}