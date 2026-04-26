package net.javadenemeleri.carrental;

import java.time.LocalDateTime;
import java.util.List;

public class CarRentalApplication {

    public static void main(String[] args) {
        CarRentalService rentalService = new CarRentalService();

        // Test 1: SUCCESSFUL - Future date
        try {
            List<Car> availableSedans = rentalService.getAvailableCars("SEDAN", LocalDateTime.now().plusDays(5), 3);
            if (!availableSedans.isEmpty()) {
                Car selectedCar = availableSedans.get(0);
                Reservation reservation1 = new Reservation(
                    selectedCar,
                    LocalDateTime.now().plusDays(5),
                    3
                );
                boolean success = rentalService.makeReservation(reservation1);
                System.out.println("Test 1 (Future date): Reservation " + (success ? "successful!" : "failed!"));
            } else {
                System.out.println("Test 1: No available Sedan");
            }
        } catch (InvalidReservationException e) {
            System.out.println("Test 1 Error: " + e.getMessage());
        }

        System.out.println();

        // Test 2: INVALID - Past date
        try {
            List<Car> availableSuvs = rentalService.getAvailableCars("SUV", LocalDateTime.now().minusDays(1), 2);
            if (!availableSuvs.isEmpty()) {
                Car selectedCar = availableSuvs.get(0);
                Reservation reservation2 = new Reservation(
                    selectedCar,
                    LocalDateTime.now().minusDays(1),
                    2
                );
                System.out.println("Test 2: Reservation created with past date (should not happen)");
            } else {
                System.out.println("Test 2: No available SUV");
            }
        } catch (InvalidReservationException e) {
            System.out.println("Test 2 (Past date): Error caught - " + e.getMessage());
        }

        System.out.println();

        // Test 3: INVALID - Null car
        try {
            Reservation reservation3 = new Reservation(
                null,
                LocalDateTime.now().plusDays(3),
                2
            );
        } catch (InvalidReservationException e) {
            System.out.println("Test 3 (Null car): Error caught - " + e.getMessage());
        }

        System.out.println();

        // Test 4: INVALID - Negative day count
        try {
            List<Car> availableVans = rentalService.getAvailableCars("VAN", LocalDateTime.now().plusDays(2), -1);
            if (!availableVans.isEmpty()) {
                Car selectedCar = availableVans.get(0);
                Reservation reservation4 = new Reservation(
                    selectedCar,
                    LocalDateTime.now().plusDays(2),
                    -1
                );
            } else {
                System.out.println("Test 4: No available Van");
            }
        } catch (InvalidReservationException e) {
            System.out.println("Test 4 (Negative days): Error caught - " + e.getMessage());
        }

        System.out.println();

        // Test 5: Availability check
        List<Car> availableSedans = rentalService.getAvailableCars("SEDAN", LocalDateTime.now().plusDays(10), 2);
        boolean available = !availableSedans.isEmpty();
        System.out.println("Sedan available (future date): " + available);
    }
}
