package ru.rougegibbons.landsanddungeons.components.core.properties.number;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.properties.AbstractProperty;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.SingleNumberComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.properties.number.constant.SingleNumberPropertyComponent;

/**
 * abstract implementation of {@link SingleNumberPropertyComponent} interface.
 *
 * @see AbstractProperty
 * @see SingleNumberPropertyComponent
 * @see SingleNumberComponent.SingleIntComponent
 * @since 0.4.6
 */
public abstract class AbstractSingleNumberProperty extends AbstractProperty
        implements SingleNumberPropertyComponent {
    private final SingleNumberComponent.SingleIntComponent valueHolder;

    /**
     * default constructor for creating new components. Value holder is passed from outside.
     *
     * @param label       - component label.
     * @param valueHolder - value holder ({@link SingleNumberComponent.SingleIntComponent} implementation instance).
     */
    public AbstractSingleNumberProperty(@NotNull Long label,
                                        @NotNull SingleIntComponent valueHolder) {
        super(label);
        this.valueHolder = valueHolder;
    }

    /**
     * default constructor for creating new components. Value holder is instantiated from passed value.
     *
     * @param label - component label.
     * @param value - {@link Integer} value.
     */
    public AbstractSingleNumberProperty(@NotNull Long label,
                                        @NotNull Integer value) {
        super(label);
        valueHolder = initValueHolder(value);
    }

    /**
     * constructor for component deserialization from some data model instance.
     *
     * @param id          - instance id.
     * @param label       - component label.
     * @param valueHolder - component value holder ({@link SingleNumberComponent.SingleIntComponent} implementation).
     */
    public AbstractSingleNumberProperty(@NotNull Long id,
                                        @NotNull Long label,
                                        @NotNull SingleIntComponent valueHolder) {
        super(id, label);
        this.valueHolder = valueHolder;
    }

    /**
     * constructor for component deserialization from some data model instance.
     *
     * @param id    - instance id.
     * @param label - component label.
     * @param value - {@link Integer} value.
     */
    public AbstractSingleNumberProperty(@NotNull Long id,
                                        @NotNull Long label,
                                        @NotNull Integer value) {
        super(id, label);
        valueHolder = initValueHolder(value);
    }

    /**
     * see {@link SingleNumberComponent} description.
     *
     * @return value stored in component.
     */
    @Override
    public @NotNull Integer getValue() {
        return valueHolder.getValue();
    }

    /**
     * get component value holder.
     *
     * @return some {@link SingleNumberComponent.SingleIntComponent} implementation instance.
     */
    protected @NotNull SingleIntComponent getValueHolder() {
        return valueHolder;
    }

    /**
     * creates value holder from given value.
     *
     * @param value - {@link Integer} to create value holder from.
     * @return some {@link SingleNumberComponent.SingleIntComponent} implementation instance.
     */
    protected abstract @NotNull SingleIntComponent initValueHolder(@NotNull Integer value);
}
