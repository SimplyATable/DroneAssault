/******************************************************************************/
/*!
\file   CollisionData.java
\author Daniel Ospina
\par    email: daniel.ospina\@digipen.edu
\par    DigiPen login: daniel.ospina
\par    Course: VP1
\par    Physics Development
\date   7/26/2016
\brief
    - getCollisionData: Given 2 Game Objects that have collided, gets the data
        involved in the collision (The normal vector and the penetration).
        Refers to other functions based on the collider types.

    - circleToCircleCollision: Given 2 Game Objects with circle colliders,
        returns the appropriate collision data.

    - rectangleToRectangleCollision: Given 2 Game Objects with rectangle
        colliders, returns the appropriate collision data.

    - circleToRectangleCollision: Given a Game Object with a circle collider and
        another Game Object with a rectangle collider (order does not matter),
        returns the appropriate collision data.


*/
/******************************************************************************/

import edu.digipen.gameobject.GameObject;
import edu.digipen.math.Vec2;
import edu.digipen.physics.Collider;

public class CollisionData
{
    public Vec2 Normal = new Vec2();
    public float Penetration = 0.0f;

    /**************************************************************************/
      /*!
      \brief
        Given 2 Game Objects that have collided, calls one of 3 helper functions
        to return the appropriate collision data.

      \param a
        The first Game Object

      \param b
        The second Game Object

      \return
        A CollisionData object containing the normal vector and the penetration
        value for the collision.

      */
    /**************************************************************************/
    public static CollisionData getCollisionData(GameObject a, GameObject b)
    {
        // If either one of the objects does not have a collider
        if(a == null || b == null || a.getCollider() == null || b.getCollider() == null)
        {
            // Return the default collision data
            return new CollisionData();
        }

        // If they are both circle colliders
        if(a.getCollider().getType() == Collider.ColliderType.E_CIRCLE_COLLIDER
                && b.getCollider().getType() == Collider.ColliderType.E_CIRCLE_COLLIDER)
        {
            // Calculate using circle to circle collision methods
            return circleToCircleCollision(a, b);
        }

        // If they are both rectangle colliders
        else if(a.getCollider().getType() == Collider.ColliderType.E_RECTANGLE_COLLIDER
                && b.getCollider().getType() == Collider.ColliderType.E_RECTANGLE_COLLIDER)
        {
            // Calculate using rectangle to rectangle collision methods
            return rectangleToRectangleCollision(a, b);
        }

        // If they are a mix of the two
        else
        {
            // Calculate using circle to rectangle collision methods
            return circleToRectangleCollision(a, b);
        }
    }

    /**************************************************************************/
      /*!
      \brief
        Given 2 Game Objects with circle colliders that have collided, uses
        circle-to-circle collision detection to calculate the normal vector
        for the collision and the penetration value of the collision.

      \param a
        The first Game Object

      \param b
        The second Game Object

      \return
        A CollisionData object containing the normal vector and the penetration
        value for the collision.

      */
    /**************************************************************************/
    private static CollisionData circleToCircleCollision(GameObject a, GameObject b)
    {
        // Create a Collision Data object for when we return
        CollisionData cd = new CollisionData();

        // Find the contact normal by just subtracting their positions
        Vec2 normal = Vec2.subtract(a.getPosition(), b.getPosition());

        // Find out the total radius and the distance between them to calculate the penetration
        float totalRadius = a.getCollider().getRadius() + b.getCollider().getRadius();
        cd.Penetration = totalRadius - normal.getLength();

        // Normalize the contact normal
        normal.normalize();
        cd.Normal = new Vec2(normal);

        // Return the collision data
        return cd;
    }

    /**************************************************************************/
      /*!
      \brief
        Given 2 Game Objects with rectangle colliders that have collided, uses
        rectangle-to-rectangle collision detection to calculate the normal
        vector for the collision and the penetration value of the collision.

      \param a
        The first Game Object

      \param b
        The second Game Object

      \return
        A CollisionData object containing the normal vector and the penetration
        value for the collision.

      */
    /**************************************************************************/
    private static CollisionData rectangleToRectangleCollision(GameObject a, GameObject b)
    {
        // Create a Collision Data object for when we return
        CollisionData cd = new CollisionData();

        // Get a vector for the difference in positions and for the normal
        Vec2 dPos = Vec2.subtract(b.getPosition(), a.getPosition());
        Vec2 normal;

        // Create a float for the penetration and then calculate the

        float penetration;
        float xDiff = (a.getCollider().getWidth() / 2 + b.getCollider().getWidth() / 2) - Math.abs(dPos.getX());
        float yDiff = (a.getCollider().getHeight() / 2 + b.getCollider().getHeight() / 2) - Math.abs(dPos.getY());

        if(xDiff < yDiff)
        {
            normal = (dPos.getX() < 0) ? new Vec2(1, 0) : new Vec2(-1, 0);

            penetration = xDiff;
        }
        else
        {
            normal = (dPos.getY() < 0) ? new Vec2(0, 1) : new Vec2(0, -1);
            penetration = yDiff;
        }

        cd.Normal = new Vec2(normal);
        cd.Penetration = penetration;

        return cd;
    }

