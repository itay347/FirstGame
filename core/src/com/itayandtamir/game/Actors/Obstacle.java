package com.itayandtamir.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.itayandtamir.game.Assets;

public class Obstacle extends Actor {

    private TextureRegion textureRegion;

    public Obstacle(float x, float y) {
        textureRegion = Assets.obstacleStone;
        setWidth(textureRegion.getRegionWidth());
        setHeight(textureRegion.getRegionHeight());

        setPosition(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
