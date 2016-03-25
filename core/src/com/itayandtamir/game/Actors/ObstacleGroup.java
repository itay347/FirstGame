package com.itayandtamir.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.itayandtamir.game.Screens.Stages.PlayHud;

import java.util.Random;

public class ObstacleGroup extends Group {
    private static final int OBSTACLES_AMOUNT = 4;
    private static final float MIN_SPACING = 200;
    private static final float MAX_SPACING = 300;

    private Random random;
    private Array<Obstacle> obstacles;
    private PlayHud hud;

    public ObstacleGroup(Stage stage, PlayHud hud) {
        this.hud = hud;
        random = new Random();
        obstacles = new Array<Obstacle>(OBSTACLES_AMOUNT);
        obstacles.add(new Obstacle(getFirstRandomLane().x,
                stage.getCamera().position.y + stage.getCamera().viewportHeight / 2));
        addActor(obstacles.get(0));
        for (int i = 1; i < OBSTACLES_AMOUNT; i++) {
            obstacles.add(new Obstacle(getRandomLane().x,
                    obstacles.get(i - 1).getY(Align.top) + getRandomSpacing()));
            addActor(obstacles.get(i));
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateObstacles();
    }

    private void updateObstacles() {
        for (int i = 0; i < OBSTACLES_AMOUNT; i++) {
            if (obstacles.get(i).getY(Align.center) <= getParent().findActor("boat").getY(Align.center) && !obstacles.get(i).getHasPassed()) {
                obstacles.get(i).setHasPassed(true);
                hud.addScore(10);
            }
            if (obstacles.get(i).getY(Align.top) < getStage().getCamera().position.y - getStage().getCamera().viewportHeight / 2) {
                obstacles.get(i).updatePosition(getRandomLane().x, getHighestObstacle().getY(Align.top) + getRandomSpacing());
                obstacles.get(i).setHasPassed(false);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    private Lane getFirstRandomLane() {
        int randLane = random.nextInt(3);

        switch (randLane) {
            case 0:
                return Lane.LEFT;
            case 1:
                return Lane.MIDDLE;
            case 2:
                return Lane.RIGHT;
            default:
                return Lane.MIDDLE;
        }
    }

    private Lane getRandomLane() {
        int randLane = random.nextInt(2);
        float lastLaneX = getHighestObstacle().getLaneX();

        if (lastLaneX == Lane.LEFT.x) {
            switch (randLane) {
                case 0:
                    return Lane.MIDDLE;
                case 1:
                    return Lane.RIGHT;
            }
        } else if (lastLaneX == Lane.MIDDLE.x) {
            switch (randLane) {
                case 0:
                    return Lane.LEFT;
                case 1:
                    return Lane.RIGHT;
            }
        } else { //Lane.RIGHT
            switch (randLane) {
                case 0:
                    return Lane.MIDDLE;
                case 1:
                    return Lane.LEFT;
            }
        }

        return Lane.MIDDLE;
    }

    private float getRandomSpacing() {
        return MIN_SPACING + random.nextInt((int) (MAX_SPACING - MIN_SPACING));
    }

    private Obstacle getHighestObstacle() {
        Obstacle highestObstacle = obstacles.first();
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getY() > highestObstacle.getY()) {
                highestObstacle = obstacle;
            }
        }
        return highestObstacle;
    }

    public boolean isCollidingWithBoat(Boat boat) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isCollidingWithBoat(boat))
                return true;
        }
        return false;
    }
}
