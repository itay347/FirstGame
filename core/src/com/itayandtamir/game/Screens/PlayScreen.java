package com.itayandtamir.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.itayandtamir.game.Actors.Boat;
import com.itayandtamir.game.Actors.ObstacleGroup;
import com.itayandtamir.game.Actors.PlayBackgrounds;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;

public class PlayScreen extends ScreenAdapter {

    private Stage stage;

    private PlayBackgrounds backgrounds;
    private ObstacleGroup obstacles;
    private Boat boat;

    public PlayScreen() {
        stage = new Stage(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch);

        backgrounds = new PlayBackgrounds();
        obstacles = new ObstacleGroup(stage);
        boat = new Boat();

        stage.addActor(backgrounds);
        stage.addActor(obstacles);
        stage.addActor(boat);

        initInputProcessor();
    }

    @Override
    public void render(float delta) {
//        Gdx.gl.glClearColor(1, 1, 1, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }

    private void initInputProcessor() {
        Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                if (Math.abs(velocityX) > Math.abs(velocityY)) {
                    if (velocityX < 0)
                        boat.moveLeft();
                    else if (velocityX > 0)
                        boat.moveRight();
                    return true;
                }
                return false;
            }
        }));
    }
}
