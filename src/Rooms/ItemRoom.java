package Rooms;

import People.Person;

public class ItemRoom extends Room
{
    public static String[] items;
    public static int r;
    public static String inv;
    public static int enter;
    public ItemRoom(int x, int y) {
        super(x, y);
        enter = 0;

    }

    /**
     * Triggers the game ending conditions.
     * @param x the Person entering
     */

    @Override
    public void enterRoom(Person x)
    {
        if(enter < 1) {
            items = new String[]{"Potion of Resurrection", "The best useless potion of all time"};
            if (Math.random() >= .5)
                r = 1;
            else
                r = 0;
            occupant = x;
            x.setxLoc(this.xLoc);
            x.setyLoc(this.yLoc);
            System.out.println("You have found a " + items[r]);
            inv = items[r];
            if(inv.contains("Potion of Resurrection"))
            {
                TrapRoom.potion = true;
            }
            enter++;
        }
        else
        {
            occupant = x;
            x.setxLoc(this.xLoc);
            x.setyLoc(this.yLoc);
            System.out.println("Where'd it go O.o");
        }

    }
}