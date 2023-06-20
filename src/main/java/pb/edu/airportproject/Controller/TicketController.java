package pb.edu.airportproject.Controller;

import org.springframework.web.bind.annotation.*;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Model.Ticket;
import pb.edu.airportproject.Service.FlightService;
import pb.edu.airportproject.Service.TicketService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @PostMapping("{flightCode}")
    public int buyTicket(@PathVariable Long flightCode) {
        return ticketService.bookTicket(flightCode);
    }


    @GetMapping("pdf/{flightCode}")
    public void generateTicketPDF(@PathVariable Long flightCode) throws IOException {
        ticketService.generateTicketPDF(flightCode);
    }

    @GetMapping("checkReservation")
    public Boolean checkTicketReservation(@RequestParam String flightCode, @RequestParam int seatNumber) {
        return ticketService.checkReservation(Long.parseLong(flightCode), seatNumber);
    }

}
