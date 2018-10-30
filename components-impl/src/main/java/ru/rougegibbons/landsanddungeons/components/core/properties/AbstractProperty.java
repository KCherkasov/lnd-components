package ru.rougegibbons.landsanddungeons.components.core.properties;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.properties.PropertyComponent;

/**
 * abstract implementation of {@link PropertyComponent} interface.
 *
 * @see AbstractComponent
 * @see PropertyComponent
 * @since 0.4.6
 */
public abstract class AbstractProperty extends AbstractComponent
        implements PropertyComponent {
    private final Long label;

    public AbstractProperty(@NotNull Long label) {
        super();
        this.label = label;
    }

    public AbstractProperty(@NotNull Long id,
                            @NotNull Long label) {
        super(id);
        this.label = label;
    }

    @Override
    public @NotNull Long getLabel() {
        return label;
    }
}
