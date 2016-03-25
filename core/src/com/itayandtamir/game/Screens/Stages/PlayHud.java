package com.itayandtamir.game.Screens.Stages;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itayandtamir.game.Assets;

public class PlayHud extends Stage {
    private Integer worldTime;
    private float timeCount;

    Label timeLabel;
    Label countLabel;

    public PlayHud(Viewport viewport, SpriteBatch batch) {
        super(viewport, batch);
        worldTime = 0;
        timeCount = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLabel = new Label("Time", Assets.skin, "play-hud");
        countLabel = new Label(String.format("%03d", worldTime), Assets.skin, "play-hud");

        timeLabel.setFontScale(2.5f);
        countLabel.setFontScale(2.5f);

        table.add(timeLabel).expandX();
        table.add(countLabel).expandX();

        addActor(table);
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTime++;
            countLabel.setText(String.format("%03d", worldTime));
            timeCount = 0;
        }
    }
}
