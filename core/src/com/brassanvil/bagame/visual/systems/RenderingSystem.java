package com.brassanvil.bagame.visual.systems;

import java.util.LinkedList;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassanvil.bagame.map.components.PositionComponent;
import com.brassanvil.bagame.visual.components.TextureComponent;

/**
 * Loops through all drawable entities in the system and renders them.
 */
public class RenderingSystem extends IteratingSystem{
	
	// A queue of entities to be rendered
	private LinkedList<Entity> renderQueue;
	
	// The SpriteBatch to draw onto
	private SpriteBatch batch;
	
	// Component mappers
	private ComponentMapper<TextureComponent> textureMapper;
	private ComponentMapper<PositionComponent> positionMapper;

	@SuppressWarnings("unchecked")
	public RenderingSystem(SpriteBatch batch, int priority) {
		super(Family.getFor(PositionComponent.class, TextureComponent.class), priority);
		textureMapper = ComponentMapper.getFor(TextureComponent.class);
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		renderQueue = new LinkedList<Entity>();
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		render(deltaTime);
		resetRenderQueue();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}
	
	/**
	 * Render every entity in the family defined above.
	 */
	protected void render(float deltaTime) {
		batch.begin();
		
		// Loop through every entity and render it.
		for(Entity entity : renderQueue) {
			TextureComponent tex = textureMapper.get(entity);
			PositionComponent pos = positionMapper.get(entity);
			
			// No texture to render.
			if(tex.texture == null)
				continue;
			
			batch.draw(tex.texture, pos.x, pos.y);
		}
		
		batch.end();
	}
	
	/**
	 * Clears the render queue.
	 */
	private void resetRenderQueue() {
		renderQueue.clear();
	}
}
