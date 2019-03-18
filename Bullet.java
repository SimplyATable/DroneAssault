import edu.digipen.gameobject.GameObject;
import edu.digipen.math.Vec2;

public class Bullet extends GameObject {

    public Vec2 direction;
    public float bulletSpeed = 8.0f;

    // custom constructor for the bullet
    public Bullet(Vec2 direction_, Vec2 position_) {
        super("Bullet", 15, 15, "Bullet.png");
        setCircleCollider(7);
        direction = new Vec2(direction_);
        setPosition(position_);
    }

    public void update(float dt)
    {
        setPositionX(getPositionX() + direction.getX()*bulletSpeed);
        setPositionY(getPositionY() + direction.getY()*bulletSpeed);

        if(!isInViewport())
        {
            kill();
        }
    }

    public void collisionReaction(GameObject collidedWith)
    {
        if(collidedWith.getName().equals("Enemy") || (collidedWith.getName().equals("Boss")  || (collidedWith.getName().equals("EnemyBullet") || (collidedWith.getName().equals("FastRobot") || (collidedWith.getName().equals("Shooter") || (collidedWith.getName().equals("Gunner"))))))) {
            kill();
        }
        if (collidedWith.getName().equals("Wall"))
        {
            kill();
        }
    }
}
