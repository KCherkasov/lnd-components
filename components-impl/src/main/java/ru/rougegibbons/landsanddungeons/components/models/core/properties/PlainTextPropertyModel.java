package ru.rougegibbons.landsanddungeons.components.models.core.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.models.core.string.StringComponentModel;

/**
 * {@link StringComponentModel} extension representing
 * {@link ru.rougegibbons.landsanddungeons.components.core.properties.string.PlainTextPropertyComponent}
 * data model.
 *
 * @since 0.4.6
 */
public class PlainTextPropertyModel extends StringComponentModel {
    private final Long label;

    /**
     * default constructor serializing component data into data model instance.
     *
     * @param id    - instance id.
     * @param label - component label.
     * @param text  - component text.
     */
    public PlainTextPropertyModel(@JsonProperty("id") @NotNull Long id,
                                  @JsonProperty("label") @NotNull Long label,
                                  @JsonProperty("text") @NotNull String text) {
        super(id, text);
        this.label = label;
    }

    /**
     * get component label stored in data model instance.
     *
     * @return stored component label.
     */
    @JsonProperty("label")
    public @NotNull Long getLabel() {
        return label;
    }
}
