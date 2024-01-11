package dao;

import dto.Hotel;
import utils.Alert;
import utils.Input;

import java.io.*;
import java.util.*;

import static utils.Input.checkIdIndex;

public class HotelManager {
    private static String id;
    private static String name;
    private static int roomAvailable;
    private static String address;
    private static String phone;
    private static float rating;

    public static ArrayList<Hotel> hotels = new ArrayList<>();

    public static void saveToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Hotel.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hotels);
            Alert.printSuccess("Data Saved!");
        } catch (IOException e) {
            Alert.printError(e.getMessage());
        }
    }

    public static void readFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("Hotel.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            hotels = (ArrayList<Hotel>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            Alert.printError(e.getMessage());
        }
    }

    public static void addNewHotel() {
        do {
            id = Input.inputString("ID", false);
            name = Input.inputString("NAME", false);
            roomAvailable = Integer.parseInt(Input.inputNumbers("ROOMS", false));
            address = Input.inputString("ADDRESS", false);
            phone = Input.inputString("PHONE", false);
            rating = Float.parseFloat(Input.inputNumbers("RATING", false));
            Hotel hotel = new Hotel(id, name, roomAvailable, address, phone, rating);
            hotels.add(hotel);
            Alert.printSuccess("Hotel added successfully!");
            saveToFile();
        }
        while (Input.inputContinue("Continue Adding? : "));
    }

    public static void checkExistHotel() {
        readFromFile();
        if (hotels.isEmpty()) Alert.printError("Add a hotel first!");
        else {
            ArrayList<Hotel> hotelClone = new ArrayList<>(hotels);
            boolean check = false;
            String id;
            do {
                Scanner sc = new Scanner(System.in);
                System.out.print("Input Hotel's ID: ");
                id = sc.nextLine();
                if (id.isBlank()) Alert.printError("ID cannot left blank!");
                else check = true;
            }
            while (!check);
            readFromFile();
            for (Hotel hotel : hotelClone) {
                if (id.equals(hotel.getId())) {
                    Alert.printSuccess("Exist Hotel");
                    return;
                }
            }
            Alert.printError("No Hotel Found!");
        }
    }

    public static void updateHotel() {
        readFromFile();
        if (hotels.isEmpty()) Alert.printError("Add a hotel first!");
        else {
            id = Input.inputString("ID", true);
            int Index = checkIdIndex(id);
            name = Input.inputString("NAME", true);
            String roomAvailable = Input.inputNumbers("ROOMS", true);
            address = Input.inputString("ADDRESS", true);
            phone = Input.inputString("PHONE", true);
            String rating = Input.inputNumbers("RATING", true);

            if (!name.isBlank()) {
                hotels.get(Index).setName(name);
            }
            if (!roomAvailable.isBlank()) {
                hotels.get(Index).setRoomAvailable(Integer.parseInt(roomAvailable));
            }
            if (!address.isBlank()) {
                hotels.get(Index).setAddress(address);
            }
            if (!phone.isBlank()) {
                hotels.get(Index).setPhone(phone);
            }
            if (!rating.isBlank()) {
                hotels.get(Index).setRating(Float.parseFloat(rating));
            }
            Alert.printSuccess("Hotel Updated Successfully!");
            saveToFile();
        }
    }

    public static void deleteHotel() {
        readFromFile();
        if (hotels.isEmpty()) Alert.printError("Add a hotel first!");
        else {
            id = Input.inputString("ID", true);
            int Index = checkIdIndex(id);
            if (Index != -1) {
                if (Input.inputContinue("Do you really want to delete this hotel? : ")) {
                    hotels.remove(Index);
                    Alert.printSuccess("Removed Successfully!");
                    saveToFile();
                }
            }
        }
    }

    public static void searchHotel(boolean ID) {
        readFromFile();
        List<Hotel> hotelSort = new ArrayList<>();
        if (hotels.isEmpty()) Alert.printError("Add a hotel first!");
        else {
            int sum = 0;
            int max = 0;
            if (ID) {
                boolean check = false;
                String id;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's ID: ");
                    id = sc.nextLine();
                    if (id.isBlank()) Alert.printError("ID cannot left blank!");
                    else check = true;
                }
                while (!check);

                for (Hotel hotel : hotels) {
                    if (hotel.getId().toLowerCase().contains(id.toLowerCase())) {
                        hotelSort.add(hotel);
                        sum++;
                    }
                    if (max <= hotel.getAddress().length()) {
                        max = hotel.getAddress().length();
                    }
                    if (max < 13) max = 13;
                }
                if (!hotelSort.isEmpty()) {
                    Collections.sort(hotelSort, new Comparator<Hotel>() {
                        @Override
                        public int compare(Hotel o1, Hotel o2) {
                            return o2.getId().compareTo(o1.getId());
                        }
                    });
                    System.out.println("Found " + sum + " Hotel(s)!");
                    System.out.printf("|Hotel_id  |Hotel_Name         |Hotel_Room_Available|" + "%-" + (max) + "s|Hotel_Phone    |Hotel_Rating|\n", "Hotel_Address");
                    for (int j = 0; j < hotelSort.size(); j++) {
                        System.out.printf("|%-10s|%-19s|%-20d|" + "%-" +(max)+ "s|%-15s|%-3.1f %-8s|\n",
                                hotelSort.get(j).getId(),
                                hotelSort.get(j).getName(),
                                hotelSort.get(j).getRoomAvailable(),
                                hotelSort.get(j).getAddress(),
                                hotelSort.get(j).getPhone(),
                                hotelSort.get(j).getRating(),
                                "star");
                    }
                } else Alert.printError("ID Not Found!");
            }
            if (!ID) {
                boolean check = false;
                String name;
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input Hotel's Name: ");
                    name = sc.nextLine();
                    if (name.isBlank()) Alert.printError("Name cannot left blank!");
                    else check = true;
                }
                while (!check);
                for (Hotel hotel : hotels) {
                    if (hotel.getName().toLowerCase().contains(name.toLowerCase())) {
                        hotelSort.add(hotel);
                        sum++;
                    }
                    if (max <= hotel.getAddress().length()) {
                        max = hotel.getAddress().length();
                    }
                    if (max < 13) max = 13;
                }
                if (!hotelSort.isEmpty()) {
                    Collections.sort(hotelSort, new Comparator<Hotel>() {
                        @Override
                        public int compare(Hotel o1, Hotel o2) {
                            return o2.getName().compareTo(o1.getName());
                        }
                    });
                    System.out.println("Found " + sum + " Hotel(s)!");
                    System.out.printf("|Hotel_id  |Hotel_Name         |Hotel_Room_Available|" + "%-" + (max) + "s|Hotel_Phone    |Hotel_Rating|\n", "Hotel_Address");
                    for (int j = 0; j < hotelSort.size(); j++) {
                        System.out.printf("|%-10s|%-19s|%-20d|" + "%-" +(max)+ "s|%-15s|%-3.1f %-8s|\n",
                                hotelSort.get(j).getId(),
                                hotelSort.get(j).getName(),
                                hotelSort.get(j).getRoomAvailable(),
                                hotelSort.get(j).getAddress(),
                                hotelSort.get(j).getPhone(),
                                hotelSort.get(j).getRating(),
                                "star");
                    }
                } else Alert.printError("Name Not Found!");
            }
        }
    }

    public static void showList() {
        readFromFile();
        int max = 0;
        if (hotels.isEmpty()) Alert.printError("Add a hotel first!");
        else {
            Collections.sort(hotels, new Comparator<Hotel>() {
                @Override
                public int compare(Hotel o1, Hotel o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            });
            for (Hotel hotel : hotels) {
                if (max <= hotel.getAddress().length()) {
                    max = hotel.getAddress().length();
                }
                if (max < 13) max = 13;
            }
            System.out.printf("|Hotel_id  |Hotel_Name         |Hotel_Room_Available|" + "%-" + (max) + "s|Hotel_Phone    |Hotel_Rating|\n", "Hotel_Address");
            for (int j = 0; j < hotels.size(); j++) {
                System.out.printf("|%-10s|%-19s|%-20d|" + "%-" +(max)+ "s|%-15s|%-3.1f %-8s|\n",
                        hotels.get(j).getId(),
                        hotels.get(j).getName(),
                        hotels.get(j).getRoomAvailable(),
                        hotels.get(j).getAddress(),
                        hotels.get(j).getPhone(),
                        hotels.get(j).getRating(),
                        "star");
            }
        }
    }
}

