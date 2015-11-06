package com.itayandtamir.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.Sprites.Boat;


public class PlayState extends State {
    private static final int BOAT_Y_AXIS_START = 50;

    private Texture background;
    private Boat boat;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        boat = new Boat(FirstGame.WIDTH / 2 - 46 ,BOAT_Y_AXIS_START);
        background = new Texture("BackgroundPlayV1.png");
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
        sb.draw(background, 0, 0);
        sb.draw(boat.getTexture(), boat.getPosition().x, boat.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose(){
        boat.dispose();
        background.dispose();
    }
}
