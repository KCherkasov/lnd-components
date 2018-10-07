package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.integer;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic.ClampedNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.ClampedIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.IntegerArithmeticsProxyImpl;
import ru.rougegibbons.landsanddungeons.utils.proxies.IntegerTrimmerProxyImpl;
import ru.rougegibbons.landsanddungeons.utils.proxies.TrimmerProxy;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link ClampedNumberComponentImpl} extension for {@link Integer} numbers.
 *
 * @see ClampedNumberComponent
 * @see ClampedNumberComponentImpl
 * @see ArithmeticsProxy
 * @see TrimmerProxy
 * @see ClampedIntComponentModel
 * @since 0.3.5
 */
public class ClampedIntegerComponentImpl extends ClampedNumberComponentImpl<Integer>
        implements ClampedNumberComponent.ClampedIntegerComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    private static final ArithmeticsProxy.IntArithmeticsProxy ARITHMETICS_PROXY =
            new IntegerArithmeticsProxyImpl();

    private static final TrimmerProxy.IntTrimmerProxy TRIMMER_PROXY = new IntegerTrimmerProxyImpl();

    /**
     * constructor for creating new components with preinitialized current value.
     *
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     */
    public ClampedIntegerComponentImpl(@NotNull Integer lowerBoundary,
                                       @NotNull Integer upperBoundary) {
        super(lowerBoundary, upperBoundary);
    }

    /**
     * constructor for creating new components with given current value.
     *
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     * @param currentValue  - current value.
     */
    public ClampedIntegerComponentImpl(@NotNull Integer lowerBoundary,
                                       @NotNull Integer upperBoundary,
                                       @NotNull Integer currentValue) {
        super(lowerBoundary, upperBoundary, currentValue);
    }

    /**
     * constructor for deserialization from {@link ClampedIntComponentModel} data model.
     *
     * @param model - {@link ClampedIntComponentModel} data model instance with component data.
     */
    public ClampedIntegerComponentImpl(@NotNull ClampedIntComponentModel model) {
        super(model.getId(), model.getLowerBoundary(),
                model.getUpperBoundary(), model.getCurrentValue());
    }

    /**
     * serializes component into {@link ClampedIntComponentModel} data model instance.
     *
     * @return {@link ClampedIntComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new ClampedIntComponentModel(getId(), getLowerBoundary(),
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
     * see {@link ClampedNumberComponentImpl} description.
     *
     * @return {@link ArithmeticsProxy.IntArithmeticsProxy} implementation instance.
     */
    @Override
    protected @NotNull ArithmeticsProxy.IntArithmeticsProxy getArithmeticsProxy() {
        return ARITHMETICS_PROXY;
    }

    /**
     * see {@link ClampedNumberComponentImpl} description.
     *
     * @return {@link TrimmerProxy.IntTrimmerProxy} implementation instance.
     */
    @Override
    protected @NotNull TrimmerProxy.IntTrimmerProxy getTrimmerProxy() {
        return TRIMMER_PROXY;
    }
}
