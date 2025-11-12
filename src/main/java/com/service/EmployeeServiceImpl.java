package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EmployeeDao;
import com.dto.EmployeeDTO;
import com.entity.EmployeeEntity;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public void registerEmp(EmployeeDTO employeeDTO) {
		 
		EmployeeEntity employeeEntity=new EmployeeEntity();
		
		//copy the data from employeeDTO to employeeEntity
		BeanUtils.copyProperties(employeeDTO, employeeEntity);
		
		employeeDao.save(employeeEntity);
	}

	@Override
	public EmployeeDTO authenticate(String emailId, String password) {
		Optional<EmployeeEntity> optional=employeeDao.findByEmailIdAndPassword(emailId,password);
		
		EmployeeDTO employeeDTO=null;
		
		  if(optional.isPresent()) {
			 //if value is present 
			  EmployeeEntity  employeeEntity=optional.get();
			  
			  employeeDTO=new EmployeeDTO();
			  
			  BeanUtils.copyProperties(employeeEntity, employeeDTO);
			  
			  return employeeDTO;	//it is having values    ---> NOT_NULL
		  }else {
			  //if value not present
			  
			  return employeeDTO;   //it is not having value   ---> NULL
		  }
	
		  
		  //fetch all records  -----------> findAll()
	}

	@Override
	public List<EmployeeDTO> findAllEmployees() {
		List<EmployeeEntity> employeeEntity=employeeDao.findAll();
		
		//now we have to copy these objects(records)to employeeDTO
		List<EmployeeDTO> employeeDtoList=new ArrayList<>();
		
		    if(employeeEntity.size()>0) {
		    	for(EmployeeEntity   tempa:employeeEntity) {
		    		EmployeeDTO employeeDTO=new EmployeeDTO();
		    		
		    		BeanUtils.copyProperties(tempa,employeeDTO);
		    		employeeDtoList.add(employeeDTO);   //in the first iteration, first object added in blank arrayList 
		    		                                   //1   +1   +  1  + 1  + 1    <===5 records added in the form of objects
		    	}
		   }
	
		return employeeDtoList;    //it is collection of records in the form of objects
	}

	
	//delete operation
	@Override
	public void deleteEmp(int employeeId) {
		employeeDao.deleteById(employeeId);
		
	}
	
	
	
	
	
}
