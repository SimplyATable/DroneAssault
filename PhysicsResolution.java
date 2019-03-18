/******************************************************************************/
/*!
\file   PhysicsResolution.java
\author Daniel Ospina
\par    email: daniel.ospina\@digipen.edu
\par    DigiPen login: daniel.ospina
\par    Course: VP1
\par    Physics Development
\date   7/26/2016
\brief
  This file contains functions for Physics Resolution.

    -

*/
/******************************************************************************/


import edu.digipen.gameobject.GameObject;
import edu.digipen.math.Vec2;

public class PhysicsResolution
{
    private static ContactSet contacts = new ContactSet();
    private static boolean AccelerationBuildUp = true;

    final static float PenetrationEpsilon = 0.2f;
    final static float PenetrationResolvePercentage = 0.8f;

    /**************************************************************************/
      /*!
      \brief
        Adds a BodyContact to the ContactSet

      \param a
        The first Game Object to be used in making the BodyContact

      \param b
        The second Game Object to be used in making the BodyContact

      \param restitution
        The amount of energy that should be saved from the collision

      */
    /**************************************************************************/
    public static void addContact(GameObject a, GameObject b, float
            restitution)
    {
        contacts.addContact(new BodyContact(a, b, restitution));
    }



    /**************************************************************************/
      /*!
      \brief
        Adds a BodyContact to the ContactSet, defaults restitution to 0

      \param a
        The first Game Object to be used in making the BodyContact

      \param b
        The second Game Object to be used in making the BodyContact

      */
    /**************************************************************************/
    public static void addContact(GameObject a, GameObject b)
    {
        contacts.addContact(new BodyContact(a, b, 0f));
    }


    /**************************************************************************/
      /*!
      \brief
        Resolves the velocities in a contact

      \param bc
        The contact to resolve the velocity of

      \param dt
        The time since the last frame

      */
    /**************************************************************************/
    private static void resolveContactVelocity(BodyContact bc, float dt)
    {
        float separatingVelocity = bc.calculateSeparatingVelocity();
        float newSeparatingVelocity = -separatingVelocity * bc.Restitution;

        /*
        if(AccelerationBuildUp)
        {
            Vec2 accCausedVelocity = Vec2Math.subtract(bc.Bodies[0].getAcceleration(), bc.Bodies[1].getAcceleration());
            float accCausedSepVelocity = Vec2Math.dot(accCausedVelocity, bc.ContactNormal) * dt;

            if(accCausedSepVelocity < 0)
            {
                newSepVelocity += bc.Restitution * accCausedSepVelocity;

                if(newSepVelocity < 0)
                    newSepVelocity = 0;
            }
        }

         */


        float deltaVelocity = newSeparatingVelocity - separatingVelocity;

        float totalInverseMass = bc.getInverseMass(0) + bc.getInverseMass(1);

        float impulse = deltaVelocity / totalInverseMass;

        bc.contactImpulse = impulse;

        Vec2 impulsePerIMass = new Vec2(bc.cData.Normal);
        impulsePerIMass.scale(impulse);

        bc.Bodies[0].setVelocity(Vec2Math.add(bc.Bodies[0].getVelocity(), Vec2Math.getScaled(impulsePerIMass, bc
                .getInverseMass(0))));

        bc.Bodies[1].setVelocity(Vec2Math.add(bc.Bodies[1].getVelocity(), Vec2Math.getScaled(impulsePerIMass, -bc
                .getInverseMass(1))));
    }

    /**************************************************************************/
      /*!
      \brief
        Resolve the penetration of a BodyContact

      \param bc
        The BodyContact to resolve the penetration of

      \param dt
        The time since the last frame

      */
    /**************************************************************************/
    private static void ResolvePenetration(BodyContact bc, float dt)
    {

        float totalInverseMass = bc.getInverseMass(0) + bc.getInverseMass(1);

        Vec2 movePerIMass = Vec2Math.getScaled(bc.cData.Normal, (bc.cData.Penetration / totalInverseMass));
        movePerIMass.scale(PenetrationResolvePercentage);

        bc.Movement[0] = Vec2Math.getScaled(movePerIMass, bc.getInverseMass(0));
        bc.Movement[1] = Vec2Math.getScaled(movePerIMass, -bc.getInverseMass(1));

        bc.Objects[0].setPosition(Vec2Math.add(bc.Objects[0].getPosition(), bc.Movement[0]));
        bc.Objects[1].setPosition(Vec2Math.add(bc.Objects[1].getPosition(), bc.Movement[1]));
    }

