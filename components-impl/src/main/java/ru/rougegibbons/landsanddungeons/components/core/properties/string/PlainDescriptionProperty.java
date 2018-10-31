package ru.rougegibbons.landsanddungeons.components.core.properties.string;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.constants.ComponentLabels;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.properties.PlainDescriptionModel;

/**
 * {@link PlainTextPropertyComponent} class extension
 * representing no-markdown description component.
 *
 * @see PlainTextPropertyComponent
 * @since 0.4.6
 */
public class PlainDescriptionProperty extends PlainTextPropertyComponent {
    /**
     * default constructor for creating new components.
     *
     * @param text - text to store in property.
     */
    public PlainDescriptionProperty(@NotNull String text) {
        super(ComponentLabels.CL_DESCRIPTION, text);
    }

    /**
     * constructor for deserialization from {@link PlainDescriptionModel} data model instance.
     *
     * @param model - {@link PlainDescriptionModel} data model instance containing component data.
     */
    public PlainDescriptionProperty(@NotNull PlainDescriptionModel model) {
        super(model.getId(), ComponentLabels.CL_DESCRIPTION, model.getText());
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.PackableComponent}.
     *
     * @return {@link PlainDescriptionModel} data model instance containing component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new PlainDescriptionModel(getId(), getText());
    }
}
