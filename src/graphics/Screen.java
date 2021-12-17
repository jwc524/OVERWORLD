package graphics;

import overworld.OverworldGame;

import java.util.Arrays;

public class Screen {

    private OverworldGame game;
    private int width, height;
    public int[] pixels;

    public Screen(int width, int height, OverworldGame game) {
        this.width = width;
        this.height = height;
        this.game = game;

        pixels = new int[width * height];
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0x000000;
            }
        }

    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

}