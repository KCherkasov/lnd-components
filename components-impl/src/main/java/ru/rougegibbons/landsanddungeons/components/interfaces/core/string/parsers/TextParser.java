package ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers;

import org.jetbrains.annotations.NotNull;

/**
 * interface for all classes containing text parsing logic.
 */
public interface TextParser {
    /**
     * processes source string from {@link MarkdownedString} instance using data from {@link Substitutions} instance.
     * @param source - source markdowned string.
     * @param substitutions - substitutions to be applied to source string.
     * @return processed strings with completely or partially substituted markdown tags.
     */
    @NotNull String process(@NotNull MarkdownedString source,
                            @NotNull Substitutions substitutions);
}
