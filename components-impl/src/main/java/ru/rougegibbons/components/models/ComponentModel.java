package ru.rougegibbons.components.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.components.models.core.SingleFloatComponentModel;
import ru.rougegibbons.components.models.core.SingleIntComponentModel;
import ru.rougegibbons.components.models.core.SingleLongComponentModel;
import ru.rougegibbons.components.models.core.StringComponentModel;

/**
 * base data model class for components.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(StringComponentModel.class),
        @JsonSubTypes.Type(SingleIntComponentModel.class),
        @JsonSubTypes.Type(SingleLongComponentModel.class),
        @JsonSubTypes.Type(SingleFloatComponentModel.class)
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
