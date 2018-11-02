package Game;

import People.Person;
import Rooms.Room;
import Rooms.WinningRoom;

import java.util.Arrays;


public class Board {
    static int row;
    static int col;
    public static String[][] mapCreate;
    public static String[][] mapWalls;


    public Board(int row, int col)
    {
        this.row = row;
        this.col = col;
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
        mapWalls = new String[row][col];
        for (int i = 0; i < mapCreate.length; i++)
        {
            for (int j = 0; j < mapCreate[i].length; j++)
            {
                mapCreate[i][j] = "?";
            }
        }
        /*
        Creates the walls
         */
        int amountOfWalls = (int)((3 + Math.random()) * 5);
        for (int i = 0; i < mapWalls.length; i++)
        {
            for (int j = 0; j < mapWalls[i].length; j++)
            {
                mapWalls[i][j] = "U";
            }
        }
        for(int n = 0;n<=amountOfWalls;n++)
        {
            int randomMapWallsX = (int) (Math.random() * 10);
            int randomMapWallsY = (int) (Math.random() * 10);
            while(randomMapWallsX == 0 && randomMapWallsY == 0 || (randomMapWallsY == 9 && randomMapWallsX == 8) || (randomMapWallsY == 8 && randomMapWallsX == 9))
            {
                randomMapWallsX = (int) (Math.random() * 10);
                randomMapWallsY = (int) (Math.random() * 10);
            }
                mapWalls[randomMapWallsY][randomMapWallsX] = mapCreate[randomMapWallsY][randomMapWallsX];
                mapWalls[randomMapWallsY][randomMapWallsX] = "|";
                mapCreate[randomMapWallsY][randomMapWallsX] = "?";
        }

    }
}
