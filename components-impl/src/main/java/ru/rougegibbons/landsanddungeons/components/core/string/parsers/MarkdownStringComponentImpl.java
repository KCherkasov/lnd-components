package ru.rougegibbons.landsanddungeons.components.core.string.parsers;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.string.StringComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers.MarkdownStringComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.StringComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.parsers.MarkdownStringComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * implementation of {@link MarkdownStringComponent} interface.
 * Extension of {@link StringComponentImpl} class.
 * @see MarkdownStringComponent
 * @see ru.rougegibbons.landsanddungeons.components.interfaces.core.string.StringComponent
 * @see ComponentModel
 * @see StringComponentModel
 * @see MarkdownStringComponentModel
 * @see String
 */
public class MarkdownStringComponentImpl extends StringComponentImpl
        implements MarkdownStringComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);
    private final Set<String> tags = new HashSet<>();

    /**
     * default constructor for creating new component without directly passing tags keyset.
     * @param text - string containing markdown tags.
     */
    public MarkdownStringComponentImpl(@NotNull String text) {
        super(text);
        extractTags();
    }

    /**
     * constructor for creating new components with passing tags keyset directly.
     * @param text - string containing markdown.
     * @param tags - tags keyset.
     */
    public MarkdownStringComponentImpl(@NotNull String text,
                                       @NotNull Set<String> tags) {
        super(text);
        this.tags.addAll(tags);
    }

    /**
     * constructor for deserializing component from {@link StringComponentModel} data model. Tags keyset is not passed.
     * @param model - {@link StringComponentModel} data model instance.
     */
    public MarkdownStringComponentImpl(@NotNull StringComponentModel model) {
        super(model);
        extractTags();
    }

    /**
     * constructor for deserializing component from {@link MarkdownStringComponentModel} data model, with tags keyset.
     * @param model - {@link MarkdownStringComponentModel} data model instance.
     */
    public MarkdownStringComponentImpl(@NotNull MarkdownStringComponentModel model) {
        super(model);
        tags.addAll(model.getTags());
    }

    /**
     * get tags keyset.
     * @return tags keyset.
     */
    @Override
    public @NotNull Set<String> getTags() {
        return tags;
    }

    /**
     * serialize component's data into {@link MarkdownStringComponentModel} data model.
     * @return {@link MarkdownStringComponentModel} data model instance.
     */
    @Override
    public @NotNull ComponentModel pack() {
        return new MarkdownStringComponentModel(getId(), getText(), tags);
    }

    /**
     * instance id generator to be used in {@link ru.rougegibbons.landsanddungeons.components.core.AbstractComponent}.
     * @return generated instance id.
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }

    /**
     * tag extraction algorithm. Processes the source string and forms tags keyset from it.
     */
    private void extractTags() {
        final String source = getText();
        if (source.isEmpty()) {
            return;
        }
        Integer pos = Constants.ZERO_INT;
        while (pos != IdsConstants.UNDEFINED_ID) {
            Integer tagPos = source.indexOf(MarkdownConfig.TAG_CHARACTER, pos);
            if ((tagPos >= pos) && (source.length() - tagPos) > 1) {
                final StringBuilder tag = new StringBuilder();
                while (tagPos < source.length() && !MarkdownConfig.SEPARATORS
                        .contains(source.substring(tagPos, tagPos + 1))) {
                    tag.append(source.charAt(tagPos++));
                }
                tags.add(tag.toString());
            }
            pos = tagPos;
        }
    }
}
