package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.integer;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiableOrderedPairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiablePairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.ComparatorProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.IntegerArithmeticsProxyImpl;
import ru.rougegibbons.landsanddungeons.utils.proxies.IntegerComparatorProxyImpl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiableOrderedPairNumberComponentImpl} extension for {@link Integer} numbers.
 * Provides ordering by condition "firstValue <= secondValue".
 *
 * @see ModifiablePairNumberComponent
 * @see ModifiableOrderedPairNumberComponentImpl
 * @see PairIntComponentModel
 * @see Integer
 * @see ArithmeticsProxy
 * @see ComparatorProxy
 * @since 0.3.3
 */
public class ModifiableMinMaxPairIntComponent extends ModifiableOrderedPairNumberComponentImpl<Integer>
        implements ModifiablePairNumberComponent.ModifiablePairIntComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private static final ArithmeticsProxy.IntArithmeticsProxy ARITHMETICS_PROXY =
            new IntegerArithmeticsProxyImpl();
    private static final ComparatorProxy.IntComparatorProxy COMPARATOR_PROXY =
            new IntegerComparatorProxyImpl();

    /**
     * default constructor for creating new components with preinitialized values.
     */
    public ModifiableMinMaxPairIntComponent() {
        super();
    }

    /**
     * constructor for creating new components with given values.
     *
     * @param firstValue  - first number to store.
     * @param secondValue - second number to store.
     */
    public ModifiableMinMaxPairIntComponent(@NotNull Integer firstValue,
                                            @NotNull Integer secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserializing component from {@link PairIntComponentModel} data model instance.
     *
     * @param model - {@link PairIntComponentModel} data model instance with component data.
     */
    public ModifiableMinMaxPairIntComponent(@NotNull PairIntComponentModel model) {
        super(model.getId(), model.getFirstValue(), model.getSecondValue());
    }

    /**
     * serializes component data into {@link PairIntComponentModel} data model instance.
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
     * see {@link ModifiableOrderedPairNumberComponentImpl} decription.
     */
    @Override
    protected void initValues() {
        setBoth(Constants.ZERO_INT, Constants.ZERO_INT);
    }

    /**
     * see {@link ModifiableOrderedPairNumberComponentImpl} decription.
     *
     * @return {@link IntegerArithmeticsProxyImpl} instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.IntArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }

    /**
     * see {@link ModifiableOrderedPairNumberComponentImpl} decription.
     *
     * @return {@link IntegerComparatorProxyImpl} instance.
     */
    @Override
    protected @NotNull ComparatorProxy.IntComparatorProxy getComparatorProxy() {
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
    protected @NotNull Integer defineFirstValue(@NotNull Integer first,
                                                @NotNull Integer second) {
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
    protected @NotNull Integer defineSecondValue(@NotNull Integer first,
                                                 @NotNull Integer second) {
        return second >= first ? second : first;
    }
}
