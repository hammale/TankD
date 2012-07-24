package com.echoeight.tankd.states;

import org.lwjgl.opengl.Display;

import com.echoeight.bison.states.State;
import com.echoeight.tankd.Game;

public class StateThreadManager {
	
	protected State activeState;
	protected Game game;
	
	public StateThreadManager(Game game, State activeState){
		this.activeState = activeState;
		this.thread = new StateThread(activeState);
		this.game = game;
	}
	
	boolean running;
	boolean paused;
	
	protected StateThread thread;
	
	public void start(){
		running = true;
		while(running){
			while(!paused){
				if(Display.isCloseRequested()){
					game.save();
					Display.destroy();
					System.exit(0);
				}
				activeState.update();
			}
		}
	}
	
	public boolean isActive(){
		return running;
	}

	public void pause(boolean pause){
		paused = pause;		
	}
	
	public void stop(){
		running = false;
	}
	
}