    /**************************************************************************/
      /*!
      \brief
        Given a Game Object with a circle collider and an object with a
        rectangle collider (order does not matter), calculates the nomral
        vector and penetration value of the collision.

      \param a
        The first Game Object

      \param b
        The second Game Object

      \return
        A CollisionData object containing the normal vector and the penetration
        value for the collision.

      */
    /**************************************************************************/
    // Lots of Math, much cryptic, does work though
    private static CollisionData circleToRectangleCollision(GameObject a, GameObject b)
    {
        CollisionData cd = new CollisionData();
        Vec2 p, minV, maxV, q, normal;
        float radius, penetration;

        // If a is the circle collider
        if(a.getCollider().getType() == Collider.ColliderType.E_CIRCLE_COLLIDER)
        {
            radius = a.getCollider().getRadius();
            p = new Vec2(a.getPosition());
            minV = new Vec2(b.getPositionX() - b.getCollider().getWidth() / 2,
                    b.getPositionY() - b.getCollider().getHeight() / 2);

            maxV = new Vec2(b.getPositionX() + b.getCollider().getWidth() / 2,
                    b.getPositionY() + b.getCollider().getHeight() / 2);

            q = new Vec2(p);

            if(q.getX() < minV.getX())
                q.setX(minV.getX());
            else if(q.getX() > maxV.getX())
                q.setX(maxV.getX());

            if(q.getY() < minV.getY())
                q.setY(minV.getY());
            else if(q.getY() > maxV.getY())
                q.setY(maxV.getY());

            Vec2 delta = Vec2.subtract(p, q);
            float disSq = delta.getLengthSquared();

            if(disSq == 0.0f)
            {
                Vec2 bdelta = Vec2.subtract(a.getPosition(), b.getPosition());

                float xd = radius + b.getCollider().getWidth() / 2 - Math.abs(bdelta.getX());
                float yd = radius + b.getCollider().getHeight() / 2 - Math.abs(bdelta.getY());

                if(xd < yd)
                {
                    normal = bdelta.getX() < 0 ? new Vec2(-1, 0) : new Vec2(1, 0);
                    penetration = xd;
                }
                else
                {
                    normal = bdelta.getY() < 0 ? new Vec2(0, -1) : new Vec2(0, 1);
                    penetration = yd;
                }
            }
            else
            {
                normal = new Vec2(delta);
                normal.normalize();

                float dis = delta.getLength();
                penetration = -(dis - radius);
            }
        }

        // Otherwise if b is the circle collider
        else
        {
            radius = b.getCollider().getRadius();
            p = new Vec2(b.getPosition());
            minV = new Vec2(a.getPositionX() - a.getCollider().getWidth() / 2,
                    a.getPositionY() - a.getCollider().getHeight() / 2);

            maxV = new Vec2(a.getPositionX() + a.getCollider().getWidth() / 2,
                    a.getPositionY() + a.getCollider().getHeight() / 2);

            q = new Vec2(p);

            if(q.getX() < minV.getX())
                q.setX(minV.getX());
            else if(q.getX() > maxV.getX())
                q.setX(maxV.getX());

            if(q.getY() < minV.getY())
                q.setY(minV.getY());
            else if(q.getY() > maxV.getY())
                q.setY(maxV.getY());

            Vec2 delta = Vec2.subtract(p, q);
            float disSq = delta.getLengthSquared();

            if(disSq == 0.0f)
            {
                Vec2 adelta = Vec2.subtract(b.getPosition(), a.getPosition());

                float xd = radius + a.getCollider().getWidth() / 2 - Math.abs(adelta.getX());
                float yd = radius + a.getCollider().getHeight() / 2 - Math.abs(adelta.getY());

                if(xd < yd)
                {
                    normal = adelta.getX() < 0 ? new Vec2(-1, 0) : new Vec2(1, 0);
                    penetration = xd;
                }
                else
                {
                    normal = adelta.getY() < 0 ? new Vec2(0, -1) : new Vec2(0, 1);
                    penetration = yd;
                }
            }
            else
            {
                normal = new Vec2(delta);
                normal.normalize();
                normal.negate();

                float dis = delta.getLength();
                penetration = -(dis - radius);
            }
        }

        cd.Normal = new Vec2(normal);
        cd.Penetration = penetration;

        return cd;
    }
}
