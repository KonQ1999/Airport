package pb.edu.airportproject.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Table(name = "FLIGHT")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long flyCode;

    public String cityFrom;
    public String cityTo;
    public LocalDateTime departureDate;
    public int seatsNumber;

    public Flight() {

    }

    public Flight(Long flyCode, String cityFrom, String cityTo, LocalDateTime departureDate, int seatsNumber) {
        this.flyCode = flyCode;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.departureDate = departureDate;
        this.seatsNumber = seatsNumber;
    }
}