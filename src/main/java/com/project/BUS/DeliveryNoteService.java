
package com.project.BUS;
import com.project.DTO.*;
import com.project.DAO.*;
import java.util.List;
public class DeliveryNoteService {
    DeliveryNoteDao deliveryNoteDao;
    public DeliveryNoteService()
    {
        deliveryNoteDao = new DeliveryNoteDao();
    }
    public void AddDeliveryNote(DeliveryNote deliveryNote)
    {
        deliveryNoteDao.addDeliveryNote(deliveryNote);
    }
    public List<DeliveryNote> getAllDeliveryNote()
    {
        return deliveryNoteDao.getAllDeliveryNote();
    }
}
