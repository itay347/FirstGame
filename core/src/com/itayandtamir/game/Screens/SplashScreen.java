package com.itayandtamir.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen extends ScreenAdapter {

    private FirstGame game;
    private Stage stage;

    private Image splashImg;
    private boolean skipped;

    public SplashScreen(FirstGame firstGame) {
        this.game = firstGame;
        stage = new Stage(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch);

        splashImg = new Image(Assets.splash);
        splashImg.setSize(stage.getWidth(), stage.getHeight());
        splashImg.setColor(splashImg.getColor().r, splashImg.getColor().g, splashImg.getColor().b, 0);
        splashImg.addAction(sequence(fadeIn(1f), delay(2), fadeOut(1), run(new Runnable() {
            @Override
            public void run() {
                SetToMenu();
            }
        })));

        skipped = false;

        stage.addActor(splashImg);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!skipped) {
                    skipped = true;
                    splashImg.getActions().clear();
                    splashImg.addAction(sequence(fadeOut(0.5f), run(new Runnable() {
                        @Override
                        public void run() {
                            SetToMenu();
                        }
                    })));
                }
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    public void SetToMenu() {
        game.setScreen(new MenuScreen(game));
    }

}
