import edu.digipen.gameobject.GameObject;
import edu.digipen.math.Vec2;

public class BossBullet extends GameObject {

    public Vec2 direction;
    public float bulletSpeed = 6.5f;

    // custom constructor for the bullet
    public BossBullet(Vec2 direction_, Vec2 position_) {
        super("BossBullet", 30, 30, "particle.png");
        setCircleCollider(15);
        direction = new Vec2(direction_);
        setPosition(position_);
    }

    public void update(float dt)
    {
        direction.normalize();
        setPositionX(getPositionX() + direction.getX()*bulletSpeed);
        setPositionY(getPositionY() + direction.getY()*bulletSpeed);

        //System.out.println("Direction" + direction.getX() + " " + direction.getY());

        if(!isInViewport())
        {
            kill(); // omg
            //System.out.println("Viewport bug");
            bulletSpeed = 6.0f;
        }
    }

    public void collisionReaction(GameObject collidedWith)
    {
        if(collidedWith.getName().equals("Enemy") || (collidedWith.getName().equals("Boss")  || (collidedWith.getName().equals("EnemyBullet") || (collidedWith.getName().equals("FastRobot") || (collidedWith.getName().equals("GunnerBullet") || (collidedWith.getName().equals("Shooter") || (collidedWith.getName().equals("Gunner")))))))) {
    // Nothing here yet
        }
        if (collidedWith.getName().equals("Wall") || (collidedWith.getName().equals("Player")))
        {
            kill();
            bulletSpeed = 6.0f;        }
    }
}
