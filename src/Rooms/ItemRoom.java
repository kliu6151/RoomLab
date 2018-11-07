package Rooms;

import Game.Runner;
import People.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemRoom extends Room
{
    public static String[] items;
    public static int r;
    public static ArrayList inv;
    public ItemRoom(int x, int y) {
        super(x, y);

    }

    /**
     * Triggers the game ending conditions.
     * @param x the Person entering
     */

    @Override
    public void enterRoom(Person x)
    {
        items = new String[]{"Potion of Resurrection", "Teleport potion"};
        inv=new ArrayList();
        if(Math.random()>=.5)
            r = 1;
        else
            r = 0;
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        System.out.println("You have found a " + items[r]);
        inv.add(items[r]);
    }

    public static String yourItems() {
        System.out.println(inv);
        return "BEEP";
    }
}
