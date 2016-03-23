package com.itayandtamir.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;

/**
 * Created by Tamir on 3/22/2016.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTime;
    private float timeCount;

    Label timeLabel;
    Label countLabel;

    public Hud(SpriteBatch spriteBatch){
        worldTime = 0;
        timeCount = 0;

        viewport = new StretchViewport(FirstGame.WORLD_WIDTH,FirstGame.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);



        timeLabel = new Label("Time", new Label.LabelStyle(Assets.font, Color.WHITE));
        countLabel = new Label(String.format("%03d", worldTime), new Label.LabelStyle(Assets.font, Color.WHITE));

        timeLabel.setFontScale(2.5f);
        countLabel.setFontScale(2.5f);

        table.add(timeLabel).expandX();
        table.add(countLabel).expandX();

        stage.addActor(table);
    }
}
