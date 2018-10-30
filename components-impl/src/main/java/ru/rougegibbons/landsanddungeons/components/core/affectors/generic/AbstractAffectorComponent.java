package ru.rougegibbons.landsanddungeons.components.core.affectors.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent;

import java.util.Set;

/**
 * generic implementation of {@link AffectorComponent} interface.
 * contains of single {@link Integer} affection,
 * one target {@link ru.rougegibbons.landsanddungeons.components.interfaces.Component}
 * {@link Long} digital label, and {@link Set} of {@link Long} labels describing the component itself.
 *
 * @see AbstractComponent
 * @see AffectorComponent
 * @see Integer
 * @see Long
 * @see Set
 * @since 0.4.6
 */
public abstract class AbstractAffectorComponent extends AbstractComponent
        implements AffectorComponent {
    private Boolean toRemove = false;
    private Integer affection;
    private final Long targetLabel;

    private final Set<Long> labels;

    /**
     * default constructor for creating new components.
     *
     * @param affection   - affection value.
     * @param targetLabel - target component label.
     * @param labels      - {@link Set} of labels describing the component.
     */
    public AbstractAffectorComponent(@NotNull Integer affection,
                                     @NotNull Long targetLabel,
                                     @NotNull Set<Long> labels) {
        super();
        this.affection = affection;
        this.targetLabel = targetLabel;
        this.labels = labels;
    }

    /**
     * constructor for deserializing an object from some data model instance.
     *
     * @param id - instance id.
     * @param affection - affection value.
     * @param targetLabel - target component label.
     * @param labels - {@link Set} of labels describing the component.
     */
    public AbstractAffectorComponent(@NotNull Long id,
                                     @NotNull Integer affection,
                                     @NotNull Long targetLabel,
                                     @NotNull Set<Long> labels) {
        super(id);
        this.affection = affection;
        this.targetLabel = targetLabel;
        this.labels = labels;
    }

    /**
     * see {@link AffectorComponent} description.
     *
     * @return instance affection value.
     */
    @Override
    public @NotNull Integer getAffection() {
        return affection;
    }

    /**
     * see {@link AffectorComponent} description.
     *
     * @return target label.
     */
    @Override
    public @NotNull Long getTargetLabel() {
        return targetLabel;
    }

    /**
     * see {@link AffectorComponent} description.
     *
     * @return true if instance is marked for removal, false otherwise.
     */
    @Override
    public @NotNull Boolean isToRemove() {
        return toRemove;
    }

    /**
     * see {@link AffectorComponent} description.
     */
    @Override
    public void markToRemove() {
        toRemove = true;
    }

    /**
     * see {@link AffectorComponent} description.
     *
     * @param label - label to check.
     * @return true if such label is in instance's labels set, false otherwise.
     */
    @Override
    public @NotNull Boolean hasLabel(@NotNull Long label) {
        return labels.contains(label);
    }

    /**
     * see {@link AffectorComponent} description.
     *
     * @param label - label to add.
     */
    @Override
    public void addLabel(@NotNull Long label) {
        labels.add(label);
    }


    /**
     * see {@link AffectorComponent} description.
     *
     * @param label - label to remove.
     */
    @Override
    public void removeLabel(@NotNull Long label) {
        labels.remove(label);
    }
}
