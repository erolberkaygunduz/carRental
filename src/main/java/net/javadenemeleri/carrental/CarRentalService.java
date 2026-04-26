package net.javadenemeleri.carrental;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarRentalService {

    private final List<Car> carInventory = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public CarRentalService() {
        initializeInventory(5, new Sedan());
        initializeInventory(5, new Suv());
        initializeInventory(5, new Van());
    }


    private Car createCar(String carType) {
        return switch (carType) {
            case "SEDAN" -> new Sedan();
            case "SUV" -> new Suv();
            case "VAN" -> new Van();
            default -> throw new IllegalArgumentException("Unknown car type: " + carType);
        };
    }

    public boolean makeReservation(Reservation reservation) throws InvalidReservationException {
        if (isAvailable(reservation.getCar(), reservation.getStartDate(), reservation.getDayTime())) {
            reservations.add(reservation);
            return true;
        } else {
            return false;
        }
    }

    public boolean isAvailable(Car car, LocalDateTime startDate, int dayTime) {
        if (car == null) return false;
        long overlappingCount = reservations.stream()
                .filter(r -> r.getCar().equals(car)
                        && r.getStartDate().isBefore(startDate.plusDays(dayTime))
                        && r.getStartDate().plusDays(r.getDayTime()).isAfter(startDate))
                .count();

        return overlappingCount == 0; // Since each car is unique, check if not overlapping
    }

    public void addCarToInventory(String carType, int quantity) {
        if (carType == null || quantity <= 0) {
            throw new IllegalArgumentException("carType cannot be null and quantity must be greater than 0!");
        }
        for (int i = 1; i <= quantity; i++) {
            Car newCar = createCar(carType);
            carInventory.add(newCar);
        }
    }

    // New method to get available cars by type
    public List<Car> getAvailableCars(String carType, LocalDateTime startDate, int dayTime) {
        return carInventory.stream()
                .filter(car -> car.getType().equals(carType))
                .filter(car -> isAvailable(car, startDate, dayTime))
                .toList();
    }


    private void initializeInventory(int amount, Car car) {
        for (int i = 1; i <= amount; i++) {
            carInventory.add(car);
        }
    }
}
