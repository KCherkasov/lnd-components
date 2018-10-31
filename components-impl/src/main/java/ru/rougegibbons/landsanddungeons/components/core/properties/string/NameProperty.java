package ru.rougegibbons.landsanddungeons.components.core.properties.string;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.constants.ComponentLabels;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.properties.NamePropertyModel;

/**
 * {@link PlainTextPropertyComponent} extension for representing components storing entity name.
 *
 * @see PlainTextPropertyComponent
 * @since 0.4.6
 */
public class NameProperty extends PlainTextPropertyComponent {
    /**
     * default constructor for creating new component.
     *
     * @param text - text to store in the component.
     */
    public NameProperty(@NotNull String text) {
        super(ComponentLabels.CL_NAME, text);
    }

    /**
     * constructor for component deserialization from {@link NamePropertyModel} data model instance.
     *
     * @param model - {@link NamePropertyModel} data model instance containing component data.
     */
    public NameProperty(@NotNull NamePropertyModel model) {
        super(model.getId(), ComponentLabels.CL_NAME, model.getText());
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.PackableComponent}.
     *
     * @return {@link NamePropertyModel} data model instance storing component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new NamePropertyModel(getId(), getText());
    }
}
