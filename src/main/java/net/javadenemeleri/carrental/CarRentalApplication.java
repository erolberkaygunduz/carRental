package net.javadenemeleri.carrental;

import java.time.LocalDateTime;

public class CarRentalApplication {

    public static void main(String[] args) {
        CarRentalService rentalService = new CarRentalService();

        // Test 1: SUCCESSFUL - Correct date
        try {
            Reservation reservation1 = new Reservation(
                CarType.SEDAN,
                LocalDateTime.now().plusDays(5),
                3
            );
            boolean success = rentalService.makeReservation(reservation1);
            System.out.println("Test 1 : Reservation " + (success ? "successful!" : "failed!"));
        } catch (InvalidReservationException e) {
            System.out.println("Test 1 : Error: " + e.getMessage());
        }

        System.out.println();

        // Test 2: INVALID - Past date
        try {
            Reservation reservation2 = new Reservation(
                CarType.SUV,
                LocalDateTime.now().minusDays(1),
                2
            );

        } catch (InvalidReservationException e) {
            System.out.println("Test 2 : Past date : Error caught - " + e.getMessage());
        }

        System.out.println();

        // Test 3: INVALID - Null car type
        try {
            Reservation reservation3 = new Reservation(
                null,
                LocalDateTime.now().plusDays(3),
                2
            );
        } catch (InvalidReservationException e) {
            System.out.println("Test 3 Null car type: Error : " + e.getMessage());
        }

        System.out.println();

        // Test 4: INVALID - Negative day time
        try {
            Reservation reservation4 = new Reservation(
                CarType.VAN,
                LocalDateTime.now().plusDays(2),
                -1
            );
        } catch (InvalidReservationException e) {
            System.out.println("Test 4 Negative days: Error caught : " + e.getMessage());
        }

        System.out.println();

        // Test 5: Availability check
        boolean available = rentalService.isAvailable(
            CarType.SEDAN,
            LocalDateTime.now().plusDays(10),
            2
        );
        System.out.println("Test 5 Sedan available : " + available);

        System.out.println();

        //Test 6 : Add cars to inventory
        rentalService.addCarToInventory(CarType.VAN, 3);
        boolean available1 = rentalService.isAvailable(CarType.VAN, LocalDateTime.now().plusDays(1), 1);
        System.out.println("Test 6 VAN available after adding to inventory : " + available1);

    }

}
