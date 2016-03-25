package com.itayandtamir.game.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;

/**
 * Created by Tamir on 25/03/2016.
 */
public class PauseScreen extends ScreenAdapter {
    private FirstGame game;
    private Stage stage;

    public PauseScreen(FirstGame firstGame){
        this.game = firstGame;
        stage = new Stage(new StretchViewport(FirstGame.WORLD_WIDTH/2, FirstGame.WORLD_HEIGHT/2), Assets.batch);
        final Image pauseBackground = new Image(Assets.backgroundPause);


        stage.addActor(pauseBackground);
    }
    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

