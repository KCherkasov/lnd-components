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
public class ClampedFloatComponentModel extends ComponentModel {
    private final Float lowerBoundary;
    private final Float upperBoundary;
    private final Float currentValue;

    /**
     * \
     * constructor for serialization component data.
     *
     * @param id            - instance id.
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     * @param currentValue  - current value.
     */
    public ClampedFloatComponentModel(@NotNull @JsonProperty("id") Long id,
                                      @NotNull @JsonProperty("lowerBoundary")
                                              Float lowerBoundary,
                                      @NotNull @JsonProperty("upperBoundary")
                                              Float upperBoundary,
                                      @NotNull @JsonProperty("currentValue")
                                              Float currentValue) {
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
    public @NotNull Float getLowerBoundary() {
        return lowerBoundary;
    }

    /**
     * get upper boundary.
     *
     * @return upper boundary.
     */
    @JsonProperty("upperBoundary")
    public @NotNull Float getUpperBoundary() {
        return upperBoundary;
    }

    /**
     * get current value.
     *
     * @return current value.
     */
    @JsonProperty("currentValue")
    public @NotNull Float getCurrentValue() {
        return currentValue;
    }
}
