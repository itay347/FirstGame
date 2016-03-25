package com.itayandtamir.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    enum GameState {
        Play, Pause, Dead
    }

    private GameState gameState;

    public PlayScreen(FirstGame firstGame) {
        this.game = firstGame;
        stage = new Stage(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch);
        playHud = new PlayHud(new StretchViewport(FirstGame.WORLD_WIDTH, FirstGame.WORLD_HEIGHT), Assets.batch, firstGame);

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
            case Pause:
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
        multiplexer.addProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(gameState == GameState.Dead)
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
}
