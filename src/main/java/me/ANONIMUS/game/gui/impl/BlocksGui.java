package me.ANONIMUS.game.gui.impl;

import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.enums.BlockType;
import me.ANONIMUS.game.gui.Gui;
import me.ANONIMUS.game.render.impl.BlockRender;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class BlocksGui implements Gui {
    private final List<BlockRender> blocks;
    private BlockType selected;

    public BlocksGui() {
        this.selected = BlockType.BABA_MATERIAL;

        this.blocks = new ArrayList<>();

        int x = 0, y = 0;
        for(BlockType blockType : BlockType.values()) {
            blocks.add(new BlockRender(new Vector2f(x - 15, y - 8), blockType));
            y++;
            if(y % 17 == 0) {
                y = 0;
                x++;
            }
        }
    }

    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        blocks.forEach(blockRender -> {
            blockRender.render(graphics, container, app);
            if (blockRender.getType() == selected) {
                graphics.setColor(Color.green);
                graphics.drawRect(640 + blockRender.getPosition().x * 40 - 20, 360 + blockRender.getPosition().y * 40 - 20, 40, 40);
            }
        });
    }

    @Override
    public void tick(GameContainer container, int delta, BabaIsYou app) throws SlickException {
        Input input = container.getInput();

        if (input.isMousePressed(0)) {
            final int mouseX = input.getMouseX();
            final int mouseY = input.getMouseY();

            blocks.forEach(blockRender -> {
                if (mouseX >= 640 + blockRender.getPosition().getX() * 40 - 20 && mouseX <= 640 + blockRender.getPosition().getX() * 40 + 20) {
                    if (mouseY >= 360 + blockRender.getPosition().getY() * 40 - 20 && mouseY <= 360 + blockRender.getPosition().getY() * 40 + 20) {
                        selected = blockRender.getType();
                    }
                }
            });
        }
    }

    public BlockType getSelected() {
        return selected;
    }
}
