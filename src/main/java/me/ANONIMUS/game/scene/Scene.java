package me.ANONIMUS.game.scene;

import me.ANONIMUS.game.BabaIsYou;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Scene {
    void render(Graphics graphics, GameContainer container, BabaIsYou app);

    void tick(GameContainer container, int delta, BabaIsYou app);
}