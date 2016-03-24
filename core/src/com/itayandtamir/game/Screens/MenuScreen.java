package com.itayandtamir.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;

public class MenuScreen extends ScreenAdapter {

    private FirstGame game;
    private Stage stage;

    private Image background;
    private ImageButton playButton;

    Skin skin;

    public MenuScreen(FirstGame firstGame) {
        this.game = firstGame;
        stage = new Stage(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        final TextButton button = new TextButton("Start Game", skin, "default");
        final TextButton exitButton = new TextButton("Exit Game", skin, "default");
        //Buttons
        button.setWidth(200);
        button.setHeight(50);
        button.setPosition(FirstGame.WORLD_WIDTH / 2, FirstGame.WORLD_HEIGHT / 2, Align.center);
        exitButton.setWidth(200);
        exitButton.setHeight(50);
        exitButton.setPosition(FirstGame.WORLD_WIDTH / 2, FirstGame.WORLD_HEIGHT / 2 - 80, Align.center);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        background = new Image(Assets.backgroundMenu);
        stage.addActor(background);
        stage.addActor(button);
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);
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
