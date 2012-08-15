package com.echoeight.tankd.images;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class LoadTextures {
	
	public static Texture getTexture(String path){
		try {
			return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		return null;
	}
}
