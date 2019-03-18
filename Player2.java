/*
public class Player2 extends GameObject {

    // keep track of the ships direction
    public float timer = 3;
    public float guntimer = 10;
    int h = 30;
    GameObject bar = ObjectManager.getGameObjectByName("Health");

    // default constructor for player - ship
    public Player2() {
        super("Player", 100, 100, "BlueRobot.png");
        setCircleCollider(40);
        //The player's mass
        setMass(0.1f);
        h = 50;
    }
    // player's update function
    // dt is the last time since last game loop
    public void update(float dt) {

        Graphics.setCameraPosition(getPosition());
        if (InputManager.isPressed(KeyEvent.VK_W)) {
            setPositionY(getPositionY() + 3);
            setRotation(90.0f);
        }
        if (InputManager.isPressed(KeyEvent.VK_S)) {
            setPositionY(getPositionY() - 3);
            setRotation(260.0f);

            float rotation = (float) Math.toRadians(getRotation());
        }
        if (InputManager.isPressed(KeyEvent.VK_A)) {
            setPositionX(getPositionX() - 3);
            setRotation(180.0f);
        }
        if (InputManager.isPressed(KeyEvent.VK_D)) {
            setPositionX(getPositionX() + 3);
        }
        if(guntimer > 0) {
            guntimer -= dt;
        }
        if (guntimer <= 0) {
            if (InputManager.isPressed(VK_Q)) {
                //if(InputManager.isTriggered(VK_UP))
                if (InputManager.isPressed(KeyEvent.VK_UP)) {
                    shoot(new Vec2(0, 1));
                }
                //if(InputManager.isTriggered(VK_LEFT))
                if (InputManager.isPressed(KeyEvent.VK_LEFT)) {
                    shoot(new Vec2(-1, 0));
                }
                //if(InputManager.isTriggered(KeyEvent.VK_DOWN))
                if (InputManager.isPressed(KeyEvent.VK_DOWN)) { //yes
                    shoot(new Vec2(0, -1));
                }
                //if(InputManager.isTriggered(VK_RIGHT))
                if (InputManager.isPressed(KeyEvent.VK_RIGHT)) {
                    shoot(new Vec2(1, 0));
                }
                guntimer -= dt;
                if (guntimer <= -4) {
                    guntimer = (10);
                }
            }
        }
        if (InputManager.isTriggered(VK_UP))
        {
            shoot(new Vec2(0, 1));
        }
        if (InputManager.isTriggered(VK_LEFT))
        {
            shoot(new Vec2(-1, 0));
        }
        if (InputManager.isTriggered(KeyEvent.VK_DOWN))
        {
            shoot(new Vec2(0, -1));
        }
        if (InputManager.isTriggered(VK_RIGHT))
        {
            shoot(new Vec2(1, 0));
        }
        if (InputManager.isTriggered(VK_UP) && InputManager.isPressed(VK_RIGHT))
        {
            shoot(new Vec2(1, 1));
        }
        if (InputManager.isTriggered(VK_RIGHT) && InputManager.isPressed(VK_DOWN))
        {
            shoot(new Vec2(1, -1));
        }
        if (InputManager.isTriggered(VK_DOWN) && InputManager.isPressed(VK_LEFT))
        {
            shoot(new Vec2(-1, -1));
        }
        if (InputManager.isTriggered(VK_LEFT) && InputManager.isPressed(VK_UP))
        {
            shoot(new Vec2(-1, 1));
        }
        timer -= dt;
        if (timer <= 0) {
            if (InputManager.isTriggered(VK_E))
            {
                {
                    shoot(new Vec2(0, 1));
                    shoot(new Vec2(-1, 0));
                    shoot(new Vec2(0, -1));
                    shoot(new Vec2(1, 0));
                    shoot(new Vec2(1, 1));
                    shoot(new Vec2(1, -1));
                    shoot(new Vec2(-1, -1));
                    shoot(new Vec2(-1, 1));
                    shoot(new Vec2(0, 2));
                    shoot(new Vec2(-2, 0));
                    shoot(new Vec2(0, -2));
                    shoot(new Vec2(2, 0));
                    shoot(new Vec2(2, 2));
                    shoot(new Vec2(2, -2));
                    shoot(new Vec2(-2, -2));
                    shoot(new Vec2(-2, 2));
                    shoot(new Vec2(1, 2));
                    shoot(new Vec2(-1, 2));
                    shoot(new Vec2(-1, -2));
                    shoot(new Vec2(1, -2));
                    shoot(new Vec2(-2, 1));
                    shoot(new Vec2(2, 1));
                    shoot(new Vec2(-2, -1));
                    shoot(new Vec2(2, -1));
                    shoot(new Vec2(-3, 1));
                    shoot(new Vec2(3, 1));
                    shoot(new Vec2(-3, -1));
                    shoot(new Vec2(3, -1));
                    shoot(new Vec2(1, 3));
                    shoot(new Vec2(-1, 3));
                    shoot(new Vec2(-1, -3));
                    shoot(new Vec2(1, -3));
                    shoot(new Vec2(-3, 2));
                    shoot(new Vec2(3, 2));
                    shoot(new Vec2(-3, -2));
                    shoot(new Vec2(3, -2));
                    shoot(new Vec2(2, 3));
                    shoot(new Vec2(-2, 3));
                    shoot(new Vec2(-2, -3));
                    shoot(new Vec2(2, -3));
                    shoot(new Vec2(0, 3));
                    shoot(new Vec2(-3, 0));
                    shoot(new Vec2(0, -3));
                    shoot(new Vec2(3, 0));
                    shoot(new Vec2(3, 3));
                    shoot(new Vec2(3, -3));
                    shoot(new Vec2(-3, -3));
                    shoot(new Vec2(-3, 3));
                    shoot(new Vec2(2, 3));
                    shoot(new Vec2(-2, 3));
                    shoot(new Vec2(-2, -3));
                    shoot(new Vec2(2, -3));
                    shoot(new Vec2(-3, 2));
                    shoot(new Vec2(3, 2));
                    shoot(new Vec2(-3, -2));
                    shoot(new Vec2(3, -2));
                    shoot(new Vec2(-4, 2));
                    shoot(new Vec2(4, 2));
                    shoot(new Vec2(-4, -2));
                    shoot(new Vec2(4, -2));
                    shoot(new Vec2(2, 4));
                    shoot(new Vec2(-2, 4));
                    shoot(new Vec2(-2, -4));
                    shoot(new Vec2(2, -4));
                    shoot(new Vec2(-4, 3));
                    shoot(new Vec2(4, 3));
                    shoot(new Vec2(-4, -3));
                    shoot(new Vec2(4, -3));
                    shoot(new Vec2(3, 4));
                    shoot(new Vec2(-3, 4));
                    shoot(new Vec2(-3, -4));
                    shoot(new Vec2(3, -4));

                    timer = (3); }
            }

            PhysicsResolution.resolveContacts(dt);

            bar = ObjectManager.getGameObjectByName("Health");
            bar.setPosition(getPositionX(), getPositionY() + -700);
        }
    }

    public void collisionReaction(GameObject collidedWith)
    {
        if(collidedWith.getName().equals("Enemy") || (collidedWith.getName().equals("Boss")  || (collidedWith.getName().equals("EnemyBullet") || (collidedWith.getName().equals("FastRobot") || (collidedWith.getName().equals("GunnerBullet") || (collidedWith.getName().equals("Shooter") || (collidedWith.getName().equals("Gunner")))))))) {
            h--;
            //bar = ObjectManager.getGameObjectByName("Health");
            //bar.setScaleX(0.01f*h);
            System.out.println("PLAYER HEALTH: " + h);
            PhysicsResolution.addContact(this, collidedWith);
        }
        if(collidedWith.getName().equals("BossBullet")) {
            h--;
            h--;
            h--;
            //bar = ObjectManager.getGameObjectByName("Health");
            //bar.setScaleX(0.01f*h);
            System.out.println("PLAYER HEALTH: " + h);
        }

        if(collidedWith.getName().equals("Wall") || (collidedWith.getName().equals("Player"))) {
            PhysicsResolution.addContact(this, collidedWith);
        }
        if(h == 0) {
            GameLevelManager.goToLevel(new EndGame());
        }
    }
    public void shoot(Vec2 direction_)
    {
        ObjectManager.addGameObject(new Bullet(direction_, getPosition()));
    }
}
*/