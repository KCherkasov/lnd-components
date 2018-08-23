package ru.rougegibbons.landsanddungeons.components.models.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.floatpoint.PairFloatComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;

/**
 * data model for  {@link PairFloatComponentImpl} class.
 */
public class PairFloatComponentModel extends ComponentModel {
    private final Float firstValue;
    private final Float secondValue;

    /**
     * constructor for component serialization/deserialization.
     * @param id - instance id.
     * @param firstValue - first value to be stored.
     * @param secondValue - second value to be stored.
     */
    public PairFloatComponentModel(@NotNull @JsonProperty("id") Long id,
                                   @NotNull @JsonProperty("firstValue") Float firstValue,
                                   @NotNull @JsonProperty("secondValue") Float secondValue) {
        super(id);
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @JsonProperty("firstValue")
    public @NotNull Float getFirstValue() {
        return firstValue;
    }

    @JsonProperty("secondValue")
    public @NotNull Float getSecondValue() {
        return secondValue;
    }
}
