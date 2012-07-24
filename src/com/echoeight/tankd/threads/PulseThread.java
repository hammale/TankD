package com.echoeight.tankd.threads;

import com.echoeight.bison.threads.BaseThread;
import com.echoeight.tankd.Game;

public class PulseThread extends BaseThread implements Runnable {
		
	protected Game game;
	
	public PulseThread(Game game){
		this.game = game;
	}
	
	@Override
	public void run() {
//		while(true){
//			if(game.isClosed()){
//				game.save();
//			}
//		}
	}
	
}