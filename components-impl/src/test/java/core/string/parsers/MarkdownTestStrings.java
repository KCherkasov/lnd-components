package core.string.parsers;

public class MarkdownTestStrings {
    public static final String NO_TAGS_STRING = "string with no tags. I mean, with no tags at all. Entirely.";
    public static final String ONLY_TAG_STRING = "$ONLYTAGSTRING";
    public static final String ONE_TAG_STRING = "string with $ONE tag.";
    public static final String MULTIPLE_TAGS_STRING = "$STRING with multiple $TAGS in it.";

    // test tags to be found in test components' tags keysets.
    public static final String STRING_TAG = "$STRING";
    public static final String ONE_TAG = "$ONE";
    public static final String TAGS_TAG = "$TAGS";

    // test tags substitutions
    public static final String STRING_TAG_SUB = "string_sub";
    public static final String ONE_TAG_SUB = "one_sub";
    public static final String TAGS_TAG_SUB = "tags_sub";
    public static final String ONLY_TAG_SUB = "only_tag_string_sub";

    // processed test strings
    public static final String PROCESSED_ONLY_TAG_STRING = ONLY_TAG_SUB;
    public static final String PROCESSED_ONE_TAG_STRING = "string with " + ONE_TAG_SUB + " tag.";
    public static final String PROCESSED_MULTI_TAGS_STRING = STRING_TAG_SUB
            + " with multiple " + TAGS_TAG_SUB + " in it.";
}
