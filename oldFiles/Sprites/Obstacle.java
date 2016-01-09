package com.itayandtamir.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.itayandtamir.game.States.PlayState;

import java.util.Random;

public class Obstacle {
    private Vector2 pos;
    private Texture texture;
    private Random rand;

    public Obstacle(float y){
        texture = new Texture("ObstacleStone.png");
        pos = new Vector2(0, y);
        rand = new Random();
        updatePosition();
}

    public void updatePosition(){
        int lane = rand.nextInt(3);
        if (lane == 0) pos.x = (PlayState.LANE_LEFT.getX() + PlayState.LANE_LEFT.width / 2 - texture.getWidth() / 2);
        if (lane == 1) pos.x = (PlayState.LANE_MIDDLE.getX() + PlayState.LANE_MIDDLE.width / 2 - texture.getWidth() / 2);
        if (lane == 2) pos.x = (PlayState.LANE_RIGHT.getX() + PlayState.LANE_RIGHT.width / 2 - texture.getWidth() / 2);
    }

    public void dispose(){
        texture.dispose();
    }

    public Vector2 getPos() {
        return pos;
    }

    public Texture getTexture() {
        return texture;
    }

}
