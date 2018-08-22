package ru.rougegibbons.components.models.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.components.models.ComponentModel;

/**
 * data model class for {@link ru.rougegibbons.components.core.string.StringComponentImpl} class.
 * @see ru.rougegibbons.components.core.string.StringComponentImpl
 */
public class StringComponentModel extends ComponentModel {
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