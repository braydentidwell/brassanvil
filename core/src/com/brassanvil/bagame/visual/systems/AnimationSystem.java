package com.brassanvil.bagame.visual.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.brassanvil.bagame.visual.components.AnimationComponent;
import com.brassanvil.bagame.visual.components.AnimationStateComponent;
import com.brassanvil.bagame.visual.components.TextureComponent;

public class AnimationSystem extends IteratingSystem {
	
	private ComponentMapper<AnimationComponent> animationMapper;
	private ComponentMapper<AnimationStateComponent> animationStateMapper;
	private ComponentMapper<TextureComponent> textureMapper;

	@SuppressWarnings("unchecked")
	public AnimationSystem(int priority) {
		super(Family.getFor(AnimationComponent.class, AnimationStateComponent.class, TextureComponent.class), priority);
		animationMapper = ComponentMapper.getFor(AnimationComponent.class);
		animationStateMapper = ComponentMapper.getFor(AnimationStateComponent.class);
		textureMapper = ComponentMapper.getFor(TextureComponent.class);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		AnimationComponent animCom = animationMapper.get(entity);
		AnimationStateComponent stateCom = animationStateMapper.get(entity);
		TextureComponent texCom = textureMapper.get(entity);
		
		// Get the animation currently being displayed by this entity
		Animation currentAnimation = animCom.animations.get(stateCom.state);
		
		// Update the amount of time this entity's animation has been in this state
		stateCom.timeElapsed += deltaTime;
		
		// Set this entity's current visual frame according to the amount of time this animation has been running
		if(currentAnimation != null) {
			texCom.texture = currentAnimation.getKeyFrame(stateCom.timeElapsed);
		}
	}
}
