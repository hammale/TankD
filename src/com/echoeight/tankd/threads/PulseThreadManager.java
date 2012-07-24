package com.echoeight.tankd.threads;

import com.echoeight.bison.threads.BaseThreadManager;
import com.echoeight.tankd.Game;

public class PulseThreadManager extends BaseThreadManager {
	
	
	public PulseThreadManager(Game game){
		thread = new PulseThread(game);
	}
	
	boolean running;
	boolean paused;
	
	protected PulseThread thread;
	
	public void startThread(){
		Runnable runnable = (Runnable) thread;
		Thread rthread = new Thread(runnable);
		rthread.start();
	}
	
	@Override
	public void pauseThread(boolean pause){
		thread.pause(pause);		
	}
	
	@Override
	public void stopThread(){
		
	}
	
}