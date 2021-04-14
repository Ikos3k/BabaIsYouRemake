package me.ANONIMUS.game.gui;

import me.ANONIMUS.game.BabaIsYou;
import org.newdawn.slick.Color;
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

    public void render(Graphics graphics) {
        gui.render(graphics, container, app);

        graphics.setColor(Color.white);
        graphics.drawString("Press the ESCAPE to close gui", 640 - graphics.getFont().getWidth("Press the ESCAPE to close gui") / 2, graphics.getFont().getHeight("Press the ESCAPE to close gui"));
    }

    public void setCurrentGui(Gui gui) { this.gui = gui; }

    public Gui getCurrentGui() { return gui; }
}
