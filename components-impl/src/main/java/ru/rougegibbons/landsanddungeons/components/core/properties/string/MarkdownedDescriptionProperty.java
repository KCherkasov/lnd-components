package ru.rougegibbons.landsanddungeons.components.core.properties.string;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.constants.ComponentLabels;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.properties.MarkdownedDescriptionModel;
import ru.rougegibbons.landsanddungeons.components.models.core.properties.PlainDescriptionModel;

import java.util.Set;

/**
 * {@link MarkdownedTextPropertyComponentImpl} class extension
 * representing markdowned descriptions.
 *
 * @see MarkdownedTextPropertyComponentImpl
 * @since 0.4.6
 */
public class MarkdownedDescriptionProperty extends MarkdownedTextPropertyComponentImpl {
    /**
     * constructor for creating new components. Tags are parsed from the raw text.
     *
     * @param text - markdowned text to store in component.
     */
    public MarkdownedDescriptionProperty(@NotNull String text) {
        super(ComponentLabels.CL_DESCRIPTION, text);
    }

    /**
     * constructor for creating new components. Tags are passed as a {@link Set}.
     *
     * @param text - markdowned text to store in component.
     * @param tags - markdown tags.
     */
    public MarkdownedDescriptionProperty(@NotNull String text,
                                         @NotNull Set<String> tags) {
        super(ComponentLabels.CL_DESCRIPTION, text, tags);
    }

    /**
     * constructor for component deserialization from {@link PlainDescriptionModel} data model instance.
     *
     * @param model - {@link PlainDescriptionModel} data model instance containing component data.
     */
    public MarkdownedDescriptionProperty(@NotNull PlainDescriptionModel model) {
        super(model.getId(), ComponentLabels.CL_DESCRIPTION, model.getText());
    }

    /**
     * constructor for component deserialization from {@link MarkdownedDescriptionModel} data model instance.
     *
     * @param model - {@link MarkdownedDescriptionModel} data model instance containing component data.
     */
    public MarkdownedDescriptionProperty(@NotNull MarkdownedDescriptionModel model) {
        super(model.getId(), ComponentLabels.CL_DESCRIPTION,
                model.getText(), model.getTags());
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.PackableComponent}.
     *
     * @return {@link MarkdownedDescriptionModel} data model instance containing component data.
     */
    @Override
    public @NotNull AbstractComponentModel pack() {
        return new MarkdownedDescriptionModel(getId(), getText(), getTags());
    }
}
