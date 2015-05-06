package com.brassanvil.bagame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.brassanvil.bagame.map.components.AccelerationComponent;
import com.brassanvil.bagame.map.components.PositionComponent;
import com.brassanvil.bagame.map.components.VelocityComponent;
import com.brassanvil.bagame.visual.components.AnimationComponent;
import com.brassanvil.bagame.visual.components.AnimationStateComponent;
import com.brassanvil.bagame.visual.components.TextureComponent;

public class TestWorld {
	
	private Engine engine;
	
	Texture img;
	TextureRegion[] frames;
	Animation animation;

	private static final int ROWS = 5;
	private static final int COLS = 6;
	
	public TestWorld(Engine engine) {
		this.engine = engine;
	}
	
	public void init() {
		Entity testEntity = new Entity();
		
		PositionComponent positionComponent = new PositionComponent();
		positionComponent.x = 100;
		positionComponent.y = 100;
		
		AccelerationComponent accelerationComponent = new AccelerationComponent();
		accelerationComponent.acceleration.set(10f, 0f);
		
		// Create the animation
		img = new Texture("sprite-animation1.png");
		TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth()/COLS, img.getHeight()/ROWS);
		frames = new TextureRegion[ROWS * COLS];
		int frame = 0;
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				frames[frame++] = tmp[i][j];
			}
		}
		animation = new Animation(0.025f, frames);
		animation.setFrameDuration(0.1f);
		animation.setPlayMode(PlayMode.LOOP);
		AnimationComponent animationComponent = new AnimationComponent();
		animationComponent.animations.put(0, animation);
		
		testEntity.add(positionComponent);
		testEntity.add(accelerationComponent);
		testEntity.add(new VelocityComponent());
		testEntity.add(new AnimationStateComponent());
		testEntity.add(new TextureComponent());
		testEntity.add(animationComponent);
		
		engine.addEntity(testEntity);
	}
}
