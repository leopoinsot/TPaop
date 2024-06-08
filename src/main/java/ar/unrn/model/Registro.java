package ar.unrn.model;

import org.aspectj.lang.JoinPoint;

public interface Registro {
	void guardarMetodoLlamado(JoinPoint joinPoint);
}
