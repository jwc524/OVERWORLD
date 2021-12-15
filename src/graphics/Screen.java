package graphics;

import game.Game;

import java.util.Arrays;

public class Screen {

    private Game game;
    private int width, height;
    public int[] pixels;

    public Screen(int width, int height, Game game) {
        this.width = width;
        this.height = height;
        this.game = game;

        pixels = new int[width * height];
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = game.getColor();
            }
        }
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

}