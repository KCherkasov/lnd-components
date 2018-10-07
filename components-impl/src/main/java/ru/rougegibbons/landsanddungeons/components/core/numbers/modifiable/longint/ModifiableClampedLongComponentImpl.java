package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.longint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ModifiableClampedNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiableClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.ClampedLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.LongArithmeticsProxyImpl;
import ru.rougegibbons.landsanddungeons.utils.proxies.LongTrimmerProxyImpl;
import ru.rougegibbons.landsanddungeons.utils.proxies.TrimmerProxy;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ModifiableClampedNumberComponentImpl} class extension for {@link Long} numbers.
 *
 * @see ModifiableClampedNumberComponentImpl
 * @see ModifiableClampedNumberComponent
 * @see ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent
 * @see ArithmeticsProxy
 * @see TrimmerProxy
 * @see ClampedLongComponentModel
 * @see Long
 * @since 0.3.5
 */
public class ModifiableClampedLongComponentImpl extends ModifiableClampedNumberComponentImpl<Long>
        implements ModifiableClampedNumberComponent.ModifiableClampedLongComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    private static final ArithmeticsProxy.LongArithmeticsProxy ARITHMETICS_PROXY =
            new LongArithmeticsProxyImpl();
    private static final TrimmerProxy.LongTrimmerProxy TRIMMER_PROXY =
            new LongTrimmerProxyImpl();

    /**
     * constructor for creating new components with preinitialized current value.
     *
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     */
    public ModifiableClampedLongComponentImpl(@NotNull Long lowerBoundary,
                                              @NotNull Long upperBoundary) {
        super(lowerBoundary, upperBoundary);
    }

    /**
     * constructor for creating new components with given current value.
     *
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     * @param currentValue  - current value.
     */
    public ModifiableClampedLongComponentImpl(@NotNull Long lowerBoundary,
                                              @NotNull Long upperBoundary,
                                              @NotNull Long currentValue) {
        super(lowerBoundary, upperBoundary, currentValue);
    }

    /**
     * constructor for deserialization from {@link ClampedLongComponentModel} data model instance.
     *
     * @param model - {@link ClampedLongComponentModel} data model instance with component data.
     */
    public ModifiableClampedLongComponentImpl(@NotNull ClampedLongComponentModel model) {
        super(model.getId(), model.getLowerBoundary(),
                model.getUpperBoundary(), model.getCurrentValue());
    }

    /**
     * serializes component data into {@link ClampedLongComponentModel} data model instance.
     *
     * @return {@link ClampedLongComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new ClampedLongComponentModel(getId(), getLowerBoundary(),
                getUpperBoundary(), getCurrentValue());
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
     * see {@link ModifiableClampedNumberComponentImpl} description.
     *
     * @return {@link ArithmeticsProxy.LongArithmeticsProxy} implementation instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.LongArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }

    /**
     * see {@link ModifiableClampedNumberComponentImpl} description.
     *
     * @return {@link TrimmerProxy.LongTrimmerProxy} implementation instance.
     */
    @Override
    protected @NotNull TrimmerProxy.LongTrimmerProxy getTrimmerProxy() {
        return TRIMMER_PROXY;
    }
}
