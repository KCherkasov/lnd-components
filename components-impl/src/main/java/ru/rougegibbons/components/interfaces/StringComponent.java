package ru.rougegibbons.components.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * {@link Component} interface extension for text containers.
 */
public interface StringComponent extends Component {
    /**
     * get access to the text stored in the component.
     * @return component text.
     */
    @NotNull String getText();
}
