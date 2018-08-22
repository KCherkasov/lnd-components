package ru.rougegibbons.components.core.string;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.components.core.AbstractComponent;
import ru.rougegibbons.components.interfaces.core.StringComponent;
import ru.rougegibbons.components.models.ComponentModel;
import ru.rougegibbons.components.models.core.StringComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Basic implementation of {@link StringComponent}.
 * @see StringComponent
 */
public class StringComponentImpl extends AbstractComponent implements StringComponent {
    private final String text;

    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * default constructor for creating a new component.
     * @param text - text data to be stored in component.
     */
    public StringComponentImpl(@NotNull String text) {
        super();
        this.text = text;
    }

    /**
     * constructor for unpacking from resource file/packet.
     * @param model - data model.
     */
    public StringComponentImpl(@NotNull StringComponentModel model) {
        super(model.getId());
        text = model.getText();
    }

    /**
     * get access to the text stored in the instance.
     * @return text data.
     */
    @Override
    public @NotNull String getText() {
        return text;
    }

    /**
     * packs instance data into model object to save in a file/send in packet.
     * @return packed instance data.
     * @see StringComponentModel
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new StringComponentModel(getId(), text);
    }

    /**
     * instance ids generator function.
     * @return generated id.
     * @see AbstractComponent
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }
}
