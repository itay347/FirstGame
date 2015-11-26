package com.itayandtamir.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Tamir on 26/11/2015.
 */
public class Obstacle {
    private Vector2 obstaclePos;
    private Texture obstacleTexture;
    private Random randPos = new Random();

    public Obstacle(Vector2 pos){
        pos = obstaclePos;
        obstacleTexture = new Texture("Obstacle");
        obstaclePos = new Vector2();
    }

    public void update(float dt){

    }
}
