/******************************************************************************/
/*!
\file   Vec2Math.java
\author Daniel Ospina
\par    email: daniel.ospina\@digipen.edu
\par    DigiPen login: daniel.ospina
\par    Course: VP1
\par    Physics Development
\date   7/26/2016
\brief
    Wrapper around the Vec2 class for Math functions. Only implemented because
    of lack of dot product function and to have a scale function that returns
    the scaled version of the Vec2 without affecting the original.

*/
/******************************************************************************/


import edu.digipen.math.Vec2;

public class Vec2Math
{
    public static Vec2 add(Vec2 a, Vec2 b)
    {
        return Vec2.add(a, b);
    }

    public static Vec2 subtract(Vec2 a, Vec2 b)
    {
        return Vec2.subtract(a, b);
    }

    public static float dot(Vec2 a, Vec2 b)
    {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    public static Vec2 getScaled(Vec2 v, float scalar)
    {
        Vec2 scaled = new Vec2(v);
        scaled.scale(scalar);

        return scaled;
    }

    public static void scale(Vec2 v, float scalar)
    {
        v.scale(scalar);
    }

    public static String Vec2String(Vec2 v)
    {
        String toString = "< " + v.getX() + ", " + v.getY() + ">";
        return toString;
    }
}
