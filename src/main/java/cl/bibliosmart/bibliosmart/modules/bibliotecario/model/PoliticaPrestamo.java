 package cl.bibliosmart.bibliosmart.modules.bibliotecario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "politica_prestamo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PoliticaPrestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dias_maximos")
    private Integer diasMaximos;

    @Column(name = "dias_tolerancia")
    private Integer diasTolerancia;

    @Column(name = "dias_renovaciones")
    private Integer diasRenovaciones;

    @Column(name = "dias_por_dia")
    private Integer diasPorDia = null;
}
