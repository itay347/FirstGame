package com.itayandtamir.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;

public class MenuScreen extends ScreenAdapter {

    private FirstGame game;
    private Stage stage;

    public MenuScreen(FirstGame firstGame) {
        this.game = firstGame;
        stage = new Stage(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch);
        final Image background = new Image(Assets.backgroundMenu2);
        final Image logo = new Image(Assets.logo);
        logo.setPosition(FirstGame.WORLD_WIDTH / 2, FirstGame.WORLD_HEIGHT * 0.8f, Align.center);

        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.center);


        //region Buttons

        final TextButton playButton = new TextButton("Start Game", Assets.skin, "menu");
        final TextButton exitButton = new TextButton("Exit Game", Assets.skin, "menu");

        playButton.addListener(new ClickListener() {
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
        //endregion

        //region Adding Actors
        stage.addActor(background);
        stage.addActor(logo);
        stage.addActor(table);

        table.add(playButton).size(200, 50).pad(20).row();
        table.add(exitButton).size(200, 50).pad(20);
        //endregion

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
