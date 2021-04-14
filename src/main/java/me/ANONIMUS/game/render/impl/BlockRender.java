package me.ANONIMUS.game.render.impl;

import lombok.Data;
import lombok.SneakyThrows;
import me.ANONIMUS.game.BabaIsYou;
import me.ANONIMUS.game.enums.BlockType;
import me.ANONIMUS.game.render.Render;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

@Data
public class BlockRender implements Render {
    private final Vector2f position;

    private BlockType type;
    private boolean active;

    public BlockRender(Vector2f position, BlockType blockType) {
        this.position = position;
        this.type = blockType;
    }

    @SneakyThrows
    @Override
    public void render(Graphics graphics, GameContainer container, BabaIsYou app) {
        if(type.isMaterial() || active) {
            new Image("BabaIsYou/assets/" + type.getTexture() + ".png").draw(640 + position.getX() * 40 - 20, 360 + (position.getY() * 40) - 20, 40, 40);
        } else {
            new Image("BabaIsYou/assets/" + type.getTexture() + ".png").draw(640 + position.getX() * 40 - 20, 360 + (position.getY() * 40) - 20, 40, 40, Color.gray);
        }
    }
}