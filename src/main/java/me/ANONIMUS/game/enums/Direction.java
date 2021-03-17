package me.ANONIMUS.game.enums;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.geom.Vector2f;

public enum Direction {
    UP(new Vector2f(0, -1), Keyboard.KEY_UP, Keyboard.KEY_W),
    DOWN(new Vector2f(0, 1), Keyboard.KEY_DOWN, Keyboard.KEY_S),
    LEFT(new Vector2f(-1, 0), Keyboard.KEY_LEFT, Keyboard.KEY_A),
    RIGHT(new Vector2f(1, 0), Keyboard.KEY_RIGHT, Keyboard.KEY_D);

    private final Vector2f pos;
    private final int[] key;

    Direction(Vector2f pos, int... key) {
        this.pos = pos;
        this.key = key;
    }

    public Vector2f getPos() { return pos; }

    public int[] getKey() { return key; }
}