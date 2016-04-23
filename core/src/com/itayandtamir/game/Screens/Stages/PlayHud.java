package com.itayandtamir.game.Screens.Stages;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.Screens.MenuScreen;
import com.itayandtamir.game.Screens.PauseScreen;
import com.itayandtamir.game.Screens.PauseStage;
import com.itayandtamir.game.Screens.PlayScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class PlayHud extends Stage {
    private FirstGame game;

    private int score;
    private float timeCount;

    public ImageButton pauseButton;
    private Label scoreLabel;
    private Label gameoverLabel;
    private TextButton backToMenu;
    //public static Window pauseWindow;

    public PlayHud(final Viewport viewport, final SpriteBatch batch, FirstGame firstGame) {
        super(viewport, batch);
        game = firstGame;

        pauseButton = new ImageButton(new TextureRegionDrawable(Assets.pauseButton));
        pauseButton.setPosition(440, 780, Align.top);
        pauseButton.setScale(2);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //addActor(pauseWindow);
                PlayScreen.gameState = PlayScreen.GameState.Pause;
                PlayScreen.stage.addActor(PlayScreen.image);


            }
        });


//        pauseWindow = new Window("", Assets.skin);
//        pauseWindow.setVisible(false);
//        pauseWindow.padTop(32);
//        pauseWindow.pack();
//        pauseWindow.setFillParent(false);
//        pauseWindow.setBackground(new TextureRegionDrawable(Assets.backgroundPause));
//        pauseWindow.setSize(FirstGame.WORLD_WIDTH / 2, FirstGame.WORLD_HEIGHT / 2);
//        pauseWindow.setPosition(getWidth() / 2 - pauseWindow.getWidth() / 2, getHeight() / 2 - pauseWindow.getHeight() / 2);
//        TextButton continueButton = new TextButton("continue", Assets.skin);
//        continueButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                PlayScreen.gameState = PlayScreen.gameState.Play;
//                pauseWindow.setVisible(false);
//            }
//        });
//
//        pauseWindow.add(continueButton).row();
//		pauseWindow.add(new TextButton("exit", Assets.skin));


        score = 0;
        timeCount = 0;

        scoreLabel = new Label(String.valueOf(score), Assets.skin, "play-hud");
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setPosition(FirstGame.WORLD_WIDTH / 2, FirstGame.WORLD_HEIGHT * 0.9f, Align.center);

        gameoverLabel = new Label("Game Over\nTap To Retry", Assets.skin, "play-hud");
        gameoverLabel.setAlignment(Align.center);
        gameoverLabel.setPosition(FirstGame.WORLD_WIDTH / 2, 0, Align.top);

        backToMenu = new TextButton("Back To Main Menu", Assets.skin, "menu");
        backToMenu.setPosition(FirstGame.WORLD_WIDTH / 2, 0, Align.top);
        backToMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        addActor(scoreLabel);
        addActor(gameoverLabel);
        addActor(backToMenu);
        addActor(pauseButton);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void updateScoreLabel(float delta) {
        timeCount += delta;
        if (timeCount >= 1) {
            score++;
            scoreLabel.setText(String.valueOf(score));
            timeCount = 0;
        }
    }

    public void addScore(int addition) {
        score += addition;
        scoreLabel.setText(String.valueOf(score));
    }

    public void gameover() {
        float moveUpDuration = 0.35f;
        gameoverLabel.addAction(moveToAligned(FirstGame.WORLD_WIDTH / 2, FirstGame.WORLD_HEIGHT * 0.75f, Align.center, moveUpDuration));
        backToMenu.addAction(moveToAligned(FirstGame.WORLD_WIDTH / 2, FirstGame.WORLD_HEIGHT * 0.6f, Align.center, moveUpDuration));
    }


}
