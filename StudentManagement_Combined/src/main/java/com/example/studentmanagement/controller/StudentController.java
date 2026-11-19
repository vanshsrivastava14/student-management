package com.example.studentmanagement.controller;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/students")
public class StudentController {
 private final StudentService s; public StudentController(StudentService s){this.s=s;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Student add(@Valid @RequestBody Student x){return s.add(x);}
 @GetMapping public List<Student> all(){return s.all();}
 @GetMapping("/{id}") public Student byId(@PathVariable Long id){return s.byId(id);}
 @PutMapping("/{id}") public Student update(@PathVariable Long id,@RequestBody Student x){return s.update(id,x);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){s.delete(id);}
 @GetMapping("/merit-list") public List<Student> merit(){return s.merit();}
 @GetMapping("/search") public List<Student> search(@RequestParam(required=false)String email,@RequestParam(required=false)String course){return s.search(email,course);}
 @GetMapping("/same-skills") public Map<String,List<Long>> sameSkills(){return s.sameSkills();}
 @GetMapping("/programming-needs-improvement") public List<Student> needs(){return s.needs();}
}