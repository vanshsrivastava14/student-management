package com.example.studentmanagement.service;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class StudentService {
 private final StudentRepository r; public StudentService(StudentRepository r){this.r=r;}
 public Student add(Student s){if(r.byId(s.getStudentId()).isPresent())throw new IllegalArgumentException("Duplicate student ID");if(r.byEmail(s.getEmail()).isPresent())throw new IllegalArgumentException("Email already exists");r.add(s);return s;}
 public List<Student> all(){return r.all();}
 public Student byId(Long id){return r.byId(id).orElseThrow(()->new NoSuchElementException("Student not found"));}
 public Student update(Long id,Student s){if(s.getProgrammingScore()==null||s.getMathematicsScore()==null)throw new IllegalArgumentException("Both scores are required");if(!r.update(id,s))throw new NoSuchElementException("Student not found");return byId(id);}
 public void delete(Long id){if(!r.delete(id))throw new NoSuchElementException("Student not found");}
 public List<Student> merit(){return r.merit();}
 public List<Student> search(String email,String course){if(email!=null)return r.byEmail(email).map(List::of).orElse(List.of());if(course!=null)return r.byCourse(course);return List.of();}
 public List<Student> needs(){return r.needs();} public Map<String,List<Long>> sameSkills(){return r.sameSkills();}
}