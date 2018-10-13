package ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins;

import ru.rougegibbons.landsanddungeons.components.interfaces.Component;

/**
 * interface providing update() method for active components.
 *
 * @see Component
 * @since 0.4.6
 */
public interface UpdateableComponent {
    /**
     * components update method, that will be called every simulation tick.
     */
    default void update() {
    }
}
