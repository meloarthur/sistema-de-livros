package dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.ConfigDB;
import domain.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDao {
    public void inserir(Livro livro){
        String sql = "INSERT INTO livro (titulo, isbn, edicao, autor, descricao) VALUES (?, ?, ?, ?, ?)";
        
        try(
            Connection connect = ConfigDB.getConnection();
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
            livro.setId(result.getInt("id"));
            stmt.close();
            System.out.println("Linha inserida com sucesso!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Livro> obterTodos(){
        String sql = "SELECT * FROM livro";
        List<Livro> livros = null;

        try(
            Connection connect = ConfigDB.getConnection();
            Statement stmt = connect.createStatement();
        ){
            ResultSet result = stmt.executeQuery(sql);
            livros = new ArrayList<>();

            while (result.next()){
                Livro livro = new Livro();

                livro.setId(result.getInt(1));
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

        return livros;
    }

    public Livro obterPorId(Livro livro){
        String sql = "SELECT * FROM livro WHERE id = ?";
        Livro livro = null;

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setInt(1, livro.getId());
            
            stmt.executeQuery();

            System.out.println(livro.toString());
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return livro;
    }

    public void atualizar(Livro livro){
        String sql = "UPDATE livro SET edicao = ? WHERE id = ?";

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setInt(1, livro.getEdicao());
            stmt.setLong(2, livro.getId());

            stmt.executeUpdate();

            System.out.println("Linha atualizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Livro livro){
        String sql = "DELETE FROM livro WHERE id = ?";

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setInt(1, livro.getId());

            stmt.executeUpdate();
            
            System.out.println("Linha excluida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepararParametros(PreparedStatement stmt, Livro livro) throws SQLException{
        stmt.setInt(1, livro.getEdicao());
        stmt.setString(2, livro.getTitulo());
        stmt.setString(3, livro.getDescricao());
        stmt.setString(4, livro.getIsbn());
    }

    private Livro obterLivroPorResultSet(ResultSet result) throws SQLException{
        Livro livro = new Livro();

        livro.setId(result.getInt("id"));
        livro.setTitulo(result.getString("titulo"));
        livro.setIsbn(result.getString("isbn"));
        livro.setEdicao(result.getInt("edicao"));
        livro.setAutor(result.getString("autor"));
        livro.setDescricao(result.getString("descricao"));

        return livro;
    }
}
