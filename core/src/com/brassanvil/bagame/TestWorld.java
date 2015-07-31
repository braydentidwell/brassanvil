package com.brassanvil.bagame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.brassanvil.bagame.map.MapComponentFactory;
import com.brassanvil.bagame.map.components.AccelerationComponent;
import com.brassanvil.bagame.map.components.PositionComponent;
import com.brassanvil.bagame.map.components.SizeComponent;
import com.brassanvil.bagame.map.components.VelocityComponent;
import com.brassanvil.bagame.visual.components.AnimationComponent;
import com.brassanvil.bagame.visual.components.AnimationStateComponent;
import com.brassanvil.bagame.visual.components.TextureComponent;

public class TestWorld {
	
	private Engine engine;
	
	Texture img, bg;
	TextureRegion[] frames;
	Animation animation;

	private static final int ROWS = 5;
	private static final int COLS = 6;

    // Note: these are in UNITS, not pixels. Do not think in terms of pixels. Only the camera is concerned with pixels.
    private static final int WORLD_HEIGHT = 100;
    private static final int WORLD_WIDTH = 100;
	
	public TestWorld(Engine engine) {
		this.engine = engine;
	}
	
	public void init() {
        // BG
        // TODO need an easier way to draw non-animated things...
        bg = new Texture("bg.jpg");
        TextureRegion[][] tmp = TextureRegion.split(bg, 1, 1);
        TextureRegion[] bgFrames = new TextureRegion[1];
        bgFrames[0] = tmp[0][0];
        Animation bgAnimation = new Animation(1000f, bgFrames);
        bgAnimation.setPlayMode(PlayMode.LOOP);
        AnimationComponent bgAnimationComponent = new AnimationComponent();
        bgAnimationComponent.animations.put(0, bgAnimation);
        Entity bgEntity = new Entity();
        PositionComponent bgPos = new PositionComponent();
        bgPos.x = bgPos.y = 0;
        SizeComponent bgSize = new SizeComponent();
        bgSize.width = WORLD_WIDTH;
        bgSize.height = WORLD_HEIGHT;
        bgEntity.add(bgPos);
        bgEntity.add(bgSize);
        bgEntity.add(bgAnimationComponent);
        bgEntity.add(new AnimationStateComponent());
        bgEntity.add(new TextureComponent());
        engine.addEntity(bgEntity);
        // End BG

        Entity testEntity = new Entity();

        PositionComponent positionComponent = new PositionComponent();
        positionComponent.x = 0;
        positionComponent.y = 0;
        SizeComponent sizeComponent = new SizeComponent();
        sizeComponent.width = 10;
        sizeComponent.height = 10;

        AccelerationComponent accelerationComponent = new AccelerationComponent();
//		accelerationComponent.acceleration.set(5f, 0f);
		
		// Create the animation
		img = new Texture("sprite-animation1.png");
		tmp = TextureRegion.split(img, img.getWidth()/COLS, img.getHeight()/ROWS);
		frames = new TextureRegion[ROWS * COLS];
		int frame = 0;
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				frames[frame++] = tmp[i][j];
			}
		}
		animation = new Animation(1f, frames);
		animation.setFrameDuration(.05f);
		animation.setPlayMode(PlayMode.LOOP);
		AnimationComponent animationComponent = new AnimationComponent();
		animationComponent.animations.put(0, animation);
		
		testEntity.add(positionComponent);
        testEntity.add(sizeComponent);
		testEntity.add(accelerationComponent);
		testEntity.add(new VelocityComponent());
		testEntity.add(new AnimationStateComponent());
		testEntity.add(new TextureComponent());
		testEntity.add(animationComponent);
		
		engine.addEntity(testEntity);
	}
}
