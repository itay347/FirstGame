package com.itayandtamir.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.itayandtamir.game.Assets;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle extends Actor {

    private TextureRegion textureRegion;
    private boolean hasPassed;

    public Obstacle(float x, float y) {
        textureRegion = Assets.obstacleStone;
        setWidth(textureRegion.getRegionWidth());
        setHeight(textureRegion.getRegionHeight());

        setPosition(x, y, Align.bottom);
        hasPassed = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void updatePosition(float x, float y) {
        setPosition(x, y, Align.bottom);
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isCollidingWithBoat(Boat boat) {
        return getBounds().overlaps(boat.getBounds());
    }

    public void setHasPassed(boolean hasPassed) {
        this.hasPassed = hasPassed;
    }

    public boolean getHasPassed() {
        return hasPassed;
    }

    public float getLaneX() {
        return getX(Align.center);
    }
}
