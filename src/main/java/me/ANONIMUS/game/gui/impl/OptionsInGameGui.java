package me.ANONIMUS.game.gui.impl;

import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.gui.Gui;
import me.ANONIMUS.game.render.impl.ButtonRender;
import me.ANONIMUS.game.scene.impl.MainScene;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class OptionsInGameGui implements Gui {
    private final ButtonRender QUIT;
    private final ButtonRender BACK;

    public OptionsInGameGui() {
        this.BACK = new ButtonRender(new Vector2f(640, 330), "BACK", 180, 60);
        this.QUIT = new ButtonRender(new Vector2f(640, 410), "QUIT", 180, 60);
    }

    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        graphics.drawRect(340, 235, 600, 250);
        graphics.setColor(new Color(192, 196, 194, 50));
        graphics.fillRect(340, 235, 600, 250);

        BACK.render(graphics, container, app);
        QUIT.render(graphics, container, app);
    }

    @Override
    public void tick(GameContainer container, int delta, BabaIsYou app) {
        Input input = container.getInput();

        if(input.isMousePressed(0)) {
            if (BACK.isTargeted(input.getMouseX(), input.getMouseY())) {
                app.getGuiManager().setCurrentGui(null);
            }
            if (QUIT.isTargeted(input.getMouseX(), input.getMouseY())) {
                app.getGuiManager().setCurrentGui(null);
                app.getSceneManager().setCurrentScene(new MainScene());
            }
        }
    }
}