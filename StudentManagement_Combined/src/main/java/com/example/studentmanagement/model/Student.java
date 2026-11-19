package com.example.studentmanagement.model;
import jakarta.validation.constraints.*;
import java.util.*;
public class Student {
 @NotNull private Long studentId;
 @NotBlank private String studentName;
 @NotBlank @Email private String email;
 @NotBlank private String courseName;
 @NotNull @Min(0) @Max(100) private Integer programmingScore;
 @NotNull @Min(0) @Max(100) private Integer mathematicsScore;
 private List<String> technicalSkills = new ArrayList<>();
 public Student() {}
 public Student(Long id,String name,String email,String course,Integer p,Integer m,List<String> skills) {
  studentId=id; studentName=name; this.email=email; courseName=course; setProgrammingScore(p); setMathematicsScore(m); setTechnicalSkills(skills);
 }
 public double getAverageScore(){return programmingScore==null||mathematicsScore==null?0:(programmingScore+mathematicsScore)/2.0;}
 public String getPerformanceCategory(){double a=getAverageScore();return a>=85?"Excellent":a>=70?"Good":a>=50?"Satisfactory":"Needs Improvement";}
 public String getStrongerSubject(){if(programmingScore==null||mathematicsScore==null)return "Not available";return programmingScore>mathematicsScore?"Programming":mathematicsScore>programmingScore?"Mathematics":"Both Equal";}
 private void check(Integer n){if(n!=null&&(n<0||n>100))throw new IllegalArgumentException("Scores must be between 0 and 100");}
 public Long getStudentId(){return studentId;} public void setStudentId(Long v){studentId=v;}
 public String getStudentName(){return studentName;} public void setStudentName(String v){studentName=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getCourseName(){return courseName;} public void setCourseName(String v){courseName=v;}
 public Integer getProgrammingScore(){return programmingScore;} public void setProgrammingScore(Integer v){check(v);programmingScore=v;}
 public Integer getMathematicsScore(){return mathematicsScore;} public void setMathematicsScore(Integer v){check(v);mathematicsScore=v;}
 public List<String> getTechnicalSkills(){return new ArrayList<>(technicalSkills);}
 public void setTechnicalSkills(List<String> v){technicalSkills=v==null?new ArrayList<>():new ArrayList<>(v);}
 @Override public String toString(){return "Student{"+studentId+", "+studentName+", average="+getAverageScore()+", category="+getPerformanceCategory()+", stronger="+getStrongerSubject()+", skills="+technicalSkills+"}";}
}