package com.itayandtamir.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Boat {
    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;

    public Boat(float x, float y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 50);
        texture = new Texture("Boat.png");
    }

    public void update(float dt){
        velocity.scl(dt);
        position.add(0, velocity.y);
        velocity.scl(1 / dt);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setLane(Rectangle lane){
        position.x = lane.getX() + lane.getWidth() / 2 - 46;
    }

    public void dispose(){
        texture.dispose();
    }
}
