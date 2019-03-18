import edu.digipen.Game;
import edu.digipen.InputManager;
import edu.digipen.SoundManager;
import edu.digipen.gameobject.ObjectManager;
import edu.digipen.graphics.Graphics;
import edu.digipen.level.GameLevel;
import edu.digipen.level.GameLevelManager;

import java.awt.event.KeyEvent;

public class Level1 extends GameLevel {
    public float timer = 7;
    public float fasttimer = 10;
    public float bosstimer = 40;
    public float shootertimer = 17;
    public float GunTimer = 13;

    @Override
    public void create() {
        // this gets called once when creating the level the first time
        // most memory allocation
        Graphics.hideMouseCursor();
        Graphics.setBackgroundColor(0.7f, 0.7f, 0.7f);
        SoundManager.addBackgroundSound("BG", "Frantic-Gameplay.wav", true);
        //Graphics.setDrawCollisionData(true);


    }

    @Override
    public void initialize() {
        // this gets called whenever the level is restarted
        SoundManager.stopAllPlayingSounds();
        SoundManager.playBackgroundSound("BG");
        Player p = new Player();
        ObjectManager.addGameObject(p);
        //Generator g = new Generator();
        //ObjectManager.addGameObject(g);
        //Player2 o = new Player2();
        //if (InputManager.isTriggered(VK_E)) {
            //ObjectManager.addGameObject(o);
        //}
        Health h = new Health();
        ObjectManager.addGameObject(h);
        GenHealth o = new GenHealth();
        ObjectManager.addGameObject(o);
        wall();

    }

    @Override
    public void update(float v) {
        // this gets called for every game loop
        // v is the amount of time from the last loop
        timer -= v;
        if (timer <= 0) {
            Enemy e = new Enemy();
            ObjectManager.addGameObject(e);
            timer = (7); }
        shootertimer -= v;
        if (shootertimer <= 0) {
            Shooter s = new Shooter();
            ObjectManager.addGameObject(s);
            shootertimer = (15);
        }
            bosstimer -= v;
            if (bosstimer <= 0) {
                Boss b = new Boss();
                ObjectManager.addGameObject(b);
                bosstimer = (40);
            }
        fasttimer -= v;
        if (fasttimer <= 0) {
            FastRobot f = new FastRobot();
            ObjectManager.addGameObject(f);
            fasttimer = (10);
        }
        GunTimer -= v;
        if (GunTimer <= 0) {
            Gunner f = new Gunner();
            ObjectManager.addGameObject(f);
            GunTimer = (20);
        }

            if (InputManager.isTriggered(KeyEvent.VK_R)) {
                GameLevelManager.restartLevel();
            }
            if (InputManager.isPressed(KeyEvent.VK_ESCAPE)) {
                Game.quit();
            }
        }

        @Override
        public void uninitialize () {
            // this gets called when the level is done (memory de-allocation)
            ObjectManager.removeAllObjectsByName("Enemy");
            ObjectManager.removeAllObjectsByName("Player");
            ObjectManager.removeAllObjectsByName("Health");
            ObjectManager.removeAllObjectsByName("Boss");
            ObjectManager.removeAllObjectsByName("Shooter");
            ObjectManager.removeAllObjectsByName("FastRobot");
            ObjectManager.removeAllObjectsByName("Bullet");
            ObjectManager.removeAllObjectsByName("EnemyBullet");
            ObjectManager.removeAllObjectsByName("Gunner");
            ObjectManager.removeAllObjectsByName("BossBullet");
            wall();

        }

    //This starts the wall code
    /***********************************************/
    public void wall() {
        for (int i = 0; i < 7; i++) {
            Wall w = new Wall();
            w.setPosition(i * -187, 700);
            ObjectManager.addGameObject(w);
        }
        for (int i = 0; i < 7; i++) {
            Wall w = new Wall();
            w.setPosition(i * 187, -700);
            ObjectManager.addGameObject(w);
        }
        for (int i = 0; i < 5; i++) {
            Wall w = new Wall();
            w.setPosition(1000, i * 187);
            w.setRotation(w.getRotation() - 90);
            ObjectManager.addGameObject(w);
        }
        for (int i = 0; i < 5; i++) {
            Wall w = new Wall();
            w.setPosition(-1000, i * -187);
            w.setRotation(w.getRotation() + 90);
            ObjectManager.addGameObject(w);
        }
        for (int i = 0; i < 7; i++) {
            Wall w = new Wall();
            w.setPosition(i * -187, -700);
            ObjectManager.addGameObject(w);
        }
        for (int i = 0; i < 7; i++) {
            Wall w = new Wall();
            w.setPosition(i * 187, 700);
            ObjectManager.addGameObject(w);
        }
        for (int i = 0; i < 5; i++) {
            Wall w = new Wall();
            w.setPosition(1000, i * -187);
            w.setRotation(w.getRotation() - 90);
            ObjectManager.addGameObject(w);
        }
        for (int i = 0; i < 5; i++) {
            Wall w = new Wall();
            w.setPosition(-1000, i * 187);
            w.setRotation(w.getRotation() + 90);
            ObjectManager.addGameObject(w);
        }
    }
}
