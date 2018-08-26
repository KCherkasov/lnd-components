package ru.rougegibbons.landsanddungeons.components.interfaces.core.string;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.PackableComponent;

/**
 * {@link PackableComponent} interface extension for text containers.
 */
public interface StringComponent extends PackableComponent {
    /**
     * get access to the text stored in the component.
     * @return component text.
     */
    @NotNull String getText();
}
