package dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDao {
    public void inserir(Livro livro){
        String sql = "INSERT INTO livro (titulo, isbn, edicao, autor, descricao) VALUES (?, ?, ?, ?, ?)";
        
        try(
            Connection connect = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Livros", "postgres", "admin");
            PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getIsbn());
            stmt.setInt(3, livro.getEdicao());
            stmt.setString(4, livro.getAutor());
            stmt.setString(5, livro.getDescricao());

            stmt.executeUpdate();

            ResultSet result = stmt.getGeneratedKeys();
            result.next();
            livro.setId(result.getLong("id"));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void obterTodos(){
        String sql = "SELECT id, titulo, isbn, edicao, autor, descricao FROM livro";

        try(
            Connection connect = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Livros", "postgres", "admin");
            Statement statement = connect.createStatement();
        ){
            ResultSet result = statement.executeQuery(sql);
            List<Livro> livros = new ArrayList<>();

            while (result.next()){
                Livro livro = new Livro();

                livro.setId(result.getLong(1));
                livro.setTitulo(result.getString(2));
                livro.setIsbn(result.getString(3));
                livro.setEdicao(result.getInt(4));
                livro.setAutor(result.getString(5));
                livro.setDescricao(result.getString(6));

                livros.add(livro);
            }

            System.out.println(livros.toString());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void obterPorId(){
        String sql = "SELECT id, titulo, isbn, edicao, autor, descricao FROM livro WHERE id = 1";

        try(
            Connection connect = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Livros", "postgres", "admin");
            Statement statement = connect.createStatement();
        ){
            ResultSet result = statement.executeQuery(sql);
            result.next();

            Livro livro = new Livro();

            livro.setId(result.getLong(1));
            livro.setTitulo(result.getString(2));
            livro.setIsbn(result.getString(3));
            livro.setEdicao(result.getInt(4));
            livro.setAutor(result.getString(5));
            livro.setDescricao(result.getString(6));

            System.out.println(livro);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(){
        String sql = "UPDATE livro SET edicao = 2 WHERE id = 1";

        try(
            Connection connect = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Livros", "postgres", "admin");
            Statement statement = connect.createStatement();
        ){
            int numLinhas = statement.executeUpdate(sql);
            System.out.println(numLinhas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(){
        String sql = "DELETE FROM livro WHERE id = 1";

        try(
            Connection connect = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Livros", "postgres", "admin");
            Statement statement = connect.createStatement();
        ){
            int numLinhas = statement.executeUpdate(sql);
            System.out.println(numLinhas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
