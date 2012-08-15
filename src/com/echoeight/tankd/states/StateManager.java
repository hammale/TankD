package com.echoeight.tankd.states;

import com.echoeight.bison.states.State;
import com.echoeight.tankd.Game;

public class StateManager {
	
	public State activeState;
	StateThreadManager tm;	
	
	protected Game game;
	
    public StateManager(Game game) {
    	this.game = game;
    	tm = new StateThreadManager(game, activeState);
	}
	
    public void enterState(State state){
    	setActiveState(state);
    	activeState = state;
    	if(tm.isActive()){
        	game.dm.clear();
    		tm.stop();
    	}
    	state.init();
    	tm.start();
    }
    
	public void setActiveState(State state){
		activeState = state;
    	tm = new StateThreadManager(game, activeState);
	}
	
	public State getActiveState(){
		return activeState;			
	}
    
}