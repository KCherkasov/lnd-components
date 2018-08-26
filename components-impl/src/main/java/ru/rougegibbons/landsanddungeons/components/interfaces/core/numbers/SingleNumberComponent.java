package ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.PackableComponent;

/**
 * Generic interface for components containing single number.
 * @param <T> - any {@link Number} class derivatives.
 * @see Number
 */
public interface SingleNumberComponent<T extends Number> extends PackableComponent {
    /**
     * get stored value.
     * @return stored value
     */
    @NotNull T getValue();

    interface SingleIntComponent extends SingleNumberComponent<Integer> {

    }

    interface SingleLongComponent extends SingleNumberComponent<Long> {
    }

    interface SingleFloatComponent extends SingleNumberComponent<Float> {
    }
}
