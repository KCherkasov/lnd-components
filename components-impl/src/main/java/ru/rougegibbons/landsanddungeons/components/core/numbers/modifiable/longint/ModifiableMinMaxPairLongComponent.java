package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.longint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiableOrderedPairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiablePairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.ComparatorProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.LongArithmeticsProxyImpl;
import ru.rougegibbons.landsanddungeons.utils.proxies.LongComparatorProxyImpl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiableOrderedPairNumberComponentImpl} extension for {@link Long} numbers.
 * Provides ordering by condition "firstValue <= secondValue".
 *
 * @see ModifiablePairNumberComponent
 * @see ModifiableOrderedPairNumberComponentImpl
 * @see PairLongComponentModel
 * @see Long
 * @see ArithmeticsProxy
 * @see ComparatorProxy
 * @since 0.3.3
 */
public class ModifiableMinMaxPairLongComponent extends ModifiableOrderedPairNumberComponentImpl<Long>
        implements ModifiablePairNumberComponent.ModifiablePairLongComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private static final ArithmeticsProxy.LongArithmeticsProxy ARITHMETICS_PROXY =
            new LongArithmeticsProxyImpl();
    private static final ComparatorProxy.LongComparatorProxy COMPARATOR_PROXY =
            new LongComparatorProxyImpl();

    /**
     * default constructor for creating new components with preinitialized values.
     */
    public ModifiableMinMaxPairLongComponent() {
        super();
    }

    /**
     * constructor for creating new components with given values.
     *
     * @param firstValue  - first number to store.
     * @param secondValue - second number to store.
     */
    public ModifiableMinMaxPairLongComponent(@NotNull Long firstValue,
                                             @NotNull Long secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserializing component from {@link PairLongComponentModel} data model instance.
     *
     * @param model - {@link PairLongComponentModel} data model instance with component data.
     */
    public ModifiableMinMaxPairLongComponent(@NotNull PairLongComponentModel model) {
        super(model.getId(), model.getFirstValue(), model.getSecondValue());
    }

    /**
     * serializes component data into {@link PairLongComponentModel} data model instance.
     *
     * @return {@link PairLongComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull AbstractComponentModel pack() {
        return new PairLongComponentModel(getId(), getFirstValue(), getSecondValue());
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
        setBoth(Constants.ZERO_LONG, Constants.ZERO_LONG);
    }

    /**
     * see {@link ModifiableOrderedPairNumberComponentImpl} decription.
     *
     * @return {@link LongArithmeticsProxyImpl} instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.LongArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }

    /**
     * see {@link ModifiableOrderedPairNumberComponentImpl} decription.
     *
     * @return {@link LongComparatorProxyImpl} instance.
     */
    @Override
    protected @NotNull ComparatorProxy.LongComparatorProxy getComparatorProxy() {
        return COMPARATOR_PROXY;
    }

    /**
     * determines which of two numbers will be stored as first. This implementation picks lesser number as first.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return lesser value.
     */
    @Override
    protected @NotNull Long defineFirstValue(@NotNull Long first,
                                             @NotNull Long second) {
        return first <= second ? first : second;
    }

    /**
     * determines which of two numbers will be stored as second. This implementation picks greater number as second.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return greater number.
     */
    @Override
    protected @NotNull Long defineSecondValue(@NotNull Long first,
                                              @NotNull Long second) {
        return second >= first ? second : first;
    }
}
