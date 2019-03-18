import edu.digipen.gameobject.GameObject;
import edu.digipen.gameobject.ObjectManager;
import edu.digipen.math.Vec2;

import java.util.Random;

public class Boss extends GameObject {

    int i = 0;
    //public int e = 0;
    //enemy's speed
    public float movementspeed = 1.5f;
    public float timer = 5;

    // default constructor for enemy
    public Boss() {
        super("Boss", 330, 310, "Boss.png");
        setPositionX(getRandomValue(0, 1500));
        setPositionY(getRandomValue(0, 1500));
        setCircleCollider(150);
        setMass(1.0f);
    }

    public void update(float dt) {

        // figure out where the player is
        // get the player (and the players position)
        GameObject player = ObjectManager.getGameObjectByName("Player");

        if(player != null) {

            // calculate direction
            Vec2 direction = new Vec2();
            direction.setX(player.getPositionX() - getPositionX());
            direction.setY(player.getPositionY() - getPositionY());

            //control the speed of the enemy
            direction.normalize();

            //move the enemy toward the player
            setPositionX(getPositionX() + direction.getX() * movementspeed);
            setPositionY(getPositionY() + direction.getY() * movementspeed);

            // point towards the player
            setRotation((float) Math.toDegrees(Math.atan2(direction.getY(), direction.getX())));
            timer -= dt;
            if (timer <= 0) {
                fire(direction);
                timer = (3); }
        }

        PhysicsResolution.resolveContacts(dt);
    }

    /**
     * Returns a random value between min a
     * nd max
     *
     * @param min The lowest value it can return
     * @param max The highest value it can return
     * @return A random number between min and max
     */
    float getRandomValue(float min, float max) {
        Random r = new Random();
        return r.nextFloat() * (max - min) + min;
    }

    public void collisionReaction(GameObject collidedWith) {
        if (collidedWith.getName().equals("Wall")) {
            PhysicsResolution.addContact(this, collidedWith);
        }
        if(collidedWith.getName().equals("Enemy") || (collidedWith.getName().equals("FastRobot") || (collidedWith.getName().equals("Shooter") || (collidedWith.getName().equals("Gunner"))))) {
            PhysicsResolution.addContact(this, collidedWith);
            //setPosition(200, 200);
        } //kill();

        if (collidedWith.getName().equals("Bullet")) {
            i++;
            System.out.println("BOSS HEALTH: " + i);
            if (i == 200) {
                kill();
                //e++;
                //System.out.println("INTEGER E IS " + e);
            }
            //if(e == 7);
            //{
            //qGameLevelManager.goToLevel(new Level2());
            //}
        }

    }
    public void fire(Vec2 direction_)
    {
        ObjectManager.addGameObject(new BossBullet(direction_, getPosition()));
    }
}