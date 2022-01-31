package repo;

import db.DBConnection;
import dto.ClassDescriptionDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAO {

    private Connection dbConnection;

    public QueryDAO() throws Exception {
        dbConnection = DBConnection.getInstance().getConnection();
    }

    public ArrayList<ClassDescriptionDTO> searchClassWithStudentCount() throws SQLException {
        String sql = "SELECT c.class_name, c.year_name, COUNT(s.stu_id) FROM student s LEFT JOIN class c ON c.class_id = s.class_id GROUP BY c.class_id";
        ResultSet rst = dbConnection.prepareStatement(sql).executeQuery();


        ArrayList<ClassDescriptionDTO> dtos = new ArrayList<>();
        while (rst.next()) {
            System.out.println(rst.getString(2));
            dtos.add(new ClassDescriptionDTO(rst.getString(1), rst.getString(2), rst.getInt(3)));
        }
        return dtos;
    }

    public ArrayList<ClassDescriptionDTO> searchClassWithStudentCount(String year) throws SQLException {
        String sql = "SELECT c.class_name, c.year_name, COUNT(s.stu_id) FROM student s LEFT JOIN class c ON c.class_id = s.class_id WHERE c.year_name='"+ year +"' GROUP BY c.year_name;";
        ResultSet rst = dbConnection.prepareStatement(sql).executeQuery();
        ArrayList<ClassDescriptionDTO> dtos = new ArrayList<>();
        while (rst.next()) {
            dtos.add(new ClassDescriptionDTO(rst.getString(1), rst.getString(2), rst.getInt(3)));
        }
        return dtos;
    }

}
