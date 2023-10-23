package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;
import java.util.stream.Collectors;


@Controller
public class CarsController {

    private final CarService carService;

    @Autowired
    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String index(@RequestParam(value = "count", required = false, defaultValue = "0") int count,
                        Model model) {
        // При запросе /cars выводить весь список. При запросе /cars?count=2 должен отобразиться список из 2 машин,
        // при /cars?count=3 - из 3, и тд. При count ≥ 5 выводить весь список машин.

        if (count <= 0) {
            model.addAttribute("cars", carService.getCarsList());
        } else if (count >= 5) {
            model.addAttribute("cars", carService.getCarsList());
        } else {
            model.addAttribute("cars", carService.getCarsList().stream().limit(count).collect(Collectors.toList()));
        }

        return "cars";
    }
}
