package pb.edu.airportproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.edu.airportproject.Model.Flight;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long > {
}