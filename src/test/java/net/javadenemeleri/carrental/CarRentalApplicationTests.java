package net.javadenemeleri.carrental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarRentalApplicationTests {

    private CarRentalService rentalService;

    @BeforeEach
    void setUp() {
        rentalService = new CarRentalService();
    }

    @Test
    void testValidReservation() throws InvalidReservationException {
        List<Car> availableSedans = rentalService.getAvailableCars("SEDAN", LocalDateTime.now().plusDays(5), 3);
        assertFalse(availableSedans.isEmpty(), "There should be available Sedans");

        Car selectedCar = availableSedans.get(0);
        Reservation reservation = new Reservation(
            selectedCar,
            LocalDateTime.now().plusDays(5),
            3
        );

        boolean success = rentalService.makeReservation(reservation);
        assertTrue(success, "Reservation should be successful for valid input");
    }

    @Test
    void testPastDateReservation() {
        assertThrows(InvalidReservationException.class, () -> {
            List<Car> availableSedans = rentalService.getAvailableCars("SEDAN", LocalDateTime.now().minusDays(1), 3);
            if (!availableSedans.isEmpty()) {
                Car selectedCar = availableSedans.get(0);
                new Reservation(
                    selectedCar,
                    LocalDateTime.now().minusDays(1),
                    3
                );
            } else {
                throw new InvalidReservationException("No available car");
            }
        }, "Old date reservation should throw InvalidReservationException");
    }

    @Test
    void testNullCarType() {
        assertThrows(InvalidReservationException.class, () -> {
            new Reservation(
                null,
                LocalDateTime.now().plusDays(5),
                3
            );
        }, "null car should throw InvalidReservationException");
    }

    @Test
    void testInvalidDayTime() {
        assertThrows(InvalidReservationException.class, () -> {
            List<Car> availableSuvs = rentalService.getAvailableCars("SUV", LocalDateTime.now().plusDays(2), 0);
            if (!availableSuvs.isEmpty()) {
                Car selectedCar = availableSuvs.get(0);
                new Reservation(
                    selectedCar,
                    LocalDateTime.now().plusDays(2),
                    0
                );
            } else {
                throw new InvalidReservationException("No available car");
            }
        }, "0 or negative day time should throw InvalidReservationException");
    }

    @Test
    void testAddCarInventory(){
        int initialSedanCount = rentalService.getAvailableCars("SEDAN", LocalDateTime.now().plusDays(1), 1).size();
        rentalService.addCarToInventory("SEDAN", 5);
        int afterSedanCount = rentalService.getAvailableCars("SEDAN", LocalDateTime.now().plusDays(1), 1).size();
        assertEquals(initialSedanCount + 5, afterSedanCount, "After adding cars to inventory, count should increase");
    }

}
