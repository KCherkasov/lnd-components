package ru.rougegibbons.components.interfaces.core;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.components.interfaces.Component;

import java.util.List;

/**
 * Component interface for pairs of numbers.
 * @param <T> - any class derived from {@link Number} class.
 */
public interface PairNumberComponent<T extends Number> extends Component {
    /**
     * get first number in the pair.
     * @return first stored number.
     */
    @NotNull T getFirstValue();

    /**
     * get second number in the pair.
     * @return second stored number.
     */
    @NotNull T getSecondValue();

    /**
     * gets both numbers and represents them as a list.
     * @return list where 0th element is first number and 1st - the second one.
     */
    @NotNull List<T> getBoth();

    interface PairIntComponent extends PairNumberComponent<Integer> {}
    interface PairLongComponent extends PairNumberComponent<Long> {}
    interface PairFloatComponent extends PairNumberComponent<Float> {}
}
