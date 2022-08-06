package net.danh.PClass;

public enum CName {
    MAGE("&bMage", "Mage"),
    KNIGHT("&aKnight", "Knight"),
    NONE("&cChưa Có Class", "None");

    private final String name;
    private final String format_name;

    CName(String name, String format_name) {
        this.name = name;
        this.format_name = format_name;
    }

    public String getName() {
        return name;
    }

    public String getFormatName() {
        return format_name;
    }
}