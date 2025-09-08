package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.project.DTO.ActionDTO;

public class ActionDAO {
    public ArrayList<ActionDTO> getAll() {
        ArrayList<ActionDTO> actions = null;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "Select * from Quyen";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            actions = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("ten_quyen");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();

                actions.add(new ActionDTO(id, name));
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actions;
    }
}
