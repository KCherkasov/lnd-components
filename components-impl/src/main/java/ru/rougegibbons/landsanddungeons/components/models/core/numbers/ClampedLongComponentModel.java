package ru.rougegibbons.landsanddungeons.components.models.core.numbers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;

/**
 * data model for class.
 *
 * @see ComponentModel
 * @since 0.3.5
 */
public class ClampedLongComponentModel extends ComponentModel {
    private final Long lowerBoundary;
    private final Long upperBoundary;
    private final Long currentValue;

    /**
     * \
     * constructor for serialization component data.
     *
     * @param id            - instance id.
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     * @param currentValue  - current value.
     */
    public ClampedLongComponentModel(
            @NotNull @JsonProperty("id") Long id,
            @NotNull @JsonProperty("lowerBoundary")
                    Long lowerBoundary,
            @NotNull @JsonProperty("upperBoundary")
                    Long upperBoundary,
            @NotNull @JsonProperty("currentValue")
                    Long currentValue) {
        super(id);
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
        this.currentValue = currentValue;
    }

    /**
     * get lower boundary.
     *
     * @return lower boundary.
     */
    @JsonProperty("lowerBoundary")
    public @NotNull Long getLowerBoundary() {
        return lowerBoundary;
    }

    /**
     * get upper boundary.
     *
     * @return upper boundary.
     */
    @JsonProperty("upperBoundary")
    public @NotNull Long getUpperBoundary() {
        return upperBoundary;
    }

    /**
     * get current value.
     *
     * @return current value.
     */
    @JsonProperty("currentValue")
    public @NotNull Long getCurrentValue() {
        return currentValue;
    }
}
