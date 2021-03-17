package me.ANONIMUS.game.scene.impl;

import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.render.impl.ButtonRender;
import me.ANONIMUS.game.scene.Scene;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class MainScene implements Scene {
    private final ButtonRender playBTN;

    public MainScene() {
        this.playBTN = new ButtonRender(new Vector2f(Display.getWidth() / 2, Display.getHeight() / 2), "PLAY", 200, 70);
    }

    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        graphics.drawString("Remake Baba Is You by Ikos3k", (container.getWidth() / 2) - graphics.getFont().getWidth("Remake Baba Is You by Ikos3k") / 2, container.getHeight() - graphics.getFont().getHeight("Remake Baba Is You by Ikos3k"));
        playBTN.render(graphics, container, app);
    }

    @Override
    public void tick(GameContainer container, int delta, BabaIsYou app) {
        Input input = container.getInput();
        if(input.isMousePressed(0) && playBTN.isTargeted(input.getMouseX(), input.getMouseY())) {
            app.getSceneManager().setCurrentScene(new PlayScene(app.getLevelManager().getLevels().get(0)));
        }
    }
}