package sample.model;

import sample.controller.Controller;

import java.sql.*;
import java.util.ArrayList;

public class VartotojasDAO {
    public static void insert(Vartotojas vartotojas) {
        String query = "INSERT INTO vartotojas (userName, userPassword, userEmail) VALUES (?,?,?)";
        String url = "jdbc:mysql://localhost:3306/dakaras?serverTimezone=UTC";

        try {
            Connection prisijungimas = DriverManager.getConnection(url, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            uzklausa.setString(1, vartotojas.getUserName());
            uzklausa.setString(2, vartotojas.getUserPassword());
            uzklausa.setString(3, vartotojas.getUserEmail());
            uzklausa.executeUpdate();
            uzklausa.close();


            System.out.println("Sukurtas naujas įrašas");
        } catch (SQLException e) {
            System.out.println("Batai. Nepavyko :)");
            e.printStackTrace();
        }
    }

    public static ArrayList selectUsername(String userName) {
        String query = "SELECT userName FROM vartotojas WHERE username LIKE '" + userName + "'";
        String url = "jdbc:mysql://localhost:3306/dakaras?serverTimezone=UTC";
        ArrayList<Vartotojas> vartotojuVardai = new ArrayList<>();
        try {
            Connection prisijungimas = DriverManager.getConnection(url, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            ResultSet rezultatas = uzklausa.executeQuery(query);

            while (rezultatas.next()) {
                String usernameList = rezultatas.getString("userName");
                vartotojuVardai.add(new Vartotojas(usernameList));
            }
            uzklausa.close();
        } catch (SQLException e) {
            System.out.println("Batai. Nepavyko :)");
            e.printStackTrace();
        }
        return vartotojuVardai;
    }
}
