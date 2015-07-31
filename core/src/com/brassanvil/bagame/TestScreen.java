package com.brassanvil.bagame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
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

	// Note: these are in UNITS, not pixels. Do not think in terms of pixels. The aspect ratio takes care of the pixels for us.
	private static final int CAM_WIDTH = 100;
	private static final int CAM_HEIGHT = 100;

    private float rotationSpeed = 0.5f;
	
	public TestScreen(BrassAnvil game) {
		this.game = game;
		this.batch = game.getBatch();

        // Application display width and height (in pixels)
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        float aspectRatio = h / w;

        // Sets camera position to bottom-left of map. Note that the position refers to the CENTER of the camera.
        // This results in the bottom-left of our camera being at (0,0).
		this.cam = new OrthographicCamera(CAM_WIDTH, CAM_HEIGHT * aspectRatio);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        // This needs to happen any time the camera is updated. This updates the matrices under the hood.
		cam.update();
		
		engine = new Engine();
		engine.addSystem(new MovementSystem(0));
		engine.addSystem(new AnimationStateSystem(1));
		engine.addSystem(new AnimationSystem(2));
		engine.addSystem(new RenderingSystem(batch, cam, 3));
		
		world = new TestWorld(engine);
		world.init();
	}
	
	@Override
	public void render(float deltaTime) {
        handleInput();
		updateGameState(deltaTime);
	}
	
	@Override
	public void resize(int windowWidth, int windowHeight) {
		float aspectRatio = (float) windowHeight / (float) windowWidth;
        cam.viewportWidth = CAM_WIDTH;
		cam.viewportHeight = CAM_HEIGHT * aspectRatio;
        cam.update();
	}
	
	public void updateGameState(float deltaTime) {
		// IMPORTANT: Uncomment to allow game to slow down instead of skipping frames
		// if (deltaTime > 0.1f) deltaTime = 0.1f;
		engine.update(deltaTime);
	}

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }

        // The following code places boundaries on moving the viewport.
        // cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100 / cam.viewportWidth);
        // float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        // float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
        // cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        // cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);

        cam.update();
    }
}
