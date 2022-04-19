package com.example.Payroll;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class Input{

    @Id Long id;
    List<String> data;

    Input(){}

    Input(List<String> data)
    {
        id = 1l;
        this.data = data;
    }

    public void setData(List<String> data)
    {
        this.data = data;
    }

    public List<String> getData()
    {
        return data;
    }
}

//interface InputRepository extends JpaRepository<Input, Long> {
//
//}


@RestController
class EmployeeController {

    public boolean isNumber(String str)
    {
        try
        {
            Integer.parseInt(str);
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping(
        "/employees/bfhl"
    )
    String newEmployee(@RequestBody Input input) {
        System.out.println(input.getData());

        ArrayList<String> num = new ArrayList<>();
        ArrayList<String> alpha = new ArrayList<>();

        for (String str : input.data)
        {
            if (isNumber(str))
            {
                num.add(str);
            }
            else
            {
                alpha.add(str);
            }
        }

        System.out.println(num);
        System.out.println(alpha);

        Employee emp = new Employee(num , alpha);

        return emp.toString();
    }

    // Single item

    @GetMapping("/employees/bfhl")
    EntityModel<Employee> one() {

        Employee employee = repository.findById(1l).get();

        System.out.println(employee.toString());

        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).one()).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

//    @PutMapping("/employees/{id}")
//    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    return repository.save(newEmployee);
//                });
//    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}