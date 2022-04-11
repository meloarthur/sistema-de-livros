package dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class LivroDao {
    public void inserir(){
        try{
            DriverManager.getConnection("jdbc:postgresql://localhost:5432/UFG", "postgres", "admin");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
