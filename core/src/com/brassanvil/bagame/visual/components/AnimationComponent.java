package com.brassanvil.bagame.visual.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

/**
 * Represents an entity's collection of possible animations.
 */
public class AnimationComponent extends Component{

	public IntMap<Animation> animations = new IntMap<Animation>();
}