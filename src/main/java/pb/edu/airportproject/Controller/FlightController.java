package pb.edu.airportproject.Controller;

import org.springframework.web.bind.annotation.*;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Service.FlightService;
import pb.edu.airportproject.Service.TicketService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("flights")
@RestController
public class FlightController {

    public final FlightService flightService;
    public final TicketService ticketService;

    public FlightController(FlightService flightService, TicketService ticketService) {
        this.flightService = flightService;
        this.ticketService = ticketService;
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


    @GetMapping("pdf")
    public void generateFlightsPDF() {
        ticketService.generateFlightsPDF();
    }





    @RequestMapping("/{id}")
    public Flight getFlight(@PathVariable Long id) {
       return flightService.getFlight(id);
    }

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