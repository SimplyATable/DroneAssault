import edu.digipen.gameobject.GameObject;
import edu.digipen.gameobject.ObjectManager;
import edu.digipen.math.Vec2;

import java.util.Random;

public class Enemy extends GameObject {

    int i = 0;
    //public int e = 0;
    //enemy's speed
    public float movementspeed = 1.0f;

    // default constructor for enemy
    public Enemy() {
        super("Enemy", 100, 100, "Robot.png");
        setPositionX(getRandomValue(0, 1500));
        setPositionY(getRandomValue(0, 1500));
        setCircleCollider(35);
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
        if(collidedWith.getName().equals("Boss")  || (collidedWith.getName().equals("FastRobot") || (collidedWith.getName().equals("Shooter") || (collidedWith.getName().equals("Gunner"))))) {
            PhysicsResolution.addContact(this, collidedWith);
                //setPosition(200, 200);
        } //kill();

        if (collidedWith.getName().equals("Bullet")) {
            i++;
            System.out.println("ENEMY HEALTH: " + i);
            if (i == 15) {
                kill();
                //e++;

            }
            if (collidedWith.getName().equals("Generator")) {
                    kill();
                }
            //if(e == 7);
            //{
                //qGameLevelManager.goToLevel(new Level2());
            //}
        }

    }
}