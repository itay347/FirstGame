package com.itayandtamir.game.Screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itayandtamir.game.Assets;


/**
 * Created by Tamir on 27/03/2016.
 */
public class PauseStage extends Stage {

    private Image pauseImage;

    public PauseStage(Viewport viewport, Batch batch) {
        super(viewport, batch);

        pauseImage = new Image(Assets.backgroundPause);

        addActor(pauseImage);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
