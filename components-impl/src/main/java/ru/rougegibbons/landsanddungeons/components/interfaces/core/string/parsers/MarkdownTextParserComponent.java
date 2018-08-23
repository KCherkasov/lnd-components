package ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.Component;

/**
 * interface for all classes containing text parsing logic.
 */
public interface MarkdownTextParserComponent extends Component {
    /**
     * processes source string from {@link MarkdownStringComponent} instance using data from {@link SubstitutionsComponent} instance.
     * @param source - source markdowned string.
     * @param substitutions - substitutions to be applied to source string.
     * @return processed strings with completely or partially substituted markdown tags.
     */
    @NotNull String process(@NotNull MarkdownStringComponent source,
                            @NotNull SubstitutionsComponent substitutions);
}
