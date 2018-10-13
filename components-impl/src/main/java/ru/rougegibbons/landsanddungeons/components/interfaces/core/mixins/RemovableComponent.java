package ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins;

import org.jetbrains.annotations.NotNull;

/**
 * interface mixin for components that can be
 * detached from game entities/other components.
 *
 * @since 0.4.6
 */
public interface RemovableComponent {
    /**
     * checks if component is marked for removal.
     *
     * @return true if component is marked for removal, false otherwise.
     */
    @NotNull Boolean isToRemove();

    /**
     * marks component to removal.
     */
    void markToRemove();
}
