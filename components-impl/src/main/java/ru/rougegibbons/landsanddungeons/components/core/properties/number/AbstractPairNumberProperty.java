package ru.rougegibbons.landsanddungeons.components.core.properties.number;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.properties.AbstractProperty;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.properties.number.constant.PairNumberPropertyComponent;

import java.util.List;

/**
 * abstract implementation of {@link PairNumberPropertyComponent} interface.
 *
 * @see AbstractProperty
 * @see PairNumberPropertyComponent
 * @see ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.PairNumberComponent.PairIntComponent
 * @since 0.4.6
 */
public abstract class AbstractPairNumberProperty extends AbstractProperty
        implements PairNumberPropertyComponent {
    private final PairIntComponent valueHolder;

    /**
     * default constructor for creating new components. Values holder passed from outside.
     *
     * @param label       - component label.
     * @param valueHolder - component value holder.
     */
    public AbstractPairNumberProperty(@NotNull Long label,
                                      @NotNull PairIntComponent valueHolder) {
        super(label);
        this.valueHolder = valueHolder;
    }

    /**
     * default constructor for creating new components. Value holder is created from passed {@link Integer} values.
     *
     * @param label       - component label.
     * @param firstValue  - first value to store.
     * @param secondValue - second value to store.
     */
    public AbstractPairNumberProperty(@NotNull Long label,
                                      @NotNull Integer firstValue,
                                      @NotNull Integer secondValue) {
        super(label);
        valueHolder = initValueHolder(firstValue, secondValue);
    }

    /**
     * constructor for component deserialization from some data model instance.
     *
     * @param id          - instance id.
     * @param label       - component label.
     * @param valueHolder - component value holder.
     */
    public AbstractPairNumberProperty(@NotNull Long id,
                                      @NotNull Long label,
                                      @NotNull PairIntComponent valueHolder) {
        super(id, label);
        this.valueHolder = valueHolder;
    }

    /**
     * constructor for component deserialization from some data model instance.
     *
     * @param id          - instance id.
     * @param label       - component label.
     * @param firstValue  - first value to store in component.
     * @param secondValue - second value to store in component.
     */
    public AbstractPairNumberProperty(@NotNull Long id,
                                      @NotNull Long label,
                                      @NotNull Integer firstValue,
                                      @NotNull Integer secondValue) {
        super(id, label);
        valueHolder = initValueHolder(firstValue, secondValue);
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.PairNumberComponent}.
     *
     * @return first value stored in property.
     */
    @Override
    public @NotNull Integer getFirstValue() {
        return valueHolder.getFirstValue();
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.PairNumberComponent}.
     *
     * @return second value stored in property.
     */
    @Override
    public @NotNull Integer getSecondValue() {
        return valueHolder.getSecondValue();
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.PairNumberComponent}.
     *
     * @return {@link List} containing both values stored in property.
     */
    @Override
    public @NotNull List<Integer> getBoth() {
        return valueHolder.getBoth();
    }

    /**
     * protected access to the value holder for derived classes.
     *
     * @return component value holder.
     */
    protected @NotNull PairIntComponent getValueHolder() {
        return valueHolder;
    }

    /**
     * value holder initializer. Creates new value holder instnace from give pair of {@link Integer} digits.
     *
     * @param firstValue  - first value to make value holder from.
     * @param secondValue - second value to make value holder from.
     * @return value holder instance.
     */
    protected abstract @NotNull PairIntComponent initValueHolder(@NotNull Integer firstValue,
                                                                 @NotNull Integer secondValue);
}
