package me.ANONIMUS.game.scene.impl;

import lombok.SneakyThrows;
import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.enums.BlockType;
import me.ANONIMUS.game.enums.Direction;
import me.ANONIMUS.game.level.Level;
import me.ANONIMUS.game.level.LevelManager;
import me.ANONIMUS.game.render.impl.BlockRender;
import me.ANONIMUS.game.scene.Scene;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayScene implements Scene {
    private final Level level;

    @SneakyThrows
    public PlayScene(String levelName) {
        level = LevelManager.loadLevel(levelName);
    }

    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        level.getBlocks().forEach(block -> block.render(graphics, container, app));

        if(!level.isGameOver()) {
            graphics.drawString(level.getName(), (container.getWidth() / 2) - graphics.getFont().getWidth(level.getName()) / 2, container.getHeight() - graphics.getFont().getHeight(level.getName()));
        } else {
            graphics.drawString("Game Over!", (container.getWidth() / 2) - graphics.getFont().getWidth("Game Over!") / 2, container.getHeight() - graphics.getFont().getHeight("Game Over!"));
        }
    }

    @Override
    public void tick(GameContainer container, int delta, BabaIsYou app) {
        Input input = container.getInput();

        for (Direction direction : Direction.values()) {
            for (int key : direction.getKey()) {
                if (input.isKeyPressed(key) && app.getGuiManager().getCurrentGui() == null) {
                    move(app, direction);
                }
            }
        }
    }

    private void move(BabaIsYou app, Direction direction) {
        level.getBlocks().stream().filter(block -> level.hasRule(block.getType(), BlockType.YOU)).forEach(block -> {
            List<BlockRender> pushBlocks = new ArrayList<>();

            Vector2f pos = direction.getPos().copy();
            while (level.getBlockByPosition(block.getPosition().getX() + pos.getX(), block.getPosition().getY() + pos.getY()) != null) {
                AtomicBoolean i = new AtomicBoolean(false);

                level.getBlocksByPosition(block.getPosition().getX() + pos.getX(), block.getPosition().getY() + pos.getY()).stream().filter(blockRender -> canPush(blockRender, direction.getPos())).forEach(blockRender -> {
                    pushBlocks.add(blockRender);
                    i.set(true);
                });

                if (!i.get()) { break; }
                pos.add(direction.getPos());
            }

            pushBlocks.forEach(blockRender -> blockRender.getPosition().add(direction.getPos()));

            BlockRender blockRender = level.getBlockByPosition(block.getPosition().getX() + direction.getPos().getX(), block.getPosition().getY() + direction.getPos().getY());
            if(blockRender == null || (!blockRender.getType().isPush() && !level.hasRule(blockRender.getType(), BlockType.PUSH) && !level.hasRule(blockRender.getType(), BlockType.STOP))) {
                block.getPosition().add(direction.getPos());
                if(blockRender != null) {
                    if(level.hasRule(blockRender.getType(), BlockType.WIN)) {
                        if(app.getLevelManager().getNext(level.getName()) != null) {
                            app.getSceneManager().setCurrentScene(new PlayScene(app.getLevelManager().getNext(level.getName())));
                        } else {
                            app.getSceneManager().setCurrentScene(new MainScene());
                        }
                    } else if(level.hasRule(blockRender.getType(), BlockType.HOT) || level.hasRule(blockRender.getType(), BlockType.DEFEAT)) {
                        level.restartLevel();
                    }
                }
            }
        });

        level.readRules();
    }

    private boolean canPush(BlockRender block, Vector2f pos) {
        if (level.hasRule(block.getType(), BlockType.STOP)) {
            return false;
        }

        Vector2f pos2 = pos.copy();
        while (level.getBlockByPosition(block.getPosition().getX() + pos2.getX(), block.getPosition().getY() + pos2.getY()) != null) {
            for(BlockRender blockRender : level.getBlocksByPosition(block.getPosition().getX() + pos2.getX(), block.getPosition().getY() + pos2.getY())) {
                if(level.hasRule(blockRender.getType(), BlockType.STOP)) {
                    return false;
                }
            }
            pos2.add(pos);
        }

        return level.hasRule(block.getType(), BlockType.PUSH) || block.getType().isPush();
    }
}