package ru.rougegibbons.components.models.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.components.models.ComponentModel;

/**
 * data model for {@link ru.rougegibbons.components.core.numbers.integer.SingleIntComponentImpl}
 * @see ru.rougegibbons.components.core.numbers.integer.SingleIntComponentImpl
 */
public class SingleIntComponentModel extends ComponentModel {
    private final Integer value;

    public SingleIntComponentModel(@NotNull @JsonProperty("id") Long id,
                                   @NotNull @JsonProperty("value") Integer value) {
        super(id);
        this.value = value;
    }

    /**
     * get stored data.
     * @return stored data.
     */
    @JsonProperty("value")
    public @NotNull Integer getValue() {
        return value;
    }
}
