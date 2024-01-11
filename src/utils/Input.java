package utils;

import java.util.Scanner;

import static dao.HotelManager.hotels;
import static dao.HotelManager.readFromFile;

public class Input {
    public static int checkIdIndex(String id) {
        readFromFile();
        int Index = -1;
        for (int i = 0; i < hotels.size(); i++) {
            if (id.equals(hotels.get(i).getId())) {
                Index = i;
            }
        }
        return Index;
    }

    public static boolean inputContinue(String message) {
        boolean check = false;
        String input = null;
        char choice = 0;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.printf(message);
            input = sc.nextLine();
            if (!(input.isBlank())) {
                choice = input.toUpperCase().charAt(0);
                if (choice == 'N' || choice == '0' || choice == 'F' || choice == 'Y' || choice == '1' || choice == 'T') {
                    check = true;
                } else Alert.printError("Invalid Choice!");
            } else Alert.printError("Invalid Choice!");
        }
        while (!check);
        return choice == 'Y' || choice == '1' || choice == 'T';
    }

    public static String inputString(String type, boolean Update) {
        if (!Update)
        {
            if (type.equalsIgnoreCase("ID")) {
                boolean check = false;
                String id;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's ID: ");
                    id = sc.nextLine();
                    if (id.isBlank()) Alert.printError("ID cannot left blank!");
                    else if (checkIdIndex(id) != -1) {
                        Alert.printError("Hotel already exist!");
                    } else check = true;
                }
                while (!check);
                return id;
            }

            if (type.equalsIgnoreCase("NAME")) {
                boolean check = false;
                String name;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's Name: ");
                    name = sc.nextLine();
                    if (name.isBlank()) Alert.printError("Name cannot be empty!");
                    else check = true;
                }
                while (!check);
                return name;
            }

            if (type.equalsIgnoreCase("ADDRESS")) {
                boolean check = false;
                String address;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's Address: ");
                    address = sc.nextLine();
                    if (address.isBlank()) Alert.printError("Address cannot be empty!");
                    else check = true;
                }
                while (!check);
                return address;
            }

            if (type.equalsIgnoreCase("PHONE")) {
                boolean check = false;
                String phone;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's Phone-Number: ");
                    phone = sc.nextLine();
                    if (phone.isBlank()) Alert.printError("Phone-number cannot be empty!");
                    else if (phone.matches("[0-9]+")) check = true;
                    else Alert.printError("Wrong number format!");
                }
                while (!check);
                return phone;
            }
        }
        if (Update)
        {
            if (type.equalsIgnoreCase("ID")) {
                boolean check = false;
                String id;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's ID: ");
                    id = sc.nextLine();
                    if (id.isBlank()) Alert.printError("ID cannot left blank!");
                    else if (checkIdIndex(id) != -1) {
                        check = true;
                    } else Alert.printError("Hotel does not exist!");
                }
                while (!check);
                return id;
            }

            if (type.equalsIgnoreCase("NAME")) {
                boolean check = false;
                String name;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's Name: ");
                    name = sc.nextLine();
                    if (name.isBlank()) return "";
                    else check = true;
                }
                while (!check);
                return name;
            }

            if (type.equalsIgnoreCase("ADDRESS")) {
                boolean check = false;
                String address;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's Address: ");
                    address = sc.nextLine();
                    if (address.isBlank()) return "";
                    else check = true;
                }
                while (!check);
                return address;
            }

            if (type.equalsIgnoreCase("PHONE")) {
                boolean check = false;
                String phone;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's Phone-Number: ");
                    phone = sc.nextLine();
                    if (phone.isBlank()) return "";
                    else if (phone.matches("[0-9]+")) check = true;
                    else Alert.printError("Wrong number format!");
                }
                while (!check);
                return phone;
            }
        }
        return null;
    }

    public static String inputNumbers(String type,boolean Update)
    {
        if (!Update)
        {
               if (type.equalsIgnoreCase("ROOMS"))
               {
                   boolean check = false;
                   String input;
                   int numberOfRooms = 0;
                   do {
                       try
                       {
                           Scanner sc = new Scanner(System.in);
                           System.out.print("Input number of available rooms: ");
                           input = sc.nextLine();
                           if (!(input.isBlank()))
                           {
                               if (Integer.parseInt(input) >= 0)
                               {
                                   numberOfRooms = Integer.parseInt(input);
                                   check = true;
                               }
                               else Alert.printError("Cannot be smaller than 0!");
                           }
                           else Alert.printError("Cannot left blank!");
                       }
                       catch (NumberFormatException e)
                       {
                           Alert.printError("Invalid Input, try again!");
                       }
                   }
                   while (!check);
                   return String.valueOf(numberOfRooms);
               }

               if (type.equalsIgnoreCase("RATING"))
               {
                   boolean check = false;
                   String input;
                   float rating = 0;
                   do {
                       try
                       {
                           Scanner sc = new Scanner(System.in);
                           System.out.print("Input Hotel's Rating: ");
                           input = sc.nextLine();
                           if (!(input.isBlank()))
                           {
                               if (Float.parseFloat(input) >= 0)
                               {
                                   rating = Float.parseFloat(input);
                                   check = true;
                               }
                               else Alert.printError("Cannot be smaller than 0!");
                           }
                           else Alert.printError("Cannot left blank!");
                       }
                       catch (NumberFormatException e)
                       {
                           Alert.printError("Invalid Input, try again!");
                       }
                   }
                   while (!check);
                   return String.valueOf(rating);
               }
        }
        if (Update)
        {
            if (type.equalsIgnoreCase("ROOMS"))
            {
                boolean check = false;
                String input;
                int numberOfRooms = 0;
                do {
                    try
                    {
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Input number of available rooms: ");
                        input = sc.nextLine();
                        if (input.isBlank()) return "";
                        else if (!(input.isBlank()))
                        {
                            if (Integer.parseInt(input) >= 0)
                            {
                                numberOfRooms = Integer.parseInt(input);
                                check = true;
                            }
                            else Alert.printError("Cannot be smaller than 0!");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        Alert.printError("Invalid Input, try again!");
                    }
                }
                while (!check);
                return String.valueOf(numberOfRooms);
            }

            if (type.equalsIgnoreCase("RATING"))
            {
                boolean check = false;
                String input;
                float rating = 0;
                do {
                    try
                    {
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Input Hotel's Rating: ");
                        input = sc.nextLine();
                        if (input.isBlank()) return "";
                        else
                        {
                            if (Float.parseFloat(input) >= 0)
                            {
                                rating = Float.parseFloat(input);
                                check = true;
                            }
                            else Alert.printError("Cannot be smaller than 0!");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        Alert.printError("Invalid Input, try again!");
                    }
                }
                while (!check);
                return String.valueOf(rating);
            }
        }
        return null;
    }
}
