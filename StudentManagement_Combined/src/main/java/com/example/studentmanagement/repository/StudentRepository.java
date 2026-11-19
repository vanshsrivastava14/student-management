package com.example.studentmanagement.repository;
import com.example.studentmanagement.model.Student;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class StudentRepository {
 private final JdbcTemplate db;
 public StudentRepository(JdbcTemplate db){this.db=db;}
 private List<String> skills(Long id){return db.query("SELECT skill FROM student_skills WHERE student_id=?",(r,n)->r.getString(1),id);}
 private Student map(java.sql.ResultSet r,int n)throws java.sql.SQLException{return new Student(r.getLong("student_id"),r.getString("student_name"),r.getString("email"),r.getString("course_name"),r.getInt("programming_score"),r.getInt("mathematics_score"),skills(r.getLong("student_id")));}
 public void add(Student s){db.update("INSERT INTO students VALUES(?,?,?,?,?,?)",s.getStudentId(),s.getStudentName(),s.getEmail(),s.getCourseName(),s.getProgrammingScore(),s.getMathematicsScore());for(String x:s.getTechnicalSkills())db.update("INSERT INTO student_skills VALUES(?,?)",s.getStudentId(),x);}
 public List<Student> all(){return db.query("SELECT * FROM students",this::map);}
 public Optional<Student> byId(Long id){return db.query("SELECT * FROM students WHERE student_id=?",this::map,id).stream().findFirst();}
 public Optional<Student> byEmail(String e){return db.query("SELECT * FROM students WHERE email=?",this::map,e).stream().findFirst();}
 public List<Student> byCourse(String c){return db.query("SELECT * FROM students WHERE course_name=?",this::map,c);}
 public boolean update(Long id,Student s){int n=db.update("UPDATE students SET programming_score=?,mathematics_score=? WHERE student_id=?",s.getProgrammingScore(),s.getMathematicsScore(),id);if(n==0)return false;db.update("DELETE FROM student_skills WHERE student_id=?",id);for(String x:s.getTechnicalSkills())db.update("INSERT INTO student_skills VALUES(?,?)",id,x);return true;}
 public boolean delete(Long id){return db.update("DELETE FROM students WHERE student_id=?",id)>0;}
 public List<Student> merit(){return db.query("SELECT * FROM students ORDER BY programming_score+mathematics_score DESC",this::map);}
 public List<Student> needs(){return db.query("SELECT * FROM students WHERE programming_score<50 AND mathematics_score>=75",this::map);}
 public Map<String,List<Long>> sameSkills(){Map<Set<String>,List<Long>> g=new HashMap<>();for(Student s:all())g.computeIfAbsent(new TreeSet<>(s.getTechnicalSkills()),k->new ArrayList<>()).add(s.getStudentId());Map<String,List<Long>> out=new LinkedHashMap<>();g.forEach((k,v)->{if(v.size()>1)out.put(String.join(", ",k),v);});return out;}
}