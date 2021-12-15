package game;

import graphics.Screen;
import input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.Serial;

public class Game extends Canvas implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String title = "OVERWORLD";
    private final int WIDTH = 300;
    private final int HEIGHT = WIDTH / 16 * 9;
    private final int SCALE = 3;

    private int cycle = 0;
    private int color = 0xaf7ac5;

    private JFrame frame;
    private Thread thread;
    private boolean running = false;

    private Screen screen;
    private Input input;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private DataBuffer buffer = image.getRaster().getDataBuffer();

    public Game() {
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        this.setPreferredSize(size);

        screen = new Screen(WIDTH, HEIGHT, this);
        input = new Input(this);
        frame = new JFrame();

        setupWindow();
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.start();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, title);
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;

            lastTime = now;
            while (delta >= 1) {
                update();
                frames++;
                delta--;
            }

            render();

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + " | " + frames + "fps");
                frames = 0;
            }
        }
        stop();
    }

    public int getColor() {
        return color;
    }

    public void changeColor() {
        if (cycle + 1 > 2) {
            cycle = 0;
        } else {
            cycle++;
        }

        switch (cycle) {
            case 0 -> color = 0xaed6f1;
            case 1 -> color = 0x82e0aa;
            case 2 -> color = 0x1d8348;
            default -> color = 0xa04000;
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        screen.clear();
        screen.render();

        Graphics graphics = bs.getDrawGraphics();

        for (int i = 0; i < buffer.getSize(); i++) {
            buffer.setElem(i, screen.pixels[i]);
        }

        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        graphics.dispose();
        bs.show();
    }

    private void update() {

    }

    private void setupWindow() {
        frame.setTitle(title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);

        this.addMouseListener(input);
        this.addKeyListener(input);
    }

}