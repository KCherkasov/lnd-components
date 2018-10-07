package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiablePairNumberComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.ComparatorProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ModifiablePairNumberComponent} interface implementation allowing to define custom numbers ordering.
 *
 * @param <T> - any extension of {@link Number} class.
 * @see ModifiablePairNumberComponent
 * @see Number
 * @see ArithmeticsProxy
 * @see ComparatorProxy
 * @see AbstractComponent
 * @since 0.3.3
 */
public abstract class ModifiableOrderedPairNumberComponentImpl<T extends Number>
        extends AbstractComponent implements ModifiablePairNumberComponent<T> {
    private T firstValue;
    private T secondValue;

    /**
     * default constructor to create new component with predefined stored values.
     */
    public ModifiableOrderedPairNumberComponentImpl() {
        super();
        initValues();
    }

    /**
     * constructor for creating new components with given values.
     *
     * @param firstValue  - first number to store.
     * @param secondValue - second number to store.
     */
    public ModifiableOrderedPairNumberComponentImpl(@NotNull T firstValue,
                                                    @NotNull T secondValue) {
        super();
        this.firstValue = defineFirstValue(firstValue, secondValue);
        this.secondValue = defineSecondValue(firstValue, secondValue);
    }

    /**
     * constructor for deserializing component from some data model.
     *
     * @param id          - instance id.
     * @param firstValue  - first number to store.
     * @param secondValue - second number to store.
     */
    public ModifiableOrderedPairNumberComponentImpl(@NotNull Long id,
                                                    @NotNull T firstValue,
                                                    @NotNull T secondValue) {
        super(id);
        this.firstValue = defineFirstValue(firstValue, secondValue);
        this.secondValue = defineSecondValue(firstValue, secondValue);
    }

    /**
     * get first stored value.
     *
     * @return first stored value.
     */
    @Override
    public @NotNull T getFirstValue() {
        return firstValue;
    }

    /**
     * get second stored value.
     *
     * @return second stored value.
     */
    @Override
    public @NotNull T getSecondValue() {
        return secondValue;
    }

    /**
     * get both stored numbers.
     *
     * @return {@link List} containing both stored numbers.
     */
    @Override
    public @NotNull List<T> getBoth() {
        final List<T> list = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);

        list.add(firstValue);
        list.add(secondValue);

        return list;
    }

    /**
     * set given values to both stored numbers in accordance with ordering rules.
     *
     * @param first  - first number to assign.
     * @param second - second number to assign.
     */
    @Override
    public void setBoth(@NotNull T first,
                        @NotNull T second) {
        firstValue = defineFirstValue(first, second);
        secondValue = defineSecondValue(first, second);
    }

    /**
     * set given value to first stored value, preserving the ordering.
     *
     * @param value - value to assign to the first stored value.
     */
    @Override
    public void setFirstValue(@NotNull T value) {
        firstValue = value;
        if (getComparatorProxy().isGreater(firstValue, secondValue)) {
            swap();
        }
    }

    /**
     * set given value to second stored value, preserving the ordering.
     *
     * @param value - value to assign to the second stored value.
     */
    @Override
    public void setSecondValue(@NotNull T value) {
        secondValue = value;
        if (getComparatorProxy().isLess(secondValue, firstValue)) {
            swap();
        }
    }

    /**
     * increases both stored numbers by same amount.
     *
     * @param amount - amount to increase both stored values on.
     */
    @Override
    public void increaseBoth(@NotNull T amount) {
        firstValue = getArithmeticsProxy().add(firstValue, amount);
        secondValue = getArithmeticsProxy().add(secondValue, amount);
    }

    /**
     * increases both stored numbers by given amounts, presrving the ordering.
     *
     * @param firstIncrease  - amount to increase first stored value on.
     * @param secondIncrease - amount to increase second stored value on.
     */
    @Override
    public void increaseBoth(@NotNull T firstIncrease,
                             @NotNull T secondIncrease) {
        final T newFirst = getArithmeticsProxy().add(firstValue, firstIncrease);
        final T newSecond = getArithmeticsProxy().add(secondValue, secondIncrease);

        firstValue = defineFirstValue(newFirst, newSecond);
        secondValue = defineSecondValue(newFirst, newSecond);
    }

    /**
     * increases first value on given amount, then checks the ordering.
     *
     * @param amount - amount to increase first stored value on.
     */
    @Override
    public void increaseFirstValue(@NotNull T amount) {
        firstValue = getArithmeticsProxy().add(firstValue, amount);
        if (!getComparatorProxy().areEqual(
                defineFirstValue(firstValue, secondValue), firstValue)) {
            swap();
        }
    }

    /**
     * increases second value on given amount, then checks the ordering.
     *
     * @param amount - amount to increase second stored value on.
     */
    @Override
    public void increaseSecondValue(@NotNull T amount) {
        secondValue = getArithmeticsProxy().add(secondValue, amount);
        if (!getComparatorProxy().areEqual(
                defineSecondValue(firstValue, secondValue), secondValue)) {
            swap();
        }
    }

    /**
     * decreases both stored numbers on the same amount.
     *
     * @param amount - amount to decrease both stored values on.
     */
    @Override
    public void decreaseBoth(@NotNull T amount) {
        firstValue = getArithmeticsProxy().subtract(firstValue, amount);
        secondValue = getArithmeticsProxy().subtract(secondValue, amount);
    }

    /**
     * decreases both stored numbers on given amounts, then checks the ordering.
     *
     * @param firstDecrease  - amount to decrease first stored value on.
     * @param secondDecrease - amount to decrease second stored value on.
     */
    @Override
    public void decreaseBoth(@NotNull T firstDecrease,
                             @NotNull T secondDecrease) {
        final T newFirst = getArithmeticsProxy().subtract(firstValue, firstDecrease);
        final T newSecond = getArithmeticsProxy().subtract(secondValue, secondDecrease);

        firstValue = defineFirstValue(newFirst, newSecond);
        secondValue = defineSecondValue(newFirst, newSecond);
    }

    /**
     * decreases first value on given amount, then checks the ordering.
     *
     * @param amount - amount to decrease first stored value on.
     */
    @Override
    public void decreaseFirstValue(@NotNull T amount) {
        firstValue = getArithmeticsProxy().subtract(firstValue, amount);
        if (!getComparatorProxy().areEqual(
                defineFirstValue(firstValue, secondValue), firstValue)) {
            swap();
        }
    }

    /**
     * decreases second value on given amount, then checks the ordering.
     *
     * @param amount - amount to decrease second stored value on.
     */
    @Override
    public void decreaseSecondValue(@NotNull T amount) {
        secondValue = getArithmeticsProxy().subtract(secondValue, amount);
        if (!getComparatorProxy().areEqual(
                defineSecondValue(firstValue, secondValue), secondValue)) {
            swap();
        }
    }

    /**
     * modifies both stored numbers by the same percentage.
     *
     * @param percent - float percentage to modify both stored values by.
     */
    @Override
    public void modifyBothByPercentage(@NotNull Float percent) {
        firstValue = getArithmeticsProxy().modifyByPercentage(firstValue, percent);
        secondValue = getArithmeticsProxy().modifyByPercentage(secondValue, percent);
    }

    /**
     * modifies both stored numbers by the same percentage.
     *
     * @param percent - integer percentage to modify both stored values by.
     */
    @Override
    public void modifyBothByPercentage(@NotNull Integer percent) {
        firstValue = getArithmeticsProxy().modifyByPercentage(firstValue, percent);
        secondValue = getArithmeticsProxy().modifyByPercentage(secondValue, percent);
    }

    /**
     * modifies both stored numbers by given percentages, then checks the ordering.
     *
     * @param firstPercent  - float percentage to modify first stored value by.
     * @param secondPercent - float percentage to modify second stored value by.
     */
    @Override
    public void modifyBothByPercentage(@NotNull Float firstPercent,
                                       @NotNull Float secondPercent) {
        firstValue = getArithmeticsProxy().modifyByPercentage(firstValue, firstPercent);
        secondValue = getArithmeticsProxy().modifyByPercentage(secondValue, secondPercent);
        if (!getComparatorProxy().areEqual(
                defineFirstValue(firstValue, secondValue), firstValue)) {
            swap();
        }
    }

    /**
     * modifies both stored numbers by given percentages, then checks the ordering.
     *
     * @param firstPercent  - integer percentage to modify first stored value by.
     * @param secondPercent - integer percentage to modify second stored value by.
     */
    @Override
    public void modifyBothByPercentage(@NotNull Integer firstPercent,
                                       @NotNull Integer secondPercent) {
        modifyBothByPercentage(FloatMath.toPercent(firstPercent),
                FloatMath.toPercent(secondPercent));
    }

    /**
     * modifies first stored value by given percentage, then checks the ordering.
     *
     * @param percent - float percentage to modify first stored value by.
     */
    @Override
    public void modifyFirstByPercentage(@NotNull Float percent) {
        firstValue = getArithmeticsProxy().modifyByPercentage(firstValue, percent);
        if (!getComparatorProxy().areEqual(
                defineFirstValue(firstValue, secondValue), firstValue)) {
            swap();
        }
    }

    /**
     * modifies first stored value by given percentage, then checks the ordering.
     *
     * @param percent - integer percentage to modify first stored value by.
     */
    @Override
    public void modifyFirstByPercentage(@NotNull Integer percent) {
        modifyFirstByPercentage(FloatMath.toPercent(percent));
    }

    /**
     * modifies second stored value by given percentage, then checks the ordering.
     *
     * @param percent - float percentage to modify second stored value by.
     */
    @Override
    public void modifySecondByPercentage(@NotNull Float percent) {
        secondValue = getArithmeticsProxy().modifyByPercentage(secondValue, percent);
        if (!getComparatorProxy().areEqual(
                defineSecondValue(firstValue, secondValue), secondValue)) {
            swap();
        }
    }

    /**
     * modifies second stored value by given percentage, then checks the ordering.
     *
     * @param percent - integer percentage to modify second stored value by.
     */
    @Override
    public void modifySecondByPercentage(@NotNull Integer percent) {
        modifySecondByPercentage(FloatMath.toPercent(percent));
    }

    /**
     * initializes both stored numbers with default values.
     */
    protected abstract void initValues();

    /**
     * gets {@link ArithmeticsProxy} instance for the class.
     *
     * @return some {@link ArithmeticsProxy} implementation.
     */
    protected abstract @NotNull ArithmeticsProxy<T> getArithmeticsProxy();

    /**
     * gets {@link ComparatorProxy} instance for the class.
     *
     * @return some {@link ComparatorProxy} implementation.
     */
    protected abstract @NotNull ComparatorProxy<T> getComparatorProxy();

    /**
     * picks the first number to store from the given pair. Default implementation contains no ordering rules.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return number chosen to be stored as first.
     */
    protected @NotNull T defineFirstValue(@NotNull T first,
                                          @NotNull T second) {
        return first;
    }

    /**
     * picks the second number to store from the given pair. Default implementation contains no ordering rules.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return number chosen to be stored as second.
     */
    protected @NotNull T defineSecondValue(@NotNull T first,
                                           @NotNull T second) {
        return second;
    }

    /**
     * swaps first and second stored numbers.
     */
    private void swap() {
        firstValue = getArithmeticsProxy().add(firstValue, secondValue);
        secondValue = getArithmeticsProxy().subtract(firstValue, secondValue);
        firstValue = getArithmeticsProxy().subtract(firstValue, secondValue);
    }
}
