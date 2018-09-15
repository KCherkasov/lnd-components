package ru.rougegibbons.landsanddungeons.components.models.core.numbers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.floatpoint.SingleFloatComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;

/**
 * data model for {@link SingleFloatComponentImpl} class.
 * @see SingleFloatComponentImpl
 */
public class SingleFloatComponentModel extends ComponentModel {
    private final Float value;

    /**
     * constructor for reading from resource file/packet or serializing the component instance.
     * @param id - instance id
     * @param value - stored value
     */
    public SingleFloatComponentModel(@NotNull @JsonProperty("id") Long id,
                                     @NotNull @JsonProperty("value") Float value) {
        super(id);
        this.value = value;
    }

    /**
     * get stored value.
     * @return stored value
     */
    @JsonProperty("value")
    public @NotNull Float getValue() {
        return value;
    }
}
