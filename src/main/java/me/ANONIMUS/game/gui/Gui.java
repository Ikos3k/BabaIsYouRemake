package me.ANONIMUS.game.gui;

import me.ANONIMUS.game.BabaIsYou;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Gui {
    void render(Graphics graphics, GameContainer container, BabaIsYou app);

    void tick(GameContainer container, int delta, BabaIsYou app) throws SlickException;
}
