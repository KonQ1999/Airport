package pb.edu.airportproject.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
}
