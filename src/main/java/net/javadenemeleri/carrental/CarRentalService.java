package net.javadenemeleri.carrental;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRentalService {

    private final Map<CarType,Integer> carInventory = new HashMap<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public CarRentalService() {
        carInventory.put(CarType.SEDAN, 10);
        carInventory.put(CarType.SUV, 5);
        carInventory.put(CarType.VAN, 3);
    }

    public boolean makeReservation(Reservation reservation) throws InvalidReservationException {
        if(isAvailable(reservation.getCarType() , reservation.getStartDate(),reservation.getDayTime())){
            reservations.add(reservation);
            return true;
        }else {
            return false;
        }
    }

    public boolean isAvailable(CarType carType, LocalDateTime startDate,int dayTime){
        long overLappingCount = reservations.stream()
                .filter(r->r.getCarType().equals(carType)
                        && r.getStartDate().isBefore(startDate.plusDays(dayTime))
                        && r.getStartDate().plusDays(r.getDayTime()).isAfter(startDate))
                .count();

        return (carInventory.get(carType) - overLappingCount) > 0;

    }

    public void addCarToInventory(CarType carType, int quantity){
        if(carType == null || quantity <= 0){
            throw new IllegalArgumentException("carType cannot be null and quantity must be greater than 0!");
        }
        carInventory.put(carType,carInventory.getOrDefault(carType,0)+quantity);
    }

}
