package com.crm.service;


import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;


    //create add method for add employee in db
         public EmployeeDto addEmployee( EmployeeDto employeeDto) {
             Employee employee = mapToEntity(employeeDto);
        Employee emp =  employeeRepository.save(employee);
        EmployeeDto dto =mapToDto(emp);
        //dto.setDate(new Date());
        return dto;

     }

    public void deleteEmployee(Long id) {
             employeeRepository.deleteById(id);

    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        employee.setId(id);

        Employee updateEmployee =  employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updateEmployee);
        return employeeDto;
    }

    public List<EmployeeDto> getEmployee(int pageSize, int pageNo, String sortBy, String sortDir) {
         Sort sort =   sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageSize,pageNo, sort);//it return back the object of the type  pageable and Sort.by(sortBy) converts the string to
        Page<Employee>  all= employeeRepository.findAll(page);
        List<Employee> employees = all.getContent(); //this method convert the page of employee into list
       // List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> dto = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;

    }


    public EmployeeDto mapToDto(Employee employee){
            EmployeeDto employeeDto=modelMapper.map(employee, EmployeeDto.class);
//          EmployeeDto employeeDto=new EmployeeDto();
//            employeeDto.setId(employee.getId());
//             employeeDto.setName(employee.getName());
//             employeeDto.setEmailId(employee.getEmailId());
//             employeeDto.setMobile(employee.getMobile());
             return  employeeDto;


    }

    public Employee mapToEntity(EmployeeDto employeeDto){
        Employee emp = modelMapper.map(employeeDto, Employee.class);
//             Employee employee=new Employee();
//        employee.setId(employeeDto.getId());
//             employee.setName(employeeDto.getName());
//             employee.setEmailId(employeeDto.getEmailId());
//             employee.setMobile(employeeDto.getMobile());
             return emp;
    }

    public EmployeeDto getEmployeeById(long empId) {
         Employee employee = employeeRepository.findById(empId).orElseThrow(
                 () ->new ResourceNotFound("record not found with id "+ empId)
         );
        EmployeeDto dto = mapToDto(employee);
        return dto;
    }

    
}
