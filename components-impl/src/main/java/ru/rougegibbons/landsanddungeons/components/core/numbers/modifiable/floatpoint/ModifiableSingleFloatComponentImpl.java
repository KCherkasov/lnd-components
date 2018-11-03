package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.floatpoint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiableSingleNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiableSingleNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.SingleFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.FloatArithmeticsProxyImpl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiableSingleNumberComponentImpl} class extension for {@link Float} numbers.
 *
 * @see ModifiableSingleNumberComponent
 * @see ModifiableSingleNumberComponentImpl
 * @see SingleFloatComponentModel
 * @see Float
 * @since 0.3.3
 */
public class ModifiableSingleFloatComponentImpl extends ModifiableSingleNumberComponentImpl<Float>
        implements ModifiableSingleNumberComponent.ModifiableSingleFloatComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private static final ArithmeticsProxy.FloatArithmeticsProxy ARITHMETICS_PROXY =
            new FloatArithmeticsProxyImpl();

    /**
     * {@link ModifiableSingleNumberComponentImpl} default constructor overload.
     */
    public ModifiableSingleFloatComponentImpl() {
        super();
    }

    /**
     * {@link ModifiableSingleNumberComponentImpl} default constructor overload.
     *
     * @param value - number to be stored.
     */
    public ModifiableSingleFloatComponentImpl(@NotNull Float value) {
        super(value);
    }

    /**
     * constructor for deserialization from {@link SingleFloatComponentModel} data model.
     *
     * @param model - data model containing component's data.
     */
    public ModifiableSingleFloatComponentImpl(@NotNull SingleFloatComponentModel model) {
        super(model.getId(), model.getValue());
    }

    /**
     * serializes component into {@link SingleFloatComponentModel} data model instance.
     *
     * @return {@link SingleFloatComponentModel} data model instance containing component's data.
     */
    @Override
    public @NotNull AbstractComponentModel pack() {
        return new SingleFloatComponentModel(getId(), getValue());
    }

    /**
     * see {@link ModifiableSingleNumberComponent} description.
     *
     * @return initial stored value.
     */
    @Override
    protected @NotNull Float initValue() {
        return Constants.ZERO_FLOAT;
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
     * @return {@link FloatArithmeticsProxyImpl} instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.FloatArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }
}
