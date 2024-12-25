package Cetas.resgate.Repositories;

import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Entities.Resgate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ResgateRepository extends JpaRepository<Resgate, Long> {

    @Query("SELECT r FROM Resgate r WHERE LOWER(r.specie) = LOWER(:specie) AND r.data BETWEEN :startDate AND :endDate")
    List<Resgate> findRescuesBySpecieAndDateRange(@Param("specie") String specie,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Resgate r WHERE r.data BETWEEN :startDate AND :endDate")
    List<Resgate> findRescueByDateRange(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);

    @Query("SELECT c FROM Resgate c WHERE LOWER(c.city) = LOWER(:city)")
    List<ResgateDto> findRescueByCityByDateRange(@Param("city") String city,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

}
