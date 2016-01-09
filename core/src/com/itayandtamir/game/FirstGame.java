package com.itayandtamir.game;

import com.badlogic.gdx.Game;
import com.itayandtamir.game.Screens.MenuScreen;

public class FirstGame extends Game {

    public static final int WORLD_WIDTH = 480;
    public static final int WORLD_HEIGHT = 800;

    @Override
    public void create() {
        Assets.load();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }
}
