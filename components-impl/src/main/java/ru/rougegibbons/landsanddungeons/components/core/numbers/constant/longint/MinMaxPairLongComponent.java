package ru.rougegibbons.landsanddungeons.components.core.numbers.constant.longint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.generic.OrderedPairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.PairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Extension of {@link OrderedPairNumberComponentImpl}, ensuring that component's first value
 * is always equal lesser or equal to the component's second value.
 *
 * @see OrderedPairNumberComponentImpl
 * @see PairNumberComponent.PairLongComponent
 * @see PairLongComponentModel
 * @since 0.3.3
 */
public class MinMaxPairLongComponent extends OrderedPairNumberComponentImpl<Long>
        implements PairNumberComponent.PairLongComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * constructor for creating new component.
     *
     * @param firstValue  - first value to be stored.
     * @param secondValue - second value to be stored.
     */
    public MinMaxPairLongComponent(@NotNull Long firstValue,
                                   @NotNull Long secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserialization from {@link PairLongComponentModel} data model.
     *
     * @param model - data model containing component's data.
     */
    public MinMaxPairLongComponent(@NotNull PairLongComponentModel model) {
        super(model.getId(), model.getFirstValue(), model.getSecondValue());
    }

    /**
     * serializes component data in {@link PairLongComponentModel} data model instance.
     *
     * @return {@link PairLongComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new PairLongComponentModel(getId(), getFirstValue(), getSecondValue());
    }

    /**
     * see {@link OrderedPairNumberComponentImpl} description.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return value to put as first stored number.
     */
    @Override
    protected @NotNull Long defineFirstValue(@NotNull Long first,
                                             @NotNull Long second) {
        return first <= second ? first : second;
    }

    /**
     * see {@link OrderedPairNumberComponentImpl} description.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return value to put as second stored number.
     */
    @Override
    protected @NotNull Long defineSecondValue(@NotNull Long first,
                                              @NotNull Long second) {
        return second >= first ? second : first;
    }

    /**
     * instance id generator to be used in {@link AbstractComponent} constructor.
     *
     * @return generated instance id.
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }
}
