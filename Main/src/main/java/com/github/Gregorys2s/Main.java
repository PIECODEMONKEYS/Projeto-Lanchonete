package com.github.Gregorys2s;
//pacotes para comunicação com o bando de dados (JBDC)
import java.sql.*;
import javax.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Lanchonete", "postgres", "admin")) {
        String db = c.getMetaData().getDatabaseProductName();
        String ver = c.getMetaData().getDatabaseProductVersion();

        System.out.println(db + "  " + ver + " Te da la bienvenida");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}