package Rooms;

import Game.Runner;
import People.Person;

public class TrapRoom extends Room
{
        public static boolean potion;
    public TrapRoom(int x, int y) {
        super(x, y);
        potion = false;

    }

    /**
     * Triggers the game ending conditions.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x)
    {
        if(potion == false) {
            occupant = x;
            x.setxLoc(this.xLoc);
            x.setyLoc(this.yLoc);
            System.out.println("I ACTIVATE MY TRAP CARD ( ͡° ͜ʖ ͡°)");
            Runner.gameOff();
        }
        else
        {
            occupant = x;
            x.setxLoc(this.xLoc);
            x.setyLoc(this.yLoc);
            System.out.println("With your quick reaction time, you escape from the traps");
            potion = false;
            ItemRoom.inv="nothing";
        }
    }


}
