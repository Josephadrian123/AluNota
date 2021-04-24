package br.edu.ifpb.jaas.dao;

import java.io.Serializable;

import javax.inject.Named;

@Named(value = "iGenericDAO")
public interface IGenericDAO<T, PK extends Serializable> {
	T insert(T t);
	T find(PK id);
	T update(T t);
	void delete(T t);
	void beginTransaction();
	void commit();
	void rollback();
	void refresh(T t);
}
