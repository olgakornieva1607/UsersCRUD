package pl.coderslab.utils;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.utils.User;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER = "INSERT INTO users ( username, email, password) VALUES (?,?,?);";
    private static final String READ_USER = "SELECT * FROM users WHERE id =?;";
    private static final String UPDATE_USER = "UPDATE users SET username =?, email =?, password =? WHERE id=?;";
    private static final String DELETE_USER = "DELETE FROM users WHERE id =?;";
    private static final String FIND_ALL = "SELECT * FROM users;";


    public User create(User user) {
        try (Connection connect = DbUtil.connect();
             PreparedStatement statement = connect.prepareStatement(CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Inserted ID: " + id);
                user.setId(rs.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String hashPassword (String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User read(int userId){
        try(Connection connect = DbUtil.connect();
            PreparedStatement statement = connect.prepareStatement(READ_USER)){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user){
        try(Connection connect = DbUtil.connect();
            PreparedStatement statement = connect.prepareStatement(UPDATE_USER)){
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, this.hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int userId){
        try(Connection connect = DbUtil.connect();
            PreparedStatement statement = connect.prepareStatement(DELETE_USER)){
            statement.setInt(1, userId);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private User[] addToArray(User u, User[] users){
        User[] tmpUsers = Arrays.copyOf(users, users.length+1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

    public User[] findAll(){
        try(Connection connect = DbUtil.connect();
            PreparedStatement statement = connect.prepareStatement(FIND_ALL)){
            User[] users = new User[0];
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}

