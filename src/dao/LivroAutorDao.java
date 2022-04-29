package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import domain.LivroAutor;

public class LivroAutorDao {

    Connection connection;

    public LivroAutorDao(Connection connection){
        this.connection = connection;
    }

    public void inserir(List<LivroAutor> livroAutores) throws SQLException{
        String sql = "INSERT INTO livro_autor (id_livro, id_autor) VALUES (?, ?)";

        try (
            PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            for (LivroAutor livroAutor : livroAutores) {
                stmt.setInt(1, livroAutor.getIdLivro());
                stmt.setInt(2, livroAutor.getIdAutor());
                stmt.addBatch();
            }

            stmt.executeBatch();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }
    
}
