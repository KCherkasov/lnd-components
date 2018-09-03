package ru.rougegibbons.landsanddungeons.components.models.core.numbers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.integer.PairIntComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;

/**
 * data model for {@link PairIntComponentImpl} class.
 * @see PairIntComponentImpl
 * @see ComponentModel
 */
public class PairIntComponentModel extends ComponentModel {
    private final Integer firstValue;
    private final Integer secondValue;

    /**
     * constructor for serialization/deserialization.
     * @param id - instance id.
     * @param firstValue - first stored value.
     * @param secondValue - second stored value.
     */
    public PairIntComponentModel(@NotNull @JsonProperty("id") Long id,
                                 @NotNull @JsonProperty("firstValue") Integer firstValue,
                                 @NotNull @JsonProperty("secondValue") Integer secondValue) {
        super(id);
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /**
     * get first stored value from model.
     * @return first stored value.
     */
    @JsonProperty("firstValue")
    public @NotNull Integer getFirstValue() {
        return firstValue;
    }

    /**
     * get second stored value from model.
     * @return second stored value.
     */
    @JsonProperty("secondValue")
    public @NotNull Integer getSecondValue() {
        return secondValue;
    }
}
