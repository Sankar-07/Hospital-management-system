package HOSPITAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Doctor {
    private Connection connection;

    public Doctor (Connection connection){
        this .connection=connection;

    }

    public void ViewDoctor(){
        String query = "select * from doctors";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctor: ");
            System.out.println("+-------------+--------------------------+-------------------------+");
            System.out.println("| Doctor Id   | Name                     | Specialization          |");
            System.out.println("+-------------+-------------------------+--------------------------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("name");
                String Specialization = resultSet.getString("Specialization");
                System.out.printf("|%-12s|%-26s|%-25s|\n", id, name, Specialization);
                System.out.println("+-------------+-------------------------+--------------------------+");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id){
        String query = "SELECT * FROM  doctor WHERE Id= ?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet= preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
