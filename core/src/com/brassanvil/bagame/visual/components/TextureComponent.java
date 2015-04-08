package com.brassanvil.bagame.visual.components;

import lombok.Getter;
import lombok.Setter;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent extends Component {
	
	@Getter @Setter
	private TextureRegion region = null;
}
