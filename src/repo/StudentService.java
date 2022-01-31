package repo;

import com.sun.istack.internal.NotNull;
import dto.ClassDescriptionDTO;
import dto.ClassFilterDTO;
import dto.StudentClassDTO;
import dto.StudentDTO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentService {

    public boolean saveStudent(@NotNull StudentDTO studentDTO) throws Exception {
        boolean result = CrudUtil.executeUpdate(
                "Insert into student values (?,?,?,?,?,?)",
                studentDTO.getStu_id(),
                studentDTO.getFull_name(),
                studentDTO.getAddress(),
                studentDTO.getGender(),
                studentDTO.getTelephone_no(),
                studentDTO.getClass_id()
        ) > 0;
        return result;
    }

    public boolean updateStudent(@NotNull StudentDTO studentDTO) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE student SET stu_id=?,full_name=?,address=?,gender=?,telephone_no=?,class_id=?,",
                studentDTO.getStu_id(),
                studentDTO.getFull_name(),
                studentDTO.getAddress(),
                studentDTO.getGender(),
                studentDTO.getTelephone_no(),
                studentDTO.getClass_id()
        ) > 0;
    }

    public boolean removeStudent(String stu_id) throws Exception {
        return CrudUtil.executeUpdate("DELETE from student where stu_id=?", stu_id) > 0;
    }

    public StudentDTO searchStudent(String stu_id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from student where stu_id=?", stu_id);
        if (rst.next()) {
            return new StudentDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6)
            );
        }
        return null;
    }

    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        ArrayList<StudentDTO> studentDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("Select * from student");
        while (rst.next()) {
            studentDTOArrayList.add(
                    new StudentDTO(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5),
                            rst.getInt(6)
                    )
            );
        }
        return studentDTOArrayList;
    }

    public ArrayList<StudentClassDTO> getAllStudentsWithClass() throws Exception {
        ArrayList<StudentClassDTO> studentDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM student A INNER JOIN class B ON A.class_id = B.class_id");
        while (rst.next()) {
            StudentClassDTO student = new StudentClassDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(8),
                    rst.getString(9)
            );
            studentDTOArrayList.add(student);
            System.out.println(student);
        }

        return studentDTOArrayList;
    }

    public ArrayList<StudentClassDTO> getAllStudentsByName(String studentName) throws Exception {
        ArrayList<StudentClassDTO> studentDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM student A INNER JOIN class B ON A.class_id = B.class_id WHERE A.full_name LIKE '%"+ studentName +"%';");
        while (rst.next()) {
            StudentClassDTO student = new StudentClassDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(8),
                    rst.getString(9)
            );
            studentDTOArrayList.add(student);
            System.out.println(student);
        }

        return studentDTOArrayList;
    }

    public ArrayList<StudentClassDTO> getAllStudentsByTelephone(String telepohneNumber) throws Exception {
        ArrayList<StudentClassDTO> studentDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM student A INNER JOIN class B ON A.class_id = B.class_id WHERE A.telephone_no LIKE '%"+ telepohneNumber +"%';");
        while (rst.next()) {
            StudentClassDTO student = new StudentClassDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(8),
                    rst.getString(9)
            );
            studentDTOArrayList.add(student);
            System.out.println(student);
        }

        return studentDTOArrayList;
    }

    public ArrayList<StudentClassDTO> getAllStudentsByClass(ClassFilterDTO dto) throws Exception {
        ArrayList<StudentClassDTO> studentDTOArrayList = new ArrayList<>();
        String sql = "SELECT * FROM student A INNER JOIN class B ON A.class_id = B.class_id" +
                " WHERE B.year_name='"+ dto.getClassYear() +"' AND B.class_name='"+ dto.getClassName() +"'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()) {
            StudentClassDTO student = new StudentClassDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(8),
                    rst.getString(9)
            );
            studentDTOArrayList.add(student);
            System.out.println(student);
        }

        return studentDTOArrayList;
    }

}
