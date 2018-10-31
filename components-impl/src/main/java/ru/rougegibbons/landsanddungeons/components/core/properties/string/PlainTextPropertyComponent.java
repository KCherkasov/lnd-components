package ru.rougegibbons.landsanddungeons.components.core.properties.string;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.string.StringComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.StringComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.properties.PlainTextPropertyModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.StringComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link AbstractTextPropertyComponent} extension for storing plain text without markdown.
 *
 * @see AbstractTextPropertyComponent
 * @since 0.4.6
 */
public class PlainTextPropertyComponent extends AbstractTextPropertyComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * default constructor to create new components. Text passed as {@link StringComponent} instance.
     *
     * @param label      - component label.
     * @param textHolder - component text holder.
     */
    public PlainTextPropertyComponent(@NotNull Long label,
                                      @NotNull StringComponent textHolder) {
        super(label, textHolder);
    }

    /**
     * default constructor to create new components. Text passed as {@link String} instance.
     *
     * @param label - component label.
     * @param text  - text string.
     */
    public PlainTextPropertyComponent(@NotNull Long label,
                                      @NotNull String text) {
        super(label, text);
    }

    /**
     * constructoor for component deserialization from {@link PlainTextPropertyModel} data model instance.
     *
     * @param model - {@link PlainTextPropertyModel} data model instance containing component data.
     */
    public PlainTextPropertyComponent(@NotNull PlainTextPropertyModel model) {
        super(model.getId(), model.getLabel(), model.getText());
    }

    /**
     * constructor for derivative classes deserialization from their data models.
     *
     * @param id    - instance id.
     * @param label - component label.
     * @param text  - component text.
     */
    public PlainTextPropertyComponent(@NotNull Long id,
                                      @NotNull Long label,
                                      @NotNull String text) {
        super(id, label, text);
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.PackableComponent}.
     *
     * @return {@link PlainTextPropertyModel} data model instance containing component data.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new PlainTextPropertyModel(getId(), getLabel(), getText());
    }

    /**
     * see {@link AbstractTextPropertyComponent} description.
     *
     * @param model - text holder data model.
     * @return {@link StringComponentImpl} instance.
     */
    @Override
    protected @NotNull StringComponent initTextHolder(@NotNull ComponentModel model) {
        if (model.getClass().equals(StringComponentModel.class)) {
            return new StringComponentImpl((StringComponentModel) model);
        } else {
            throw new ClassCastException();
        }
    }

    /**
     * see {@link AbstractTextPropertyComponent} description.
     *
     * @param text - text string.
     * @return {@link StringComponentImpl} instance.
     */
    @Override
    public @NotNull StringComponent initTextHolder(@NotNull String text) {
        return new StringComponentImpl(text);
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
}
