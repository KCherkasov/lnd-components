package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.integer;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiablePairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiablePairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.IntegerArithmeticsProxyImpl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiablePairNumberComponentImpl} extension for {@link Integer} numbers.
 *
 * @see ModifiablePairNumberComponent
 * @see ModifiablePairNumberComponentImpl
 * @see PairIntComponentModel
 * @see Integer
 * @since 0.3.3
 */
public class ModifiablePairIntComponentImpl extends ModifiablePairNumberComponentImpl<Integer>
        implements ModifiablePairNumberComponent.ModifiablePairIntComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private static final ArithmeticsProxy.IntArithmeticsProxy ARITHMETICS_PROXY =
            new IntegerArithmeticsProxyImpl();

    /**
     * default constructor for creating preinitialized components.
     */
    public ModifiablePairIntComponentImpl() {
        super();
    }

    /**
     * constructor for creating component with given numbers to store.
     *
     * @param firstValue  - first number to store.
     * @param secondValue - second number to store
     */
    public ModifiablePairIntComponentImpl(@NotNull Integer firstValue,
                                          @NotNull Integer secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserializing component from {@link PairIntComponentModel} data model.
     *
     * @param model - {@link PairIntComponentModel} data model instance with component data.
     */
    public ModifiablePairIntComponentImpl(@NotNull PairIntComponentModel model) {
        super(model.getId(), model.getFirstValue(), model.getSecondValue());
    }

    /**
     * serializes component in {@link PairIntComponentModel} data model instance.
     *
     * @return {@link PairIntComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull AbstractComponentModel pack() {
        return new PairIntComponentModel(getId(), getFirstValue(), getSecondValue());
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
        setBoth(Constants.ZERO_INT, Constants.ZERO_INT);
    }

    /**
     * see {@link ModifiablePairNumberComponentImpl} description.
     *
     * @return {@link IntegerArithmeticsProxyImpl} instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.IntArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }
}