    /**************************************************************************/
      /*!
      \brief


      \param

      \return

      */
    /**************************************************************************/
    private static void resolvePositions(float dt)
    {
        int iterationsRun = 0;
        int maxIterations = contacts.NumberOfContacts * 5;


        while(iterationsRun < maxIterations)
        {
            // Find the contact with the current maximum penetration
            float maxPenetration = 0;
            int contactIndex = contacts.NumberOfContacts;
            for(int i = 0; i < contacts.NumberOfContacts; ++i)
            {
                BodyContact bc = contacts.getContact(i);

                if(bc.cData.Penetration > maxPenetration)
                {
                    maxPenetration = bc.cData.Penetration;
                    contactIndex = i;
                }
            }

            // If the None of the contacts had a large enough penetration value
            if(contactIndex == contacts.NumberOfContacts)
                break;  // Break out

            BodyContact bc = contacts.getContact(contactIndex);


            // Resolve the penetration for that contact
            ResolvePenetration(bc, dt);

            // Get the movement vectors for the contact
            Vec2[] movement = contacts.getContact(contactIndex).Movement;

            // Update the penetrations for all related contacts
            for(int i = 0; i < contacts.NumberOfContacts; ++i)
            {
                // If the first body in this contact is one of the 2 involved in the above contact
                if(contacts.getContact(i).Bodies[0] == contacts.getContact(contactIndex).Bodies[0])
                {
                    contacts.getContact(i).cData.Penetration -= Vec2Math.dot(movement[0], contacts.getContact(i)
                            .cData.Normal);
                }
                else if(contacts.getContact(i).Bodies[0] == contacts.getContact(contactIndex).Bodies[1])
                {
                    contacts.getContact(i).cData.Penetration -= Vec2Math.dot(movement[1], contacts.getContact(i)
                            .cData.Normal);
                }

                // If the second body in this contact is one of the 2 involved in the above contact
                if(contacts.getContact(i).Bodies[1] == contacts.getContact(contactIndex).Bodies[0])
                {
                    contacts.getContact(i).cData.Penetration += Vec2Math.dot(movement[0], contacts.getContact(i)
                            .cData.Normal);
                }
                else if(contacts.getContact(i).Bodies[1] == contacts.getContact(contactIndex).Bodies[1])
                {
                    contacts.getContact(i).cData.Penetration += Vec2Math.dot(movement[1], contacts.getContact(i)
                            .cData.Normal);
                }
            }

            ++iterationsRun;
        }
    }

    /**************************************************************************/
      /*!
      \brief


      \param

      \return

      */
    /**************************************************************************/
    private static void resolveVelocities(float dt)
    {
        int iterationsRun = 0;
        int maxIterations = contacts.NumberOfContacts * 5;

        while(iterationsRun < maxIterations)
        {
            float maxVelocity = Float.MAX_VALUE;
            int contactIndex = contacts.NumberOfContacts;

            for(int i = 0; i <contacts.NumberOfContacts; ++i)
            {
                float sepVel = contacts.getContact(i).calculateSeparatingVelocity();
                if(sepVel < 0 && sepVel < maxVelocity)
                {
                    maxVelocity = sepVel;
                    contactIndex = i;
                }
            }

            if(contactIndex == contacts.NumberOfContacts)
                break;

            resolveContactVelocity(contacts.getContact(contactIndex), dt);

            ++iterationsRun;
        }
    }

    public static void resolveContacts(float dt)
    {
        if(contacts.NumberOfContacts > 0)
        {
            resolvePositions(dt);
            resolveVelocities(dt);
            contacts.reset();
        }
    }

    public static void setAccelerationBuildUp(boolean b)
    {
        AccelerationBuildUp = b;
    }
}
