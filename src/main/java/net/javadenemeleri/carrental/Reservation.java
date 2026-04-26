package net.javadenemeleri.carrental;

import java.time.LocalDateTime;

public class Reservation {

    private Car car;
    private LocalDateTime startDate;
    private int dayTime;

    public Reservation() {
    }

    public Reservation(Car car, LocalDateTime startDate, int dayTime) throws InvalidReservationException {
        validate(car, startDate, dayTime);
        this.car = car;
        this.startDate = startDate;
        this.dayTime = dayTime;
    }

    private void validate(Car car, LocalDateTime startDate, int dayTime) throws InvalidReservationException {
        if (car == null) {
            throw new InvalidReservationException("car cannot be null!");
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) throws InvalidReservationException {
        if (car == null) {
            throw new InvalidReservationException("car cannot be null!");
        }
        this.car = car;
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

    // Convenience method to get CarType
    public String getCarType() {
        return car != null ? car.getType() : null;
    }
}
