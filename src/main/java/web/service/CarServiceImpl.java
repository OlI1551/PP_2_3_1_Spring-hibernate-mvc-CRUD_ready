package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImpl implements CarService {
    private static Long CAR_COUNT = 0L;
    private List<Car> cars;

    // можно использовать конструктор для создания машин или блок инициализации
    {
        cars = new ArrayList<>();

        cars.add(new Car(++CAR_COUNT, "Toyota Land Cruiser Prado", 70));
        cars.add(new Car(++CAR_COUNT, "Toyota Land Cruiser Prado", 90));
        cars.add(new Car(++CAR_COUNT, "Toyota Land Cruiser Prado", 120));
        cars.add(new Car(++CAR_COUNT, "Toyota Land Cruiser Prado", 150));
        cars.add(new Car(++CAR_COUNT, "Toyota Land Cruiser Prado", 250));
    }

    public List<Car> getCarsList() {
        return cars;
    }

}
