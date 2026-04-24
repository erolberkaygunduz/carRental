package net.javadenemeleri.carrental;

import java.time.LocalDateTime;

public class Reservation {

    private CarType carType;
    private LocalDateTime startDate;
    private int dayTime;

    public Reservation() {
    }

    public Reservation(CarType carType, LocalDateTime startDate, int dayTime) throws InvalidReservationException {
        validate(carType, startDate, dayTime);
        this.carType = carType;
        this.startDate = startDate;
        this.dayTime = dayTime;
    }

    private void validate(CarType carType, LocalDateTime startDate, int dayTime) throws InvalidReservationException {
        if (carType == null) {
            throw new InvalidReservationException("carType cannot be null!");
        }

        if (startDate == null) {
            throw new InvalidReservationException("startDate cannot be null!");
        }

        if (startDate.isBefore(LocalDateTime.now())) {
            throw new InvalidReservationException(
                "startDate cannot not be before than today" );
        }

        if (dayTime <= 0) {
            throw new InvalidReservationException("dayTime must be greater than 0!");
        }
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) throws InvalidReservationException {
        if (carType == null) {
            throw new InvalidReservationException("carType cannot be null!");
        }
        this.carType = carType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) throws InvalidReservationException {
        if (startDate == null) {
            throw new InvalidReservationException("startDate cannot be null!");
        }
        if (startDate.isBefore(LocalDateTime.now())) {
            throw new InvalidReservationException(
                "startDate cannot not be before than today");
        }
        this.startDate = startDate;
    }

    public int getDayTime() {
        return dayTime;
    }

    public void setDayTime(int dayTime) throws InvalidReservationException {
        if (dayTime <= 0) {
            throw new InvalidReservationException("dayTime must be greater than 0!");
        }
        this.dayTime = dayTime;
    }
}
