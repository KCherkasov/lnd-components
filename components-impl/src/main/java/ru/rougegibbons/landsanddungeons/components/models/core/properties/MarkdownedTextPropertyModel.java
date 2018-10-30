package ru.rougegibbons.landsanddungeons.components.models.core.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.models.core.string.parsers.MarkdownStringComponentModel;

import java.util.Set;

/**
 * {@link MarkdownStringComponentModel} extension to represent
 * {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.properties.string.MarkdownedTextPropertyComponent}
 * data model.
 *
 * @since 0.4.6
 */
public class MarkdownedTextPropertyModel extends MarkdownStringComponentModel {
    private final Long label;

    /**
     * default constructor serializing component data into data model instance.
     *
     * @param id    - instance id.
     * @param label - component label.
     * @param text  - component text.
     * @param tags  - component markdown tags.
     */
    public MarkdownedTextPropertyModel(@JsonProperty("id") @NotNull Long id,
                                       @JsonProperty("label") @NotNull Long label,
                                       @JsonProperty("text") @NotNull String text,
                                       @JsonProperty("tags") @NotNull Set<String> tags) {
        super(id, text, tags);
        this.label = label;
    }

    /**
     * get component label stored in data model.
     *
     * @return stored component label.
     */
    @JsonProperty("label")
    public @NotNull Long getLabel() {
        return label;
    }
}
