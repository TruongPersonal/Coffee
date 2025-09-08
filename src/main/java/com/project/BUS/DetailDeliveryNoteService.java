
package com.project.BUS;
import com.project.DAO.*;
import com.project.DTO.*;
import java.sql.SQLException;
public class DetailDeliveryNoteService {
    DetailDeliveryNoteDao detailDeliveryNoteDao;
    public DetailDeliveryNoteService()
    {
        detailDeliveryNoteDao = new DetailDeliveryNoteDao();
    }
    public void addDetailDeliveryNote(DetailDeliveryNote detailDeliveryNote) throws SQLException
    {
        detailDeliveryNoteDao.addDetailDeliveryNote(detailDeliveryNote);
    }
}
