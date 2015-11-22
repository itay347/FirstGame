package com.itayandtamir.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.Sprites.Boat;
import com.badlogic.gdx.math.Rectangle;

public class PlayState extends State {

    private static final int BOAT_Y_AXIS_START = 50;
    private static final Rectangle LANE_LEFT = new Rectangle(0, 0, FirstGame.WIDTH / 3, FirstGame.HEIGHT);
    private static final Rectangle LANE_MIDDLE = new Rectangle(FirstGame.WIDTH / 3, 0, FirstGame.WIDTH / 3, FirstGame.HEIGHT);
    private static final Rectangle LANE_RIGHT = new Rectangle(FirstGame.WIDTH / 3 * 2, 0, FirstGame.WIDTH / 3, FirstGame.HEIGHT);

    private Texture background;
    private Boat boat;
    private Vector2 proportion;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        proportion = new Vector2(cam.viewportWidth / Gdx.graphics.getWidth(), cam.viewportHeight / Gdx.graphics.getHeight());
        boat = new Boat(cam.viewportWidth / 2 - 46, BOAT_Y_AXIS_START);
        background = new Texture("BackgroundPlay.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector2 pressPos = new Vector2(Gdx.input.getX() * proportion.x, Gdx.input.getY() * proportion.y);
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
        cam.position.set(cam.viewportWidth / 2, 350,0);
        cam.translate(0, boat.getPosition().y);
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, cam.viewportWidth, cam.viewportHeight);
        sb.draw(boat.getTexture(), boat.getPosition().x, boat.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose(){
        boat.dispose();
        background.dispose();
    }
}
