package pb.edu.airportproject.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "TICKET")
@Data
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long ticketId;

    public Long flightId;

    public int seatNumber;

    public Ticket(Long flightId, int seatNumber) {
        this.flightId = flightId;
        this.seatNumber = seatNumber;
    }

    public Ticket() {

    }

    public Ticket(Long ticketId, Long flightId, int seatNumber) {
        this.ticketId = ticketId;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
    }


    public static class TicketWithSeatNumber {
        public Flight flight;
        public int seatNumber;

        public TicketWithSeatNumber(Flight flight, int seatNumber) {
            this.flight = flight;
            this.seatNumber = seatNumber;
        }
    }

    public static class TicketWithLeftSeats {
        public Flight flight;
        public int seatNumber;

        public TicketWithLeftSeats(Flight flight, int seatNumber) {
            this.flight = flight;
            this.seatNumber = seatNumber;
        }
    }
}
