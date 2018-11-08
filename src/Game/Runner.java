package Game;

import People.Person;
import Rooms.ItemRoom;
import Rooms.Room;
import Rooms.TrapRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Rooms.ItemRoom.inv;
import static Rooms.ItemRoom.r;

public class Runner {

	private static boolean gameOn = true;
	
	public static void main(String[] args)
	{
		System.out.println("Background info:\nYou were kidnapped by a bunch of thieves and you have no recollection of how it happened. They left you in a mansion!!!\n");
		System.out.println("I'll be here to help and guide you. Dw bout who I am");
		Scanner in = new Scanner(System.in);
		Board layout = new Board(10,10);
		Room[][] building = layout.createTemplate();
		 
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
			if(move.toLowerCase().equals("i"))
			{
				inventory();
			}

			
			
		}
		in.close();
	}
	public static void help()
	{
		System.out.println("To move: type w,a,s,d");
		System.out.println("Please answer prompts with \"yes\"");
		System.out.println("SYMBOLS\n--------\nYour position: \"X\"\nExplored areas: \"-\"\nUnexplored areas: \"?\"\nWalls: \"|\"\nRooms: \"R\"\n");
		System.out.println("Items\n--------\nPotion of Ressurection: Used when you discover a trap room\nTeleport Potion: Use to teleport to a random location on the map");
	}
	public static void inventory()
	{
		ArrayList<String> inv = new ArrayList<String>();
		System.out.print("Your inventory\n--------\n");
		System.out.println(inv);
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
		Scanner in = new Scanner(System.in);
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
						System.out.println("You have found a room, would you like to continue?");
						String prompt = in.nextLine();
						if(prompt.equals("yes"))
						{
							System.out.println("Woah what's this");
							map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
							map[p.getxLoc() - 1][p.getyLoc()].enterRoom(p);
							Board.mapCreate[p.getxLoc()][p.getyLoc()] = "R";

						}
						else
						{
							map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
							map[p.getxLoc()][p.getyLoc()].enterRoom(p);
							Board.mapCreate[p.getxLoc() - 1][p.getyLoc()] = "R";
						}
					}
					else if(Board.mapCreate[p.getxLoc() - 1][p.getyLoc()].equals("?"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc() - 1][p.getyLoc()].enterRoom(p);
						if (Board.mapRooms[p.getxLoc() + 1][p.getyLoc()].equals("R"))
						{
							Board.mapCreate[p.getxLoc() + 1][p.getyLoc()] = "R";
						}
					}
					else if(Board.mapCreate[p.getxLoc() - 1][p.getyLoc()].equals("-"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc() - 1][p.getyLoc()].enterRoom(p);
						if (Board.mapRooms[p.getxLoc() + 1][p.getyLoc()].equals("R"))
						{
							Board.mapCreate[p.getxLoc() + 1][p.getyLoc()] = "R";
						}
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
						System.out.println("You have found a room, would you like to continue?");
						String prompt = in.nextLine();
						if(prompt.equals("yes")) {
							System.out.println("Woah what's this");
							map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
							map[p.getxLoc()][p.getyLoc() - 1].enterRoom(p);
							Board.mapCreate[p.getxLoc()][p.getyLoc()] = "R";
						}
						else
						{
							map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
							map[p.getxLoc()][p.getyLoc()].enterRoom(p);
							Board.mapCreate[p.getxLoc()][p.getyLoc() - 1] = "R";
						}
					}
					else if(Board.mapCreate[p.getxLoc()][p.getyLoc() - 1].equals("?"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc() - 1].enterRoom(p);
						if (Board.mapRooms[p.getxLoc()][p.getyLoc() + 1].equals("R"))
						{
							Board.mapCreate[p.getxLoc()][p.getyLoc() + 1] = "R";
						}
					}
					else if(Board.mapCreate[p.getxLoc()][p.getyLoc() - 1].equals("-"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc() - 1].enterRoom(p);
						if (Board.mapRooms[p.getxLoc()][p.getyLoc() + 1].equals("R"))
						{
							Board.mapCreate[p.getxLoc()][p.getyLoc() + 1] = "R";
						}
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
						System.out.println("You have found a room, would you like to continue?");
						String prompt = in.nextLine();
						if(prompt.equals("yes")) {
							System.out.println("Woah what's this");
							map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
							map[p.getxLoc() + 1][p.getyLoc()].enterRoom(p);
							Board.mapCreate[p.getxLoc()][p.getyLoc()] = "R";
						}
						else
						{
							map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
							map[p.getxLoc()][p.getyLoc()].enterRoom(p);
							Board.mapCreate[p.getxLoc() + 1][p.getyLoc()] = "R";
						}

					}
					else if(Board.mapCreate[p.getxLoc() + 1][p.getyLoc()].equals("?"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc() + 1][p.getyLoc()].enterRoom(p);
						if (Board.mapRooms[p.getxLoc() - 1][p.getyLoc()].equals("R"))
						{
							Board.mapCreate[p.getxLoc() - 1][p.getyLoc()] = "R";
						}
					}
					else if(Board.mapCreate[p.getxLoc() + 1][p.getyLoc()].equals("-"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc() + 1][p.getyLoc()].enterRoom(p);
						if (Board.mapRooms[p.getxLoc() - 1][p.getyLoc()].equals("R"))
						{
							Board.mapCreate[p.getxLoc() - 1][p.getyLoc()] = "R";
						}
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
						map[p.getxLoc()][p.getyLoc()].enterRoom(p);
						Board.mapCreate[p.getxLoc()][p.getyLoc() + 1] = "|";
					}
					else if (Board.mapRooms[p.getxLoc()][p.getyLoc() + 1].equals("R"))
					{
						System.out.println("You have found a room, would you like to continue?");
						String prompt = in.nextLine();
						if(prompt.equals("yes")) {
							System.out.println("Woah what's this");
							map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
							map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
							Board.mapCreate[p.getxLoc()][p.getyLoc()] = "R";
						}
						else
						{
							map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
							map[p.getxLoc()][p.getyLoc()].enterRoom(p);
							Board.mapCreate[p.getxLoc()][p.getyLoc() + 1] = "R";
						}
					}
					else if(Board.mapCreate[p.getxLoc()][p.getyLoc() + 1].equals("?"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
						if (Board.mapRooms[p.getxLoc()][p.getyLoc() - 1].equals("R"))
						{
							Board.mapCreate[p.getxLoc()][p.getyLoc() - 1] = "R";
						}
					}
					else if(Board.mapCreate[p.getxLoc()][p.getyLoc() + 1].equals("-"))
					{
						map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
						map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
						if (Board.mapRooms[p.getxLoc()][p.getyLoc() - 1].equals("R"))
						{
							Board.mapCreate[p.getxLoc()][p.getyLoc() - 1] = "R";
						}
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
