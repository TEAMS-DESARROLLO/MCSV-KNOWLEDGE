package com.bussinesdomain.knowledge.commons;

import java.util.List;

import org.springframework.data.domain.Page;

import java.util.Optional;


public interface IBaseInterfaceService<T, ID> {

	public Page<?> pagination(Integer pagenumber, Integer rows, List<SortModel> sortModel, Filter filter);

	public T create(T entidad);

	public List<T> createAll(List<T> entidades);

	public Optional<T> readById(ID id);

	public T update(T entidad, ID id);

	public void delete(T entidad);

	public void deleteById(ID id);

	public Boolean exists(ID id);

	public Long count();

	public List<T> getAll();
}