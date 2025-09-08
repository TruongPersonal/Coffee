
package com.project.BUS;
import com.project.DTO.*;
import com.project.DAO.*;
import java.util.List;
public class ReceiptService {
    ReceiptDao receiptdao;
    public ReceiptService()
    {
        receiptdao = new ReceiptDao();
    }
    public void AddReceipt(Receipt receipt)
    {
        receiptdao.addReceipt(receipt);
    }
    public List<Receipt> getAllReceipt()
    {
        return receiptdao.getAllReceipt();
    }
}
