package com.brassanvil.bagame.visual.components;

import lombok.Getter;
import lombok.Setter;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

public class AnimationComponent extends Component{

	@Getter @Setter
	private IntMap<Animation> animations = new IntMap<Animation>();
}
