package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import config.ConfigDB;
import domain.Autor;

public class AutorDao {
    public boolean verificaNome(String nome){
        String sql = "SELECT * FROM autor WHERE nome = ?";

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setString(1, nome);

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

    public List<Autor> obterTodos(){
        String sql = "SELECT * FROM autor ORDER BY nome";
        List<Autor> autores = null;

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            ResultSet result = stmt.executeQuery();
            autores = new ArrayList<>();
            Autor autor;

            while (result.next()){
                autor = obterAutorPorResultSet(result);
                autores.add(autor);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return autores;
    }

    public List<Autor> obterPorNome(String nome){
        String sql = "SELECT * FROM autor WHERE UPPER(nome) LIKE UPPER(?) ORDER BY nome";
        Autor autor = null;
        List<Autor> autores = null;

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setString(1, ("%" + nome + "%"));;
            ResultSet result = stmt.executeQuery();
            autores = new ArrayList<>();

            while (result.next()){
                autor = obterAutorPorResultSet(result);
                autores.add(autor);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }

    public void inserir(Autor autor){
        String sql = "INSERT INTO autor (nome, nacionalidade, ano_nasc) VALUES (?, ?, ?)";
        
        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getAno_nasc());

            stmt.executeUpdate();

            ResultSet result = stmt.getGeneratedKeys();
            result.next();
            autor.setId(result.getInt("id"));
            stmt.close();
            JOptionPane.showMessageDialog(null, "Autor(a) criado(a) com sucesso!", "Cadastro de Autor", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizar(Autor autor){
        String sql = "UPDATE autor SET nome = ?, nacionalidade = ?, ano_nasc = ? WHERE id = ?";

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getAno_nasc());
            stmt.setInt(4, autor.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autor(a) atualizado(a) com sucesso!", "Cadastro de Autor", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(String nome){
        String sql = "DELETE FROM autor WHERE id = (select id from autor where nome = ?)";

        try(
            Connection connect = ConfigDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
        ){
            stmt.setString(1, nome);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Autor(a) excluido(a) com sucesso!", "Exclusão de Livro", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Autor obterAutorPorResultSet(ResultSet result) throws SQLException{
        Autor autor = new Autor();

        autor.setId(result.getInt("id"));
        autor.setNome(result.getString("nome"));
        autor.setNacionalidade(result.getString("nacionalidade"));
        autor.setAno_nasc(result.getInt("ano_nasc"));

        return autor;
    }
}
