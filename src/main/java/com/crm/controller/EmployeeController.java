package com.crm.controller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {


    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //http://localhost:8080/api/v1/employee/add
    @PostMapping("/add")
    public ResponseEntity<?>  addEmployee(@Valid @RequestBody EmployeeDto employeeDto,
    BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
        return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

        EmployeeDto Dto = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(Dto, HttpStatus.CREATED);

    }

    //http://localhost:8080/api/v1/employee?id=1
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestParam Long id,
            @RequestBody EmployeeDto dto) {
            EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee?pageSize=5&pageNo=0&sortBy=email
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee(
          @RequestParam(name = "pageSize",required = false,defaultValue = "5")int pageSize,
           @RequestParam(name = "pageNo",required = false,defaultValue = "0")int pageNo,
          @RequestParam(name = "sortBy",required = false,defaultValue = "name")String sortBy,
          @RequestParam(name = "sortDir",required = false,defaultValue = "asc")String sortDir

    ) {

        List<EmployeeDto> employeeDto = employeeService.getEmployee(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/employeeId/1
    @GetMapping ("/employeeId/{empId}")

    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long empId) {

        EmployeeDto employeeDto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }


}