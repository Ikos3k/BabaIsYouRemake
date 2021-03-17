package me.ANONIMUS.game.scene;

import me.ANONIMUS.game.BabaIsYou;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class SceneManager {
    private final GameContainer container;
    private final BabaIsYou app;
    private Scene scene;

    public SceneManager(GameContainer container, BabaIsYou app) {
        this.container = container;
        this.app = app;
    }

    public void setCurrentScene(Scene scene) { this.scene = scene; }

    public Scene getCurrentScene() { return scene; }

    public void render(Graphics graphics) { scene.render(graphics, container, app); }

    public void tick(int delta) { scene.tick(container, delta, app); }
}