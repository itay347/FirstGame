package com.itayandtamir.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.itayandtamir.game.Actors.Boat;
import com.itayandtamir.game.Actors.ObstacleGroup;
import com.itayandtamir.game.Actors.PlayBackgrounds;
import com.itayandtamir.game.Assets;
import com.itayandtamir.game.FirstGame;
import com.itayandtamir.game.Screens.Stages.PlayHud;

import java.security.AllPermission;

public class PlayScreen extends ScreenAdapter {
    private FirstGame game;
    public static Stage stage;

    public static Image image;
    private PlayBackgrounds backgrounds;
    private ObstacleGroup obstacles;
    private Boat boat;
    private PlayHud playHud;
    Vector3 touchPos;
    Rectangle resumeRec;


    public enum GameState {
        Play, Pause, Dead
    }

    public static GameState gameState;

    public PlayScreen(FirstGame firstGame) {
        this.game = firstGame;
        stage = new Stage(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch);
        playHud = new PlayHud(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch, firstGame);
        touchPos = new Vector3();
        resumeRec = new Rectangle();
        resumeRec.setSize(192, 36);

        image = new Image(Assets.resume);

        image.setSize(192, 36);
        backgrounds = new PlayBackgrounds();
        obstacles = new ObstacleGroup(stage, playHud);
        boat = new Boat();

        stage.addActor(backgrounds);
        stage.addActor(obstacles);
        stage.addActor(boat);


        initInputProcessor();

        gameState = GameState.Play;
    }

    @Override
    public void render(float delta) {
        switch (gameState) {
            case Play:
                stage.act(delta);
                checkCollisions();
                playHud.updateScoreLabel(delta);
                updateLocation(delta);
            case Pause:
                //PlayHud.pauseWindow.setVisible(true);
                gamePaused();
            case Dead:
                stage.draw();
                playHud.act(delta);
                playHud.draw();
                break;
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        playHud.dispose();
    }

    private void initInputProcessor() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(playHud);
        multiplexer.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
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
        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (gameState == GameState.Dead)
                    game.setScreen(new PlayScreen(game));
                return true;
            }
        });
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void checkCollisions() {
        if (obstacles.isCollidingWithBoat(boat)) {
            //TODO: change what happens here
            gameState = GameState.Dead;
            playHud.gameover();
        }
    }

    public void gamePaused() {
        if (Gdx.input.justTouched()) {
            stage.getCamera().unproject(touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (resumeRec.contains(touchPos.x, touchPos.y)) {
                PlayScreen.gameState = PlayScreen.GameState.Play;
                image.remove();
            }
        }
    }

    public void updateLocation(float delta) {
        image.setPosition(stage.getCamera().position.x - image.getWidth() / 2, stage.getCamera().position.y);
        resumeRec.setPosition(stage.getCamera().position.x - image.getWidth() / 2, stage.getCamera().position.y);
    }

}

