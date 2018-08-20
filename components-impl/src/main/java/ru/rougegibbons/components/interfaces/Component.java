package ru.rougegibbons.components.interfaces;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.components.models.ComponentModel;

/**
 * Main interface for game mechanics components in ECS model.
 */
public interface Component {
    /**
     * Returns component instance's unique ID.
     * @return instance ID.
     */
    @NotNull Long getId();

    /**
     * packs instance data into JSON model, suitable for sending as packet or write into a file.
     * @return packed instance data.
     */
    @NotNull ComponentModel pack();
}
