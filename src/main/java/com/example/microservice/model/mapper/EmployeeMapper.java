/**
 * 
 */
package com.example.microservice.model.mapper;

import org.mapstruct.Mapper;

import com.example.microservice.model.Employee;
import com.example.microservice.model.EmployeeRequestDTO;

/**
 * @author govindarajuv
 *
 */
@Mapper
public interface EmployeeMapper {
	Employee sourceToDestination(EmployeeRequestDTO source);
}
