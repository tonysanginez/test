package com.tonysanginez.clienteApi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cliente")
    private Long codigoCliente;

    @OneToOne
    @JoinColumn(name = "codigo_persona", referencedColumnName = "codigo_persona")
    private Persona persona;

    @NotNull
    @Column(name = "clave")
    private String clave;

    @NotNull
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
}