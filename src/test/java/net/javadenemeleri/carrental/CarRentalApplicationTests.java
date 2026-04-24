package net.javadenemeleri.carrental;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CarRentalApplicationTests {

    @Test
    void testValidReservation() throws InvalidReservationException {
        CarRentalService rentalService = new CarRentalService();
        Reservation reservation = new Reservation(
            CarType.SEDAN,
            LocalDateTime.now().plusDays(5),
            3
        );

        boolean success = rentalService.makeReservation(reservation);
        assertTrue(success, "Reservation should be successful for valid input");
    }

    @Test
    void testPastDateReservation() {
        assertThrows(InvalidReservationException.class, () -> new Reservation(
            CarType.SEDAN,
            LocalDateTime.now().minusDays(1),
            3
        ), "Old date reservation should throw InvalidReservationException");
    }

    @Test
    void testNullCarType() {
        assertThrows(InvalidReservationException.class, () -> new Reservation(
                null,
                LocalDateTime.now().plusDays(5),
                3
            ),"null car type should throw InvalidReservationException");
    }

    @Test
    void testInvalidDayTime() {
        assertThrows(InvalidReservationException.class, () ->
            new Reservation(
                CarType.SUV,
                LocalDateTime.now().plusDays(2),
                0
            ), "0 or negative day time should throw InvalidReservationException");
    }

    @Test
    void testAddCarInventory(){
        CarRentalService rentalService = new CarRentalService();
        rentalService.addCarToInventory(CarType.SEDAN, 5);
        assertTrue(rentalService.isAvailable(CarType.SEDAN, LocalDateTime.now().plusDays(1), 1));
    }

}
