package com.itayandtamir.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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

        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.center);
        table.setDebug(true);

        final Label title = new Label("Boat", Assets.skin, "default");
        title.setFontScale(3);

        final TextButton playButton = new TextButton("Start Game", Assets.skin, "default");
        final TextButton exitButton = new TextButton("Exit Game", Assets.skin, "default");
        //Buttons
        playButton.setWidth(200);
        playButton.setHeight(50);
//        playButton.setPosition(FirstGame.WORLD_WIDTH / 2, FirstGame.WORLD_HEIGHT / 2, Align.center);
        exitButton.setWidth(200);
        exitButton.setHeight(50);
//        exitButton.setPosition(FirstGame.WORLD_WIDTH / 2, FirstGame.WORLD_HEIGHT / 2 - 80, Align.center);

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


        stage.addActor(background);
        stage.addActor(table);

        table.add(title).size(100, 100).pad(75).row();
        table.add(playButton).size(400, 100).pad(20).row();
        table.add(exitButton).size(400, 100).pad(20);

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
