package com.brassanvil.bagame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brassanvil.bagame.BrassAnvil;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		// TODO Set fullscreen
		// config.fullscreen = true;
		
		// TODO eventually remove
		config.width = 720;
		config.height = 480;
		
		// Enable vertical sync
		config.vSyncEnabled = true;
		
		new LwjglApplication(new BrassAnvil(), config);
	}
}
