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
public class ClampedIntComponentModel extends ComponentModel {
    private final Integer lowerBoundary;
    private final Integer upperBoundary;
    private final Integer currentValue;

    /**
     * \
     * constructor for serialization component data.
     *
     * @param id            - instance id.
     * @param lowerBoundary - lower boundary.
     * @param upperBoundary - upper boundary.
     * @param currentValue  - current value.
     */
    public ClampedIntComponentModel(@NotNull @JsonProperty("id") Long id,
                                    @NotNull @JsonProperty("lowerBoundary")
                                            Integer lowerBoundary,
                                    @NotNull @JsonProperty("upperBoundary")
                                            Integer upperBoundary,
                                    @NotNull @JsonProperty("currentValue")
                                            Integer currentValue) {
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
    public @NotNull Integer getLowerBoundary() {
        return lowerBoundary;
    }

    /**
     * get upper boundary.
     *
     * @return upper boundary.
     */
    @JsonProperty("upperBoundary")
    public @NotNull Integer getUpperBoundary() {
        return upperBoundary;
    }

    /**
     * get current value.
     *
     * @return current value.
     */
    @JsonProperty("currentValue")
    public @NotNull Integer getCurrentValue() {
        return currentValue;
    }
}
