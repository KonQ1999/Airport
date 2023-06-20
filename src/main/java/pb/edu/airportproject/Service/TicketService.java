package pb.edu.airportproject.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Model.Ticket;
import pb.edu.airportproject.Other.PdfGenerator;
import pb.edu.airportproject.Repository.FlightRepository;
import pb.edu.airportproject.Repository.TicketRepository;

import java.io.IOException;
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

    public int bookTicket(Long flightCode) {
        int currentSeatsAmount = 0;
        List<Ticket> allFlightTickets = ticketRepository.findAll();
        for (Ticket ticket: allFlightTickets) {
            if (ticket.flightId.equals(flightCode)) {
                currentSeatsAmount++;
            }
        }

        List<Flight> flights = flightRepository.findAll();

        for (Flight flight: flights) {
            if (flight.flyCode.equals(flightCode) && currentSeatsAmount < flight.seatsNumber) {
                Ticket ticket = new Ticket(flightCode, ++currentSeatsAmount);

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

    public void generateTicketPDF(Long flightCode) {
        List<Flight> flights = flightRepository.findAll();
        List<Ticket> tickets = ticketRepository.findAll();

        PdfGenerator pdfGenerator = new PdfGenerator();
        try {
            pdfGenerator.createPdfTicket(flights, tickets , flightCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void generateFlightsPDF() {
        List<Flight> flights = flightRepository.findAll();
        List<Ticket> tickets = ticketRepository.findAll();

        PdfGenerator pdfGenerator = new PdfGenerator();
        try {
            pdfGenerator.createPdfForFly(flights, tickets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Boolean checkReservation(Long flightCode, int seatNumber) {
        List<Flight> flights = flightRepository.findAll();
        List<Ticket> tickets = ticketRepository.findAll();

        for (Flight flight: flights) {
            if (flight.flyCode.equals(flightCode)) {
                for (Ticket ticket : tickets) {
                    if (seatNumber == ticket.seatNumber) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
