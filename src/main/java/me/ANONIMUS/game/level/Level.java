package me.ANONIMUS.game.level;

import me.ANONIMUS.game.enums.BlockType;
import me.ANONIMUS.game.render.impl.BlockRender;
import org.newdawn.slick.geom.Vector2f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Level {
    private final HashMap<BlockType, List<BlockType>> rules;
    private final String name;
    private List<BlockRender> blocks;

    public Level(String name) {
        this.blocks = new ArrayList<>();
        this.rules = new HashMap<>();
        this.name = name;
    }

    public void readRules() {
        blocks.forEach(blockRender -> blockRender.setActive(false));
        rules.clear();

        blocks.stream().filter(block -> block.getType() == BlockType.IS_TEXT).forEach(block -> {
            getBlocksByPosition(block.getPosition().getX() - 1, block.getPosition().getY()).forEach(left -> getBlocksByPosition(block.getPosition().getX() + 1, block.getPosition().getY()).forEach(right -> {
                if(left.getType().isSelector() && right.getType().isSelector() || left.getType().isSelector() && right.getType().isAction()) {
                    addRule(left.getType(), right.getType());
                    left.setActive(true); block.setActive(true); right.setActive(true);
                }
            }));

            getBlocksByPosition(block.getPosition().getX(), block.getPosition().getY() - 1).forEach(up -> getBlocksByPosition(block.getPosition().getX(), block.getPosition().getY() + 1).forEach(down -> {
                if(up.getType().isSelector() && down.getType().isSelector() || up.getType().isSelector() && down.getType().isAction()) {
                    addRule(up.getType(), down.getType());
                    up.setActive(true); block.setActive(true); down.setActive(true);
                }
            }));
        });
    }

    public void addRule(BlockType block, BlockType rule) {
        if(block.isSelector() && rule.isSelector()) {
            blocks.stream().filter(blockRender -> blockRender.getType() == block.getBySelector()).forEach(blockRender -> blockRender.setType(rule.getBySelector()));
        } else if (block.isSelector() && rule.isAction()) {
            if(rules.get(block.getBySelector()) == null) {
                List<BlockType> blocks = new ArrayList<>() ;
                blocks.add(rule);
                rules.put(block.getBySelector(), blocks);
            } else {
                rules.get(block.getBySelector()).add(rule);
            }
        }
    }

    public List<BlockRender> getBlocksByPosition(Vector2f position) {
        List<BlockRender> tempList = new ArrayList<>();

        blocks.stream().filter(blockRender -> blockRender.getPosition().getY() == position.getY() && blockRender.getPosition().getX() == position.getX()).forEach(tempList::add);

        return tempList;
    }

    public BlockRender getBlockByPosition(Vector2f position) {
        for (BlockRender blockRender : blocks) {
            if (blockRender.getPosition().getY() == position.getY()) {
                if (blockRender.getPosition().getX() == position.getX()) {
                    return blockRender;
                }
            }
        }
        return null;
    }

    public void restartLevel() {
        try {
            rules.clear();

            blocks = LevelManager.loadLevel(name).blocks;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isGameOver() {
        for(BlockRender block : blocks) {
            if (hasRule(block.getType(), BlockType.YOU)) {
                return false;
            }
        }
        return true;
    }

    public String getName() { return name; }

    public List<BlockRender> getBlocks() { return blocks; }

    public BlockRender getBlockByPosition(float x, float y) { return getBlockByPosition(new Vector2f(x, y)); }

    public List<BlockRender> getBlocksByPosition(float x, float y) { return getBlocksByPosition(new Vector2f(x, y)); }

    public boolean hasRule(BlockType block, BlockType rule) { return rules.containsKey(block) && rules.get(block).contains(rule); }
}