package pb.edu.airportproject.Controller;

import org.springframework.web.bind.annotation.*;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Service.FlightService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("flights")
@RestController
public class FlightController {

    public final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

//    @RequestMapping("/{id}")
//    public Flight getFlight(@PathVariable Long id) {
//        return flightService.getFlight(id);
//    }


    @GetMapping("/{cityName}")
    public ArrayList<Flight> searchFlightTo(@PathVariable String cityName) {
        return flightService.searchFlightTo(cityName);
    }

//    @RequestMapping("/{cityName}")
//    public Flight searchFlightFrom(@PathVariable String cityName) {
//        return flightService.searchFlightTo(cityName);
//    }



    @RequestMapping(method= RequestMethod.POST, value="")
    public void addFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public void updateTopic(@RequestBody Flight topic, @PathVariable String id) {
        flightService.updateFlight(id, topic);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/{id}")
    public void delTopic(@PathVariable Long id) {
        flightService.delFlight(id);
    }


}