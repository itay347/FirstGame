package com.itayandtamir.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class Boat {
    private Vector2 position;
    private Vector2 velocity;

    private Texture boat;

    public Boat(float x, float y){
        position = new Vector2(x,y);
        velocity = new Vector2(0, 1);

        boat = new Texture("Boat.png");
    }

    public void update(float dt){
        velocity.scl(dt);
        position.add(0, velocity.y);
        velocity.scl(1/dt);
    }


    public Vector2 getPosition() {
        return position;
    }


    public Texture getBoat() {
        return boat;
    }

}
