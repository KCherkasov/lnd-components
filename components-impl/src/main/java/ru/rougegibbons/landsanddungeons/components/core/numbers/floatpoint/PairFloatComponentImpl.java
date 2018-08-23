package ru.rougegibbons.landsanddungeons.components.core.numbers.floatpoint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.core.numbers.generic.PairNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.PairNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.PairFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link PairNumberComponentImpl} extension for {@link Float} numbers only.
 * @see PairNumberComponentImpl
 * @see PairNumberComponent
 * @see PairNumberComponent.PairFloatComponent
 * @see Float
 */
public class PairFloatComponentImpl extends PairNumberComponentImpl<Float>
        implements PairNumberComponent.PairFloatComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * constructor for creating new component.
     * @param firstValue - first value to be stored.
     * @param secondValue - second value to be stored.
     */
    public PairFloatComponentImpl(@NotNull Float firstValue,
                                  @NotNull Float secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * constructor for deserialization from {@link PairFloatComponentModel} data model.
     * @param model - data model containing component's data.
     */
    public PairFloatComponentImpl(@NotNull PairFloatComponentModel model) {
        super(model.getId(), model.getFirstValue(), model.getSecondValue());
    }

    /**
     * serializes component's data into {@link PairFloatComponentModel} data model.
     * @return data model containing component's data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new PairFloatComponentModel(getId(), getFirstValue(), getSecondValue());
    }

    /**
     * instance id generator to be used in {@link AbstractComponent} constructor.
     * @return generated instance id.
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }
}
