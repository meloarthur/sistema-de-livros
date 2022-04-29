package dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import config.ConfigDB;
import domain.Autor;
import domain.Livro;
import domain.LivroAutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDao {
    public boolean verificaTitulo(String titulo){
        String sql = "SELECT * FROM livro WHERE titulo = ?";

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setString(1, titulo);

            ResultSet result = stmt.executeQuery();

            if (result.next()){
                return true;
            } else {
                return false;
            }
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificaIsbn(String isbn){
        String sql = "SELECT * FROM livro WHERE isbn = ?";

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setString(1, isbn);

            ResultSet result = stmt.executeQuery();

            if (result.next()){
                return true;
            } else {
                return false;
            }
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void inserir(Livro livro){
        String sql = "INSERT INTO livro (titulo, isbn, edicao, descricao) VALUES (?, ?, ?, ?)";
        
        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            connect.setAutoCommit(false);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getIsbn());
            stmt.setInt(3, livro.getEdicao());
            stmt.setString(4, livro.getDescricao());

            stmt.executeUpdate();

            ResultSet result = stmt.getGeneratedKeys();
            result.next();
            livro.setId(result.getInt("id"));

            LivroAutorDao livroAutorDao = new LivroAutorDao(connect);
            List<LivroAutor> livroAutores = new ArrayList<LivroAutor>();
            LivroAutor livroAutor;

            for (Autor autor : livro.getAutores()) {
                livroAutor = new LivroAutor(livro.getId(), autor.getId());
                livroAutores.add(livroAutor);
            }

            livroAutorDao.inserir(livroAutores);
            connect.commit();

            stmt.close();
            JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!", "Cadastro de Livro", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Livro> obterTodos(){
        String sql = "SELECT * FROM livro ORDER BY titulo";
        List<Livro> livros = null;

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            ResultSet result = stmt.executeQuery();
            livros = new ArrayList<>();
            Livro livro;

            while (result.next()){
                livro = obterLivroPorResultSet(result);
                livros.add(livro);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return livros;
    }

    public List<Livro> obterPorTitulo(String titulo){
        String sql = "SELECT * FROM livro WHERE UPPER(titulo) LIKE UPPER(?) ORDER BY titulo";
        Livro livro = null;
        List<Livro> livros = null;

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setString(1, ("%" + titulo + "%"));;
            ResultSet result = stmt.executeQuery();
            livros = new ArrayList<>();

            while (result.next()){
                livro = obterLivroPorResultSet(result);
                livros.add(livro);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

    public void atualizar(Livro livro){
        String sql = "UPDATE livro SET titulo = ?, isbn = ?, edicao = ?, descricao = ? WHERE id = ?";

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getIsbn());
            stmt.setInt(3, livro.getEdicao());
            stmt.setString(4, livro.getDescricao());
            stmt.setInt(5, livro.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Linha atualizada com sucesso!", "Cadastro de Livro", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(String titulo){
        String sql = "DELETE FROM livro WHERE id = (select id from livro where titulo = ?)";

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setString(1, titulo);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Linha excluida com sucesso!", "Exclusão de Livro", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
