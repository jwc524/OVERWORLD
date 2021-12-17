package overworld;

import battle.Battle;
import battle.Map;
import graphics.Screen;
import input.Input;
import olympian.Zeus;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.Serial;

public class OverworldGame extends Canvas implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String title = "OVERWORLD";
    private final int WIDTH = 300;
    private final int HEIGHT = WIDTH / 16 * 9;
    private final int SCALE = 3;

    private int cycle = 0;
    private int color = 0xaf7ac5;

    private final JFrame frame;
    private Thread thread;
    private boolean running = false;

    private int updates = 0;

    private final Screen screen;
    private final Input input;
    private static Battle battle;

    private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private final DataBuffer buffer = image.getRaster().getDataBuffer();

    public OverworldGame() {
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        this.setPreferredSize(size);

        screen = new Screen(WIDTH, HEIGHT, this);
        input = new Input(this);
        frame = new JFrame();

        setupWindow();
    }

    public static void main(String[] args) {
        OverworldGame game = new OverworldGame();

        game.start();

        battle = new Battle(new Map()).setMatchTime(10);
//        Player player = new Player("enigma").setCurrentOlympian(new Zeus("Zeus"));

//        battle.addGod(player);
        battle.start();
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
                updates = frames;

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

        Font font = new Font("Lunatix OT", Font.PLAIN, 50);
        FontMetrics fm = this.getFontMetrics(font);

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, fm.stringWidth("00fps"), fm.getHeight());

        graphics.setColor(Color.WHITE);
        graphics.setFont(font);
        graphics.drawString(updates + "fps", 0, fm.getAscent());

        if (battle.isOngoing()) {
            font = new Font("Lunatix OT", Font.PLAIN, 30);
            String timeLeft = battle.getTimeLeftFancy();
            fm = this.getFontMetrics(font);

            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, getHeight() - fm.getHeight(), getWidth(), fm.getHeight());

            graphics.setColor(Color.PINK);
            graphics.setFont(font);
            graphics.drawString(timeLeft, (getWidth() / 2) - fm.stringWidth(timeLeft), getHeight() - fm.getDescent());
        }

        int xOffset = -100;
        int yOffset = 0;

        font = new Font("Lunatix OT", Font.BOLD, 50);
        graphics.setFont(font);

        graphics.setColor(Color.DARK_GRAY);
        graphics.drawString("W", (getWidth() / 2) + xOffset, (getHeight() / 2 - 50) + yOffset);
        graphics.drawString("A", (getWidth() / 2 - 50) + xOffset, (getHeight() / 2) + yOffset);
        graphics.drawString("S", (getWidth() / 2) + xOffset, (getHeight() / 2 + 50) + yOffset);
        graphics.drawString("D", (getWidth() / 2 + 50) + xOffset, (getHeight() / 2) + yOffset);

        graphics.setColor(Color.PINK);
        if (input.up) graphics.drawString("W", (getWidth() / 2) + xOffset, (getHeight() / 2 - 50) + yOffset);
        if (input.left) graphics.drawString("A", (getWidth() / 2 - 50) + xOffset, (getHeight() / 2) + yOffset);
        if (input.down) graphics.drawString("S", (getWidth() / 2) + xOffset, (getHeight() / 2 + 50) + yOffset);
        if (input.right) graphics.drawString("D", (getWidth() / 2 + 50) + xOffset, (getHeight() / 2) + yOffset);

        xOffset = 100;

        graphics.setColor(Color.DARK_GRAY);
        graphics.drawString("J", (getWidth() / 2) + xOffset, (getHeight() / 2) + yOffset);
        graphics.drawString("K", (getWidth() / 2 + 25) + xOffset, (getHeight() / 2) + yOffset);
        graphics.drawString("L", (getWidth() / 2 + 50) + xOffset, (getHeight() / 2) + yOffset);
        graphics.drawString("U", (getWidth() / 2 + 25) + xOffset, (getHeight() / 2 - 25) + yOffset);

        graphics.setColor(Color.PINK);
        if (input.L1) graphics.drawString("J", (getWidth() / 2) + xOffset, (getHeight() / 2) + yOffset);
        if (input.L2) graphics.drawString("K", (getWidth() / 2 + 25) + xOffset, (getHeight() / 2) + yOffset);
        if (input.L3) graphics.drawString("L", (getWidth() / 2 + 50) + xOffset, (getHeight() / 2) + yOffset);
        if (input.ultimate) graphics.drawString("U", (getWidth() / 2 + 25) + xOffset, (getHeight() / 2 - 25) + yOffset);

        graphics.dispose();
        bs.show();
    }

    private void update() {
        input.update();
    }

    private void setupWindow() {
        frame.setTitle(title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();
        frame.setAlwaysOnTop(false);

        this.addMouseListener(input);
        this.addKeyListener(input);
    }

}