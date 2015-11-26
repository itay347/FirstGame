package com.itayandtamir.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.Sprites.Boat;
import com.badlogic.gdx.math.Rectangle;
import com.itayandtamir.game.Sprites.Obstacle;

public class PlayState extends State {
    private static final int BOAT_Y_AXIS_START = 50;
    private static final int BOAT_Y_AXIS_OFFSET = FirstGame.HEIGHT / 2 - BOAT_Y_AXIS_START;
    public static final Rectangle LANE_LEFT = new Rectangle(0, 0, FirstGame.WIDTH / 3, FirstGame.HEIGHT);
    public static final Rectangle LANE_MIDDLE = new Rectangle(FirstGame.WIDTH / 3, 0, FirstGame.WIDTH / 3, FirstGame.HEIGHT);
    public static final Rectangle LANE_RIGHT = new Rectangle(FirstGame.WIDTH / 3 * 2, 0, FirstGame.WIDTH / 3, FirstGame.HEIGHT);

    private Texture background;
    private Boat boat;
    private Vector2 proportion;
    private Vector2 backgroundPos1, backgroundPos2;
    private Obstacle[] obstacles = new Obstacle[3];

    public PlayState(GameStateManager gsm) {
        super(gsm);
        proportion = new Vector2(cam.viewportWidth / Gdx.graphics.getWidth(), cam.viewportHeight / Gdx.graphics.getHeight());
        boat = new Boat(cam.viewportWidth / 2 - 46, BOAT_Y_AXIS_START);
        background = new Texture("BackgroundPlay.png");
        backgroundPos1 = new Vector2(0, 0);
        backgroundPos2 = new Vector2(0, background.getHeight());
        obstacles[0] = new Obstacle(cam.position.y);
        obstacles[1] = new Obstacle(cam.position.y);
        obstacles[2] = new Obstacle(cam.position.y);

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
        updateBackground();
        updateObstacles();
        boat.update(dt);
        cam.position.set(cam.viewportWidth / 2, boat.getPosition().y + BOAT_Y_AXIS_OFFSET, 0);
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, backgroundPos1.x, backgroundPos1.y, cam.viewportWidth, cam.viewportHeight);
        sb.draw(background, backgroundPos2.x, backgroundPos2.y, cam.viewportWidth, cam.viewportHeight);
        sb.draw(boat.getTexture(), boat.getPosition().x, boat.getPosition().y);
        sb.draw(obstacles[0].getTexture(), obstacles[0].getPos().x, obstacles[0].getPos().y);
        sb.draw(obstacles[1].getTexture(), obstacles[1].getPos().x, obstacles[1].getPos().y);
        sb.draw(obstacles[2].getTexture(), obstacles[2].getPos().x, obstacles[2].getPos().y);
        sb.end();
    }

    @Override
    public void dispose(){
        boat.dispose();
        background.dispose();
    }

    private void updateBackground(){
        if(cam.position.y - cam.viewportHeight / 2 > backgroundPos1.y + background.getHeight())
            backgroundPos1.add(0, background.getHeight() * 2);
        if(cam.position.y - cam.viewportHeight / 2 > backgroundPos2.y + background.getHeight())
            backgroundPos2.add(0, background.getHeight() * 2);
    }

    private void updateObstacles(){
        if(cam.position.y - cam.viewportHeight / 2 > obstacles[0].getPos().y + obstacles[0].getTexture().getHeight()) {
            obstacles[0].getPos().add(0, background.getHeight() + obstacles[0].getTexture().getHeight());
            obstacles[0].updatePostition();
        }
        if(cam.position.y - cam.viewportHeight / 2 > obstacles[1].getPos().y + obstacles[1].getTexture().getHeight()) {
            obstacles[1].getPos().add(0, background.getHeight() + obstacles[1].getTexture().getHeight());
            obstacles[1].updatePostition();
        }
        if(cam.position.y - cam.viewportHeight / 2 > obstacles[2].getPos().y + obstacles[2].getTexture().getHeight()) {
            obstacles[2].getPos().add(0, background.getHeight() + obstacles[2].getTexture().getHeight());
            obstacles[2].updatePostition();
        }

    }
}
