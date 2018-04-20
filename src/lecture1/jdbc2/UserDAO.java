package lecture1.jdbc2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import lecture1.DB;

public class UserDAO {

	public static User createUser(ResultSet resultSet) throws SQLException{
		User user = new User();
		user.setUserid(resultSet.getString("userid"));
		user.setName(resultSet.getString("name"));
		user.setEmail(resultSet.getString("email"));
		user.setDepartmentName(resultSet.getString("departmentName"));
		user.setUserType(resultSet.getString("userType"));

		return user;
	}

	public static List<User> findAll(int currentPage, int pageSize) throws SQLException, NamingException {
        String sql = "SELECT u.*, d.departmentName" +
                     " FROM user u LEFT JOIN department d ON u.departmentId = d.id" +
                     " LIMIT ?, ?";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (currentPage - 1) * pageSize);
            statement.setInt(2, pageSize);
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<User> list = new ArrayList<User>();
                while (resultSet.next())
                	list.add(createUser(resultSet));
                
                return list;
            }
        }
    }
	
	public static List<User> findByName(String name) throws SQLException, NamingException{
		String sql = "SELECT u.*, d.departmentName " +
				"FROM user u LEFT JOIN department d ON u.departmentId = d.id " + 
				"WHERE u.name LIKE ?";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, name + "%");
			
			try(ResultSet resultSet = statement.executeQuery()){
				ArrayList<User> list = new ArrayList<User>();
				while (resultSet.next())
					list.add(createUser(resultSet));

				return list;
			}
		}
	}
	
	public static List<User> findByDepartmentId(int departmentId) throws SQLException, NamingException{
		String sql = "SELECT u.*, d.departmentName " +
				"FROM user u LEFT JOIN department d ON u.departmentId = d.id " + 
				"WHERE u.departmentId = ?";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, departmentId);
			
			try(ResultSet resultSet = statement.executeQuery()){
				ArrayList<User> list = new ArrayList<User>();
				while (resultSet.next())
					list.add(createUser(resultSet));

				return list;
			}
		}
	}

	public static List<User> findBy(String name, int departmentId) throws SQLException, NamingException{
		String sql = "SELECT u.*, d.departmentName " +
				"FROM user u LEFT JOIN department d ON u.departmentId = d.id " + 
				"WHERE u.name LIKE ?";
		
		if(departmentId != 0)
			sql += " AND u.departmentId = ?";
		
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, name + "%");
			if(departmentId != 0) statement.setInt(2, departmentId);
			
			try(ResultSet resultSet = statement.executeQuery()){
				ArrayList<User> list = new ArrayList<User>();
				while (resultSet.next())
					list.add(createUser(resultSet));

				return list;
			}
		}
	}
	
	public static int count() throws Exception {
        String sql = "SELECT COUNT(*) FROM user";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt(1);
        }
        return 0;
    }
}
