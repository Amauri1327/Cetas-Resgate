package Cetas.resgate.Repositories;

import Cetas.resgate.Entities.Resgate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ResgateRepository extends JpaRepository<Resgate, Long> {
}
