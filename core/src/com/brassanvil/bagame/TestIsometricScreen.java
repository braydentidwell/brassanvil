package com.brassanvil.bagame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class TestIsometricScreen extends ScreenAdapter {

    private TiledMap map;
    private IsometricTiledMapRenderer renderer;
    private OrthographicCamera cam;

    // Note: these are in UNITS, not pixels. Do not think in terms of pixels. The aspect ratio takes care of the pixels for us.
    private static final int CAM_WIDTH = 100;
    private static final int CAM_HEIGHT = 100;
    private static final float rotationSpeed = 0.5f;

    public TestIsometricScreen(BrassAnvil game) {
        map = new TmxMapLoader().load("testIsometric.tmx");
        renderer = new IsometricTiledMapRenderer(map);

        // Dunno what this does
        renderer.getBatch().setShader(null);

        // Application display width and height (in pixels)
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        float aspectRatio = h / w;

        cam = new OrthographicCamera(CAM_WIDTH, CAM_HEIGHT * aspectRatio);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
    }

    @Override
    public void render(float deltaTime) {
        handleInput();
        renderer.setView(cam);
        renderer.render();
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
