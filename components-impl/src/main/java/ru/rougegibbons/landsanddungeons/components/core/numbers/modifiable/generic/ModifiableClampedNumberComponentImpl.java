package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiableClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.ComparatorProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.TrimmerProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic implementation of {@link ModifiableClampedNumberComponent} interface.
 * Ensures that lower boundary always remains less or equal than upper boundary,
 * and current value is always within [lowerBoundary; upperBoundary] range.
 *
 * @param <T> - any extension of {@link Number} class.
 * @see AbstractComponent
 * @see ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent
 * @see ModifiableClampedNumberComponent
 * @see ArithmeticsProxy
 * @see TrimmerProxy
 * @see ComparatorProxy
 * @see Number
 * @since 0.3.5
 */
public abstract class ModifiableClampedNumberComponentImpl<T extends Number>
        extends AbstractComponent implements ModifiableClampedNumberComponent<T> {
    private T lowerBoundary;
    private T upperBoundary;
    private T currentValue;

    /**
     * constructor for creating new components with preinitialized current value.
     *
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     */
    public ModifiableClampedNumberComponentImpl(@NotNull T lowerBoundary,
                                                @NotNull T upperBoundary) {
        super();
        this.lowerBoundary = getMinimalBoundary(lowerBoundary, upperBoundary);
        this.upperBoundary = getMaximalBoundary(lowerBoundary, upperBoundary);
        currentValue = initCurrentValue();
    }

    /**
     * constructor for creating new components with given current value.
     *
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     * @param currentValue  - current value.
     */
    public ModifiableClampedNumberComponentImpl(@NotNull T lowerBoundary,
                                                @NotNull T upperBoundary,
                                                @NotNull T currentValue) {
        super();
        this.lowerBoundary = getMinimalBoundary(lowerBoundary, upperBoundary);
        this.upperBoundary = getMaximalBoundary(lowerBoundary, upperBoundary);
        this.currentValue = clampCurrentValue(currentValue);
    }

    /**
     * constructor for deserialization from some data model instance.
     *
     * @param id            - instance id.
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     * @param currentValue  - current value.
     */
    public ModifiableClampedNumberComponentImpl(@NotNull Long id,
                                                @NotNull T lowerBoundary,
                                                @NotNull T upperBoundary,
                                                @NotNull T currentValue) {
        super(id);
        this.lowerBoundary = getMinimalBoundary(lowerBoundary, upperBoundary);
        this.upperBoundary = getMaximalBoundary(lowerBoundary, upperBoundary);
        this.currentValue = clampCurrentValue(currentValue);
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @return lower boundary.
     */
    @Override
    public @NotNull T getLowerBoundary() {
        return lowerBoundary;
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @return upper boundary.
     */
    @Override
    public @NotNull T getUpperBoundary() {
        return upperBoundary;
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @return {@link List} instance with both boundaries (lower at 0, upper at 1).
     */
    @Override
    public @NotNull List<T> getBoundaries() {
        final List<T> boundaries = new ArrayList<>();

        boundaries.add(lowerBoundary);
        boundaries.add(upperBoundary);

        return boundaries;
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @return current value.
     */
    @Override
    public @NotNull T getCurrentValue() {
        return currentValue;
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @return true if current value is equal to the lower boundary, false otherwise.
     */
    @Override
    public @NotNull Boolean isEmpty() {
        return getComparatorProxy().areEqual(currentValue, lowerBoundary);
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @return true if current value is equal to the upper boundary, false otherwise.
     */
    @Override
    public @NotNull Boolean isFull() {
        return getComparatorProxy().areEqual(currentValue, upperBoundary);
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @return {@link Integer} percentage form of currentValue / upperBoundary.
     */
    @Override
    public @NotNull Integer getPercentageInt() {
        return FloatMath.multiply(getPercentageFloat(),
                (float) Constants.PERCENTAGE_CAP_INT).intValue();
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @return {@link Float} percentage form of currentValue / upperBoundary.
     */
    @Override
    public @NotNull Float getPercentageFloat() {
        return FloatMath.divide(currentValue.floatValue(), upperBoundary.floatValue());
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @param value - number to save as current value.
     */
    @Override
    public void setCurrentValue(@NotNull T value) {
        currentValue = clampCurrentValue(value);
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @param amount - amount to add to the current value.
     */
    @Override
    public void increaseCurrentValue(@NotNull T amount) {
        currentValue = clampCurrentValue(
                getArithmeticsProxy().add(currentValue, amount));
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @param amount - amount to subtract to the current value.
     */
    @Override
    public void decreaseCurrentValue(@NotNull T amount) {
        currentValue = clampCurrentValue(
                getArithmeticsProxy().subtract(currentValue, amount));
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @param percent - percentage to alter current value.
     */
    @Override
    public void modifyCurrentValueByPercentage(@NotNull Integer percent) {
        currentValue = clampCurrentValue(
                getArithmeticsProxy().modifyByPercentage(
                        currentValue, percent));
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent}.
     *
     * @param percent - percentage to alter current value.
     */
    @Override
    public void modifyCurrentValueByPercentage(@NotNull Float percent) {
        currentValue = clampCurrentValue(
                getArithmeticsProxy().modifyByPercentage(
                        currentValue, percent));
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param value - value to set as new lower value.
     */
    @Override
    public void setLowerBoundary(@NotNull T value) {
        lowerBoundary = value;
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param value - value to set as new upper value.
     */
    @Override
    public void setUpperBoundary(@NotNull T value) {
        upperBoundary = value;
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param lower - new lower value.
     * @param upper - new upper value.
     */
    @Override
    public void setBoundaries(@NotNull T lower,
                              @NotNull T upper) {
        lowerBoundary = lower;
        upperBoundary = upper;
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param amount - amount to increase lower boundary on.
     */
    @Override
    public void increaseLowerBoundary(@NotNull T amount) {
        lowerBoundary = getArithmeticsProxy().add(lowerBoundary, amount);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param amount - amount to increase upper boundary on.
     */
    @Override
    public void increaseUpperBoundary(@NotNull T amount) {
        upperBoundary = getArithmeticsProxy().add(upperBoundary, amount);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param amount - amount to increase both boundaries on.
     */
    @Override
    public void increaseBoundaries(@NotNull T amount) {
        increaseBoundaries(amount, amount);
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param lower - amount to increase lower boundary on.
     * @param upper - amount to increase upper boundary on.
     */
    @Override
    public void increaseBoundaries(@NotNull T lower,
                                   @NotNull T upper) {
        lowerBoundary = getArithmeticsProxy().add(lowerBoundary, lower);
        upperBoundary = getArithmeticsProxy().add(upperBoundary, upper);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param amount - amount to decrease lower boundary on.
     */
    @Override
    public void decreaseLowerBoundary(@NotNull T amount) {
        lowerBoundary = getArithmeticsProxy().subtract(lowerBoundary, amount);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param amount - amount to decrease upper boundary on.
     */
    @Override
    public void decreaseUpperBoundary(@NotNull T amount) {
        upperBoundary = getArithmeticsProxy().subtract(upperBoundary, amount);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param amount - amount to decrease both boundaries on.
     */
    @Override
    public void decreaseBoundaries(@NotNull T amount) {
        decreaseBoundaries(amount, amount);
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param lower - amount to decrease lower boundary on.
     * @param upper - amount to decrease upper boundary on.
     */
    @Override
    public void decreaseBoundaries(@NotNull T lower,
                                   @NotNull T upper) {
        lowerBoundary = getArithmeticsProxy().subtract(lowerBoundary, lower);
        upperBoundary = getArithmeticsProxy().subtract(upperBoundary, upper);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param percent - percentage to modify lower boundary
     */
    @Override
    public void modifyLowerByPercentage(@NotNull Integer percent) {
        lowerBoundary = getArithmeticsProxy().modifyByPercentage(lowerBoundary, percent);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param percent - percentage to modify lower boundary
     */
    @Override
    public void modifyLowerByPercentage(@NotNull Float percent) {
        lowerBoundary = getArithmeticsProxy().modifyByPercentage(lowerBoundary, percent);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param percent - percentage to modify upper boundary
     */
    @Override
    public void modifyUpperByPercentage(@NotNull Integer percent) {
        upperBoundary = getArithmeticsProxy().modifyByPercentage(upperBoundary, percent);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param percent - percentage to modify upper boundary
     */
    @Override
    public void modifyUpperByPercentage(@NotNull Float percent) {
        upperBoundary = getArithmeticsProxy().modifyByPercentage(upperBoundary, percent);
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param percent - percentage to modify both boundaries.
     */
    @Override
    public void modifyBoundariesByPercentage(@NotNull Integer percent) {
        modifyBoundariesByPercentage(percent, percent);
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param percent - percentage to modify both boundaries.
     */
    @Override
    public void modifyBoundariesByPercentage(@NotNull Float percent) {
        modifyBoundariesByPercentage(percent, percent);
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param lower - percentage to modify lower boundary.
     * @param upper - percentage to modify upper boundary.
     */
    @Override
    public void modifyBoundariesByPercentage(@NotNull Integer lower,
                                             @NotNull Integer upper) {
        lowerBoundary = getArithmeticsProxy().modifyByPercentage(lowerBoundary, lower);
        upperBoundary = getArithmeticsProxy().modifyByPercentage(upperBoundary, upper);
        swapAndClamp();
    }

    /**
     * see {@link ModifiableClampedNumberComponent} description.
     *
     * @param lower - percentage to modify lower boundary.
     * @param upper - percentage to modify upper boundary.
     */
    @Override
    public void modifyBoundariesByPercentage(@NotNull Float lower,
                                             @NotNull Float upper) {
        lowerBoundary = getArithmeticsProxy().modifyByPercentage(lowerBoundary, lower);
        upperBoundary = getArithmeticsProxy().modifyByPercentage(upperBoundary, upper);
        swapAndClamp();
    }

    /**
     * get some {@link ArithmeticsProxy} interface implementation instance.
     *
     * @return {@link ArithmeticsProxy} interface implementation.
     */
    protected abstract @NotNull ArithmeticsProxy<T> getArithmeticsProxy();

    /**
     * get some {@link TrimmerProxy} interface implementation instance.
     *
     * @return {@link TrimmerProxy} interface implementation.
     */
    protected abstract @NotNull TrimmerProxy<T> getTrimmerProxy();

    /**
     * get some {@link ComparatorProxy} interface implentation instance.
     *
     * @return {@link ComparatorProxy} interface implementation.
     */
    protected @NotNull ComparatorProxy<T> getComparatorProxy() {
        return getTrimmerProxy().getComparatorProxy();
    }

    /**
     * compares two values and returns minimal one.
     *
     * @param lhs - first value.
     * @param rhs - second value.
     * @return lhs if lhs <= rhs, rhs otherwise.
     */
    private @NotNull T getMinimalBoundary(@NotNull T lhs,
                                          @NotNull T rhs) {
        return getComparatorProxy().lessOrEqual(lhs, rhs) ? lhs : rhs;
    }

    /**
     * compares two values and returns maximal one.
     *
     * @param lhs - first value.
     * @param rhs - second value.
     * @return rhs if rhs >= lhs, lhs otherwise.
     */
    private @NotNull T getMaximalBoundary(@NotNull T lhs,
                                          @NotNull T rhs) {
        return getComparatorProxy().greaterOrEqual(rhs, lhs) ? rhs : lhs;
    }

    /**
     * get value to initialize current value by default.
     *
     * @return lower boundary.
     */
    private @NotNull T initCurrentValue() {
        return lowerBoundary;
    }

    /**
     * ensures that given value is within [lowerBoundary; upperBoundary] range.
     *
     * @param value - value to clamp.
     * @return - value if lowerBoundary <= value <= upperBoundary, one of the boundaries otherwise.
     */
    private @NotNull T clampCurrentValue(@NotNull T value) {
        return getTrimmerProxy().clamp(value, lowerBoundary, upperBoundary);
    }

    /**
     * checks if value is not in [lowerBoundary; upperBoundary] range.
     *
     * @param value - value to check range for.
     * @return true if value is less than lowerBoundary or value is greater than upperBoundary, false otherwise.
     */
    private @NotNull Boolean isOutOfRange(@NotNull T value) {
        return getComparatorProxy().isGreater(value, upperBoundary)
                || getComparatorProxy().isLess(value, lowerBoundary);
    }

    /**
     * ensures that lower boundary is not greater than upper boundary and current value is within boundaries.
     */
    private void swapAndClamp() {
        if (getComparatorProxy().isGreater(lowerBoundary, upperBoundary)) {
            upperBoundary = getArithmeticsProxy().add(upperBoundary, lowerBoundary);
            lowerBoundary = getArithmeticsProxy().subtract(upperBoundary, lowerBoundary);
            upperBoundary = getArithmeticsProxy().subtract(upperBoundary, lowerBoundary);
        }
        if (isOutOfRange(currentValue)) {
            currentValue = clampCurrentValue(currentValue);
        }
    }
}
