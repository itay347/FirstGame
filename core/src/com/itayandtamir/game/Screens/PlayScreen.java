package com.itayandtamir.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.itayandtamir.game.Actors.Boat;
import com.itayandtamir.game.Actors.ObstacleGroup;
import com.itayandtamir.game.Actors.PlayBackgrounds;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.Screens.Stages.PlayHud;

public class PlayScreen extends ScreenAdapter {
    private FirstGame game;
    private Stage stage;

    private PlayBackgrounds backgrounds;
    private ObstacleGroup obstacles;
    private Boat boat;
    private PlayHud playHud;



    public PlayScreen(FirstGame firstGame) {
        this.game = firstGame;
        stage = new Stage(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch);
        playHud = new PlayHud(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch);


        backgrounds = new PlayBackgrounds();
        obstacles = new ObstacleGroup(stage, playHud);
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
        hasPaused();
        checkCollisions();
        stage.draw();
        playHud.draw();
        stage.act(delta);
        playHud.update(delta);


    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        playHud.dispose();
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

    private void checkCollisions() {
        if (obstacles.isCollidingWithBoat(boat)) {
            //TODO: change what happens here
            game.setScreen(new MenuScreen(game));
        }
    }
    private void hasPaused(){
        playHud.pauseButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game));
            }
        });
    }
}
