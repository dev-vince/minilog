package dev.vince.log.text;

public abstract class AbstractTextFormat {
    private final String name;
    private final String description;
    private final String key;

    public AbstractTextFormat(final String name, final String description, final String key) {
        this.name = name;
        this.description = description;
        this.key = key;
    }

    public abstract String getText(final ParsingBean data);

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final String getKey() {
        return key;
    }
}
