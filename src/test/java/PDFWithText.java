// import org.apache.pdfbox.pdmodel.*;
// import org.apache.pdfbox.pdmodel.font.PDFont;
// import org.apache.pdfbox.pdmodel.font.PDType1Font;
// import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

// import java.awt.Color;
// import java.io.*;

// public class PDFWithText {
//     public static void main(String[] args) {
//         PDDocument doc = null;
//         PDPage page = null;

//         try {
//             doc = new PDDocument();
//             page = new PDPage();

//             doc.addPage(page);
//             String fontName = Standard14Fonts.getMappedFontName("HELVETICA_BOLD");
//             PDFont font = new PDType1Font(fontName);

//             PDFont font = PDType1Font.HELVETICA_BOLD;

//             PDPageContentStream content = new PDPageContentStream(doc, page);
//             content.beginText();
//             content.setFont(font, 20);
//             content.setNonStrokingColor(Color.BLUE);
//             content.newLineAtOffset(100, 700);
//             content.showText("Hello It's me");

//             content.endText();
//             content.close();

//             doc.save("pdf_with_text.pdf");
//             doc.close();
//         } catch (Exception ex) {
//             System.out.println(ex);
//         }
//     }
// }
