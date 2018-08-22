package ru.rougegibbons.components.core.numbers.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.components.core.AbstractComponent;
import ru.rougegibbons.components.interfaces.core.PairNumberComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * generic implementation of {@link PairNumberComponent} interface, suitable for every {@link Number} derivative class.
 * @param <T> - any class derived from {@link Number} class.
 * @see PairNumberComponent
 * @see Number
 */
public abstract class PairNumberComponentImpl<T extends Number>
        extends AbstractComponent implements PairNumberComponent<T> {
    private final T firstValue;
    private final T secondValue;

    /**
     * constuctor for instantiating a new component.
     * @param firstValue - first value to be stored.
     * @param secondValue - second value to be stored.
     */
    public PairNumberComponentImpl(@NotNull T firstValue,
                                   @NotNull T secondValue) {
        super();
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /**
     * constructor for deserialization from some data model.
     * @param id - instance id.
     * @param firstValue - first value to be stored.
     * @param secondValue - second value to be stored.
     */
    public PairNumberComponentImpl(@NotNull Long id,
                                   @NotNull T firstValue,
                                   @NotNull T secondValue) {
        super(id);
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /**
     * see {@link PairNumberComponent}.
     * @return first stored value.
     */
    @Override
    public @NotNull T getFirstValue() {
        return firstValue;
    }

    /**
     * see {@link PairNumberComponent}.
     * @return second stored value.
     */
    @Override
    public @NotNull T getSecondValue() {
        return secondValue;
    }

    /**
     * see {@link PairNumberComponent}.
     * @return list containing both stored values.
     */
    @Override
    public @NotNull List<T> getBoth() {
        final List<T> list = new ArrayList<>();
        list.add(firstValue);
        list.add(secondValue);
        return list;
    }
}
