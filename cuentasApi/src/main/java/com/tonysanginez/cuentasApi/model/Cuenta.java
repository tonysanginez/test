package com.tonysanginez.cuentasApi.model;

import java.io.Serializable;
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
@Table(name = "cuenta")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	 @Id
	 @NotNull
	 @EqualsAndHashCode.Include
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "codigo_cuenta")
	 private Long codigoCuenta;
	 
	 
	 @NotNull
	 @Column(name = "numero_cuenta")
	 private String numeroCuenta;
	 
	 @NotNull
	 @Column(name = "tipo_cuenta")
	 private String tipoCuenta;
	 
	 @NotNull
	 @Column(name ="saldo_inicial")
	 private Double saldoInicial;
	 
	 @NotNull
	 @Column(name ="estado")
	 private String estado;
	 
	 @NotNull
	 @Column(name="codigo_cliente")
	 private Long codigoCliente;
	 
	 
	 
	 

}
