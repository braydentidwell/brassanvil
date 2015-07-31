package com.brassanvil.bagame.map;

import com.badlogic.gdx.math.Vector2;
import com.brassanvil.bagame.map.components.AccelerationComponent;
import com.brassanvil.bagame.map.components.PositionComponent;
import com.brassanvil.bagame.map.components.SizeComponent;
import com.brassanvil.bagame.map.components.VelocityComponent;

/**
 * Convenience factory methods for map-related components
 */
public class MapComponentFactory {

    public static VelocityComponent createVelocityComponent(float dx, float dy) {
        VelocityComponent component = new VelocityComponent();
        component.velocity = new Vector2(dx, dy);
        return component;
    }

    public static AccelerationComponent createAccelerationComponent(float dx, float dy) {
        AccelerationComponent component = new AccelerationComponent();
        component.acceleration = new Vector2(dx, dy);
        return component;
    }

    public static PositionComponent createPositionComponent(float x, float y) {
        PositionComponent component = new PositionComponent();
        component.x = x;
        component.y = y;
        return component;
    }

    public static SizeComponent createSizeComponent(float width, float height) {
        SizeComponent component = new SizeComponent();
        component.width = width;
        component.height = height;
        return component;
    }
}
