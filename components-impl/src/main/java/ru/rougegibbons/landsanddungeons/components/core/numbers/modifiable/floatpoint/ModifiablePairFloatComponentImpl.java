package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.floatpoint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiablePairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiablePairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.FloatArithmeticsProxyImpl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiablePairNumberComponentImpl} class extension for {@link Float} numbers.
 *
 * @see ModifiablePairNumberComponent
 * @see ModifiablePairNumberComponentImpl
 * @see PairFloatComponentModel
 * @see Float
 * @since 0.3.3
 */
public class ModifiablePairFloatComponentImpl extends ModifiablePairNumberComponentImpl<Float>
        implements ModifiablePairNumberComponent.ModifiablePairFloatComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private static final ArithmeticsProxy.FloatArithmeticsProxy ARITHMETICS_PROXY =
            new FloatArithmeticsProxyImpl();

    /**
     * default constructor for creating component with preinitialized values.
     */
    public ModifiablePairFloatComponentImpl() {
        super();
    }

    /**
     * constructor for creating components with given numbers to store.
     *
     * @param firstValue  - first number to store.
     * @param secondValue - second number to store.
     */
    public ModifiablePairFloatComponentImpl(@NotNull Float firstValue,
                                            @NotNull Float secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserializing component from {@link PairFloatComponentModel} data model instance.
     *
     * @param model - {@link PairFloatComponentModel} data model instance with component data.
     */
    public ModifiablePairFloatComponentImpl(@NotNull PairFloatComponentModel model) {
        super(model.getId(), model.getFirstValue(), model.getSecondValue());
    }

    /**
     * serializes component data into {@link PairFloatComponentModel} data model instance.
     *
     * @return {@link PairFloatComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull AbstractComponentModel pack() {
        return new PairFloatComponentModel(getId(), getFirstValue(), getSecondValue());
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
     * see {@link ModifiablePairNumberComponentImpl} description.
     */
    @Override
    protected void initValues() {
        setBoth(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
    }

    /**
     * see {@link ModifiablePairNumberComponentImpl} description.
     *
     * @return {@link FloatArithmeticsProxyImpl} instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.FloatArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }
}
