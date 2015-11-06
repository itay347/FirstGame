package com.itayandtamir.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itayandtamir.game.FirstGame;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("BackgroundMenu.png");
        playButton = new Texture("PlayButton.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FirstGame.WIDTH, FirstGame.HEIGHT);
        sb.draw(playButton, FirstGame.WIDTH / 2 - playButton.getWidth() / 2, FirstGame.HEIGHT / 2 - playButton.getHeight() / 2);
        sb.end();
    }

    public void dispose(){
        background.dispose();
        playButton.dispose();
        System.out.println("Menu State Disposed");
    }
}
