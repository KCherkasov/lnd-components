package ru.rougegibbons.landsanddungeons.components.core.numbers.constant.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.SingleNumberComponent;

/**
 * generic implementation for {@link SingleNumberComponent}.
 * @param <T> any class derived from {@link Number}.
 * @see SingleNumberComponent
 * @see AbstractComponent
 */
public abstract class SingleNumberComponentImpl<T extends Number> extends AbstractComponent
        implements SingleNumberComponent<T> {
    private final T value;

    /**
     * constructor for loading from resource file/unpacking from packet.
     * @param id - stored id.
     * @param value - stored value.
     */
    public SingleNumberComponentImpl(@NotNull Long id,
                                     @NotNull T value) {
        super(id);
        this.value = value;
    }

    /**
     * constructor for new object creation.
     * @param value - data to store in component.
     */
    public SingleNumberComponentImpl(@NotNull T value) {
        super();
        this.value = value;
    }

    /**
     * get stored value.
     * @return stored value.
     */
    @Override
    public @NotNull T getValue() {
        return value;
    }
}
