package com.itayandtamir.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.States.PlayState;

import java.util.Random;

/**
 * Created by Tamir on 26/11/2015.
 */
public class Obstacle {
    private static final int OBSTACLE_Y_OFFSET = 50;
    private Vector2 pos;
    private Texture texture;
    private Random rand;

    public Obstacle(float camY){
        texture = new Texture("ObstacleStone.png");
        pos = new Vector2(0, camY + FirstGame.HEIGHT/2 + OBSTACLE_Y_OFFSET);
        rand = new Random();
        int lane = rand.nextInt(3);

        if (lane == 0)
            pos.x = (PlayState.LANE_LEFT.getX() + PlayState.LANE_LEFT.width/2 - texture.getWidth()/2);

        if (lane == 1)
            pos.x = (PlayState.LANE_MIDDLE.getX() + PlayState.LANE_MIDDLE.width/2 - texture.getWidth()/2);

        if (lane == 2)
            pos.x = (PlayState.LANE_RIGHT.getX() + PlayState.LANE_RIGHT.width/2 - texture.getWidth()/2);
}

    public void updatePostition(){
        int lane = rand.nextInt(3);

        if (lane == 0)
            pos.x = (PlayState.LANE_LEFT.getX() + PlayState.LANE_LEFT.width/2 - texture.getWidth()/2);

        if (lane == 1)
            pos.x = (PlayState.LANE_MIDDLE.getX() + PlayState.LANE_MIDDLE.width/2 - texture.getWidth()/2);

        if (lane == 2)
            pos.x = (PlayState.LANE_RIGHT.getX() + PlayState.LANE_RIGHT.width/2 - texture.getWidth()/2);
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
