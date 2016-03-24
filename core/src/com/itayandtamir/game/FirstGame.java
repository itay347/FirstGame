package com.itayandtamir.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itayandtamir.game.Screens.MenuScreen;
import com.itayandtamir.game.Screens.SplashScreen;

public class FirstGame extends Game {

    public static final int WORLD_WIDTH = 480;
    public static final int WORLD_HEIGHT = 800;

    @Override
    public void create() {
        Assets.load();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }
}
