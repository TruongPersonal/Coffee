
package com.project.BUS;
import com.project.DAO.*;
import com.project.DTO.*;
import java.sql.SQLException;
public class DetailReceiptService {
    DetailReceiptDao detailReceiptDao;
    public DetailReceiptService()
    {
        detailReceiptDao = new DetailReceiptDao();
    }
    public void addDetailReceipt(DetailReceipt detailReceipt) throws SQLException
    {
        detailReceiptDao.addDetailReceipt(detailReceipt);
    }
}
