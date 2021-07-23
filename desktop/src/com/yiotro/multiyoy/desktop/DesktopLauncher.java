package com.yiotro.multiyoy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.yiotro.multiyoy.Constants;
import com.yiotro.multiyoy.MultiYoyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;
		config.title = Constants.TITLE;
		config.foregroundFPS = 0;
		config.vSyncEnabled = false;
		new LwjglApplication(new MultiYoyGame(), config);
	}
}
