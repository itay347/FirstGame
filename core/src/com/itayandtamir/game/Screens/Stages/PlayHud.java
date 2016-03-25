package com.itayandtamir.game.Screens.Stages;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.Screens.MenuScreen;
import com.itayandtamir.game.Screens.PauseScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class PlayHud extends Stage {
    private FirstGame game;

    private int score;
    private float timeCount;

    private Label scoreLabel;
    private Label gameoverLabel;
    private TextButton backToMenu;
    public ImageButton pauseButton;

    public PlayHud(Viewport viewport, SpriteBatch batch, FirstGame firstGame) {
        super(viewport, batch);
        game = firstGame;

        pauseButton = new ImageButton(new TextureRegionDrawable(Assets.pauseButton));
        pauseButton.setPosition(440, 780, Align.top);
        pauseButton.setScale(2);
        pauseButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game));
            }
        });

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
