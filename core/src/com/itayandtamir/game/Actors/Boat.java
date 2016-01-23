package com.itayandtamir.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Boat extends Actor {

    private static final float STARTING_Y_POSITION = FirstGame.WORLD_HEIGHT * 0.15f; //Aligned to the center of the boat
    private static final float SPEED = 100f;
    private static final float LANE_SWITCH_DURATION = 0.2f;

    private TextureRegion textureRegion;
    private Vector2 velocity;
    private Lane lane;
    private boolean switchingLane;

    public Boat() {
        textureRegion = new TextureRegion(Assets.boat);
        setWidth(textureRegion.getRegionWidth());
        setHeight(textureRegion.getRegionHeight());

        velocity = new Vector2(0, SPEED);
        lane = Lane.MIDDLE;
        switchingLane = false;
        setPosition(lane.x, STARTING_Y_POSITION, Align.center);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        setPosition(getX(), getY() + velocity.y * delta);

        getStage().getCamera().position.y += velocity.y * delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void moveLeft() {
        if (!switchingLane) {
            if (lane == Lane.MIDDLE) {
                lane = Lane.LEFT;
                applyMoveAction(-1);
            }
            if (lane == Lane.RIGHT) {
                lane = Lane.MIDDLE;
                applyMoveAction(-1);
            }
        }
    }

    public void moveRight() {
        if (!switchingLane) {
            if (lane == Lane.MIDDLE) {
                lane = Lane.RIGHT;
                applyMoveAction(1);
            }
            if (lane == Lane.LEFT) {
                lane = Lane.MIDDLE;
                applyMoveAction(1);
            }
        }
    }

    private void applyMoveAction(float direction) {
        addAction(sequence(
                run(new Runnable() {
                    @Override
                    public void run() {
                        switchingLane = true;
                    }
                }),

                Actions.moveBy(lane.width * direction, 0, LANE_SWITCH_DURATION),

                run(new Runnable() {
                    @Override
                    public void run() {
                        switchingLane = false;
                    }
                })));
    }
}
