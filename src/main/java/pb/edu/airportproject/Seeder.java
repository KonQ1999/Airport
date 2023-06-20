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


        Flight flight1 = new Flight(1l, "Białystok", "Warszawa",  LocalDateTime.of(2023, 7, 28, 10, 33, 38, 640000) , 40);
        Flight flight2 = new Flight(2l, "Białystok", "Kielce",  LocalDateTime.of(2024, 8, 14, 14, 19, 28, 640000), 80);
        Flight flight3 = new Flight(3l, "Białystok", "Lublin",  LocalDateTime.of(2023, 9, 15, 12, 21, 48, 640000), 50);
        Flight flight4 = new Flight(4l, "Madryt", "Bialystok",  LocalDateTime.of(2024, 12, 28, 9, 5, 18, 640000), 100);
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
