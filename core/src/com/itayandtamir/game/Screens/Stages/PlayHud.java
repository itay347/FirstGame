package com.itayandtamir.game.Screens.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.Screens.PauseScreen;

public class PlayHud extends Stage {
    private int score;
    private float timeCount;

    Label scoreLabel;
    Label scoreCountLabel;

    public ImageButton pauseButton;
    private TextureRegionDrawable regionDrawable = new TextureRegionDrawable(Assets.pauseButton);

    public PlayHud(Viewport viewport, SpriteBatch batch) {
        super(viewport, batch);
        pauseButton = new ImageButton(regionDrawable);
        pauseButton.setPosition(440, 780, Align.top);
        pauseButton.setScale(2);

        score = 0;
        timeCount = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label("Score", Assets.skin, "play-hud");
        scoreCountLabel = new Label(String.format("%03d", score), Assets.skin, "play-hud");

        scoreLabel.setFontScale(1.5f);
        scoreCountLabel.setFontScale(1.5f);

        table.add(scoreLabel).expandX();
        table.add(scoreCountLabel).expandX();

        addActor(table);
        addActor(pauseButton);

    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            score++;
            scoreCountLabel.setText(String.format("%03d", score));
            timeCount = 0;
        }
    }

    public void addScore(int addition) {
        score += addition;
        scoreCountLabel.setText(String.format("%03d", score));
    }


}
