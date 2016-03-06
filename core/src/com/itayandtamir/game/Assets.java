package com.itayandtamir.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    //Disposeables
    public static TextureAtlas atlas;
    public static SpriteBatch batch;

    //Non-Disposeables
    public static TextureRegion backgroundMenu;
    public static TextureRegion backgroundPlay;
    public static TextureRegion boat;
    public static TextureRegion obstacleStone;
    public static TextureRegion playButton;
    public static TextureRegion splash;


    public static void load() {
        atlas = new TextureAtlas("pack.atlas");
        batch = new SpriteBatch();

        backgroundMenu = atlas.findRegion("BackgroundMenu");
        backgroundPlay = atlas.findRegion("BackgroundPlay");
        boat = atlas.findRegion("Boat");
        obstacleStone = atlas.findRegion("ObstacleStone");
        playButton = atlas.findRegion("PlayButton");
        splash = atlas.findRegion("SplashImg");
    }

    public static void dispose() {
        if (atlas != null) atlas.dispose();
        if (batch != null) batch.dispose();
    }

}
