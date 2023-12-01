package com.tonysanginez.cuentasApi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tonysanginez.cuentasApi.dto.MovimientosDTO;
import com.tonysanginez.cuentasApi.model.Movimientos;
@Service
public class MovimientosDAO extends BaseDAO<Movimientos,Long>{
	@PersistenceContext
	private EntityManager em;
	
	protected MovimientosDAO() {
		super(Movimientos.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
    public void persist(Movimientos t) throws PersistenceException {
        super.persist(t);
    }

	public Map<String, Object> obtenerEstadoCuenta(Long codigoCliente, Date fechaInicio, Date fechaFin) {
		Map<String, Object> resultado = new HashMap<>();
		StringBuilder strQuery = new StringBuilder();
		try {
            strQuery.append(" SELECT  mov.fecha as fecha, ");
            strQuery.append("         p.nombres as cliente, ");
            strQuery.append("         cue.numero_cuenta as numeroCuenta, ");
            strQuery.append("         cue.tipo_cuenta as tipo, ");
            strQuery.append("         cue.saldo_inicial as saldoInicial, ");
            strQuery.append("         cue.estado as estado, ");
            strQuery.append("         mov.valor as movimiento, ");
            strQuery.append("         mov.saldo as saldoDisponible ");
            strQuery.append(" FROM    movimientos mov, ");
            strQuery.append("         cuenta cue, ");
            strQuery.append("         persona p, ");
            strQuery.append("         cliente c ");
            strQuery.append(" WHERE   c.codigo_persona=p.codigo_persona ");
            strQuery.append(" AND     cue.codigo_cliente=c.codigo_cliente ");
            strQuery.append(" AND     mov.codigo_cuenta=cue.codigo_cuenta  ");
            
            if (!ObjectUtils.isEmpty(codigoCliente)) {
                strQuery.append(" AND     c.codigo_cliente = :codigoCliente ");
            }
            if (!ObjectUtils.isEmpty(fechaInicio) && !ObjectUtils.isEmpty(fechaFin)) {
                strQuery.append(" AND     mov.fecha between :fechaInicio and :fechaFin ");
            }
            
                strQuery.append(" ORDER BY fecha desc");
            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);   
            query.setParameter("codigoCliente", codigoCliente);

            if (!ObjectUtils.isEmpty(fechaInicio) && !ObjectUtils.isEmpty(fechaFin)) {
                query.setParameter("fechaInicio", fechaInicio);
                query.setParameter("fechaFin", fechaFin);
            }
          

            List<Tuple> lsResult = query.getResultList();
            List<MovimientosDTO> lsMovimientos = new ArrayList<>();
            if (lsResult != null) {
            	MovimientosDTO objMovimiento = null;
                for (Tuple tuple : lsResult){
                	objMovimiento = new MovimientosDTO();
                	objMovimiento.setFecha(tuple.get("fecha", Date.class));
                	objMovimiento.setNombres(tuple.get("cliente",String.class));
                	objMovimiento.setNumeroCuenta(tuple.get("numeroCuenta",String.class));
                	objMovimiento.setTipoCuenta(tuple.get("tipo",String.class));
                	objMovimiento.setSaldoInicial(tuple.get("saldoInicial",Number.class).doubleValue());
                	objMovimiento.setEstado(tuple.get("estado",String.class));
                	objMovimiento.setValor(tuple.get("movimiento",Number.class).doubleValue());
                	objMovimiento.setSaldo(tuple.get("saldoDisponible",Number.class).doubleValue());
                	lsMovimientos.add(objMovimiento);
                }
            }
            resultado.put("movimientos", lsMovimientos);
            return resultado;
        } catch (NoResultException e) {
            return null;
        }
	}
}
