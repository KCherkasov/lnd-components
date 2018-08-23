package ru.rougegibbons.landsanddungeons.components.core;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.Component;

/**
 * Minimal implementation of the {@link Component} interface,
 * containing id field. Ids generation though must be defined in derived classes.
 * @see Component
 */
public abstract class AbstractComponent implements Component {
    private final Long id;

    /**
     * default constructor, uses initId() method to generate instance id.
     */
    public AbstractComponent() {
        id = initId();
    }

    /**
     * constructor for loading/unpacking component from resources or packets.
     * @param id - component's id.
     */
    public AbstractComponent(@NotNull Long id) {
        this.id = id;
    }

    /**
     * returns instance id.
     * @see Component
     * @return instance id.
     */
    @Override
    public @NotNull Long getId() {
        return id;
    }

    /**
     * method for generating instance id on component's creation.
     * @return generated instance id.
     */
    protected abstract @NotNull Long initId();
}
