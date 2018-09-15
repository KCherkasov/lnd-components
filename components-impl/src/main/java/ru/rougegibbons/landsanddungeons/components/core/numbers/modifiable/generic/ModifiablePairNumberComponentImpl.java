package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiablePairNumberComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * generic implementation of {@link ModifiablePairNumberComponent} interface.
 *
 * @param <T> - any extension of {@link Number} class.
 * @see ModifiablePairNumberComponent
 * @see AbstractComponent
 * @see Number
 * @since 0.3.3
 */
public abstract class ModifiablePairNumberComponentImpl<T extends Number>
        extends AbstractComponent implements ModifiablePairNumberComponent<T> {
    private T firstValue;
    private T secondValue;

    /**
     * default constructor to create new component with preinitialized stored values.
     */
    public ModifiablePairNumberComponentImpl() {
        super();
        initValues();
    }

    /**
     * constructor for creating new component with externally passed values.
     *
     * @param firstValue  - first value to store.
     * @param secondValue - second value to store.
     */
    public ModifiablePairNumberComponentImpl(@NotNull T firstValue,
                                             @NotNull T secondValue) {
        super();
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /**
     * constructor for deserializing a component from data model.
     *
     * @param id          - instance id.
     * @param firstValue  - first value to store.
     * @param secondValue - second value to store.
     */
    public ModifiablePairNumberComponentImpl(@NotNull Long id,
                                             @NotNull T firstValue,
                                             @NotNull T secondValue) {
        super(id);
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /**
     * get current value of first stored number.
     *
     * @return first stored value.
     */
    @Override
    public @NotNull T getFirstValue() {
        return firstValue;
    }

    /**
     * get current value of second stored number.
     *
     * @return second stored number.
     */
    @Override
    public @NotNull T getSecondValue() {
        return secondValue;
    }

    /**
     * get both stored value as a list where first value is under 0 index, and second - under 1.
     *
     * @return list with both stored numbers.
     */
    @Override
    public @NotNull List<T> getBoth() {
        final List<T> list = new ArrayList<>();

        list.add(firstValue);
        list.add(secondValue);

        return list;
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param value - value to assign to the first stored value.
     */
    @Override
    public void setFirstValue(@NotNull T value) {
        firstValue = value;
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param value - value to assign to the second stored value.
     */
    @Override
    public void setSecondValue(@NotNull T value) {
        secondValue = value;
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param first  -  value to assign to the first stored value.
     * @param second - value to assign to second stored value
     */
    @Override
    public void setBoth(@NotNull T first,
                        @NotNull T second) {
        firstValue = first;
        secondValue = second;
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param amount - amount to increase both stored values on.
     */
    @Override
    public void increaseBoth(@NotNull T amount) {
        increaseFirstValue(amount);
        increaseSecondValue(amount);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param firstIncrease  - amount to increase first stored value on.
     * @param secondIncrease - amount to increase second stored value on.
     */
    @Override
    public void increaseBoth(@NotNull T firstIncrease,
                             @NotNull T secondIncrease) {
        increaseFirstValue(firstIncrease);
        increaseSecondValue(secondIncrease);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param amount - amount to increase first stored value on.
     */
    @Override
    public void increaseFirstValue(@NotNull T amount) {
        firstValue = getArithmeticsProxy().add(firstValue, amount);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param amount - amount to increase second stored value on.
     */
    @Override
    public void increaseSecondValue(@NotNull T amount) {
        secondValue = getArithmeticsProxy().add(secondValue, amount);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param amount - amount to decrease both stored values on.
     */
    @Override
    public void decreaseBoth(@NotNull T amount) {
        decreaseFirstValue(amount);
        decreaseSecondValue(amount);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param firstDecrease  - amount to decrease first stored value on.
     * @param secondDecrease - amount to decrease second stored value on.
     */
    @Override
    public void decreaseBoth(@NotNull T firstDecrease,
                             @NotNull T secondDecrease) {
        decreaseFirstValue(firstDecrease);
        decreaseSecondValue(secondDecrease);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param amount - amount to decrease first stored value on.
     */
    @Override
    public void decreaseFirstValue(@NotNull T amount) {
        firstValue = getArithmeticsProxy().subtract(firstValue, amount);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param amount - amount to decrease second stored value on.
     */
    @Override
    public void decreaseSecondValue(@NotNull T amount) {
        secondValue = getArithmeticsProxy().subtract(secondValue, amount);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param percent - integer percentage to modify both stored values by.
     */
    @Override
    public void modifyBothByPercentage(@NotNull Integer percent) {
        modifyBothByPercentage(toPercent(percent));
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param percent - float percentage to modify both stored values by.
     */
    @Override
    public void modifyBothByPercentage(@NotNull Float percent) {
        modifyFirstByPercentage(percent);
        modifySecondByPercentage(percent);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param firstPercent  - float percentage to modify first stored value by.
     * @param secondPercent - float percentage to modify second stored value by.
     */
    @Override
    public void modifyBothByPercentage(@NotNull Float firstPercent,
                                       @NotNull Float secondPercent) {
        modifyFirstByPercentage(firstPercent);
        modifySecondByPercentage(secondPercent);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param firstPercent  - integer percentage to modify first stored value by.
     * @param secondPercent - integer percentage to modify second stored value by.
     */
    @Override
    public void modifyBothByPercentage(@NotNull Integer firstPercent,
                                       @NotNull Integer secondPercent) {
        modifyFirstByPercentage(firstPercent);
        modifySecondByPercentage(secondPercent);
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param percent - integer percentage to modify first stored value by.
     */
    @Override
    public void modifyFirstByPercentage(@NotNull Integer percent) {
        modifyFirstByPercentage(toPercent(percent));
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param percent - float percentage to modify first stored value by.
     */
    @Override
    public void modifyFirstByPercentage(@NotNull Float percent) {
        firstValue = floatToType(FloatMath.multiply(firstValue.floatValue(),
                Constants.PERCENTAGE_CAP_FLOAT + percent));
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param percent - integer percentage to modify second stored value by.
     */
    @Override
    public void modifySecondByPercentage(@NotNull Integer percent) {
        modifySecondByPercentage(toPercent(percent));
    }

    /**
     * see description in {@link ModifiablePairNumberComponent}.
     *
     * @param percent - float percentage to modify second stored value by.
     */
    @Override
    public void modifySecondByPercentage(@NotNull Float percent) {
        secondValue = floatToType(FloatMath.multiply(secondValue.floatValue(),
                Constants.PERCENTAGE_CAP_FLOAT + percent));
    }

    /**
     * initialization function to assign default values to stored numbers.
     */
    protected abstract void initValues();

    /**
     * get {@link ArithmeticsProxy} instance for class.
     *
     * @return some {@link ArithmeticsProxy} implementation instance.
     */
    protected abstract @NotNull ArithmeticsProxy<T> getArithmeticsProxy();

    /**
     * converts float number to concrete {@link Number} extension class.
     *
     * @param value - value to convert.
     * @return converted value.
     */
    protected abstract @NotNull T floatToType(@NotNull Float value);

    /**
     * converts integer percentage value into float point form.
     *
     * @param percent - integer percentage.
     * @return float point percentage.
     */
    private @NotNull Float toPercent(@NotNull Integer percent) {
        return FloatMath.divide(percent.floatValue(), (float) Constants.PERCENTAGE_CAP_INT);
    }
}
