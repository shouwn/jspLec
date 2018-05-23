package lecture1.jdbc7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import lecture1.DB;

public class StudentDAO {

    public static List<Student> findAll(int currentPage, int pageSize, String ss, String st, String od) 
    throws Exception {
        String sql = "call student_findAll(?, ?, ?, ?, ?)";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (currentPage - 1) * pageSize); // firstRecordIndex
            statement.setInt(2, pageSize);                     // pageSize
            statement.setString(3, ss);                        // 조회 방법
            statement.setString(4, st + "%");                  // 검색 문자열
            statement.setString(5, od);                        // 정렬 순서
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<Student> list = new ArrayList<Student>();
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setStudentNumber(resultSet.getString("studentNumber"));
                    student.setName(resultSet.getString("name"));
                    student.setDepartmentId(resultSet.getInt("departmentId"));
                    student.setYear(resultSet.getInt("year"));
                    student.setDepartmentName(resultSet.getString("departmentName"));
                    list.add(student);
                }
                return list;
            }
        }
    }

    public static int count(String ss, String st) throws SQLException, NamingException  {
        String sql = "call student_count(?, ?)";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ss);  // 조회 방법
            statement.setString(2, st + "%");  // 검색 문자열
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt(1);
            }
        }
        return 0;
    }

    public static Student findOne(int id) throws Exception {
        String sql = "SELECT * FROM student WHERE id=?";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setStudentNumber(resultSet.getString("studentNumber"));
                    student.setName(resultSet.getString("name"));
                    student.setDepartmentId(resultSet.getInt("departmentId"));
                    student.setYear(resultSet.getInt("year"));
                    return student;
                }
            }
            return null;
        }
    }

    public static void update(Student student) throws Exception {
        String sql = "UPDATE student SET studentNumber=?, name=?, departmentId=?, year=? " +
                     " WHERE id = ?";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getStudentNumber());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getDepartmentId());
            statement.setInt(4, student.getYear());
            statement.setInt(5, student.getId());
            statement.executeUpdate();
        }
    }

    public static void insert(Student student) throws Exception {
        String sql = "INSERT student (studentNumber, name, departmentId, year)" +
                     " VALUES (?, ?, ?, ?)";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getStudentNumber());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getDepartmentId());
            statement.setInt(4, student.getYear());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws Exception {
        String sql = "DELETE FROM student WHERE id = ?";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
