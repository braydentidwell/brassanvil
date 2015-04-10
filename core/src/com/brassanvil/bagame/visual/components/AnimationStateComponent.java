package com.brassanvil.bagame.visual.components;

import com.badlogic.ashley.core.Component;

/**
 * Represents the current animation state (e.g. walking, running) of an entity 
 */
public class AnimationStateComponent extends Component{

	// The animation state.
	public int state = 0;
	
	// The elapsed time spent in this state.
	public float timeElapsed = 0f;
}
