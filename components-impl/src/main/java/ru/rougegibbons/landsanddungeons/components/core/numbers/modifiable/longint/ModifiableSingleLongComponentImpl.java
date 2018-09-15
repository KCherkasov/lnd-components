package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.longint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiableSingleNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiableSingleNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.SingleLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.LongArithmeticsProxyImpl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiableSingleNumberComponentImpl} class extension for {@link Long} numbers.
 *
 * @see ModifiableSingleNumberComponentImpl
 * @see ModifiableSingleNumberComponent
 * @see SingleLongComponentModel
 * @see Long
 * @since 0.3.3
 */
public class ModifiableSingleLongComponentImpl extends ModifiableSingleNumberComponentImpl<Long>
        implements ModifiableSingleNumberComponent.ModifiableSingleLongComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private static final ArithmeticsProxy.LongArithmeticsProxy ARITHMETICS_PROXY =
            new LongArithmeticsProxyImpl();

    /**
     * default constructor for creating new components with preinitialized stored value.
     */
    public ModifiableSingleLongComponentImpl() {
        super();
    }

    /**
     * constructor for creating new components with externally passed initial stored value.
     *
     * @param value - initially stored value.
     */
    public ModifiableSingleLongComponentImpl(@NotNull Long value) {
        super(value);
    }

    /**
     * constructor for deserializing the component from {@link SingleLongComponentModel} data model instance.
     *
     * @param model - {@link SingleLongComponentModel} data model instance with component data.
     */
    public ModifiableSingleLongComponentImpl(@NotNull SingleLongComponentModel model) {
        super(model.getId(), model.getValue());
    }

    /**
     * serializes component data into the {@link SingleLongComponentModel} data model instance.
     *
     * @return {@link SingleLongComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new SingleLongComponentModel(getId(), getValue());
    }

    /**
     * see description in {@link ModifiableSingleNumberComponentImpl} class.
     *
     * @return initial stored value.
     */
    @Override
    protected @NotNull Long initValue() {
        return Constants.ZERO_LONG;
    }

    /**
     * instance ids generator to be used in {@link ru.rougegibbons.landsanddungeons.components.core.AbstractComponent}.
     *
     * @return generated instance id.
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }

    /**
     * see {@link ModifiableSingleNumberComponentImpl} description.
     *
     * @return {@link LongArithmeticsProxyImpl} instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.LongArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }

    /**
     * see {@link ModifiableSingleNumberComponentImpl} description.
     *
     * @param source - value to convert.
     * @return converted value.
     */
    @Override
    protected @NotNull Long floatToType(@NotNull Float source) {
        return source.longValue();
    }
}
