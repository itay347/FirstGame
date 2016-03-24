package com.itayandtamir.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.itayandtamir.game.Assets;

public class PlayBackgrounds extends Group {

    private Image background1, background2;

    public PlayBackgrounds() {
        background1 = new Image(Assets.backgroundPlay);
        background1.setPosition(0, 0);
        background2 = new Image(Assets.backgroundPlay);
        background2.setPosition(0, background2.getHeight());

        addActor(background1);
        addActor(background2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateBackgrounds();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    private void updateBackgrounds() {
        if (background1.getY(Align.top) <= getStage().getCamera().position.y - getStage().getCamera().viewportHeight / 2)
            background1.setPosition(getX(), background1.getY() + background1.getHeight() * 2);
        if (background2.getY(Align.top) <= getStage().getCamera().position.y - getStage().getCamera().viewportHeight / 2)
            background2.setPosition(getX(), background2.getY() + background2.getHeight() * 2);
    }
}
