package ru.rougegibbons.landsanddungeons.components.core.numbers.constant.integer;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.generic.OrderedPairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.PairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Extension of {@link OrderedPairNumberComponentImpl}, ensuring that component's first value
 * is always equal lesser or equal to the component's second value.
 *
 * @see OrderedPairNumberComponentImpl
 * @see PairNumberComponent.PairIntComponent
 * @see PairIntComponentModel
 * @since 0.3.3
 */
public class MinMaxPairIntComponent extends OrderedPairNumberComponentImpl<Integer>
        implements PairNumberComponent.PairIntComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * constructor for creating new component.
     *
     * @param firstValue  - first value to be stored.
     * @param secondValue - second value to be stored.
     */
    public MinMaxPairIntComponent(@NotNull Integer firstValue,
                                  @NotNull Integer secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserialization from {@link PairIntComponentModel} data model.
     *
     * @param model - data model containing component's data.
     */
    public MinMaxPairIntComponent(@NotNull PairIntComponentModel model) {
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
     * see {@link OrderedPairNumberComponentImpl} description.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return value to put as first stored number.
     */
    @Override
    protected @NotNull Integer defineFirstValue(@NotNull Integer first,
                                                @NotNull Integer second) {
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
    protected @NotNull Integer defineSecondValue(@NotNull Integer first,
                                                 @NotNull Integer second) {
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
