package ru.rougegibbons.landsanddungeons.components.models.core.numbers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.integer.SingleIntComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;

/**
 * data model for {@link SingleIntComponentImpl} class.
 * @see SingleIntComponentImpl
 */
public class SingleIntComponentModel extends AbstractComponentModel {
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
