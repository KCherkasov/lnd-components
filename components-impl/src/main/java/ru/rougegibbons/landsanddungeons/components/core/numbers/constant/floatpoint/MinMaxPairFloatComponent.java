package ru.rougegibbons.landsanddungeons.components.core.numbers.constant.floatpoint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.generic.OrderedPairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.PairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatComparator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Extension of {@link OrderedPairNumberComponentImpl}, ensuring that component's first value
 * is always equal lesser or equal to the component's second value.
 *
 * @see OrderedPairNumberComponentImpl
 * @see PairNumberComponent.PairFloatComponent
 * @see PairFloatComponentModel
 * @since 0.3.3
 */
public class MinMaxPairFloatComponent extends OrderedPairNumberComponentImpl<Float>
        implements PairNumberComponent.PairFloatComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * constructor for creating new component.
     *
     * @param firstValue  - first value to be stored.
     * @param secondValue - second value to be stored.
     */
    public MinMaxPairFloatComponent(@NotNull Float firstValue,
                                    @NotNull Float secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserialization from {@link PairFloatComponentModel} data model.
     *
     * @param model - data model containing component's data.
     */
    public MinMaxPairFloatComponent(@NotNull PairFloatComponentModel model) {
        super(model.getId(), model.getFirstValue(), model.getSecondValue());
    }

    /**
     * serializes component data in {@link PairFloatComponentModel} data model instance.
     *
     * @return {@link PairFloatComponentModel} data model instance with component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new PairFloatComponentModel(getId(), getFirstValue(), getSecondValue());
    }

    /**
     * see {@link OrderedPairNumberComponentImpl} description.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return value to put as first stored number.
     */
    @Override
    protected @NotNull Float defineFirstValue(@NotNull Float first,
                                              @NotNull Float second) {
        return FloatComparator.isLess(first, second)
                || FloatComparator.areEqual(first, second) ? first : second;
    }

    /**
     * see {@link OrderedPairNumberComponentImpl} description.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return value to put as second stored number.
     */
    @Override
    protected @NotNull Float defineSecondValue(@NotNull Float first,
                                               @NotNull Float second) {
        return FloatComparator.isGreater(second, first)
                || FloatComparator.areEqual(first, second) ? second : first;
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
