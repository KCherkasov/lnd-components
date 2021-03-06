package ru.rougegibbons.landsanddungeons.components.core.numbers.constant.integer;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.generic.SingleNumberComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.SingleNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.SingleIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link Integer} predefined version of {@link SingleNumberComponentImpl}.
 * @see SingleNumberComponent
 * @see SingleNumberComponentImpl
 */
public class SingleIntComponentImpl extends SingleNumberComponentImpl<Integer>
        implements SingleNumberComponent.SingleIntComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * constructor using {@link SingleIntComponentModel} data model.
     * @param model - data model with component data.
     */
    public SingleIntComponentImpl(@NotNull SingleIntComponentModel model) {
        super(model.getId(), model.getValue());
    }

    /**
     * overload of {@link SingleNumberComponentImpl} constructor.
     * @param value data to be stored in instance.
     */
    public SingleIntComponentImpl(@NotNull Integer value) {
        super(value);
    }

    /**
     * packs component data into data model object.
     * @return data model object.
     */
    @Override
    public @NotNull AbstractComponentModel pack() {
        return new SingleIntComponentModel(getId(), getValue());
    }

    /**
     * instance id generator for {@link AbstractComponent} constructor.
     * @return generated id.
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }
}
