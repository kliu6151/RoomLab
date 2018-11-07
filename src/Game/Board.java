package Game;

import People.Person;
import Rooms.Room;
import Rooms.TrapRoom;
import Rooms.WinningRoom;

import java.util.Arrays;


public class Board {
    static int row;
    static int col;
    public static String[][] mapCreate;
    public static String[][] mapWalls;
    public static String[][] mapRooms;
    private static int[][] buildingRooms;


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
        mapRooms = new String[row][col];

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
            while  ((randomMapWallsX == 0 && randomMapWallsY == 0) ||
                    (randomMapWallsY == 9 && randomMapWallsX == 8) ||
                    (randomMapWallsY == 8 && randomMapWallsX == 9) ||
                    (randomMapWallsY == 9 && randomMapWallsX == 9))
            {
                randomMapWallsX = (int) (Math.random() * 10);
                randomMapWallsY = (int) (Math.random() * 10);
            }
                mapWalls[randomMapWallsX][randomMapWallsY] = mapCreate[randomMapWallsY][randomMapWallsX];
                mapWalls[randomMapWallsX][randomMapWallsY] = "|";
                mapCreate[randomMapWallsX][randomMapWallsY] = "?";
        }
        /*
        Creates the rooms
         */
        int amountOfRooms = (int)((3 + Math.random()) * 5);
        int amountOfTrapRooms = (int)((1 + Math.random()) * 5);
        Room[][] trapRooms = new TrapRoom[row][col];
        for (int i = 0; i < mapRooms.length; i++)
        {
            for (int j = 0; j < mapRooms[i].length; j++)
            {
                mapRooms[i][j] = "U";
            }
        }
        for(int n = 0;n<=amountOfRooms;n++)
        {
                int i = 0;
                int randomMapRoomsX = (int) (Math.random() * 10);
                int randomMapRoomsY = (int) (Math.random() * 10);
                while (randomMapRoomsX == 0 && randomMapRoomsY == 0 || (randomMapRoomsY == 9 && randomMapRoomsX == 8) || (randomMapRoomsY == 8 && randomMapRoomsX == 9)) {
                    randomMapRoomsX = (int) (Math.random() * 10);
                    randomMapRoomsY = (int) (Math.random() * 10);
                }
                mapRooms[randomMapRoomsX][randomMapRoomsY] = mapCreate[randomMapRoomsX][randomMapRoomsY];
                mapRooms[randomMapRoomsX][randomMapRoomsY] = "R";
                mapCreate[randomMapRoomsX][randomMapRoomsY] = "?";
                if(i <= amountOfTrapRooms)
                {
                    i++;
                    trapRooms[randomMapRoomsX][randomMapRoomsY] = new TrapRoom(randomMapRoomsX, randomMapRoomsY);
                    System.out.println(i);
                }
        }
        System.out.println(amountOfTrapRooms);
        System.out.println(amountOfRooms);
        System.out.println(Arrays.deepToString(trapRooms));
        System.out.println(Arrays.deepToString(mapRooms));

    }
}
