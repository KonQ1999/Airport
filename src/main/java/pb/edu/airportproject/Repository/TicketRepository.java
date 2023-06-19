package pb.edu.airportproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.edu.airportproject.Model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long > {
}
