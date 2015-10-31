package com.itayandtamir.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.Sprites.Boat;


public class PlayState extends State {
    private Texture playBackground;
    private Boat boat;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        boat = new Boat((FirstGame.WIDTH / 2) - 46 ,0);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        boat.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(boat.getBoat(),boat.getPosition().x,boat.getPosition().y);
        sb.end();
    }
}
