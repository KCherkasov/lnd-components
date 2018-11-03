package ru.rougegibbons.landsanddungeons.components.models.core.numbers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.longint.SingleLongComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;

/**
 * data model for {@link SingleLongComponentImpl} class.
 */
public class SingleLongComponentModel extends AbstractComponentModel {
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
