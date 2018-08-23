package ru.rougegibbons.landsanddungeons.components.core.string.parsers;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers.MarkdownStringComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers.MarkdownTextParserComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers.SubstitutionsComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link MarkdownTextParserComponent} interface implementation with default markdown-processing logic.
 * @see MarkdownTextParserComponent
 * @see AbstractComponent
 */
public class MarkdownTextParserComponentImpl extends AbstractComponent
        implements MarkdownTextParserComponent {
    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(Constants.ZERO_LONG);

    /**
     * see description in {@link MarkdownTextParserComponent}.
     * @param source - source markdowned string.
     * @param substitutions - substitutions to be applied to source string.
     * @return processed string.
     */
    @Override
    public @NotNull String process(@NotNull MarkdownStringComponent source,
                                   @NotNull SubstitutionsComponent substitutions) {
        final StringBuilder processed = new StringBuilder();
        final String initial = source.getText();
        Integer currentPos = Constants.ZERO_INT;
        while (currentPos > IdsConstants.WRONG_INDEX) {
            final Integer tagPos = initial.indexOf(MarkdownConfig.TAG_CHARACTER, currentPos);
            if (tagPos > IdsConstants.WRONG_INDEX) {
                processed.append(initial, currentPos, tagPos);
                final String tag = getTag(initial, tagPos);
                if (substitutions.getTags().contains(tag)) {
                    processed.append(substitutions.getSubstitutionsMap().get(tag));
                } else {
                    processed.append(tag);
                }
            } else {
                processed.append(initial.substring(currentPos));
            }
            currentPos = tagPos;
        }
        return processed.toString();
    }

    /**
     * instance ids generator to be used in {@link AbstractComponent} constructor.
     * @return generated instance id.
     */
    @Override
    protected @NotNull Long initId() {
        return INSTANCE_COUNTER.getAndIncrement();
    }

    private @NotNull String getTag(@NotNull String source,
                                   @NotNull Integer fromPos) {
        int endTag = fromPos;
        final StringBuilder tagBuilder = new StringBuilder();
        while (!MarkdownConfig.SEPARATORS.contains(source.substring(endTag, endTag + 1))
                && endTag < source.length()) {
            tagBuilder.append(source.charAt(endTag++));
        }
        return tagBuilder.toString();
    }
}
