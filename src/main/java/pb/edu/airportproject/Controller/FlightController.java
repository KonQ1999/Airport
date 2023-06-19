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

    @GetMapping("flightsTo/{cityName}")
    public ArrayList<Flight> searchFlightTo(@PathVariable String cityName) {
        return flightService.searchFlightTo(cityName);
    }

    @GetMapping("flightsFrom/{cityName}")
    public ArrayList<Flight> searchFlightFrom(@PathVariable String cityName) {
        return flightService.searchFlightFrom(cityName);
    }




    //Nie uzywane
        //    @RequestMapping("/{id}")
//    public Flight getFlight(@PathVariable Long id) {
//        return flightService.getFlight(id);
//    }

        @RequestMapping(method= RequestMethod.POST, value="")
    public void addFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public void updateFlight(@RequestBody Flight topic, @PathVariable String id) {
        flightService.updateFlight(id, topic);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/{id}")
    public void delFlight(@PathVariable Long id) {
        flightService.delFlight(id);
    }
}