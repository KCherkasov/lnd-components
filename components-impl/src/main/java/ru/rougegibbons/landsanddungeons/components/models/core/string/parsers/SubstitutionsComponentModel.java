package ru.rougegibbons.landsanddungeons.components.models.core.string.parsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;

import java.util.HashMap;
import java.util.Map;

/**
 * data model for  {@link ru.rougegibbons.landsanddungeons.components.core.string.parsers.SubstitutionsComponentImpl}.
 * @see ru.rougegibbons.landsanddungeons.components.core.string.parsers.SubstitutionsComponentImpl
 * @see AbstractComponentModel
 */
public class SubstitutionsComponentModel extends AbstractComponentModel {
    private final Map<String, String> substitutions = new HashMap<>();

    /**
     * default constructor for serialization/deserialization purposes.
     * @param id - instance id.
     * @param substitutions - tags to substitutions map to be stored in model.
     */
    public SubstitutionsComponentModel(@NotNull @JsonProperty("id") Long id,
                                       @NotNull @JsonProperty("substitutions")
                                               Map<String, String> substitutions) {
        super(id);
        this.substitutions.putAll(substitutions);
    }

    /**
     * get substitustions map.
     * @return tags to substitutions map.
     */
    @JsonProperty("substitutions")
    public @NotNull Map<String, String> getSubstitutions() {
        return substitutions;
    }
}
