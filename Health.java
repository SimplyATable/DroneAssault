import edu.digipen.gameobject.GameObject;

public class Health extends GameObject {

    public Health() {
        super("Health", 60, 3, "RobotHealth.png");
        setPositionX(0);
        setPositionY(55);
    }

    public void update(float dt) {

    }
}