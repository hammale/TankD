package com.echoeight.tankd.entity;

import com.echoeight.bison.entity.EntityManager;

public class PlayerTank extends Tank {

	public PlayerTank(EntityManager em, double x, double y, double width,
			double height, int angle) {
		super(em, x, y, width, height, angle);
		ammo = 1;
	}

}
