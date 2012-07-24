package com.echoeight.tankd.levels;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.echoeight.bison.entity.EntityManager;
import com.echoeight.bison.util.Fullscreen;
import com.echoeight.tankd.Game;
import com.echoeight.tankd.entity.Shell;
import com.echoeight.tankd.entity.Tank;
import com.echoeight.tankd.entity.Tread;

public class CampInput {
	
	int WIDTH,HEIGHT;
	EntityManager em;
	Game game;
	
	float prevMouseY = 0;
	float prevMouseX = 0;
	
	int shelldelay = 0;
	
	public CampInput(Game game, EntityManager manager){
		this.WIDTH = game.dm.getWidth();
		this.HEIGHT = game.dm.getHeight();
		this.em = manager;
		this.game = game;
	}
	
	public void handleInput(EntityManager em, Tank tank){
		handleMouse(tank);
		boolean drawTread = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			if(tank.getAngle() == 90){
				tank.setDY(0.1);
			}else if(tank.getAngle() == 180){
				tank.setDX(-0.1);
			}else if(tank.getAngle() == 0){
				tank.setDX(0.1);
			}else if(tank.getAngle() == 360){
				tank.setDX(0.1);
			}else if(tank.getAngle() < 90 && tank.getAngle() > 0){
				double angle = (tank.getAngle()/1000);
				tank.setDX(0.1-angle);
				tank.setDY(angle);
			}else if(tank.getAngle() > 90 && tank.getAngle() < 180){
				double angle = ((tank.getAngle() - 90)/1000);
				tank.setDY((0.1-angle));
				tank.setDX(-1*angle);
			}else if(tank.getAngle() > 180 && tank.getAngle() < 270){
				double angle = ((tank.getAngle() - 180)/1000);
				tank.setDX(-1*(0.1-angle));
				tank.setDY(-1*angle);
			}else if(tank.getAngle() > 270 && tank.getAngle() < 360){
				double angle = ((tank.getAngle() - 270)/1000);
				tank.setDY(-1*(0.1-angle));
				tank.setDX(angle);
			}
			drawTread = true;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			if(tank.getAngle() == 90){
				tank.setDY(-0.1);
			}else if(tank.getAngle() == 180){
				tank.setDX(0.1);
			}else if(tank.getAngle() == 0){
				tank.setDX(-0.1);
			}else if(tank.getAngle() == 360){
				tank.setDX(-0.1);
			}else if(tank.getAngle() < 90 && tank.getAngle() > 0){
				double angle = (tank.getAngle()/1000);
				tank.setDX(-1*(0.1-angle));
				tank.setDY(-1*angle);
			}else if(tank.getAngle() > 90 && tank.getAngle() < 180){
				double angle = ((tank.getAngle() - 90)/1000);
				tank.setDY(-1*(0.1-angle));
				tank.setDX(angle);
			}else if(tank.getAngle() > 180 && tank.getAngle() < 270){
				double angle = ((tank.getAngle() - 180)/1000);
				tank.setDX((0.1-angle));
				tank.setDY(angle);
			}else if(tank.getAngle() > 270 && tank.getAngle() < 360){
				double angle = ((tank.getAngle() - 270)/1000);
				tank.setDY((0.1-angle));
				tank.setDX(-1*angle);
			}
			drawTread = true;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			tank.setAngle(tank.getAngle() - 4);
			drawTread = true;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			tank.setAngle(tank.getAngle() + 4);
			drawTread = true;
		}
	      while (Keyboard.next()) {
		      	 if (Keyboard.getEventKey() == Keyboard.KEY_F11) {
		          	    if (Keyboard.getEventKeyState()) {
		          	    	if(Fullscreen.fullscreen){
		          	    		Fullscreen.fullscreen = false;
		          	    		Fullscreen.setDisplayMode(WIDTH, HEIGHT, false);
		          	    	}else{
		          	    		Fullscreen.fullscreen = true;
		          	    		Fullscreen.setDisplayMode(WIDTH, HEIGHT, true);
		          	    	}
		          	    }
		      	 }
		      	if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
		      		em.removeAll();		      		
		      	}
		  }
	      if(drawTread){
	    	  if(tank.getTread() == null || Math.abs(tank.getX() - tank.getTread().getX()) >= 9 || Math.abs(tank.getY() - tank.getTread().getY()) >= 9){
	    		  double angle = 0;
	    		  if(tank.getAngle() > 360){
	    			  angle = tank.getAngle()-360;
	    		  }else if(tank.getAngle() < 0){
	    			  angle = 360-(Math.abs(tank.getAngle()));
	    		  }else{
	    			  angle = tank.getAngle();
	    		  }
	    		  tank.setTread(new Tread(em , tank.getX(), tank.getY(), 34, 34, angle));
	    	  }
	      }
	}
	
	public void handleMouse(Tank tank){

		tank.getTurret().setAngle(180-tank.getTurret().getMouseAngle());
		
		if(Mouse.next()){
			if(Mouse.isButtonDown(0)){
				Shell shell = new Shell(em,tank,tank.getX(),tank.getY(),3,1,(int) tank.getTurret().getAngle());
				tank.addShell(shell);
				moveShell(shell);
			}
			if(Mouse.isButtonDown(1)){
				createExplosion(100);
			}
		}
		
	}
	
	public void moveShell(Shell shell){
		double angle = shell.getAngle()+94;
		if(angle>360){
			angle = angle-360;
		}
		if(angle == 90){
			shell.setDY(0.1);
		}else if(angle == 180){
			angle -= 2;
			shell.setDX(-0.1);
		}else if(angle == 0 || angle == 360){
			shell.setDX(0.1);
		}else if(angle == 270){
			shell.setDY(-0.1);
		}else if(angle < 90 && angle > 0){
			angle = (angle/1000);
			shell.setDX(0.1-angle);
			shell.setDY(angle);
		}else if(angle > 90 && angle < 180){
			angle = ((angle - 88)/1000);
			shell.setDY((0.1-angle));
			shell.setDX(-1*angle);
		}else if(angle > 180 && angle < 270){
			angle = ((angle - 178)/1000);
			shell.setDX(-1*(0.1-angle));
			shell.setDY(-1*angle);
		}else if(angle > 270 && angle < 360){
			angle = ((angle - 270)/1000);
			shell.setDY(-1*(0.1-angle));
			shell.setDX(angle);
		}
		shell.setDX(shell.getDX()*1.5);
		shell.setDY(shell.getDY()*1.5);
		shell.setAngle(shell.getAngle()+4);
	}
	
	public void createExplosion(int amnt){
		System.out.println(Mouse.getX() + ", " + Mouse.getY());
	}
	
}
