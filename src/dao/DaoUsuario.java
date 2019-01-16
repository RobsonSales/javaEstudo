package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import entidades.Usuario;

public class DaoUsuario {

		private static Connection connection;
		
		public DaoUsuario() {
			connection = ConnectionDataBase.getConnection();
		}
		
		public List<Usuario> getUsuarios() throws Exception{
			
			List<Usuario> usuarios = new ArrayList<Usuario>();
			String sql = "select * from usuario";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet  resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setId(resultSet.getString("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setTel(resultSet.getString("tel"));
				usuario.setCep(resultSet.getString("cep"));
				usuario.setRua(resultSet.getString("rua"));
				usuario.setBairro(resultSet.getString("bairro"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setUf(resultSet.getString("uf"));
				usuario.setIbge(resultSet.getString("ibge"));
				
				usuarios.add(usuario);
				
			}
			
			return usuarios;
		}
}
