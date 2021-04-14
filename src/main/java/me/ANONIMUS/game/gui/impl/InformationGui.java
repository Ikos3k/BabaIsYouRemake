package me.ANONIMUS.game.gui.impl;

import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.gui.Gui;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class InformationGui implements Gui {
    private final String information;

    public InformationGui(String information) {
        this.information = information;
    }

    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        graphics.setColor(Color.black);
        graphics.fillRect(640 - 250, 360 - 62.5F, 500, 125);

        graphics.setColor(Color.red);
        graphics.drawRect(640 - 250, 360 - 62.5F, 500, 125);
        graphics.drawString(information, 640 - graphics.getFont().getWidth(information) / 2,360);
    }

    @Override
    public void tick(GameContainer container, int delta, BabaIsYou app) throws SlickException {}
}