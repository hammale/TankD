package com.echoeight.tankd.entity;

import java.util.ArrayList;

import com.echoeight.bison.entity.EntityManager;

public class Smoke {
		
	EntityManager em;
	int amnt;
	int i=0;
	ArrayList<BoomPart> parts = new ArrayList<BoomPart>();
	
        public Smoke(EntityManager em, int amnt, double x, double y) {      
            this.em = em;
            this.amnt = amnt;
    		for(int i=1;i<amnt;i++){
    			parts.add(new BoomPart(em, x, y));
    		}
        }

        public void draw(double x, double y) {
        	i++;
        	if(i%10==0){
	        	parts.get(0).destroy();
	        	parts.remove(0);
	        	parts.add(new BoomPart(em, x, y));
	        	for(BoomPart bp : parts){
	        		bp.draw();
	        	}
        	}
        }
        
        public void update(int delta) {
        	for(BoomPart bp : parts){
        		bp.update(delta);
        	}
        }
        
        public void destroy(){
        	for(BoomPart bp : parts){
        		em.markForDelete(bp);
        	}
        	parts.clear();
        }
}