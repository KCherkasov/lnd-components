package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.floatpoint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiableOrderedPairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiablePairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.ComparatorProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.FloatArithmeticsProxyImpl;
import ru.rougegibbons.landsanddungeons.utils.proxies.FloatComparatorProxyImpl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiableOrderedPairNumberComponentImpl} extension for {@link Float} numbers.
 * Provides ordering by condition "firstValue <= secondValue".
 *
 * @see ModifiablePairNumberComponent
 * @see ModifiableOrderedPairNumberComponentImpl
 * @see PairFloatComponentModel
 * @see Float
 * @see ArithmeticsProxy
 * @see ComparatorProxy
 * @since 0.3.3
 */
public class ModifiableMinMaxPairFloatComponent extends ModifiableOrderedPairNumberComponentImpl<Float>
        implements ModifiablePairNumberComponent.ModifiablePairFloatComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private static final ArithmeticsProxy.FloatArithmeticsProxy ARITHMETICS_PROXY =
            new FloatArithmeticsProxyImpl();
    private static final ComparatorProxy.FloatComparatorProxy COMPARATOR_PROXY =
            new FloatComparatorProxyImpl();

    /**
     * default constructor for creating new components with preinitialized values.
     */
    public ModifiableMinMaxPairFloatComponent() {
        super();
    }

    /**
     * constructor for creating new components with given values.
     *
     * @param firstValue  - first number to store.
     * @param secondValue - second number to store.
     */
    public ModifiableMinMaxPairFloatComponent(@NotNull Float firstValue,
                                              @NotNull Float secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserializing component from {@link PairFloatComponentModel} data model instance.
     *
     * @param model - {@link PairFloatComponentModel} data model instance with component data.
     */
    public ModifiableMinMaxPairFloatComponent(@NotNull PairFloatComponentModel model) {
        super(model.getId(), model.getFirstValue(), model.getSecondValue());
    }

    /**
     * serializes component data into {@link PairFloatComponentModel} data model instance.
     *
     * @return {@link PairFloatComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
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
     * see {@link ModifiableOrderedPairNumberComponentImpl} decription.
     */
    @Override
    protected void initValues() {
        setBoth(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
    }

    /**
     * see {@link ModifiableOrderedPairNumberComponentImpl} decription.
     *
     * @return {@link FloatArithmeticsProxyImpl} instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.FloatArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }

    /**
     * see {@link ModifiableOrderedPairNumberComponentImpl} decription.
     *
     * @return {@link FloatComparatorProxyImpl} instance.
     */
    @Override
    protected @NotNull ComparatorProxy.FloatComparatorProxy getComparatorProxy() {
        return COMPARATOR_PROXY;
    }

    /**
     * see {@link ModifiableOrderedPairNumberComponentImpl} decription.
     *
     * @param value - {@link Float} number.
     * @return converted value.
     */
    @Override
    protected @NotNull Float floatToType(@NotNull Float value) {
        return value;
    }

    /**
     * determines which of two numbers will be stored as first. This implementation picks lesser number as first.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return lesser value.
     */
    @Override
    protected @NotNull Float defineFirstValue(@NotNull Float first,
                                              @NotNull Float second) {
        return COMPARATOR_PROXY.lessOrEqual(first, second) ? first : second;
    }

    /**
     * determines which of two numbers will be stored as second. This implementation picks greater number as second.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return greater number.
     */
    @Override
    protected @NotNull Float defineSecondValue(@NotNull Float first,
                                               @NotNull Float second) {
        return COMPARATOR_PROXY.greaterOrEqual(second, first) ? second : first;
    }
}
