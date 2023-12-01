package com.tonysanginez.cuentasApi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimientos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Movimientos implements Serializable{
	
	 private static final long serialVersionUID = 1L;

	    @Id
	    @NotNull
	    @EqualsAndHashCode.Include
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "codigo_movimiento")
	    private Long codigoMovimiento;
	    
	    @NotNull
	    @Column(name = "fecha")
	    private Date fecha;
	    
	    @NotNull
	    @Column(name = "tipo_movimiento")
	    private String tipoMovimiento;
	    
	    @NotNull
	    @Column(name = "valor")
	    private Double valor;
	    
	    @NotNull
	    @Column(name = "saldo")
	    private Double saldo;
	    
	    @NotNull
	    @Column(name = "codigo_cuenta")
	    private Long codigoCuenta;

}
