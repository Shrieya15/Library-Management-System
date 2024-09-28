package project;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) {
        for (Student s : studentList) {
            if (s.getRegNum().equals(student.getRegNum())) {
                return;
            }
        }
        studentList.add(student);
    }

    public String showAllStudents() {
        StringBuilder result = new StringBuilder();
        for (Student s : studentList) {
            result.append(s.toString()).append("\n");
        }
        return result.length() == 0 ? "No Students Registered." : result.toString();
    }

    public Student getStudentByRegNum(String regNum) {
        for (Student s : studentList) {
            if (s.getRegNum().equals(regNum)) {
                return s;
            }
        }
        return null;
    }
}

