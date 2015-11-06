package com.itayandtamir.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.itayandtamir.game.FirstGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		System.setProperty("user.name", "Tamir");
		config.title = FirstGame.TITLE;
		config.width = FirstGame.WIDTH;
		config.height = FirstGame.HEIGHT;
		new LwjglApplication(new FirstGame(), config);
	}
}
