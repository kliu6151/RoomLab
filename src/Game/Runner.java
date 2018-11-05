package Game;

import People.Person;
import Rooms.Room;

import java.util.Arrays;
import java.util.Scanner;

public class Runner {

	private static boolean gameOn = true;
	
	public static void main(String[] args)
	{
		System.out.println("Background info:\nYou were kidnapped by a bunch of thieves and you have no recollection of how it happened. They left you in a mansion!!!\n");
		System.out.println("I'll be here to help and guide you. Dw bout who I am");
		Scanner in = new Scanner(System.in);
		Board layout = new Board(10,10);
		Room[][] building = layout.generate();
		layout.createTemplate();
		System.out.println(Arrays.deepToString(Board.mapWalls));

		 
		 //Setup player 1 and the input scanner
		System.out.println("First thing is first, What's your name?");
		String firstName = in.nextLine();
		System.out.println("Last name?");
		String lastName = in.nextLine();
		Person player1 = new Person(firstName, lastName, 0,0);
		System.out.println("Well, good luck, " + firstName + " " + lastName + ". If you have need help, type help");
		building[0][0].enterRoom(player1);
		while(gameOn)
		{

			String move = in.nextLine();
			if(validMove(move, player1, building))
			{
				System.out.println("---------------------------------------");
			}
			if(move.toLowerCase().equals("w") || move.toLowerCase().equals("a") || move.toLowerCase().equals("s") || move.toLowerCase().equals("d"))
			{
				String mapPopulate = "";
				for (int i = 0; i < building.length; i++)
				{
					for (int j = 0; j < building.length; j++)
					{
						if ((i == player1.getxLoc() && j == player1.getyLoc()))
						{
							Board.mapCreate[i][j] = "-";
							Board.mapCreate[0][0] = "-";
						}
					}
				}
				Board.mapCreate[player1.getxLoc()][player1.getyLoc()] = "X";
				for(String[] row : Board.mapCreate){
					for(String column: row){
						mapPopulate += column;
					}
					mapPopulate += "\n";
				}
				System.out.println(mapPopulate);
				Board.mapCreate[player1.getxLoc()][player1.getyLoc()] = "-";

			}
			if(move.toLowerCase().equals("help"))
			{
				help();
			}

			
			
		}
		in.close();
	}
	public static void help()
	{
		System.out.println("To move: type w,a,s,d");
		System.out.println("SYMBOLS\n--------\nYour position: \"!\"\nUnexplored areas: \"?\"\nWalls: \"|\"");
	}

	/**
	 * Checks that the movement chosen is within the valid game map.
	 * @param move the move chosen
	 * @param p person moving
	 * @param map the 2D array of rooms
	 * @return
	 */
	public static boolean validMove(String move, Person p, Room[][] map)
	{
		move = move.toLowerCase().trim();
		switch (move) {
			case "w":
				if (p.getxLoc() > 0)
				{
					if (Board.mapWalls[p.getxLoc() - 1][p.getyLoc()].equals("|"))
					{
						System.out.println("Ouch! that must've hurt");
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc()].enterRoom(p);
						Board.mapCreate[p.getxLoc() - 1][p.getyLoc()] = "|";
					}
					else if (Board.mapRooms[p.getxLoc() - 1][p.getyLoc()].equals("R"))
					{
						System.out.println("hmmm");
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc()].enterRoom(p);
						Board.mapCreate[p.getxLoc()][p.getyLoc()] = "R";
					}
					else if(Board.mapCreate[p.getxLoc() - 1][p.getyLoc()].equals("?"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc() - 1][p.getyLoc()].enterRoom(p);

					}
					else if(Board.mapCreate[p.getxLoc() - 1][p.getyLoc()].equals("-"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc() - 1][p.getyLoc()].enterRoom(p);
					}
					return true;
				}
				else
				{
					return false;
				}
			case "a":
				if (p.getyLoc() > 0)
				{
					if(Board.mapWalls[p.getxLoc()][p.getyLoc() - 1].equals("|"))
					{
					System.out.println("Ouch! that must've hurt");
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc()].enterRoom(p);
					Board.mapCreate[p.getxLoc()][p.getyLoc() - 1] = "|";
					}
					else if(Board.mapRooms[p.getxLoc()][p.getyLoc() - 1].equals("R"))
					{
						System.out.println("hmm");
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc()].enterRoom(p);
						Board.mapCreate[p.getxLoc()][p.getyLoc()] = "R";
					}
					else if(Board.mapCreate[p.getxLoc()][p.getyLoc() - 1].equals("?"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc() - 1].enterRoom(p);
					}
					else if(Board.mapCreate[p.getxLoc()][p.getyLoc() - 1].equals("-"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc() - 1].enterRoom(p);
					}
					return true;
				}
				else
				{
					return false;
				}

			case "s":
				if (p.getxLoc() < map.length - 1)
				{
					if(Board.mapWalls[p.getxLoc() + 1][p.getyLoc()].equals("|"))
					{
						System.out.println("Ouch! that must've hurt");
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc()].enterRoom(p);
						Board.mapCreate[p.getxLoc() + 1][p.getyLoc()] = "|";
					}
					else if (Board.mapRooms[p.getxLoc() + 1][p.getyLoc()].equals("R"))
					{
						System.out.println("hmm");
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc()].enterRoom(p);
						Board.mapCreate[p.getxLoc()][p.getyLoc()] = "R";
					}
					else if(Board.mapCreate[p.getxLoc() + 1][p.getyLoc()].equals("?"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc() + 1][p.getyLoc()].enterRoom(p);
					}
					else if(Board.mapCreate[p.getxLoc() + 1][p.getyLoc()].equals("-"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc() + 1][p.getyLoc()].enterRoom(p);
					}
					return true;
				}
				else
				{
					return false;
				}

			case "d":
				if (p.getyLoc()< map[p.getyLoc()].length -1)
				{
					if(Board.mapWalls[p.getxLoc()][p.getyLoc() + 1].equals("|"))
					{
						System.out.println("Ouch! that must've hurt");
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
						Board.mapCreate[p.getxLoc()][p.getyLoc() + 1] = "|";
					}
					else if (Board.mapRooms[p.getxLoc()][p.getyLoc() + 1].equals("R"))
					{
						System.out.println("hmmm");
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc()].enterRoom(p);
						Board.mapCreate[p.getxLoc()][p.getyLoc()] = "R";
					}
					else if(Board.mapCreate[p.getxLoc()][p.getyLoc() + 1].equals("?"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
					}
					else if(Board.mapCreate[p.getxLoc()][p.getyLoc() + 1].equals("-"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
					}
					return true;
				}
				else
				{
					return false;
				}
			default:
				break;
					
		}
		return true;
	}

	public static void gameOff()
	{
		gameOn = false;
	}
	


}
