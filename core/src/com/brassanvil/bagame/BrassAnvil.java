package com.brassanvil.bagame;

import lombok.Getter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BrassAnvil extends Game {
	
	@Getter
	private SpriteBatch batch;
	
	/**
	 * Get the current aspect ratio of the application
	 */
	public float getAspectRatio() {
		return Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
	}
	
	@Override
	public void create() {
		batch = new SpriteBatch();
//		setScreen(new TestScreen(this));
        setScreen(new TestIsometricScreen(this));
	}

	@Override
	public void render () {
        // TODO should this stuff be pushed down to the system instead?
		GL20 gl = Gdx.gl;
		gl.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
}
