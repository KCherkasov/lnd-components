package ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers;

import org.jetbrains.annotations.NotNull;

/**
 * extensions {@link PairNumberComponent}, allowing to modify stored values.
 *
 * @param <T> - any extension of {@link Number} class.
 * @see PairNumberComponent
 * @see Number
 * @since 0.3.3
 */
public interface ModifiablePairNumberComponent<T extends Number>
        extends PairNumberComponent<T> {
    /**
     * assigns given arguments to stored values.
     *
     * @param firstValue  - value to assign to the first stored value.
     * @param secondValue - value to assign to the second stored value.
     */
    void setBoth(@NotNull T firstValue,
                 @NotNull T secondValue);

    /**
     * assigns given argument to the first stored value.
     *
     * @param value - value to assign to the first stored value.
     */
    void setFirstValue(@NotNull T value);

    /**
     * assigns given argument to the second stored value.
     *
     * @param value - value to assign to the second stored value.
     */
    void setSecondValue(@NotNull T value);

    /**
     * increases both stored values by the same amount.
     *
     * @param amount - amount to increase both stored values on.
     */
    void increaseBoth(@NotNull T amount);

    /**
     * increases both stored values by specific amounts.
     *
     * @param firstIncrease  - amount to increase first stored value on.
     * @param secondIncrease - amount to increase second stored value on.
     */
    void increaseBoth(@NotNull T firstIncrease,
                      @NotNull T secondIncrease);

    /**
     * increases first stored value on a given amount.
     *
     * @param amount - amount to increase first stored value on.
     */
    void increaseFirstValue(@NotNull T amount);

    /**
     * increases second stored value on a given amount.
     *
     * @param amount - amount to increase second stored value on.
     */
    void increaseSecondValue(@NotNull T amount);

    /**
     * decreases both stored values by the same amount.
     *
     * @param amount - amount to decrease both stored values on.
     */
    void decreaseBoth(@NotNull T amount);

    /**
     * decreases both stored values by specific amounts.
     *
     * @param firstDecrease  - amount to decrease first stored value on.
     * @param secondDecrease - amount to decrease second stored value on.
     */
    void decreaseBoth(@NotNull T firstDecrease,
                      @NotNull T secondDecrease);

    /**
     * decreases first stored value on a given amount.
     *
     * @param amount - amount to decrease first stored value on.
     */
    void decreaseFirstValue(@NotNull T amount);

    /**
     * decreases second stored value on a given amount.
     *
     * @param amount - amount to decrease second stored value on.
     */
    void decreaseSecondValue(@NotNull T amount);

    /**
     * modifies both stored values by a given percentage value.
     *
     * @param percent - float percentage to modify both stored values by.
     */
    void modifyBothByPercentage(@NotNull Float percent);

    /**
     * modifies both stored values by a given percentage value.
     *
     * @param percent - integer percentage to modify both stored values by.
     */
    void modifyBothByPercentage(@NotNull Integer percent);

    /**
     * modifies both stored values by different percentages.
     *
     * @param firstPercent  - float percentage to modify first stored value by.
     * @param secondPercent - float percentage to modify second stored value by.
     */
    void modifyBothByPercentage(@NotNull Float firstPercent,
                                @NotNull Float secondPercent);

    /**
     * modifies both stored values by different percentages.
     *
     * @param firstPercent  - integer percentage to modify first stored value by.
     * @param secondPercent - integer percentage to modify second stored value by.
     */
    void modifyBothByPercentage(@NotNull Integer firstPercent,
                                @NotNull Integer secondPercent);

    /**
     * modifies first stored value by given percentage.
     *
     * @param percent - float percentage to modify first stored value by.
     */
    void modifyFirstByPercentage(@NotNull Float percent);

    /**
     * modifies first stored value by given percentage.
     *
     * @param percent - integer percentage to modify first stored value by.
     */
    void modifyFirstByPercentage(@NotNull Integer percent);

    /**
     * modifies second stored value by given percentage.
     *
     * @param percent - float percentage to modify second stored value by.
     */
    void modifySecondByPercentage(@NotNull Float percent);

    /**
     * modifies second stored value by given percentage.
     *
     * @param percent - integer percentage to modify second stored value by.
     */
    void modifySecondByPercentage(@NotNull Integer percent);

    interface ModifiablePairIntComponent extends PairIntComponent {

    }

    interface ModifiablePairLongComponent extends PairLongComponent {

    }

    interface ModifiablePairFloatComponent extends PairFloatComponent {

    }
}
