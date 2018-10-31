package ru.rougegibbons.landsanddungeons.components.models.core.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.models.core.string.parsers.MarkdownStringComponentModel;

import java.util.Set;

/**
 * {@link MarkdownStringComponentModel} data model extension representing
 * {@link }
 * component.
 *
 * @see MarkdownStringComponentModel
 * @since 0.4.6
 */
public class MarkdownedDescriptionModel extends MarkdownStringComponentModel {
    /**
     * constructor for serialization from description property instance.
     *
     * @param id   - instance id.
     * @param text - component text.
     * @param tags - text markdown tags.
     */
    public MarkdownedDescriptionModel(@NotNull @JsonProperty("id") Long id,
                                      @NotNull @JsonProperty("text") String text,
                                      @NotNull @JsonProperty("tags") Set<String> tags) {
        super(id, text, tags);
    }
}
