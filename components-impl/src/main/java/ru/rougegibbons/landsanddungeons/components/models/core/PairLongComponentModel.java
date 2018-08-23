package ru.rougegibbons.landsanddungeons.components.models.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.longint.PairLongComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;

/**
 * data model for {@link PairLongComponentImpl} class.
 */
public class PairLongComponentModel extends ComponentModel {
    private final Long firstValue;
    private final Long secondValue;

    /**
     * constructor for serialization/deserialization.
     * @param id - instance id
     * @param firstValue - first value to be stored
     * @param secondValue - second value to be stored
     */
    public PairLongComponentModel(@NotNull @JsonProperty("id") Long id,
                                  @NotNull @JsonProperty("firstValue") Long firstValue,
                                  @NotNull @JsonProperty("secondValue") Long secondValue) {
        super(id);
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /**
     * get first stored value.
     * @return first stored value.
     */
    @JsonProperty("firstValue")
    public @NotNull Long getFirstValue() {
        return firstValue;
    }

    /**
     * get second stored value.
     * @return second stored value.
     */
    @JsonProperty("secondValue")
    public @NotNull Long getSecondValue() {
        return secondValue;
    }
}
