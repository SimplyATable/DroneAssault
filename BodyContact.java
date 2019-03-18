
/******************************************************************************/
/*!
\file   BodyContact.java
\author Daniel Ospina
\par    email: daniel.ospina\@digipen.edu
\par    DigiPen login: daniel.ospina
\par    Course: VP1
\par    Physics Development
\date   7/26/2016
\brief
    Class for containing information on Body Contacts

*/
/******************************************************************************/

import edu.digipen.gameobject.GameObject;
import edu.digipen.math.Vec2;
import edu.digipen.physics.PhysicsSystem;

public class BodyContact
{
    public GameObject[] Objects = new GameObject[2];
    public PhysicsSystem.RigidBody[] Bodies = new PhysicsSystem.RigidBody[2];
    public Vec2[] Movement = new Vec2[2];
    public CollisionData cData;
    public float Restitution;
    public float contactImpulse;

    public BodyContact(GameObject a, GameObject b, float restitution)
    {
        // This was added when I realized the position information is kept
        // within the GameObject itself instead of in the RigidBodies
        Objects[0] = a;
        Objects[1] = b;

        Bodies[0] = a.getBody();
        Bodies[1] = b.getBody();
        cData = CollisionData.getCollisionData(a, b);
        Restitution = restitution;
        contactImpulse = 0;
    }

    public float calculateSeparatingVelocity()
    {
        //Main.debugPrintln("Calculating Separating Velocity");

        Vec2 relativeVelocity = Vec2.subtract(Bodies[0].getVelocity(),
                Bodies[1].getVelocity());

        //Main.debugPrintln("\tRelative Velocity: " + Vec2Math.Vec2String(relativeVelocity));
        //Main.debugPrintln("\tNormal Vector: " + Vec2Math.Vec2String(cData.Normal));;
        //Main.debugPrintln("\tSeparating Velocity Value: " + Vec2Math.dot(relativeVelocity, cData.Normal));

        return Vec2Math.dot(relativeVelocity, cData.Normal);
    }

    public float getInverseMass(int body)
    {
        float mass = Bodies[body].getMass();

        if(mass > 0)
        {
            return 1.0f / mass;
        }

        return 0.0f;
    }
}
