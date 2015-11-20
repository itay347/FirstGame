package com.itayandtamir.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.itayandtamir.game.Sprites.Boat;
import com.badlogic.gdx.math.Rectangle;

public class PlayState extends State {
    private static final int BOAT_Y_AXIS_START = 50;
    private static final Rectangle LANE_LEFT = new Rectangle(0, 0, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight());
    private static final Rectangle LANE_MIDDLE = new Rectangle(Gdx.graphics.getWidth() / 3, 0, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight());
    private static final Rectangle LANE_RIGHT = new Rectangle(Gdx.graphics.getWidth() / 3 * 2, 0, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight());

    private Texture background;
    private Boat boat;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        boat = new Boat(Gdx.graphics.getWidth() / 2 - 46 ,BOAT_Y_AXIS_START);
        background = new Texture("BackgroundPlay.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector2 pressPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if(LANE_LEFT.contains(pressPos))
                boat.setLane(LANE_LEFT);
            else if(LANE_MIDDLE.contains(pressPos))
                boat.setLane(LANE_MIDDLE);
            else if(LANE_RIGHT.contains(pressPos))
                boat.setLane(LANE_RIGHT);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        boat.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        sb.draw(boat.getTexture(), boat.getPosition().x, boat.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose(){
        boat.dispose();
        background.dispose();
    }
}
