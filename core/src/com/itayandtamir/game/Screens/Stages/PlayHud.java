package com.itayandtamir.game.Screens.Stages;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itayandtamir.game.Assets;

public class PlayHud extends Stage {
    private Integer score;
    private float scoreCount;

    Label scoreLabel;
    Label scoreCountLabel;

    public PlayHud(Viewport viewport, SpriteBatch batch) {
        super(viewport, batch);
        score = 0;
        scoreCount = 0;

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
    }

    public void update(float dt) {
        scoreCount += dt;
        if (scoreCount >= 1) {
            score++;
            scoreCountLabel.setText(String.format("%03d", score));
            scoreCount = 0;
        }
    }
}
