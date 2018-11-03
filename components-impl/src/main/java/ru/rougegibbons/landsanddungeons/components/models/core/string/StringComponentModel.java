package ru.rougegibbons.landsanddungeons.components.models.core.string;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.string.StringComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;

/**
 * data model class for {@link StringComponentImpl} class.
 * @see StringComponentImpl
 */
public class StringComponentModel extends AbstractComponentModel {
    private final String text;

    /**
     * constructor for deserialization from resource file/package or for component instance serialization.
     * @param id - instance id.
     * @param text - stored text
     */
    public StringComponentModel(@NotNull @JsonProperty("id") Long id,
                                @NotNull @JsonProperty("text") String text) {
        super(id);
        this.text = text;
    }

    /**
     * get access to stored text.
     * @return text, stored in model.
     */
    @JsonProperty("text")
    public @NotNull String getText() {
        return text;
    }
}
