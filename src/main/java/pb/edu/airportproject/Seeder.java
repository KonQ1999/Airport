package pb.edu.airportproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Repository.FlightRepository;

import java.time.LocalDateTime;

@Component
public class Seeder implements CommandLineRunner {

    private final FlightRepository flightRepository;

    public Seeder(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public void run(String... args) {
        deleteAllFromRepository(flightRepository);
        Flight flight1 = new Flight(1l, "Białystok", "Warszawa",  LocalDateTime.now(), 100);
        Flight flight2 = new Flight(2l, "Białystok", "Kielce",  LocalDateTime.now(), 100);
        Flight flight3 = new Flight(3l, "Białystok", "Lublin",  LocalDateTime.now(), 100);
        Flight flight4 = new Flight(4l, "Madryt", "Bialystok",  LocalDateTime.now(), 100);
        Flight flight5 = new Flight(5l, "Białystok", "Warszawa",  LocalDateTime.now(), 100);

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);
        flightRepository.save(flight4);
        flightRepository.save(flight5);

    }


    private <T> void deleteAllFromRepository(JpaRepository<T, Long> repository) {
        repository.deleteAll();
    }
}
