package pb.edu.airportproject.Service;

import org.springframework.stereotype.Service;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Model.Ticket;
import pb.edu.airportproject.Repository.FlightRepository;
import pb.edu.airportproject.Repository.TicketRepository;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    public FlightService(FlightRepository flightRepository, TicketRepository ticketRepository) {
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        List<Ticket> tickets = ticketRepository.findAll();

        for (Flight flight: flights) {
            for (Ticket ticket : tickets) {
                if (ticket.flightId.equals(flight.flyCode)) {
                    flight.seatsNumber = flight.seatsNumber - 1;
                }
            }
        }

        return flights;
    }

    public Flight getFlight(Long id) {
        Flight flight = flightRepository.findById(id).get();
        return flight;
    }

    public ArrayList<Flight> searchFlightTo(String cityName) {
        List<Flight> flights = flightRepository.findAll();
        ArrayList<Flight> foundFlights = new ArrayList();

        for (Flight flight: flights){
            if (flight.cityTo.equals(cityName)) foundFlights.add(flight);
        }

        return foundFlights;
    }

    public ArrayList<Flight> searchFlightFrom(String cityName) {
        List<Flight> flights = flightRepository.findAll();
        ArrayList<Flight> foundFlights = new ArrayList();

        for (Flight flight: flights){
            if (flight.cityFrom.equals(cityName)) foundFlights.add(flight);
        }

        return foundFlights;
    }


    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public void updateFlight(String id, Flight flight) {
		/*
		for(Topic t : topics) {
			if(id.equals(t.getId())){
				t.setId(topic.getId());
				t.setDescription(topic.getDescription());
				t.setName(topic.getName());
				return;
			}
		}*/
        flightRepository.save(flight);
    }

    public void delFlight(Long id) {
        //topics.removeIf(t -> t.getId().equals(id));
        flightRepository.deleteById(id);
    }
}
