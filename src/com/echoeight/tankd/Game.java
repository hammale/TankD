package com.echoeight.tankd;

import com.echoeight.bison.display.DisplayManager;
import com.echoeight.tankd.images.LoadTextures;
import com.echoeight.tankd.levels.Camp1State;
import com.echoeight.tankd.states.StateManager;


public class Game {
	
	public DisplayManager dm;
	public StateManager sm;
	static Thread mainThread;
	
	public Game(int width, int height, String title){
		dm = new DisplayManager(width, height, title);
		initState();
		LoadTextures.LoadAll();
		System.out.println("TankD Started");
	}

	private void initState(){
		sm = new StateManager(this);
		sm.enterState(new Camp1State(this, 0));
	}
	
	public static void main(String[] argv) {
		mainThread = Thread.currentThread();
		new Game(600, 800, "TankD");
	}

	public void save() {

	}
	
}