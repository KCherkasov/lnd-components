package ru.rougegibbons.landsanddungeons.components.core.string.parsers;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers.SubstitutionsComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.parsers.SubstitutionsComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link SubstitutionsComponent} interface implementation for storing markdown tags' substitutions.
 * @see SubstitutionsComponent
 * @see AbstractComponent
 */
public class SubstitutionsComponentImpl extends AbstractComponent
        implements SubstitutionsComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private final Map<String, String> substitutions;

    /**
     * default constructor for creating new components.
     * @param substitutions - tags to substitutions map to be stored in the instance.
     */
    public SubstitutionsComponentImpl(@NotNull Map<String, String> substitutions) {
        super();
        this.substitutions = substitutions;
    }

    /**
     * constructor for object deserialization from {@link SubstitutionsComponentModel} data model instance.
     * @param model - {@link SubstitutionsComponentModel} data model instance.
     */
    public SubstitutionsComponentImpl(@NotNull SubstitutionsComponentModel model) {
        super(model.getId());
        this.substitutions = model.getSubstitutions();
    }

    /**
     * get tags to substitutions map.
     * @return tags to substitutions map.
     */
    @Override
    public @NotNull Map<String, String> getSubstitutionsMap() {
        return substitutions;
    }

    /**
     * get markdown tags keyset.
     * @return markdown tags keyset.
     */
    @Override
    public @NotNull Set<String> getTags() {
        return substitutions.keySet();
    }

    /**
     * get substitution strings as list.
     * @return list of substitution strings.
     */
    @Override
    public @NotNull List<String> getSubstitutions() {
        final List<String> substitutionsList = new ArrayList<>();
        for (String key : substitutions.keySet()) {
            substitutionsList.add(substitutions.get(key));
        }
        return substitutionsList;
    }

    /**
     * serialize component's data into {@link SubstitutionsComponentModel} data model instance.
     * @return {@link SubstitutionsComponentModel} data model instance.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new SubstitutionsComponentModel(getId(), substitutions);
    }

    /**
     * instance ids generator to be used in {@link AbstractComponent} constructor.
     * @return generated instance id.
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }
}
