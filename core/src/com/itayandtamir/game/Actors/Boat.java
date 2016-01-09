package com.itayandtamir.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Boat extends Actor {

    private TextureRegion textureRegion;

    private Vector2 velocity;

    enum Lane {
        RIGHT(FirstGame.WORLD_WIDTH / 2 + FirstGame.WORLD_WIDTH / 3),
        MIDDLE(FirstGame.WORLD_WIDTH / 2),
        LEFT(FirstGame.WORLD_WIDTH / 2 - FirstGame.WORLD_WIDTH / 3);

        private float x;
        private final float width = FirstGame.WORLD_WIDTH / 3;

        Lane(float x) {
            this.x = x;
        }
    }

    private Lane lane;
    private boolean moving;

    public Boat(Stage stage) {
        textureRegion = new TextureRegion(Assets.boat);

        setWidth(textureRegion.getRegionWidth());
        setHeight(textureRegion.getRegionHeight());

        velocity = new Vector2(0, 100);
        lane = Lane.MIDDLE;
        moving = false;
        setPosition(stage.getWidth() / 2, stage.getHeight() * 0.15f, Align.center);
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
        if (!moving) {
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
        if (!moving) {
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
                        moving = true;
                    }
                }),

                Actions.moveBy(lane.width * direction, 0, 0.2f),

                run(new Runnable() {
                    @Override
                    public void run() {
                        moving = false;
                    }
                })));
    }
}
