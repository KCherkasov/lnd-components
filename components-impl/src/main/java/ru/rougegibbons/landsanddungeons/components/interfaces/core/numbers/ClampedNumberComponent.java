package ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.PackableComponent;

import java.util.List;

/**
 * Generic interface for modifiable {@link Number} extensions values,
 * clamped between minimal and maximal boundaries.
 *
 * @param <T> - {@link Number} class extensions.
 * @see PackableComponent
 * @see Number
 * @since 0.3.5
 */
public interface ClampedNumberComponent<T extends Number> extends PackableComponent {
    /**
     * get lower boundary.
     *
     * @return component's lower boundary.
     */
    @NotNull T getLowerBoundary();

    /**
     * get upper boundary.
     *
     * @return component's upper boundary.
     */
    @NotNull T getUpperBoundary();

    /**
     * get both boundaries.
     *
     * @return {@link List} containing both component boundaries (lower and upper).
     */
    @NotNull List<T> getBoundaries();

    /**
     * get current value.
     *
     * @return component's current value.
     */
    @NotNull T getCurrentValue();

    /**
     * checks if current value is equal to the minimal boundary.
     *
     * @return true if current value is equal to minimal boundary and false otherwise.
     */
    @NotNull Boolean isEmpty();

    /**
     * checks if current value is equal to the maximal boundary.
     *
     * @return true if current value is equal to maximal boundary and false otherwise.
     */
    @NotNull Boolean isFull();

    /**
     * get current value as {@link Integer} percentage representation.
     *
     * @return {@link Integer} percentage (currentValue / upperBoundary).
     */
    @NotNull Integer getPercentageInt();

    /**
     * get current value as {@link Float} percentage representation.
     *
     * @return {@link Float} percentage (currentValue / upperBoundary).
     */
    @NotNull Float getPercentageFloat();

    /**
     * sets current value into given number.
     *
     * @param value - number to save as current value.
     */
    void setCurrentValue(@NotNull T value);

    /**
     * increases the current value on a given amount, but not more than upper boundary.
     *
     * @param amount - amount to add to the current value.
     */
    void increaseCurrentValue(@NotNull T amount);

    /**
     * decreases the current value on a given amount, but not less than lower boundary.
     *
     * @param amount - amount to subtract to the current value.
     */
    void decreaseCurrentValue(@NotNull T amount);

    /**
     * modifies current value by given percentage. Value remains in [lowerBoundary; upperBoundary] range.
     *
     * @param percent - percentage to alter current value.
     */
    void modifyCurrentValueByPercentage(@NotNull Integer percent);

    /**
     * modifies current value by given percentage. Value remains in [lowerBoundary; upperBoundary] range.
     *
     * @param percent - percentage to alter current value.
     */
    void modifyCurrentValueByPercentage(@NotNull Float percent);
}
