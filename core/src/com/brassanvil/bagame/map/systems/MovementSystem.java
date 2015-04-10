package com.brassanvil.bagame.map.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.brassanvil.bagame.map.components.AccelerationComponent;
import com.brassanvil.bagame.map.components.PositionComponent;
import com.brassanvil.bagame.map.components.VelocityComponent;

/**
 * This system adjusts an entity's velocity (based on its acceleration) and then its position (based on its velocity), according to
 * the amount of time elapsed.
 */
public class MovementSystem extends IteratingSystem{
	
	private ComponentMapper<PositionComponent> positionMapper;
	private ComponentMapper<VelocityComponent> velocityMapper;
	private ComponentMapper<AccelerationComponent> accelerationMapper;

	@SuppressWarnings("unchecked")
	public MovementSystem(int priority) {
		super(Family.getFor(PositionComponent.class, VelocityComponent.class, AccelerationComponent.class), priority);
		velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		accelerationMapper = ComponentMapper.getFor(AccelerationComponent.class);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		PositionComponent pos = positionMapper.get(entity);
		VelocityComponent vel = velocityMapper.get(entity);
		AccelerationComponent acc = accelerationMapper.get(entity);
		
		Vector2 tempVector = new Vector2();
		
		// Calculate the total amount accelerated over the elapsed time and adjust the velocity according to the result.
		tempVector.set(acc.acceleration).scl(deltaTime);
		vel.velocity.add(tempVector);
		
		// Calculate the total amount moved over the elapsed time and adjust the position accordingly.
		tempVector.set(vel.velocity).scl(deltaTime);
		pos.x += tempVector.x;
		pos.y += tempVector.y;
	}
}
