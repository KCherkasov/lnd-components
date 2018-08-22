package ru.rougegibbons.components.core.numbers.floatpoint;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.components.core.numbers.generic.SingleNumberComponentImpl;
import ru.rougegibbons.components.interfaces.core.SingleNumberComponent;
import ru.rougegibbons.components.models.ComponentModel;
import ru.rougegibbons.components.models.core.SingleFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link Float} implementation of {@link SingleNumberComponentImpl} class.
 * @see SingleNumberComponent
 * @see SingleNumberComponentImpl
 */
public class SingleFloatComponentImpl extends SingleNumberComponentImpl<Float>
        implements SingleNumberComponent.SingleFloatComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * constructor for deserialization from data model ({@link SingleFloatComponentModel}).
     * @param model data model
     */
    public SingleFloatComponentImpl(@NotNull SingleFloatComponentModel model) {
        super(model.getId(), model.getValue());
    }

    /**
     * overloaded constructor from {@link SingleNumberComponentImpl}.
     * @param value value to be stored in instance.
     */
    public SingleFloatComponentImpl(@NotNull Float value) {
        super(value);
    }

    /**
     * object serialization into {@link SingleFloatComponentModel} instance.
     * @return serialized data model
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new SingleFloatComponentModel(getId(), getValue());
    }

    /**
     * instance id generator to be used in {@link ru.rougegibbons.components.core.AbstractComponent} constructor.
     * @return generated id
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }
}