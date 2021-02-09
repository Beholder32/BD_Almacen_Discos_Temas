package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Album>datos = new ArrayList<>();
        String sql = "SELECT * FROM album";
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Album a = new Album();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setGrup(rs.getString(3));
                a.setFecha(rs.getString(4));
                datos.add(a);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error en el listado");
        }
        return datos;
    }
    
    public int agregar(Album a){
        String sql="INSERT INTO album (nombreDisco, nombreGrupo, fechaPublicacion) VALUES (?,?,?)";
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getNom());
            ps.setString(2, a.getGrup());
            ps.setString(3, a.getFecha());
            ps.executeUpdate();
            ps.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error al agregar alemento");
        }
        return 1; 
    }
    
    public int actualizar(Album a){
        String sql = "UPDATE album SET nombreDisco=?, nombreGrupo=?, fechaPublicacion=? WHERE idDisco=?";
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getNom());
            ps.setString(2, a.getGrup());
            ps.setString(3, a.getFecha());
            ps.setInt(4, a.getId());
            ps.executeUpdate();
            ps.close();
            con.close();
        }catch (Exception e){
            System.out.println("Error al agregar actualizar los datos");
        }
        return 1;
    }
    
    public void eliminar(int id){
        String sql = "DELETE FROM album WHERE idDisco="+id;
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error al borrar un registro");
        }
    }
    
}
