package ru.rougegibbons.landsanddungeons.components.core.properties.string;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.properties.AbstractProperty;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.properties.string.TextPropertyComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.StringComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;

/**
 * Abstract {@link TextPropertyComponent} implementation, representing property containing some text.
 * Text is incapsulated in some {@link StringComponent} interface implementation.
 *
 * @see AbstractProperty
 * @see ru.rougegibbons.landsanddungeons.components.interfaces.core.properties.PropertyComponent
 * @see TextPropertyComponent
 * @see StringComponent
 * @since 0.4.6
 */
public abstract class AbstractTextPropertyComponent
        extends AbstractProperty implements TextPropertyComponent {
    private final StringComponent textHolder;

    /**
     * default constructor for creating new components. Text is passed as {@link StringComponent} instance.
     *
     * @param label      - component label.
     * @param textHolder - {@link StringComponent} instance containing component text.
     */
    public AbstractTextPropertyComponent(@NotNull Long label,
                                         @NotNull StringComponent textHolder) {
        super(label);
        this.textHolder = textHolder;
    }

    /**
     * default constructor for deserialization from some data model instance.
     *
     * @param id         - instance id.
     * @param label      - component label.
     * @param textHolder - {@link StringComponent} instance containing component text.
     */
    public AbstractTextPropertyComponent(@NotNull Long id,
                                         @NotNull Long label,
                                         @NotNull StringComponent textHolder) {
        super(id, label);
        this.textHolder = textHolder;
    }

    /**
     * default constructor for creating new components. Text is passed as a {@link String} instance.
     *
     * @param label - component label.
     * @param text  - text.
     */
    public AbstractTextPropertyComponent(@NotNull Long label,
                                         @NotNull String text) {
        super(label);
        textHolder = initTextHolder(text);
    }

    /**
     * constructor for deserialization from some data model instance.
     *
     * @param id    - component instance id.
     * @param label - component label.
     * @param text  - component text.
     */
    public AbstractTextPropertyComponent(@NotNull Long id,
                                         @NotNull Long label,
                                         @NotNull String text) {
        super(id, label);
        textHolder = initTextHolder(text);
    }

    /**
     * get text stored in the component.
     *
     * @return stored text.
     */
    @Override
    public @NotNull String getText() {
        return textHolder.getText();
    }

    /**
     * component text holder initializer. Deserializes text holder from data model.
     *
     * @param model - text holder data model.
     * @return component text holder.
     */
    protected abstract @NotNull StringComponent initTextHolder(@NotNull AbstractComponentModel model);

    /**
     * component text holder initializer. Creates text holder from text string.
     *
     * @param text - text string.
     * @return component text holder.
     */
    protected abstract @NotNull StringComponent initTextHolder(@NotNull String text);

    /**
     * get component's text holder.
     *
     * @return component text holder.
     */
    protected @NotNull StringComponent getTextHolder() {
        return textHolder;
    }
}
