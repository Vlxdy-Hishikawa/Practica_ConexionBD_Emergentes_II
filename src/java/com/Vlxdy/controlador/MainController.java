package com.Vlxdy.controlador;

import com.Vlxdy.ConexionDB;
import com.Vlxdy.modelo.Tareas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Vlxdy Hishikawa
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        ArrayList<Tareas> lista = new ArrayList<Tareas>();
        ArrayList<Tareas> listaux = new ArrayList<Tareas>();
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (op.equals("list")) {
            try {

                String sql = "SELECT * FROM tareas";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Tareas lib = new Tareas();
                    lib.setId(rs.getInt("id"));
                    lib.setTarea(rs.getString("tarea"));
                    lib.setPrioridad(rs.getInt("prioridad"));
                    lib.setCompletado(rs.getInt("completado"));
                    lista.add(lib);
                }

                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error en SQL: " + ex.getMessage());
            } finally {
                canal.desconectar();
            }

        } 
        if (op.equals("nuevo")) {
            Tareas l = new Tareas();
            request.setAttribute("tarea", l);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if (op.equals("eliminar")){
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                String sql = "delete from tareas where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error en SQL: " + ex.getMessage());
            } finally {
                canal.desconectar();
            }  
            response.sendRedirect("MainController");
        }
        
        if (op.equals("modificar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "SELECT * FROM tareas where id=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Tareas lib = new Tareas();
                    lib.setId(rs.getInt("id"));
                    lib.setTarea(rs.getString("tarea"));
                    lib.setPrioridad(rs.getInt("prioridad"));
                    lib.setCompletado(rs.getInt("completado"));
                    listaux.add(lib);
                }
                Tareas l = new Tareas();
                l = listaux.get(0);
                request.setAttribute("tarea", l);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error en SQL: " + ex.getMessage());
            } finally {
                canal.desconectar();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tarea = request.getParameter("tarea");
        int prioridad = Integer.parseInt(request.getParameter("prioridad"));
        int completado = Integer.parseInt(request.getParameter("completado"));

        Tareas l = new Tareas();

        l.setId(id);
        l.setTarea(tarea);
        l.setPrioridad(prioridad);
        l.setCompletado(completado);

        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (id == 0) {
            try {
                String sql = "insert into tareas (tarea,prioridad,completado) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, l.getTarea());
                ps.setInt(2, l.getPrioridad());
                ps.setInt(3, l.getCompletado());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error en SQL: " + ex.getMessage());
            } finally {
                canal.desconectar();
            }
            response.sendRedirect("MainController");
        } 
        else {
            try {
                int q = Integer.parseInt(request.getParameter("id"));
                String sql = "update tareas set tarea=?,prioridad=?,completado=? where id=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, l.getTarea());
                ps.setInt(2, l.getPrioridad());
                ps.setInt(3, l.getCompletado());
                ps.setInt(4, q);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error en SQL:" + ex.getMessage());
            } finally {
                canal.desconectar();
            }
            response.sendRedirect("MainController");
        }
    }
}
