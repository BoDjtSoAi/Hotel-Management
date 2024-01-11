import dao.HotelManager;
import utils.Alert;
import utils.Input;
import utils.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        String choice;
        boolean check = false;
        do
        {
            Menu.printMenu();
            Scanner sc = new Scanner(System.in);
            System.out.printf("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.isBlank()) Alert.printError("Try again!");
            else if (choice.equals("1"))
            {
                HotelManager.addNewHotel();
            }
            else if (choice.equals("2"))
            {
                HotelManager.checkExistHotel();
            }
            else if (choice.equals("3"))
            {
                HotelManager.updateHotel();
            }
            else if (choice.equals("4"))
            {
                HotelManager.deleteHotel();
            }
            else if (choice.equals("5"))
            {
                do {
                    System.out.println("1.Search By ID");
                    System.out.println("2.Search By Name");
                    System.out.printf("Enter your choice: ");
                    choice = sc.nextLine();
                    if (choice.equals("1")) {
                        check = true;
                        HotelManager.searchHotel(true);
                    }
                    else if (choice.equals("2")){
                        check = true;
                        HotelManager.searchHotel(false);
                    }
                    else Alert.printError("Try again!");
                }
                while (!check);
            }
            else if (choice.equals("5.1"))
            {
                HotelManager.searchHotel(true);
            }
            else if (choice.equals("5.2"))
            {
                HotelManager.searchHotel(false);
            }
            else if (choice.equals("6"))
            {
                HotelManager.showList();
            }
            else
            {
                return;
            }
        }
        while (Input.inputContinue("Continue? : "));
    }
}