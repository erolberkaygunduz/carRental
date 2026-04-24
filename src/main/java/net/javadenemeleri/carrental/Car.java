package net.javadenemeleri.carrental;

public abstract class Car {

    private String id;
    private CarType type;

    public Car() {
    }

    public Car(String id, CarType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }
}
