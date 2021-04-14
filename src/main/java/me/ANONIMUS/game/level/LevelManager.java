package me.ANONIMUS.game.level;

import me.ANONIMUS.game.enums.BlockType;
import me.ANONIMUS.game.render.impl.BlockRender;
import org.newdawn.slick.geom.Vector2f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LevelManager {
    private List<String> levels;

    public void init() {
        levels = new ArrayList<>();

        for (File file : Objects.requireNonNull(new File("BabaIsYou/levels").listFiles())) {
            levels.add(file.getName());
        }
    }

    public String getNext(String level) {
        if(levels.indexOf(level) == levels.size() - 1) {
            return null;
        }
        return levels.get(levels.indexOf(level) + 1);
    }

    public static Level loadLevel(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("BabaIsYou/levels/" + name));
        Level level = new Level(name);
        String st;
        while((st = br.readLine()) != null) {
            String[] s = st.split(" ");
            level.getBlocks().add(new BlockRender(new Vector2f(Integer.parseInt(s[1]), Integer.parseInt(s[2])), BlockType.valueOf(s[0])));
        }
        level.readRules();
        return level;
    }

    public List<String> getLevels() { return levels; }
}