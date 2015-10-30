package com.itayandtamir.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itayandtamir.game.FirstGame;


public class MenuState extends State {
    Texture Background;
    Texture Button;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        Background = new Texture("Background.png");
        Button = new Texture("Button.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            despose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(Background, 0, 0, FirstGame.WIDTH,FirstGame.HEIGHT);
        sb.draw(Button,(FirstGame.WIDTH/2) - (Button.getWidth()/2),FirstGame.HEIGHT/2 - 50);
        sb.end();
    }

    public void despose(){
        Background.dispose();
        Button.dispose();
    }
}
