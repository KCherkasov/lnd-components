package ru.rougegibbons.landsanddungeons.components.models.core.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.models.core.string.StringComponentModel;

/**
 * {@link StringComponentModel} data model extension
 * for {@link ru.rougegibbons.landsanddungeons.components.core.properties.string.NameProperty}
 * component.
 *
 * @see StringComponentModel
 * @since 0.4.6
 */
public class NamePropertyModel extends StringComponentModel {
    /**
     * constructor for serialization from property instance.
     *
     * @param id   - instance id.
     * @param text - text stored in property.
     */
    public NamePropertyModel(@NotNull @JsonProperty("id") Long id,
                             @NotNull @JsonProperty("text") String text) {
        super(id, text);
    }
}
