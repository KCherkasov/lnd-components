package ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * default interface for substitution strings used for processing {@link MarkdownedString} implementations.
 */
public interface Substitutions {
    /**
     * get substitutions mapped to markdown tags.
     * @return tags to substitutions map.
     */
    @NotNull Map<String, String> getSubstitutionsMap();

    /**
     * get markdrown tags keyset.
     * @return tags keyset.
     */
    @NotNull Set<String> getTags();

    /**
     * get substitutions as list.
     * @return substitutions list.
     */
    @NotNull List<String> getSubstitutions();
}
