package com.itayandtamir.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    //Disposeables
    public static TextureAtlas atlas;
    public static SpriteBatch batch;
    public static Skin skin;

    //Non-Disposeables
    public static TextureRegion backgroundMenu;
    public static TextureRegion logo;
    public static TextureRegion backgroundMenu2;
    public static TextureRegion backgroundPlay;
    public static TextureRegion boat;
    public static TextureRegion obstacleStone;
    public static TextureRegion playButton;
    public static TextureRegion splash;

    public static void load() {
        atlas = new TextureAtlas("pack.atlas");
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("skins/skin.json"));

        backgroundMenu = atlas.findRegion("BackgroundMenu");
        backgroundMenu2 = atlas.findRegion("Background-Boat");
        logo = atlas.findRegion("Boat-Logo");
        backgroundPlay = atlas.findRegion("BackgroundPlay");
        boat = atlas.findRegion("Boat");
        obstacleStone = atlas.findRegion("ObstacleStone");
        playButton = atlas.findRegion("PlayButton");
        splash = atlas.findRegion("SplashImg");

    }

    public static void dispose() {
        if (atlas != null) atlas.dispose();
        if (batch != null) batch.dispose();
        if (skin != null) skin.dispose();
    }

}
