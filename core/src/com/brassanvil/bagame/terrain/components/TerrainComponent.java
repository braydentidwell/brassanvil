package com.brassanvil.bagame.terrain.components;

import lombok.Getter;
import lombok.Setter;

import com.badlogic.ashley.core.Component;

public class TerrainComponent extends Component{

	@Getter @Setter
	private boolean obstructed;
}
