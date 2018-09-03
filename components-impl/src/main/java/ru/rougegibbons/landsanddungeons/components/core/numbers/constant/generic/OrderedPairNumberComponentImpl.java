package ru.rougegibbons.landsanddungeons.components.core.numbers.constant.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.PairNumberComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * generic implementation of {@link PairNumberComponent} allowing custom ordering of stored values.
 *
 * @param <T> - any {@link Number} class extension
 * @see PairNumberComponent
 * @see AbstractComponent
 * @see Number
 * @since 0.3.3
 */
public abstract class OrderedPairNumberComponentImpl<T extends Number>
        extends AbstractComponent implements PairNumberComponent<T> {
    private final T firstValue;
    private final T secondValue;

    /**
     * default constructor for creating new components.
     *
     * @param firstValue  - first value to store.
     * @param secondValue - second value to store.
     */
    public OrderedPairNumberComponentImpl(@NotNull T firstValue,
                                          @NotNull T secondValue) {
        super();
        this.firstValue = defineFirstValue(firstValue, secondValue);
        this.secondValue = defineSecondValue(firstValue, secondValue);
    }

    /**
     * constructor for deserialization from some data model.
     *
     * @param id          - instance id.
     * @param firstValue  - first value to store.
     * @param secondValue - second value to store.
     */
    public OrderedPairNumberComponentImpl(@NotNull Long id,
                                          @NotNull T firstValue,
                                          @NotNull T secondValue) {
        super(id);
        this.firstValue = defineFirstValue(firstValue, secondValue);
        this.secondValue = defineSecondValue(firstValue, secondValue);
    }

    /**
     * gets first stored value.
     *
     * @return first stored value.
     */
    @Override
    public @NotNull T getFirstValue() {
        return firstValue;
    }

    /**
     * gets second stored value.
     *
     * @return second stored value.
     */
    @Override
    public @NotNull T getSecondValue() {
        return secondValue;
    }

    /**
     * gets both stored value and represents them as a list with first value under 0 index, and second - under 1.
     *
     * @return list containing both stored values.
     */
    @Override
    public @NotNull List<T> getBoth() {
        final List<T> list = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);

        list.add(firstValue);
        list.add(secondValue);

        return list;
    }

    /**
     * default implementation of ordering function. Picks a number to put in first stored value from two numbers.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return number to put in first stored value.
     */
    protected @NotNull T defineFirstValue(@NotNull T first,
                                          @NotNull T second) {
        return first;
    }

    /**
     * default implementation of ordering function. Picks a number to put in second stored value from two numbers.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return number to put in second stored value.
     */
    protected @NotNull T defineSecondValue(@NotNull T first,
                                           @NotNull T second) {
        return second;
    }
}
