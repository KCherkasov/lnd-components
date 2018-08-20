package ru.rougegibbons.components.models.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.components.models.ComponentModel;

/**
 * data model for {@link ru.rougegibbons.components.core.numbers.longint.SingleLongComponentImpl}
 */
public class SingleLongComponentModel extends ComponentModel {
    private final Long value;

    /**
     * constructor for packing instance data.
     * @param id - instance id
     * @param value - instance value
     */
    public SingleLongComponentModel(@NotNull @JsonProperty("id") Long id,
                                    @NotNull @JsonProperty("value") Long value) {
        super(id);
        this.value = value;
    }

    /**
     * get stored value.
     * @return stored value.
     */
    @JsonProperty("value")
    public @NotNull Long getValue() {
        return value;
    }
}
