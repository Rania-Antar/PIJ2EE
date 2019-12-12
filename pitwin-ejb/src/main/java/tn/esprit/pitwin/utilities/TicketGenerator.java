package tn.esprit.pitwin.utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import javax.ejb.EJB;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.pitwin.entities.CandidateQuiz;

public class TicketGenerator {

	private static String FILE = "C:\\Users\\DELL\\Desktop\\FirstPdf.pdf";
	private static Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 25,
            Font.BOLD,BaseColor.WHITE);
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD,BaseColor.WHITE);
    private static Font greyFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
                    Font.NORMAL,BaseColor.GRAY);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                    Font.BOLD,BaseColor.WHITE);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.BOLD,BaseColor.WHITE);
    public static final String IMAGE = "C:\\Users\\DELL\\Desktop\\sticker.jpg";

    
    
    
    public static void GenerateTicket(CandidateQuiz quiz)
    {
    	Document document = new Document();
        try {
        	PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(FILE));
        	Rectangle pagesize = new Rectangle(700,300);
			document.open();
			Paragraph emptyline = new Paragraph();
			emptyline.add(new Paragraph(" "));
			document.setPageSize(pagesize);
			document.newPage();
			//PIC
			PdfContentByte canvas = writer.getDirectContentUnder();
			Image image = Image.getInstance(IMAGE);
	        //image.scaleAbsolute(pagesize.rotate());
	        image.setAbsolutePosition(0,0);
	        canvas.addImage(image);
			//PIC
			document.addTitle("Your Quiz is "+quiz.getQuizModel().getName());
	        document.addSubject(quiz.getQuizModel().getName());
	        document.addKeywords(quiz.getQuizModel().getName()+"Quiz");
	        document.addAuthor("Rania Antar");
	        document.addCreator("Rania Antar");
	        Paragraph prefacetitle = new Paragraph();
	        prefacetitle.add(new Paragraph("Your Quiz For "+quiz.getQuizModel().getName(), bigFont));
	        document.add(prefacetitle);
	        document.add(emptyline);

			Paragraph prefacetime = new Paragraph();
			prefacetime.add(new Paragraph(quiz.getQuizModel().toString(),greyFont));
			document.add(prefacetime);
			document.add(emptyline);
			document.add(emptyline);
			Paragraph prefacetype = new Paragraph();
			prefacetype.add(new Paragraph("You Reserved For: "+quiz.getQuizModel().getName(), catFont));
			document.add(prefacetype);
			//QR
			BarcodeQRCode qrcode = new BarcodeQRCode("REF:#"+quiz.getId()+""+quiz.getQuizModel().getName().trim(), 1, 1, null);
	         Image qrcodeImage = qrcode.getImage();
	         qrcodeImage.setAbsolutePosition(520,70);
	         qrcodeImage.scalePercent(400);
	         document.add(qrcodeImage);
			//QR
	         
	         //Bar
	         PdfContentByte cb = writer.getDirectContent();
	         Barcode128 code128 = new Barcode128();
	         
	         BarcodeEAN codeEAN = new BarcodeEAN();
	         codeEAN.setCode("REF:#"+quiz.getId()+""+quiz.getQuizModel().getName().trim());
	         codeEAN.setCodeType(BarcodeEAN.EAN13);
	         Image codeEANImage = code128.createImageWithBarcode(cb, null, null);
	         codeEANImage.setAbsolutePosition(10,10);
	         codeEANImage.scalePercent(125);
	         document.add(codeEANImage);
	         //Bar
			
	        document.close();
			
			
			
        } catch (Exception e) {
            e.printStackTrace();
    }
        
    	
    	
    }
    
    
    
    
    
    public static void main(String[] args) {
    	
        try {
    		//Category category = new Category(EventCategory.Health.toString()); 
        	//Event e1 = new Event("Your Health is ours", "Health for everyone",new Date(), new Date(), 12, 9, 1000, EventType.Conference, category, 10, new Date(),"FbLink","TwitterLink",EventState.UNPUBLISHED,"http://il2.picdn.net/shutterstock/videos/762418/thumb/4.jpg","health@email.com","+214264777");
        	CandidateQuiz quiz = new CandidateQuiz( (long) 1, new Date(01/01/2020));
        	
        	GenerateTicket(quiz);
        	/*
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(FILE));
                document.open();
                addMetaData(document);
                addTitlePage(document);
                addContent(document);
                qr(document);
                document.close();*/
        } catch (Exception e) {
                e.printStackTrace();
        }
}

    
    //***********************************************************************************************************************************
    
    public static void qr(Document document)
    {
    	BarcodeQRCode qrcode = new BarcodeQRCode("fdf".trim(), 30, 30, null);
        Image qrcodeImage=null;
		try {
			qrcodeImage = qrcode.getImage();
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        qrcodeImage.setAbsolutePosition(10,500);
        qrcodeImage.scalePercent(800);
        try {
			document.add(qrcodeImage);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
            document.addTitle("My first PDF");
            document.addSubject("Using iText");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("Lars Vogel");
            document.addCreator("Lars Vogel");
    }

    private static void addTitlePage(Document document)
                    throws DocumentException {
            Paragraph preface = new Paragraph();
            // We add one empty line
            addEmptyLine(preface, 1);
            // Lets write a big header
            preface.add(new Paragraph("Title of the document", catFont));

            addEmptyLine(preface, 1);
            // Will create: Report generated by: _name, _date
            preface.add(new Paragraph(
                            "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                            smallBold));
            addEmptyLine(preface, 3);
            preface.add(new Paragraph(
                            "This document describes something which is very important ",
                            smallBold));

            addEmptyLine(preface, 8);

            preface.add(new Paragraph(
                            "This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
                            greyFont));

            document.add(preface);
            // Start a new page
            document.newPage();
    }

    private static void addContent(Document document) throws DocumentException {
            Anchor anchor = new Anchor("First Chapter", catFont);
            anchor.setName("First Chapter");

            // Second parameter is the number of the chapter
            Chapter catPart = new Chapter(new Paragraph(anchor), 1);

            Paragraph subPara = new Paragraph("Subcategory 1", subFont);
            Section subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("Hello"));

            subPara = new Paragraph("Subcategory 2", subFont);
            subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("Paragraph 1"));
            subCatPart.add(new Paragraph("Paragraph 2"));
            subCatPart.add(new Paragraph("Paragraph 3"));

            // add a list
            createList(subCatPart);
            Paragraph paragraph = new Paragraph();
            addEmptyLine(paragraph, 5);
            subCatPart.add(paragraph);

            // add a table
            createTable(subCatPart);

            // now add all this to the document
            document.add(catPart);

            // Next section
            anchor = new Anchor("Second Chapter", catFont);
            anchor.setName("Second Chapter");

            // Second parameter is the number of the chapter
            catPart = new Chapter(new Paragraph(anchor), 1);

            subPara = new Paragraph("Subcategory", subFont);
            subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("This is a very important message"));

            // now add all this to the document
            document.add(catPart);

    }

    private static void createTable(Section subCatPart)
                    throws BadElementException {
            PdfPTable table = new PdfPTable(3);

            // t.setBorderColor(BaseColor.GRAY);
            // t.setPadding(4);
            // t.setSpacing(4);
            // t.setBorderWidth(1);

            PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Table Header 2"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Table Header 3"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            table.setHeaderRows(1);

            table.addCell("1.0");
            table.addCell("1.1");
            table.addCell("1.2");
            table.addCell("2.1");
            table.addCell("2.2");
            table.addCell("2.3");

            subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
            List list = new List(true, false, 10);
            list.add(new ListItem("First point"));
            list.add(new ListItem("Second point"));
            list.add(new ListItem("Third point"));
            subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
            for (int i = 0; i < number; i++) {
                    paragraph.add(new Paragraph(" "));
            }
    }
    
    
    
    
}
