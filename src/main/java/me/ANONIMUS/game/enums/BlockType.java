package me.ANONIMUS.game.enums;

public enum BlockType {
    BABA_MATERIAL("baba"),
    WALL_MATERIAL("wall"),
    ROCK_MATERIAL("rock"),
    FLAG_MATERIAL("flag"),
    LAVA_MATERIAL("lava"),
    WATER_MATERIAL("water"),
    SKULL_MATERIAL("skull"),

    BABA_TEXT("text/baba"),
    ROCK_TEXT("text/rock"),
    FLAG_TEXT("text/flag"),
    WALL_TEXT("text/wall"),
    MELT_TEXT("text/melt"),
    LAVA_TEXT("text/lava"),
    SKULL_TEXT("text/skull"),
    IS_TEXT("text/is"),

    DEFEAT("text/defeat"),
    WIN("text/win"),
    STOP("text/stop"),
    PUSH("text/push"),
    YOU("text/you"),
    HOT("text/hot"),
    MELT("text/melt");

    public boolean isMaterial() {
        return this == BABA_MATERIAL
            || this == FLAG_MATERIAL
            || this == WALL_MATERIAL
            || this == ROCK_MATERIAL
            || this == WATER_MATERIAL
            || this == SKULL_MATERIAL
            || this == LAVA_MATERIAL;
    }

    public boolean isSelector() {
        return this == BABA_TEXT
            || this == FLAG_TEXT
            || this == WALL_TEXT
            || this == ROCK_TEXT
            || this == MELT_TEXT
            || this == SKULL_TEXT
            || this == LAVA_TEXT;
    }

    public boolean isAction() {
        return this == WIN
            || this == STOP
            || this == PUSH
            || this == YOU
            || this == HOT
            || this == MELT
            || this == DEFEAT;
    }

    public BlockType getBySelector() {
        switch (this) {
            case BABA_TEXT:
                return BABA_MATERIAL;
            case FLAG_TEXT:
                return FLAG_MATERIAL;
            case WALL_TEXT:
                return WALL_MATERIAL;
            case ROCK_TEXT:
                return ROCK_MATERIAL;
            case LAVA_TEXT:
                return LAVA_MATERIAL;
            case SKULL_TEXT:
                return SKULL_MATERIAL;
        }
        return null;
    }

    private final String texture;

    BlockType(String texture) {
        this.texture = texture;
    }

    public boolean isPush() { return isSelector() || isAction() || this == IS_TEXT; }

    public String getTexture() { return texture; }
}