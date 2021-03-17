package me.ANONIMUS.game.render.impl;

import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.render.Render;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class ButtonRender implements Render {
    private final Vector2f position;
    private final String name;
    private final int width, height;

    public ButtonRender(Vector2f position, String name, int width, int height) {
        this.position = position;
        this.height = height;
        this.width = width;
        this.name = name;
    }

    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        Input input = container.getInput();
        graphics.setColor(Color.lightGray);
        if (isTargeted(input.getMouseX(), input.getMouseY())) {
            graphics.setColor(Color.magenta);
        }
        graphics.drawRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
        graphics.drawString(name, position.getX() - graphics.getFont().getWidth(name) / 2, position.getY() - graphics.getFont().getHeight(name) / 2);
    }

    @Override
    public void tick(GameContainer container, BabaIsYou app) { }

    public boolean isTargeted(int x, int y) {
        if (x >= position.getX() - width / 2 && x <= position.getX() + width / 2) {
            return y >= position.getY() - height / 2 && y <= position.getY() + height / 2;
        }
        return false;
    }
}