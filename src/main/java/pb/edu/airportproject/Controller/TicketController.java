package pb.edu.airportproject.Controller;

import org.springframework.web.bind.annotation.*;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Model.Ticket;
import pb.edu.airportproject.Service.FlightService;
import pb.edu.airportproject.Service.TicketService;

import java.util.List;


@RequestMapping("tickets")
@RestController
public class TicketController {

    public final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
    @PostMapping("/{flightId}")
    public int buyTicket(@PathVariable Long flightId) {
        return ticketService.bookTicket(flightId);
    }


}
