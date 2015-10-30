package com.itayandtamir.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class Boat {
    private Vector2 position;
    private Vector2 velocity;

    private Texture boat;

    public Boat(float x, float y){
        position = new Vector2(x,y);
        velocity = new Vector2(0, 0);

        boat = new Texture("Boat.png");
    }

    public void update(float dt){
        velocity.add(0, 0.01f);
        velocity.scl(dt);
        position.add(0, velocity.y);

        velocity.scl(1/dt);
    }


    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Texture getBoat() {
        return boat;
    }

    public void setBoat(Texture boat) {
        this.boat = boat;
    }
}
