package cl.bibliosmart.bibliosmart.modules.bibliotecario.model;

import java.time.LocalDate;
import cl.bibliosmart.bibliosmart.modules.catalogo.model.Ejemplar;
import cl.bibliosmart.bibliosmart.modules.login.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Table(name = "prestamo")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ejemplar_id")
    private Ejemplar ejemplar;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;;

    @Column(name = "fecha_prestamo")
    private LocalDate fechaPrestamo;

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    @Column(name = "dias_atraso")
    private Integer diasAtraso;

    @Column(name = "multa")
    private Integer multa;

    @ManyToOne
    @JoinColumn(name = "politica_prestamo_id")
    private PoliticaPrestamo politicaPrestamo;
}
