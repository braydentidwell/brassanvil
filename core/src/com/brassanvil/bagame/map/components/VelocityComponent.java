package com.brassanvil.bagame.map.components;

import lombok.Getter;
import lombok.Setter;

import com.badlogic.ashley.core.Component;

public class VelocityComponent extends Component {

	@Getter @Setter
	private int x = 0;
	@Getter @Setter
	private int y = 0;
}
