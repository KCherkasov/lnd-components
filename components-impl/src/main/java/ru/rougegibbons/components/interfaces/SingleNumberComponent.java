package ru.rougegibbons.components.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Generic interface for components containing single number.
 * @param <T> - any {@link Number} class derivatives.
 * @see Number
 */
public interface SingleNumberComponent<T extends Number> extends Component {
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
