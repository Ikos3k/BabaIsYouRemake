package me.ANONIMUS.game.gui;

import me.ANONIMUS.game.BabaIsYou;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GuiManager {
    private final GameContainer container;
    private final BabaIsYou app;
    private Gui gui;

    public GuiManager(GameContainer container, BabaIsYou app) {
        this.container = container;
        this.app = app;
    }

    public void tick(int delta) throws SlickException { gui.tick(container, delta, app); }

    public void render(Graphics graphics) { gui.render(graphics, container, app); }

    public void setCurrentGui(Gui gui) {
        this.gui = gui;
        if(gui != null) {
            this.gui.init();
        }
    }

    public Gui getCurrentGui() {
        return gui;
    }
}
