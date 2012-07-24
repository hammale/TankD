package com.echoeight.tankd.images;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class LoadTextures {
	
	public static void LoadAll() {
		/*
		try {
			loadShell();
			loadTank();
			loadBG();
			loadWall();
			loadTurret();
			loadBoom();
			loadTread();
			System.out.println("BISON Loaded all textures!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
	public static Texture getTexture(String path){
		try {
			return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		return null;
	}
}
