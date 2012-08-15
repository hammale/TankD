package com.echoeight.tankd.levels;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.CursorLoader;

import com.echoeight.bison.entity.EntityManager;
import com.echoeight.bison.states.BaseState;
import com.echoeight.tankd.Game;
import com.echoeight.tankd.entity.BG;

public class MainMenuState extends BaseState {
	
	protected Game game;
	private long lastFrame;
	
	EntityManager em;
	CampInput ui;
	
	int WIDTH, HEIGHT;
	
	BG bg;
	
    public MainMenuState(Game game, int id) {
		super(id);
		this.game = game;
		WIDTH = game.dm.getWidth();
		HEIGHT = game.dm.getHeight();
		em = new EntityManager();
	}
    
    private long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public int getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
	
	@Override
	public void init() {
		initGL();
        try {
            Cursor cursor = (CursorLoader.get()).getCursor("res/cursor.png",8,8);
			Mouse.setNativeCursor(cursor);
		} catch (Exception e) {
			e.printStackTrace();
		}
        bg = new BG(em, 0, 0, 600, 800);
	}
	
	@Override
	public void update() {
		Color.white.bind();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		bg.draw();
		
		input();
		
		em.flush();
	    Display.update();
	    Display.sync(60);
	}
	
	public void input(){
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			game.sm.enterState(new Camp1State(game, 1));
		}
	}	
	
	private void initGL(){
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.create();
			Display.setTitle(game.dm.getTitle());
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		//GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING); 
		
		GL11.glClearColor(255f, 255f, 255f, 0f);                
		GL11.glClearDepth(1);
		
		GL11.glEnable(GL11.GL_BLEND);
      	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      	
      	GL11.glViewport(0,0,WIDTH,HEIGHT);
      
      	GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
      	GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);  
	}
	
}