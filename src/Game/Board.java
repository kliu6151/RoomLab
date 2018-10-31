package Game;

import People.Person;
import Rooms.Room;
import Rooms.WinningRoom;


public class Board {
    static int row;
    static int col;
    public static String[][] mapCreate;


    public Board(int row, int col)
    {
        this.row = row;
        this.col = col;
        this.mapCreate = mapCreate;
    }

    public static Room[][] generate()
    {
        Room[][] building = new Room[row][col];

        for(int i = 0; i<building.length;i++)
        {
            for(int j = 0; j<building[i].length;j++)
            {
                building[i][j] = new Room(i,j);
            }
        }

        int x = (int)(Math.random()*building.length);
        int y = (int)(Math.random()*building.length);
        building[9][9] = new WinningRoom(x,y);

        return building;
    }
    public static void createTemplate()
    {
        mapCreate = new String[row][col];
        for (int i = 0; i < mapCreate.length; i++)
        {
            for (int j = 0; j < mapCreate[i].length; j++)
            {
                mapCreate[i][j] = "?";
            }
        }

    }
}
