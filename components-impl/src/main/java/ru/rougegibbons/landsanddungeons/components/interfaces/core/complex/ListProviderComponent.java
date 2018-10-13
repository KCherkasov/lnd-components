package ru.rougegibbons.landsanddungeons.components.interfaces.core.complex;

import ru.rougegibbons.landsanddungeons.components.interfaces.Component;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.PackableComponent;

import java.util.List;

/**
 * interface for components containing other components
 * and representing them as a {@link List}.
 *
 * @param <T> - any implementation of {@link Component} interface and its extensions.
 * @see Component
 * @see PackableComponent
 * @see List
 * @since 0.4.6
 */
public interface ListProviderComponent<T extends Component> extends
        PackableComponent, List<T> {
}
