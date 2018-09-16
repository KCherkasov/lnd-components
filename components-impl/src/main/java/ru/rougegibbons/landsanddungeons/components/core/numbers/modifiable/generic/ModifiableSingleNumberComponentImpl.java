package ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiableSingleNumberComponent;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;

/**
 * generic implementation of {@link ModifiableSingleNumberComponent} interface.
 *
 * @param <T> - any extension of {@link Number} class.
 * @see ModifiableSingleNumberComponent
 * @see AbstractComponent
 * @see Number
 * @since 0.3.3
 */
public abstract class ModifiableSingleNumberComponentImpl<T extends Number>
        extends AbstractComponent implements ModifiableSingleNumberComponent<T> {
    private T value = initValue();

    /**
     * default constructor to create new component with value initialized by initValue() function.
     */
    public ModifiableSingleNumberComponentImpl() {
        super();
    }

    /**
     * default constructor to create new component with given value.
     *
     * @param value - value to store in the component.
     */
    public ModifiableSingleNumberComponentImpl(@NotNull T value) {
        super();
        this.value = value;
    }

    /**
     * constructor for component deserialization from some data model.
     *
     * @param id    - instance id.
     * @param value - stored value.
     */
    public ModifiableSingleNumberComponentImpl(@NotNull Long id,
                                               @NotNull T value) {
        super(id);
        this.value = value;
    }

    /**
     * see {@link ModifiableSingleNumberComponent} description.
     *
     * @return value currently stored in the component.
     */
    @Override
    public @NotNull T getValue() {
        return value;
    }

    /**
     * see {@link ModifiableSingleNumberComponent} description.
     *
     * @param newValue - new value to store.
     */
    @Override
    public void setValue(@NotNull T newValue) {
        value = newValue;
    }

    /**
     * see {@link ModifiableSingleNumberComponent} description.
     *
     * @param amount - value to add.
     */
    @Override
    public void increase(@NotNull T amount) {
        value = getArithmeticsProxy().add(value, amount);
    }

    /**
     * see {@link ModifiableSingleNumberComponent} description.
     *
     * @param amount - value to subtract.
     */
    @Override
    public void decrease(@NotNull T amount) {
        value = getArithmeticsProxy().subtract(value, amount);
    }

    /**
     * see {@link ModifiableSingleNumberComponent} description.
     *
     * @param percent - float percentage value to modify the stored one by.
     */
    @Override
    public void modifyByPercentage(@NotNull Float percent) {
        value = getArithmeticsProxy().modifyByPercentage(value, percent);
    }

    /**
     * see {@link ModifiableSingleNumberComponent} description.
     *
     * @param percent - integer percentage value to modify the stored one by.
     */
    @Override
    public void modifyByPercentage(@NotNull Integer percent) {
        value = getArithmeticsProxy().modifyByPercentage(value, percent);
    }

    /**
     * method used to initialize default value to store in the component.
     *
     * @return default stored value.
     */
    protected abstract @NotNull T initValue();

    /**
     * get instance's {@link ArithmeticsProxy}.
     *
     * @return {@link ArithmeticsProxy} instance for class.
     */
    protected abstract @NotNull ArithmeticsProxy<T> getArithmeticsProxy();
}
