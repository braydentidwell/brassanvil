package com.brassanvil.bagame.visual.systems;

import java.util.LinkedList;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassanvil.bagame.map.components.PositionComponent;
import com.brassanvil.bagame.map.components.SizeComponent;
import com.brassanvil.bagame.visual.components.TextureComponent;
import lombok.Setter;

/**
 * Loops through all draw-able entities in the system and renders them.
 */
public class RenderingSystem extends IteratingSystem {
	
	// A queue of entities to be rendered
	private LinkedList<Entity> renderQueue;
	
	// The SpriteBatch to draw onto
	private SpriteBatch batch;

    // Camera to control which part of the world we see
    @Setter
    private Camera camera;
	
	// Component mappers
	private ComponentMapper<TextureComponent> textureMapper;
	private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<SizeComponent> sizeMapper;

	@SuppressWarnings("unchecked")
	public RenderingSystem(SpriteBatch batch, Camera camera, int priority) {
		super(Family.getFor(PositionComponent.class, SizeComponent.class, TextureComponent.class), priority);
		textureMapper = ComponentMapper.getFor(TextureComponent.class);
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
        sizeMapper = ComponentMapper.getFor(SizeComponent.class);
		renderQueue = new LinkedList<Entity>();
		this.batch = batch;
        this.camera = camera;
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
        // Updates our SpriteBatch instance with our Camera's view and projection matrices.
        batch.setProjectionMatrix(camera.combined);
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // TODO should this be done here or up a few levels? (like it is now)
		batch.begin();
		
		// Loop through every entity and render it.
		for(Entity entity : renderQueue) {
			TextureComponent tex = textureMapper.get(entity);
			PositionComponent pos = positionMapper.get(entity);
            SizeComponent size = sizeMapper.get(entity);
			
			// No texture to render.
			if(tex.texture == null)
				continue;
			
			batch.draw(tex.texture, pos.x, pos.y, size.width, size.height);
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
