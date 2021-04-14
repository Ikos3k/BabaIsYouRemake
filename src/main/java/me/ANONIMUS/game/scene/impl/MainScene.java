package me.ANONIMUS.game.scene.impl;

import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.gui.impl.BlocksGui;
import me.ANONIMUS.game.render.impl.ButtonRender;
import me.ANONIMUS.game.scene.Scene;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class MainScene implements Scene {
    private final ButtonRender playBTN;
    private final ButtonRender lvlCreatorBTN;

    public MainScene() {
        this.playBTN = new ButtonRender(new Vector2f(640, 360), "PLAY", 200, 70);
        this.lvlCreatorBTN = new ButtonRender(new Vector2f(640, 430), "LEVEL CREATOR", 200, 50);
    }

    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        graphics.drawString("Remake Baba Is You by Ikos3k", 640 - graphics.getFont().getWidth("Remake Baba Is You by Ikos3k") / 2, 720 - graphics.getFont().getHeight("Remake Baba Is You by Ikos3k"));

        playBTN.render(graphics, container, app);
        lvlCreatorBTN.render(graphics, container, app);
    }

    @Override
    public void tick(GameContainer container, int delta, BabaIsYou app) {
        Input input = container.getInput();

        if(input.isMousePressed(0)) {
            if(playBTN.isTargeted(input.getMouseX(), input.getMouseY())) {
                app.getSceneManager().setCurrentScene(new PlayScene(app.getLevelManager().getLevels().get(0)));
            }
            if(lvlCreatorBTN.isTargeted(input.getMouseX(), input.getMouseY())) {
                BlocksGui blocksGui = new BlocksGui();

                app.getSceneManager().setCurrentScene(new LevelCreatorScene(blocksGui));
                app.getGuiManager().setCurrentGui(blocksGui);
            }
        }
    }
}