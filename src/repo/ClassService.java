package repo;

import dto.ClassDTO;
import dto.ClassFilterDTO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClassService {

    public boolean saveClass(ClassDTO classDTO) throws Exception {
        boolean result = CrudUtil.executeUpdate(
                "Insert into class values (?,?,?)",
                classDTO.getClassID(),
                classDTO.getClassName(),
                classDTO.getClassYear()
        ) > 0;
        return result;
    }

    public boolean updateClass(ClassDTO classDTO) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE class SET class_id=?,class_name=?,year_name=?,",
                classDTO.getClassID(),
                classDTO.getClassName(),
                classDTO.getClassYear()
        ) > 0;
    }

    public boolean removeClass(int classID) throws Exception {
        return CrudUtil.executeUpdate("DELETE from class where barometerID=?", classID) > 0;
    }

    public ClassDTO searchClass(int class_id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from class where class_id=?", class_id);
        if (rst.next()) {
            return new ClassDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }
        return null;
    }

    public ArrayList<ClassDTO> getAllClasses() throws Exception {
        ArrayList<ClassDTO> classDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("Select * from class");
        while (rst.next()) {
            classDTOArrayList.add(new ClassDTO(rst.getInt(1), rst.getString(2), rst.getString(3)));
        }
        return classDTOArrayList;
    }

    public ArrayList<ClassFilterDTO> getAllClassesForSearch() throws Exception {
        ArrayList<ClassFilterDTO> classDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("Select * from class");
        while (rst.next()) {
            classDTOArrayList.add(new ClassFilterDTO(rst.getInt(1), rst.getString(2), rst.getString(3)));
        }
        return classDTOArrayList;
    }

    public ArrayList<ClassDTO> getAllClasses(String yearName) throws Exception {
        ArrayList<ClassDTO> classDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("Select * from class where year_name=?", yearName);
        while (rst.next()) {
            classDTOArrayList.add(new ClassDTO(rst.getInt(1), rst.getString(2), rst.getString(3)));
        }
        return classDTOArrayList;
    }

}
