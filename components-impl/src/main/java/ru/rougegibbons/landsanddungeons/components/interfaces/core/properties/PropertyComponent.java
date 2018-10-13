package ru.rougegibbons.landsanddungeons.components.interfaces.core.properties;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.PackableComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.LabeledComponent;

/**
 * default interface for components representing game entities properties.
 *
 * @since 0.4.6
 */
public interface PropertyComponent extends PackableComponent, LabeledComponent {
    /**
     * checks if property instance has update() method.
     *
     * @return true if instance has update() method, false otherwise.
     */
    default @NotNull Boolean isUpdateable() {
        return false;
    }
}
