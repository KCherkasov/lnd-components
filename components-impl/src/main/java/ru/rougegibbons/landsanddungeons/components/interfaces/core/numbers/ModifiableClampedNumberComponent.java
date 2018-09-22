package ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers;

import org.jetbrains.annotations.NotNull;

/**
 * Modifiable extension of {@link ClampedNumberComponent} interface.
 * Supports upper and lower boundaries changes.
 *
 * @param <T> - any extension of {@link Number} class.
 * @see ClampedNumberComponent
 * @see Number
 * @since 0.3.5
 */
public interface ModifiableClampedNumberComponent<T extends Number>
        extends ClampedNumberComponent<T> {
    /**
     * sets lower value into given value.
     *
     * @param value - value to set as new lower value.
     */
    void setLowerBoundary(@NotNull T value);

    /**
     * sets upper value into given value.
     *
     * @param value - value to set as new upper value.
     */
    void setUpperBoundary(@NotNull T value);

    /**
     * sets both boundaries into given values.
     *
     * @param lower - new lower value.
     * @param upper - new upper value.
     */
    void setBoundaries(@NotNull T lower,
                       @NotNull T upper);

    /**
     * increases lower boundary on given amount.
     *
     * @param amount - amount to increase lower boundary on.
     */
    void increaseLowerBoundary(@NotNull T amount);

    /**
     * increases upper boundary on given amount.
     *
     * @param amount - amount to increase upper boundary on.
     */
    void increaseUpperBoundary(@NotNull T amount);

    /**
     * increases both boundaries on the same given amount.
     *
     * @param amount - amount to increase both boundaries on.
     */
    void increaseBoundaries(@NotNull T amount);

    /**
     * increases both boundaries on differen amounts.
     *
     * @param lower - amount to increase lower boundary on.
     * @param upper - amount to increase upper boundary on.
     */
    void increaseBoundaries(@NotNull T lower,
                            @NotNull T upper);

    /**
     * decreases lower boundary on given amount.
     *
     * @param amount - amount to decrease lower boundary on.
     */
    void decreaseLowerBoundary(@NotNull T amount);

    /**
     * decreases upper boundary on given amount.
     *
     * @param amount - amount to decrease upper boundary on.
     */
    void decreaseUpperBoundary(@NotNull T amount);

    /**
     * decreases both boundaries on the same given amount.
     *
     * @param amount - amount to decrease both boundaries on.
     */
    void decreaseBoundaries(@NotNull T amount);

    /**
     * decreases both boundaries on different amounts.
     *
     * @param lower - amount to decrease lower boundary on.
     * @param upper - amount to decrease upper boundary on.
     */
    void decreaseBoundaries(@NotNull T lower,
                            @NotNull T upper);

    /**
     * modifies lower boundary by given percentage.
     *
     * @param percent - percentage to modify lower boundary
     */
    void modifyLowerByPercentage(@NotNull Integer percent);

    /**
     * modifies lower boundary by given percentage.
     *
     * @param percent - percentage to modify lower boundary
     */
    void modifyLowerByPercentage(@NotNull Float percent);

    /**
     * modifies upper boundary by given percentage.
     *
     * @param percent - percentage to modify upper boundary
     */
    void modifyUpperByPercentage(@NotNull Integer percent);

    /**
     * modifies upper boundary by given percentage.
     *
     * @param percent - percentage to modify upper boundary
     */
    void modifyUpperByPercentage(@NotNull Float percent);

    /**
     * modifies both boundaries by given percentage.
     *
     * @param percent - percentage to modify both boundaries.
     */
    void modifyBoundariesByPercentage(@NotNull Integer percent);

    /**
     * modifies both boundaries by given percentage.
     *
     * @param percent - percentage to modify both boundaries.
     */
    void modifyBoundariesByPercentage(@NotNull Float percent);

    /**
     * modifies both boundaries by different percentages.
     *
     * @param lower - percentage to modify lower boundary.
     * @param upper - percentage to modify upper boundary.
     */
    void modifyBoundariesByPercentage(@NotNull Integer lower,
                                      @NotNull Integer upper);

    /**
     * modifies both boundaries by different percentages.
     *
     * @param lower - percentage to modify lower boundary.
     * @param upper - percentage to modify upper boundary.
     */
    void modifyBoundariesByPercentage(@NotNull Float lower,
                                      @NotNull Float upper);

    interface ModifiableClampedIntegerComponent
            extends ModifiableClampedNumberComponent<Integer> {

    }

    interface ModifiableClampedLongComponent
            extends ModifiableClampedNumberComponent<Long> {

    }

    interface ModifiableClampedFloatComponent
            extends ModifiableClampedNumberComponent<Float> {

    }
}
