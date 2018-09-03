package ru.rougegibbons.landsanddungeons.components.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.floatpoint.PairFloatComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.*;
import ru.rougegibbons.landsanddungeons.components.models.core.string.StringComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.parsers.MarkdownStringComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.parsers.SubstitutionsComponentModel;

/**
 * base data model class for components.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(StringComponentModel.class),
        @JsonSubTypes.Type(MarkdownStringComponentModel.class),
        @JsonSubTypes.Type(SubstitutionsComponentModel.class),

        @JsonSubTypes.Type(SingleIntComponentModel.class),
        @JsonSubTypes.Type(SingleLongComponentModel.class),
        @JsonSubTypes.Type(SingleFloatComponentModel.class),

        @JsonSubTypes.Type(PairIntComponentModel.class),
        @JsonSubTypes.Type(PairLongComponentModel.class),
        @JsonSubTypes.Type(PairFloatComponentImpl.class)
})
public abstract class ComponentModel {
    private final Long id;

    public ComponentModel(@NotNull @JsonProperty("id") Long id) {
        this.id = id;
    }

    /**
     * get stored id.
     * @return stored id.
     */
    @JsonProperty("id")
    public @NotNull Long getId() {
        return id;
    }
}
