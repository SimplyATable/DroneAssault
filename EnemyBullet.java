import edu.digipen.gameobject.GameObject;
import edu.digipen.math.Vec2;

public class EnemyBullet extends GameObject {

    public Vec2 direction;
    public float bulletSpeed = 10.0f;

    // custom constructor for the bullet
    public EnemyBullet(Vec2 direction_, Vec2 position_) {
        super("EnemyBullet", 15, 15, "particle.png");
        setCircleCollider(7);
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
        }
    }

    public void collisionReaction(GameObject collidedWith)
    {
        if(collidedWith.getName().equals("Enemy") || (collidedWith.getName().equals("Boss"))) {
            kill();
        }
        if (collidedWith.getName().equals("Wall") || (collidedWith.getName().equals("Player")))
        {
            //System.out.println("I collided with an player or wall");
            kill();
        }
    }
}
