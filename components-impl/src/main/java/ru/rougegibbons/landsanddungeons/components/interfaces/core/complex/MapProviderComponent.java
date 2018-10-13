package ru.rougegibbons.landsanddungeons.components.interfaces.core.complex;

import ru.rougegibbons.landsanddungeons.components.interfaces.Component;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.PackableComponent;

import java.util.Map;

/**
 * interface for components containing other components
 * and representing them as a {@link Map}.
 *
 * @param <T> - any implementation of {@link Component} interface and its extensions.
 * @param <K> - type of key objects.
 * @see Component
 * @see PackableComponent
 * @see Map
 * @since 0.4.6
 */
public interface MapProviderComponent<K, T extends Component>
        extends PackableComponent, Map<K, T> {
}
