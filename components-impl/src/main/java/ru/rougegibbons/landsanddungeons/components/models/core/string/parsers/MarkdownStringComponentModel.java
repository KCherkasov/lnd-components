package ru.rougegibbons.landsanddungeons.components.models.core.string.parsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.models.core.string.StringComponentModel;

import java.util.HashSet;
import java.util.Set;

/**
 * data model for class.
 */
public class MarkdownStringComponentModel extends StringComponentModel {
    private final Set<String> tags = new HashSet<>();

    /**
     * constructor for serialization/deserialization purposes.
     * @param id - instance id.
     * @param text - source markdown string.
     * @param tags - tags keyset (may be omitted).
     */
    public MarkdownStringComponentModel(@NotNull @JsonProperty("id") Long id,
                                        @NotNull @JsonProperty("text") String text,
                                        @NotNull @JsonProperty("tags") Set<String> tags) {
        super(id, text);
        this.tags.addAll(tags);
    }

    /**
     * get tags keyset.
     * @return tags keyset.
     */
    @JsonProperty("tags")
    public @NotNull Set<String> getTags() {
        return tags;
    }
}
