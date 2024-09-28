package Cetas.resgate.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.attribute.standard.Destination;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_resgate")
public class Resgate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String applicant;
    private String phoneApplicant;
    private String specie;
    private String address;
    private String neighborhood;
    private String city;
    private String data;
    private String animalSituation;
    private String animalDestination;
}
