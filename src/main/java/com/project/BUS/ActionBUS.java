package com.project.BUS;

import java.util.ArrayList;

import com.project.DAO.ActionDAO;
import com.project.DTO.ActionDTO;

public class ActionBUS {
    private ActionDAO actionDAO;
    public ActionBUS(){
        actionDAO = new ActionDAO();
    }

    public ArrayList<ActionDTO> getAll(){
        return actionDAO.getAll();
    }
}
