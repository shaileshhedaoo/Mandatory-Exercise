package Controller;
import Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create-student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id)
    {
        return studentService.getStudentById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents()
    {
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails)
    {
        return new ResponseEntity<>(studentService.updateStudent(id, studentDetails),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
