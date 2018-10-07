package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.ComparatorProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.TrimmerProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic implementation of {@link ClampedNumberComponent}.
 * Ensures that lower boundary is not greater than upper boundary
 * and current value is within [lowerBoundary; upperBoundary] range.
 *
 * @param <T> - any extension of {@link Number} class.
 * @see ClampedNumberComponent
 * @see AbstractComponent
 * @see Number
 * @see ArithmeticsProxy
 * @see ComparatorProxy
 * @see TrimmerProxy
 * @since 0.3.5
 */
public abstract class ClampedNumberComponentImpl<T extends Number>
        extends AbstractComponent implements ClampedNumberComponent<T> {
    private final T lowerBoundary;
    private final T upperBoundary;

    private T currentValue;

    /**
     * default constructor for creating new components with current value set equal to lower boundary.
     *
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     */
    public ClampedNumberComponentImpl(@NotNull T lowerBoundary,
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
    public ClampedNumberComponentImpl(@NotNull T lowerBoundary,
                                      @NotNull T upperBoundary,
                                      @NotNull T currentValue) {
        super();
        this.lowerBoundary = getMinimalBoundary(lowerBoundary, upperBoundary);
        this.upperBoundary = getMaximalBoundary(lowerBoundary, upperBoundary);
        this.currentValue = clampCurrentValue(currentValue);
    }

    /**
     * constructor for deserialization from some data model.
     *
     * @param id            - instance id.
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     * @param currentValue  - current value.
     */
    public ClampedNumberComponentImpl(@NotNull Long id,
                                      @NotNull T lowerBoundary,
                                      @NotNull T upperBoundary,
                                      @NotNull T currentValue) {
        super(id);
        this.lowerBoundary = getMinimalBoundary(lowerBoundary, upperBoundary);
        this.upperBoundary = getMaximalBoundary(lowerBoundary, upperBoundary);
        this.currentValue = clampCurrentValue(currentValue);
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @return lower boundary value.
     */
    @Override
    public @NotNull T getLowerBoundary() {
        return lowerBoundary;
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @return upper boundary value.
     */
    @Override
    public @NotNull T getUpperBoundary() {
        return upperBoundary;
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @return {@link List} instance containing both boundaries (lower at 0, upper at 1).
     */
    @Override
    public @NotNull List<T> getBoundaries() {
        final List<T> boundaries = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);

        boundaries.add(lowerBoundary);
        boundaries.add(upperBoundary);

        return boundaries;
    }


    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @return current value.
     */
    @Override
    public @NotNull T getCurrentValue() {
        return currentValue;
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @return true if current value is equal to lower boundary, false otherwise.
     */
    @Override
    public @NotNull Boolean isEmpty() {
        return getComparatorProxy().areEqual(currentValue, lowerBoundary);
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @return true if current value is equal to upper boundary, false otherwise.
     */
    @Override
    public @NotNull Boolean isFull() {
        return getComparatorProxy().areEqual(currentValue, upperBoundary);
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @return {@link Integer} percentage form of currentValue / upperBoundary.
     */
    @Override
    public @NotNull Integer getPercentageInt() {
        return FloatMath.multiply(getPercentageFloat(),
                (float) Constants.PERCENTAGE_CAP_INT).intValue();
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @return {@link Float} percentage form of currentValue / upperBoundary.
     */
    @Override
    public @NotNull Float getPercentageFloat() {
        return FloatMath.divide(currentValue.floatValue(), upperBoundary.floatValue());
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @param value - number to save as current value.
     */
    @Override
    public void setCurrentValue(@NotNull T value) {
        currentValue = clampCurrentValue(value);
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @param amount - amount to add to the current value.
     */
    @Override
    public void increaseCurrentValue(@NotNull T amount) {
        currentValue = clampCurrentValue(
                getArithmeticsProxy().add(currentValue, amount));
    }

    /**
     * see {@link ClampedNumberComponent} description.
     *
     * @param amount - amount to subtract to the current value.
     */
    @Override
    public void decreaseCurrentValue(@NotNull T amount) {
        currentValue = clampCurrentValue(
                getArithmeticsProxy().subtract(currentValue, amount));
    }

    /**
     * see {@link ClampedNumberComponent} description.
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
     * see {@link ClampedNumberComponent} description.
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
     * get some {@link ArithmeticsProxy}'s implementation instance.
     *
     * @return {@link ArithmeticsProxy} implementation instance.
     */
    protected abstract @NotNull ArithmeticsProxy<T> getArithmeticsProxy();

    /**
     * get some {@link ComparatorProxy} implementation instance, by default from {@link TrimmerProxy}.
     *
     * @return {@link ComparatorProxy} instance.
     */
    protected @NotNull ComparatorProxy<T> getComparatorProxy() {
        return getTrimmerProxy().getComparatorProxy();
    }

    /**
     * get some {@link TrimmerProxy} implementation instance.
     *
     * @return {@link TrimmerProxy} instance.
     */
    protected abstract @NotNull TrimmerProxy<T> getTrimmerProxy();

    /**
     * get number to store as current value by default.
     *
     * @return default current value.
     */
    protected @NotNull T initCurrentValue() {
        return lowerBoundary;
    }

    /**
     * compares two numbers and returns minimal one.
     *
     * @param lhs - first number.
     * @param rhs - second number.
     * @return lhs if lhs <= rhs, rhs otherwise.
     */
    private @NotNull T getMinimalBoundary(@NotNull T lhs,
                                          @NotNull T rhs) {
        return getComparatorProxy().lessOrEqual(lhs, rhs) ? lhs : rhs;
    }

    /**
     * compares two numbers and returns maximal one.
     *
     * @param lhs - first number.
     * @param rhs - second number.
     * @return rhs if rhs >= lhs, lhs otherwise.
     */
    private @NotNull T getMaximalBoundary(@NotNull T lhs,
                                          @NotNull T rhs) {
        return getComparatorProxy().greaterOrEqual(rhs, lhs) ? rhs : lhs;
    }

    /**
     * ensures that given value is within [lowerBoundary; upperBoundary] range.
     *
     * @param value - value to clamp.
     * @return value if lowerBoundary <= value <= upperBoundary, one of the boundaries otherwise.
     */
    private @NotNull T clampCurrentValue(@NotNull T value) {
        return getTrimmerProxy().clamp(value, lowerBoundary, upperBoundary);
    }
}
