/******************************************************************************/
/*!
\file   ContactSet.java
\author Daniel Ospina
\par    email: daniel.ospina\@digipen.edu
\par    DigiPen login: daniel.ospina
\par    Course: VP1
\par    Physics Development
\date   7/26/2016
\brief
    A wrapper around an ArrayList of BodyContacts

*/
/******************************************************************************/

import java.util.ArrayList;

public class ContactSet
{
    ArrayList<BodyContact> BodyContacts = new ArrayList<BodyContact>();
    public int NumberOfContacts = 0;
    //private int CurrentContact = 0;
    private int MaxContacts = Integer.MAX_VALUE;

    /**************************************************************************/
      /*!
      \brief
        Adds a BodyContact to the ArrayList of BodyContacts

      \param bc
        The BodyContact to add

      */
    /**************************************************************************/
    public void addContact(BodyContact bc)
    {
        BodyContacts.add(bc);
        ++NumberOfContacts;
    }

    /**************************************************************************/
      /*!
      \brief
        Gets the next contact in the ArrayList

      \return
        The Next BodyContact in the ArrayList. Asserts that the next index is a
        valid index

      */
    /**************************************************************************/
    /*public BodyContact getNextContact()
    {
        boolean validIndex = (CurrentContact < NumberOfContacts) &&
                (CurrentContact < MaxContacts) && (CurrentContact > 0);
        assert validIndex;
        return BodyContacts.get(CurrentContact++);
    }*/

    public BodyContact getContact(int i)
    {
        boolean validIndex = (i < NumberOfContacts) && (i < MaxContacts) && (i > 0);
        assert validIndex;
        return BodyContacts.get(i);
    }

    /**************************************************************************/
      /*!
      \brief
        Resets the ContactSet

      */
    /**************************************************************************/
    public void reset()
    {
        NumberOfContacts = 0;
        BodyContacts.clear();
    }
}
