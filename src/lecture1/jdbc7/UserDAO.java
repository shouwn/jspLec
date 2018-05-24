package lecture1.jdbc7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import lecture1.DB;

public class UserDAO {
	
    public static List<User> findAll(int currentPage, int pageSize, String ss, String st, String od) 
    throws SQLException, NamingException 
    {
        String sql = "call user_findAll(?, ?, ?, ?, ?)";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (currentPage - 1) * pageSize); // firstRecordIndex
            statement.setInt(2, pageSize);                     // pageSize
            statement.setString(3, ss);                        // 조회 방법
            statement.setString(4, st + "%");                  // 검색 문자열
            statement.setString(5, od);                        // 정렬 순서
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<User> list = new ArrayList<User>();
                while (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setUserid(resultSet.getString("userid"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setDepartmentName(resultSet.getString("departmentName"));
					user.setDepartmentId(resultSet.getInt("departmentId"));
					user.setEnabled(resultSet.getBoolean("enabled"));
					user.setUserType(resultSet.getString("userType"));
					list.add(user);
                }
                return list;
            }
        }
    }

	public static List<User> findByUserId(String userId, int currentPage, int pageSize) throws SQLException, NamingException {
		String sql = "SELECT u.*, d.departmentName" +
				" FROM user u LEFT JOIN department d ON u.departmentId = d.id" +
				" WHERE u.userid LIKE ?" +
				" LIMIT ?, ?";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, userId + "%");
			statement.setInt(2, (currentPage - 1) * pageSize);
			statement.setInt(3, pageSize);
			System.out.println("currentPage: " + currentPage + " pageSize: " + pageSize);
			System.out.println(statement);
			try (ResultSet resultSet = statement.executeQuery()) {
				ArrayList<User> list = new ArrayList<>();
				while (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setUserid(resultSet.getString("userid"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setDepartmentName(resultSet.getString("departmentName"));
					user.setDepartmentId(resultSet.getInt("departmentId"));
					user.setEnabled(resultSet.getBoolean("enabled"));
					user.setUserType(resultSet.getString("userType"));
					list.add(user);
				}
				return list;
			}
		}
	}
	
	public static List<User> findUserId(String userId) throws SQLException, NamingException{
		String sql = "SELECT u.*, d.departmentName" +
				" FROM user u LEFT JOIN department d ON u.departmentId = d.id" +
				" WHERE userid LIKE ?";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, userId + "%");
			try (ResultSet resultSet = statement.executeQuery()) {
				ArrayList<User> list = new ArrayList<>();
				while (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setUserid(resultSet.getString("userid"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setDepartmentName(resultSet.getString("departmentName"));
					user.setDepartmentId(resultSet.getInt("departmentId"));
					user.setEnabled(resultSet.getBoolean("enabled"));
					user.setUserType(resultSet.getString("userType"));
					list.add(user);
				}
				return list;
			}
		}
	}

	public static int count(String ss, String st) throws Exception {
		String sql = "CALL user_count(?, ?)";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, ss);
			statement.setString(2, st + "%");
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next())
					return resultSet.getInt(1);
			}
		}
		return 0;
	}

	public static User findOne(int id) throws Exception {
		String sql = "SELECT * FROM user WHERE id=?";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setUserid(resultSet.getString("userid"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setDepartmentId(resultSet.getInt("departmentId"));
					user.setEnabled(resultSet.getBoolean("enabled"));
					user.setUserType(resultSet.getString("userType"));
					return user;
				}
			}
			return null;
		}
	}

	public static void update(User user) throws SQLException, NamingException {
		String sql = "UPDATE user SET " +
				"userid=?, name=?, email=?, departmentId=?, enabled=?, userType=? " +
				"WHERE id = ? ";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, user.getUserid());
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmail());
			statement.setInt(4, user.getDepartmentId());
			statement.setBoolean(5, user.isEnabled());
			statement.setString(6, user.getUserType());
			statement.setInt(7, user.getId());
			statement.executeUpdate();
		}
	}

	public static void insert(User user) throws Exception {
		String sql = "INSERT user (userid, password, name, email, departmentId, enabled, userType)" +
				" VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, user.getUserid());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getEmail());
			statement.setInt(5, user.getDepartmentId());
			statement.setBoolean(6, user.isEnabled());
			statement.setString(7, user.getUserType());
			statement.executeUpdate();
		}
	}

	public static void delete(int id) throws Exception {
		String sql = "DELETE FROM user WHERE id = ?";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		}
	}

	public static List<String> findUserType() throws SQLException, NamingException{
		String sql = "SELECT DISTINCT userType FROM user";
		try (Connection connection = DB.getConnection("student1");
				PreparedStatement statement = connection.prepareStatement(sql)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				List<String> list = new ArrayList<>();
				while (resultSet.next()) {
					list.add(resultSet.getString("userType"));
				}
				return list;
			}
		}
	}

}