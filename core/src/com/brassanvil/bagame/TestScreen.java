package com.brassanvil.bagame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassanvil.bagame.map.systems.MovementSystem;
import com.brassanvil.bagame.visual.systems.AnimationStateSystem;
import com.brassanvil.bagame.visual.systems.AnimationSystem;
import com.brassanvil.bagame.visual.systems.RenderingSystem;

public class TestScreen extends ScreenAdapter {
	
	private BrassAnvil game;
	private Engine engine;
	private OrthographicCamera cam;
	private SpriteBatch batch;
	
	private TestWorld world;
	
	public TestScreen(BrassAnvil game) {
		this.game = game;
		this.batch = game.batch;
		this.cam = new OrthographicCamera(640, 480);
		cam.position.set(640/2, 480/2, 0);
		
		batch = game.batch;
		
		engine = new Engine();
		engine.addSystem(new MovementSystem(0));
		engine.addSystem(new AnimationStateSystem(1));
		engine.addSystem(new AnimationSystem(2));
		engine.addSystem(new RenderingSystem(batch, 3));
		
		world = new TestWorld(engine);
		world.init();
	}
	
	@Override
	public void render(float deltaTime) {
		updateGameState(deltaTime);
		drawUI();
	}
	
	public void updateGameState(float deltaTime) {
		// TODO uncomment to allow game to slow down instead of skipping frames
		// if (deltaTime > 0.1f) deltaTime = 0.1f;
		
		engine.update(deltaTime);
	}
	
	private void drawUI() {
		cam.update();
		batch.setProjectionMatrix(cam.combined);
	}
}
