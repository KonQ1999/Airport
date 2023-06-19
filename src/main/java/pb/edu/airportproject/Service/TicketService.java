package pb.edu.airportproject.Service;

import org.springframework.stereotype.Service;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Model.Ticket;
import pb.edu.airportproject.Repository.FlightRepository;
import pb.edu.airportproject.Repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;


    public TicketService(TicketRepository ticketRepository, FlightRepository flightRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
    }

    public int bookTicket(Long flightId) {
        int currentSeatsAmount = 0;
        List<Ticket> allFlightTickets = ticketRepository.findAll();
        for (Ticket ticket: allFlightTickets) {
            if (ticket.flightId.equals(flightId)) {
                currentSeatsAmount++;
            }
        }

        List<Flight> flights = flightRepository.findAll();

        for (Flight flight: flights) {
            if (flight.flyCode.equals(flightId) && currentSeatsAmount < flight.seatsNumber) {
                Ticket ticket = new Ticket(flightId, ++currentSeatsAmount);

                ticketRepository.save(ticket);
                return currentSeatsAmount;
            }
        }
        return 0;
    }

    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }
}
