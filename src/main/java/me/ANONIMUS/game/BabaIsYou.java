package me.ANONIMUS.game;

import lombok.Getter;
import me.ANONIMUS.game.gui.GuiManager;
import me.ANONIMUS.game.gui.impl.OptionsInGameGui;
import me.ANONIMUS.game.level.LevelManager;
import me.ANONIMUS.game.scene.SceneManager;
import me.ANONIMUS.game.scene.impl.MainScene;
import org.newdawn.slick.*;

@Getter
public class BabaIsYou extends BasicGame {
    public static void main(String[] args) throws SlickException {
        final AppGameContainer app = new AppGameContainer(new BabaIsYou(), 1280, 720, false);
        app.setVSync(true);
        app.setTargetFrameRate(60);
        app.setShowFPS(false);
        app.setAlwaysRender(true);
        app.start();
    }

    public BabaIsYou() { super("BabaIsYou"); }

    private LevelManager levelManager;
    private SceneManager sceneManager;
    private GuiManager guiManager;

    @Override
    public void init(GameContainer gameContainer) {
        levelManager = new LevelManager();
        levelManager.init();

        guiManager = new GuiManager(gameContainer, this);

        sceneManager = new SceneManager(gameContainer, this);
        sceneManager.setCurrentScene(new MainScene());
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        sceneManager.tick(i);

        if(guiManager.getCurrentGui() != null) {
            guiManager.tick(i);
        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE) && !(sceneManager.getCurrentScene() instanceof MainScene)) {
            if(guiManager.getCurrentGui() == null) {
                guiManager.setCurrentGui(new OptionsInGameGui());
            } else {
                guiManager.setCurrentGui(null);
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        sceneManager.render(graphics);
        graphics.setColor(Color.white);

        if(guiManager.getCurrentGui() != null) {
            guiManager.render(graphics);
            graphics.setColor(Color.white);
        }
    }
}