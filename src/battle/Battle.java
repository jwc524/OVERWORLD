package battle;

import player.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Battle implements Runnable {

    private UUID uuid;
    private Map map;
    private ArrayList<Player> gods;
    private ArrayList<Player> mortals;

    private int BATTLE_TIME = 300;
    private boolean running = false;
    private Thread thread;

    public Battle(Map map) {
        uuid = UUID.randomUUID();
        this.map = map;

        gods = new ArrayList<>();
        mortals = new ArrayList<>();
    }

    public Battle addGod(Player player) {
        if (!gods.contains(player)) gods.add(player);
        return this;
    }

    public Battle addMortal(Player player) {
        if (!mortals.contains(player)) mortals.add(player);
        return this;
    }

    public Battle removeGod(Player player) {
        if (gods.contains(player)) gods.remove(player);
        return this;
    }

    public Battle removeMortal(Player player) {
        if (mortals.contains(player)) mortals.remove(player);
        return this;
    }

    /** Sets duration, in time, of the battle. **/
    public Battle setMatchTime(int time) {
        this.BATTLE_TIME = time;
        return this;
    }

    public synchronized void start() {
        thread = new Thread(this, "OVERWORLD-battle");
        running = true;
        thread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;

            lastTime = now;
            while (delta >= 1) {
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;


                BATTLE_TIME--;
                if (BATTLE_TIME == -1) running = false;
                System.out.println("TIME LEFT: " + getTimeLeftFancy());
            }
        }
        stop();
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UUID getBattleUUID() {
        return this.uuid;
    }

    public Map getMap() {
        return this.map;
    }

    public int getTimeLeft() {
        return this.BATTLE_TIME;
    }

    public String getTimeLeftFancy() {
        String minutes = Integer.toString(BATTLE_TIME / 60);
        String seconds = Integer.toString(BATTLE_TIME % 60);
        return minutes + ":" + seconds;
    }

    public boolean isOngoing() {
        return running;
    }

}