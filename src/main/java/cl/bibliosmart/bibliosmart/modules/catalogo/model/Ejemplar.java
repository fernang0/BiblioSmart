package cl.bibliosmart.bibliosmart.modules.catalogo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ejemplar")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ejemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_ejemplar")
    private String codigoEjemplar;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoEjemplar estado;

    @Column(name = "ubicacion")
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
    
    public Boolean isDisponible() {
        return this.getEstado() == EstadoEjemplar.DISPONIBLE;
    }

}
