package me.ANONIMUS.game.enums;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public enum Direction {
    UP(new Vector2f(0, -1), Input.KEY_UP, Input.KEY_W),
    DOWN(new Vector2f(0, 1), Input.KEY_DOWN, Input.KEY_S),
    LEFT(new Vector2f(-1, 0), Input.KEY_LEFT, Input.KEY_A),
    RIGHT(new Vector2f(1, 0), Input.KEY_RIGHT, Input.KEY_D);

    private final Vector2f pos;
    private final int[] key;

    Direction(Vector2f pos, int... key) {
        this.pos = pos;
        this.key = key;
    }

    public Vector2f getPos() { return pos; }

    public int[] getKey() { return key; }
}