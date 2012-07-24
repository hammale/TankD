package com.echoeight.tankd.states;

import com.echoeight.bison.states.State;
import com.echoeight.bison.threads.BaseThread;

public class StateThread extends BaseThread implements Runnable {
		
	protected State state;
	
	public StateThread(State state){
		this.state = state;
	}
	
	@Override
	public void run() {
        while (running) {
			while (paused){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			state.update();
        }
	}
	
}