package me.ANONIMUS.game.render;

import me.ANONIMUS.game.BabaIsYou;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Render {
    void render(Graphics graphics, GameContainer container, BabaIsYou app) throws SlickException;
}
