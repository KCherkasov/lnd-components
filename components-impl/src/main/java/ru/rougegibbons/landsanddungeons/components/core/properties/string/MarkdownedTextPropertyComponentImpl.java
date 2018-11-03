package ru.rougegibbons.landsanddungeons.components.core.properties.string;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.string.parsers.MarkdownStringComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.properties.string.MarkdownedTextPropertyComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.StringComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.properties.MarkdownedTextPropertyModel;
import ru.rougegibbons.landsanddungeons.components.models.core.properties.PlainTextPropertyModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.StringComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.parsers.MarkdownStringComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link AbstractTextPropertyComponent} class extension
 * implementing {@link MarkdownedTextPropertyComponent} interface.
 *
 * @see AbstractTextPropertyComponent
 * @see MarkdownedTextPropertyComponent
 * @since 0.4.6
 */
public class MarkdownedTextPropertyComponentImpl
        extends AbstractTextPropertyComponent
        implements MarkdownedTextPropertyComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * default constructor for creating new components. Tags are passed directly.
     *
     * @param label - property label.
     * @param text  - text to store in property.
     * @param tags  - markdown tags to store.
     */
    public MarkdownedTextPropertyComponentImpl(@NotNull Long label,
                                               @NotNull String text,
                                               @NotNull Set<String> tags) {
        super(label, new MarkdownStringComponentImpl(text, tags));
    }

    /**
     * default constructor to create new components. Tags are parsed from the raw text.
     *
     * @param label - property label.
     * @param text  - text to store in property.
     */
    public MarkdownedTextPropertyComponentImpl(@NotNull Long label,
                                               @NotNull String text) {
        super(label, text);
    }

    /**
     * constructor for deserializing from {@link PlainTextPropertyModel} data model. Tags are parsed from the raw text.
     *
     * @param model - {@link PlainTextPropertyModel} data model instance containing component data.
     */
    public MarkdownedTextPropertyComponentImpl(@NotNull PlainTextPropertyModel model) {
        super(model.getId(), model.getLabel(), model.getText());
    }

    /**
     * constructor for deserializing from {@link MarkdownedTextPropertyModel} data model. Tags are passed from model.
     *
     * @param model - {@link MarkdownedTextPropertyModel} data model instance containing component data.
     */
    public MarkdownedTextPropertyComponentImpl(@NotNull MarkdownedTextPropertyModel model) {
        super(model.getId(), model.getLabel(),
                new MarkdownStringComponentImpl(
                        model.getText(), model.getTags()));
    }

    /**
     * constructor for deserializing from derived classes' data models.
     *
     * @param id    - instance id.
     * @param label - component label.
     * @param text  - component text.
     * @param tags  - component tags.
     */
    public MarkdownedTextPropertyComponentImpl(@NotNull Long id,
                                               @NotNull Long label,
                                               @NotNull String text,
                                               @NotNull Set<String> tags) {
        super(id, label, new MarkdownStringComponentImpl(text, tags));
    }

    /**
     * constructor for deserializing from derived classes' data models.
     *
     * @param id    - instance id.
     * @param label - component label.
     * @param text  - component text.
     */
    public MarkdownedTextPropertyComponentImpl(@NotNull Long id,
                                               @NotNull Long label,
                                               @NotNull String text) {
        super(id, label, text);
    }

    /**
     * see {@link MarkdownedTextPropertyComponent} description.
     *
     * @return tags {@link Set} stored in the component.
     */
    @Override
    public @NotNull Set<String> getTags() {
        return markdownedHolder().getTags();
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.PackableComponent}.
     *
     * @return {@link MarkdownedTextPropertyModel} data model instance containing component data.
     */
    @Override
    public @NotNull AbstractComponentModel pack() {
        return new MarkdownedTextPropertyModel(
                getId(), getLabel(), getText(), getTags());
    }

    /**
     * see {@link AbstractTextPropertyComponent} description.
     *
     * @param text - text string.
     * @return {@link MarkdownStringComponentImpl} text holder instance.
     */
    @Override
    protected @NotNull StringComponent initTextHolder(@NotNull String text) {
        return new MarkdownStringComponentImpl(text);
    }

    /**
     * see {@link AbstractTextPropertyComponent description}.
     *
     * @param model - text holder data model.
     * @return {@link MarkdownStringComponentImpl} text holder instance.
     */
    @Override
    protected @NotNull StringComponent initTextHolder(@NotNull AbstractComponentModel model) {
        if (model.getClass().equals(StringComponentModel.class)) {
            return new MarkdownStringComponentImpl((StringComponentModel) model);
        } else if (model.getClass().equals(MarkdownStringComponentModel.class)) {
            return new MarkdownStringComponentImpl((MarkdownStringComponentModel) model);
        } else {
            throw new ClassCastException();
        }
    }

    /**
     * instance id generator to be used in {@link ru.rougegibbons.landsanddungeons.components.core.AbstractComponent}.
     *
     * @return generated instance id.
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }

    /**
     * returns text holder casted from {@link StringComponent} to  {@link MarkdownStringComponentImpl}.
     *
     * @return component text holder as {@link MarkdownStringComponentImpl} instance.
     */
    private @NotNull MarkdownStringComponentImpl markdownedHolder() {
        return (MarkdownStringComponentImpl) getTextHolder();
    }
}
