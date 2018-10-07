package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.longint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiablePairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiablePairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.LongArithmeticsProxyImpl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiablePairNumberComponentImpl} class extension for {@link Long} numbers.
 *
 * @see ModifiablePairNumberComponent
 * @see ModifiablePairNumberComponentImpl
 * @see PairLongComponentModel
 * @see Long
 * @since 0.3.3
 */
public class ModifiablePairLongComponentImpl extends ModifiablePairNumberComponentImpl<Long>
        implements ModifiablePairNumberComponent.ModifiablePairLongComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private static final ArithmeticsProxy.LongArithmeticsProxy ARITHMETICS_PROXY =
            new LongArithmeticsProxyImpl();

    /**
     * default constructor for creating components with preinitialized values.
     */
    public ModifiablePairLongComponentImpl() {
        super();
    }

    /**
     * constructor for creating component storing given numbers.
     *
     * @param firstValue  - first number to store.
     * @param secondValue - second number to store.
     */
    public ModifiablePairLongComponentImpl(@NotNull Long firstValue,
                                           @NotNull Long secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserializing component from {@link PairLongComponentModel} data model instance.
     *
     * @param model - {@link PairLongComponentModel} data model instance with component data.
     */
    public ModifiablePairLongComponentImpl(@NotNull PairLongComponentModel model) {
        super(model.getId(), model.getFirstValue(), model.getSecondValue());
    }

    /**
     * seriazlizes component in {@link PairLongComponentModel} data model instance.
     *
     * @return {@link PairLongComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new PairLongComponentModel(getId(), getFirstValue(), getSecondValue());
    }

    /**
     * see {@link ModifiablePairNumberComponentImpl} description.
     */
    @Override
    protected void initValues() {
        setBoth(Constants.ZERO_LONG, Constants.ZERO_LONG);
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
     *
     * @return {@link LongArithmeticsProxyImpl} instance
     */
    @Override
    protected @NotNull ArithmeticsProxy.LongArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }
}
