package me.ANONIMUS.game.scene.impl;

import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.gui.impl.BlocksGui;
import me.ANONIMUS.game.gui.impl.InformationGui;
import me.ANONIMUS.game.gui.impl.OptionsInGameGui;
import me.ANONIMUS.game.render.impl.BlockRender;
import me.ANONIMUS.game.scene.Scene;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class LevelCreatorScene implements Scene {
    private final BlocksGui blocksGui;
    private List<BlockRender> blocks;
    private boolean showGrid;

    public LevelCreatorScene(BlocksGui blocksGui) {
        this.showGrid = true;

        this.blocks = new ArrayList<>();
        this.blocksGui = blocksGui;
    }

    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        if(app.getGuiManager().getCurrentGui() == null) {
            if(showGrid) {
                for (int y = 0; y < 17; y++) {
                    for (int x = 0; x < 31; x++) {
                        graphics.drawRect(640 + (x - 15) * 40 - 20, 360 + (y - 8) * 40 - 20, 40, 40);
                    }
                }
            }
            blocks.forEach(blockRender -> blockRender.render(graphics, container, app));
        } else if(app.getGuiManager().getCurrentGui() instanceof OptionsInGameGui) {
            graphics.drawString("[A] open blocks gui", 1, 1);
            graphics.drawString("[F] show grid: " + showGrid, 1, 16);
            graphics.drawString("[S] save map [" + (app.getLevelManager().getLevels().size() + 1) + ".map]", 1, 31);
            graphics.drawString("[C] clear map", 1, 46);
        }
    }

    @Override
    public void tick(GameContainer container, int delta, BabaIsYou app) {
        Input input = container.getInput();

        if(input.isKeyPressed(Input.KEY_A)) {
            if(app.getGuiManager().getCurrentGui() == null) {
                app.getGuiManager().setCurrentGui(blocksGui);
            } else {
                app.getGuiManager().setCurrentGui(null);
            }
        }

        if(app.getGuiManager().getCurrentGui() == null) {
            if(input.isKeyPressed(Input.KEY_F)) {
                showGrid = !showGrid;
            }
            if(input.isKeyPressed(Input.KEY_S)) {
                save(app);
            }
            if(input.isKeyPressed(Input.KEY_C)) {
                this.blocks = new ArrayList<>();
            }

            final int mouseX = input.getMouseX();
            final int mouseY = input.getMouseY();

            for (int y = 0; y < 17; y++) {
                for (int x = 0; x < 31; x++) {
                    if (mouseX >= 640 + (x - 15) * 40 - 20 && mouseX <= 640 + (x - 15) * 40 + 20) {
                        if (mouseY >= 360 + (y - 8) * 40 - 20 && mouseY <= 360 + (y - 8) * 40 + 20) {
                            if (input.isMousePressed(0)) {
                                for (BlockRender blockRender : blocks) {
                                    if (blockRender.getPosition().equals(new Vector2f((x - 15), (y - 8)))) {
                                        return;
                                    }
                                }
                                blocks.add(new BlockRender(new Vector2f((x - 15), (y - 8)), blocksGui.getSelected()));
                            }
                            if (input.isMousePressed(1)) {
                                for (BlockRender blockRender :  blocks.toArray(new BlockRender[0])) {
                                    if (blockRender.getPosition().equals(new Vector2f((x - 15), (y - 8)))) {
                                        blocks.remove(blockRender);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void save(BabaIsYou app) {
        if(blocks.isEmpty()) {
            app.getGuiManager().setCurrentGui(new InformationGui("You cannot save the empty list!"));
            return;
        }
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("BabaIsYou/levels/" + (app.getLevelManager().getLevels().size() + 1) + ".map")));
            for(BlockRender blockRender : blocks) {
                bw.write(blockRender.getType().name() + " " + (int)blockRender.getPosition().getX() + " " + (int)blockRender.getPosition().getY());
                bw.newLine();
            }
            bw.close();
            app.getGuiManager().setCurrentGui(new InformationGui("Successfully saved your level to BabaIsYou/levels/" + (app.getLevelManager().getLevels().size() + 1) + ".map"));
            app.getLevelManager().init();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}