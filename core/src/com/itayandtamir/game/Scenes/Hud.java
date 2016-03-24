package com.itayandtamir.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itayandtamir.game.Assets;



/**
 * Created by Tamir on 3/22/2016.
 */
public class Hud extends Stage {
    private Integer worldTime;
    private float timeCount;

    Label timeLabel;
    Label countLabel;

    public Hud(Viewport viewport, SpriteBatch batch){
        super(viewport,batch);
        worldTime = 0;
        timeCount = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLabel = new Label("Time", new Label.LabelStyle(Assets.font, Color.WHITE));
        countLabel = new Label(String.format("%03d", worldTime), new Label.LabelStyle(Assets.font, Color.WHITE));

        timeLabel.setFontScale(2.5f);
        countLabel.setFontScale(2.5f);

        table.add(timeLabel).expandX();
        table.add(countLabel).expandX();

        addActor(table);
    }

    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            worldTime++;
            countLabel.setText(String.format("%03d", worldTime));
            timeCount = 0;
        }
    }
}
