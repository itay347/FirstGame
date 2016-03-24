package com.itayandtamir.game.Actors;

import com.itayandtamir.game.FirstGame;

public enum Lane {
    RIGHT(FirstGame.WORLD_WIDTH / 2 + FirstGame.WORLD_WIDTH / 3),
    MIDDLE(FirstGame.WORLD_WIDTH / 2),
    LEFT(FirstGame.WORLD_WIDTH / 2 - FirstGame.WORLD_WIDTH / 3);

    protected float x;
    protected final float width = FirstGame.WORLD_WIDTH / 3;

    Lane(float x) {
        this.x = x;
    }
}
