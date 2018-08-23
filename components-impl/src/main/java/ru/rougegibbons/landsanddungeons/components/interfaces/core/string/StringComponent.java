package ru.rougegibbons.landsanddungeons.components.interfaces.core.string;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.Component;

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
