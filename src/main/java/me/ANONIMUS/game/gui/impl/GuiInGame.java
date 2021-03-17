package me.ANONIMUS.game.gui.impl;

import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.gui.Gui;
import me.ANONIMUS.game.render.impl.ButtonRender;
import me.ANONIMUS.game.scene.impl.MainScene;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class GuiInGame implements Gui {
    private ButtonRender QUIT;
    private ButtonRender BACK;

    @Override
    public void init() {
        this.BACK = new ButtonRender(new Vector2f(Display.getWidth() / 2, Display.getHeight() / 2 - 30), "BACK", 180, 60);
        this.QUIT = new ButtonRender(new Vector2f(Display.getWidth() / 2, Display.getHeight() / 2 + 50), "QUIT", 180, 60);
    }

    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        graphics.drawRect(container.getWidth() / 2 - 300, container.getHeight() / 2 - 125, 600, 250);
        graphics.setColor(new Color(192, 196, 194, 50));
        graphics.fillRect(container.getWidth() / 2 - 300, container.getHeight() / 2 - 125, 600, 250);
        graphics.setColor(new Color(255, 255, 255, 1.0F));

        BACK.render(graphics, container, app);
        QUIT.render(graphics, container, app);
    }

    @Override
    public void tick(GameContainer container, int delta, BabaIsYou app) throws SlickException {
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