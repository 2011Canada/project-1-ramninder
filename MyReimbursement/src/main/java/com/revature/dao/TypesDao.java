package com.revature.dao;

import java.util.List;

import com.revature.models.Type;

public interface TypesDao {
	
	
	public List<Type> findAllTypes();
	
	public Type findByTypeId(Integer id);
	

	public Type saveType(Type obj);
	
	public Type updateType(Type obj);
	
	public void deleteType(Type obj);
	
	
	

}
