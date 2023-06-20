package pb.edu.airportproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Other.PdfGenerator;
import pb.edu.airportproject.Repository.FlightRepository;
import pb.edu.airportproject.Service.FlightService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class FilipRoszkowskiAirportProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilipRoszkowskiAirportProjectApplication.class, args);
    }
}
