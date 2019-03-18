import edu.digipen.gameobject.GameObject;

public class Wall extends GameObject {
    int i = 0;
    public Wall() {
        super("Wall", 187, 187, "Wall.png");
        setRectangleCollider(90, 90);

        setMass(0.0f);
    }
        public void collisionReaction(GameObject collidedWith) {
        if (collidedWith.getName().equals("Bullet")) {
            i++;
            System.out.println("ENEMY HEALTH: " + i);
            if (i == 25) {
                kill();
            }
        }
    }
}