package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.integer;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiableSingleNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiableSingleNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.SingleIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.IntegerArithmeticsProxyImpl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiableSingleNumberComponentImpl} class extension for {@link Integer} numbers.
 *
 * @see ModifiableSingleNumberComponentImpl
 * @see ModifiableSingleNumberComponent
 * @see SingleIntComponentModel
 * @see Integer
 * @since 0.3.3
 */
public class ModifiableSingleIntComponentImpl extends ModifiableSingleNumberComponentImpl<Integer>
        implements ModifiableSingleNumberComponent.ModifiableSingleIntComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private static final ArithmeticsProxy.IntArithmeticsProxy ARITHMETICS_PROXY =
            new IntegerArithmeticsProxyImpl();

    /**
     * default constructor for creating new components with preinitialized stored value.
     */
    public ModifiableSingleIntComponentImpl() {
        super();
    }

    /**
     * constructor for creating new components with externally passed stored value.
     *
     * @param value - initial stored value.
     */
    public ModifiableSingleIntComponentImpl(@NotNull Integer value) {
        super(value);
    }

    /**
     * constructor for deserializing component from {@link SingleIntComponentModel} data model.
     *
     * @param model - {@link SingleIntComponentModel} data model instance, containing component's data.
     */
    public ModifiableSingleIntComponentImpl(@NotNull SingleIntComponentModel model) {
        super(model.getId(), model.getValue());
    }

    /**
     * serializes component data into {@link SingleIntComponentModel} data model instance.
     *
     * @return {@link SingleIntComponentModel} data model instance with component's data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new SingleIntComponentModel(getId(), getValue());
    }

    /**
     * see {@link ModifiableSingleNumberComponentImpl} description.
     *
     * @return initial stored value by default.
     */
    @Override
    protected @NotNull Integer initValue() {
        return Constants.ZERO_INT;
    }

    /**
     * instance id generator to be used in {@link ru.rougegibbons.landsanddungeons.components.core.AbstractComponent}.
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
     * @return {@link IntegerArithmeticsProxyImpl} instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.IntArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }
}
