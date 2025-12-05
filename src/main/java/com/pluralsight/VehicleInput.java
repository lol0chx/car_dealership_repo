package com.pluralsight;

import java.util.Scanner;

import static java.lang.System.out;

public class VehicleInput {
        private static Scanner scanner = new Scanner(System.in);

        public static int addVehicle1(){
            String input = ("What is the vin of the car you wish to add?");
            System.out.println(input);
            return Integer.parseInt(scanner.nextLine());
        }
    public static boolean addVehicle2(){
        String input = ("What is the sale status of the car you wish to add?");
        System.out.println(input);
        return Boolean.parseBoolean(scanner.nextLine());
    }
    public static String addVehicle3(){
        String input = ("What is the color of the car you wish to add?");
        System.out.println(input);
        return scanner.nextLine();
    }
    public static String addVehicle4(){
        String input = ("What is the make of the car you wish to add?");
        System.out.println(input);
        return scanner.nextLine();
    }
    public static String addVehicle5(){
        String input = ("What is the model of the car you wish to add?");
        System.out.println(input);
        return scanner.nextLine();
    }
    public static double addVehicle6(){
        String input = ("What is the price of the car you wish to add?");
        System.out.println(input);
        return Double.parseDouble(scanner.nextLine());
    }
    public static int addVehicle7(){
        String input = ("What is the year of the car you wish to add?");
        System.out.println(input);
        return Integer.parseInt(scanner.nextLine());
    }
    public static int addVehicle8(){
        String input = ("What is the mileage of the car you wish to add?");
        System.out.println(input);
        return Integer.parseInt(scanner.nextLine());
    }
    public static String addVehicle9(){
        String input = ("What is the type of the car you wish to add?");
        System.out.println(input);
        return scanner.nextLine();
    }
    public static int deleteVehicle(){
        String input = ("What is the vin of the vehicle you would like to delete?");
        System.out.println(input);
        return Integer.parseInt(scanner.nextLine());
    }
        public static double searchbyPrice(){
            String input = ("What is the minimum price you'd like to search for?");
            System.out.println(input);
            return Double.parseDouble(scanner.nextLine());
        }
    public static double searchbyPrice2(){
        String input = ("What is the maximum price you'd like to search for?");
        System.out.println(input);
        return Double.parseDouble(scanner.nextLine());
    }
    public static String searchbyMake(){
        String input = ("What is the make of the vehicle you'd like to search for?");
        System.out.println(input);
        return scanner.nextLine();
    }
    public static String searchbyModel(){
        String input = ("What is the model of the vehicle you'd like to search for?");
        System.out.println(input);
        return scanner.nextLine();
    }
    public static int searchbyYear(){
        String input = ("What is the minimum year you'd like to search for?");
        System.out.println(input);
        return Integer.parseInt(scanner.nextLine());
    }
    public static int searchbyYear2(){
        String input = ("What is the maximum year you'd like to search for?");
        System.out.println(input);
        return Integer.parseInt(scanner.nextLine());
    }
    public static String searchbyColor(){
        String input = ("What is the color of the vehicle you'd like to search for?");
        System.out.println(input);
        return scanner.nextLine();
    }
    public static int searchbyMileage(){
        String input = ("What is the minimum mileage you'd like to search for?");
        System.out.println(input);
        return Integer.parseInt(scanner.nextLine());
    }
    public static int searchbyMileage2(){
        String input = ("What is the maximum mileage you'd like to search for?");
        System.out.println(input);
        return Integer.parseInt(scanner.nextLine());
    }
    public static String searchbyType(){
        String input = ("What is the type of the vehicle you'd like to search for?");
        System.out.println(input);
        return scanner.nextLine();
    }

        public static int getNumberChoice(String options) {
            out.println(options);
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        }

}
