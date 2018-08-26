package ru.rougegibbons.landsanddungeons.components.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Main interface for game mechanics components in ECS model.
 */
public interface Component {
    /**
     * Returns component instance's unique ID.
     * @return instance ID.
     */
    @NotNull Long getId();
}
