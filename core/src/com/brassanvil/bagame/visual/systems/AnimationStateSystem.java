package com.brassanvil.bagame.visual.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.brassanvil.bagame.visual.components.AnimationStateComponent;

/**
 * Progresses all animations' elapsed time.
 */
public class AnimationStateSystem extends IteratingSystem{
	
	private ComponentMapper<AnimationStateComponent> stateComponent;

	@SuppressWarnings("unchecked")
	public AnimationStateSystem(int priority) {
		super(Family.getFor(AnimationStateComponent.class), priority);
		
		// Component mappers
		stateComponent = ComponentMapper.getFor(AnimationStateComponent.class);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		stateComponent.get(entity).timeElapsed += deltaTime;
	}
}
