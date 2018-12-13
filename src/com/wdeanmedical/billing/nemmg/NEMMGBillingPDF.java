package com.wdeanmedical.billing.nemmg;

import java.io.IOException;
import java.util.Iterator;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.wdeanmedical.util.PDFCreator;

public class NEMMGBillingPDF {

  private static final int FONT_NORMAL = 10;
  private static final int CHAR_HEIGHT = 15;

  private static final int LOGO_Y = 750;
  private static final int TITLE_Y = 700;
  private static final int PATIENT_INFO_Y = 680;
  private static final int FACILITY_Y = 620;

  private static final int COLUMN1_X = 25;
  private static final int COLUMN2_X = 150;
  private static final int DIAG_COLUMN_X = 250;

  private static final int CPT_Y = 520;

  private String logoPath;
  
  public NEMMGBillingPDF() {}
  
  public NEMMGBillingPDF(String logoPath) {
    this.logoPath = logoPath;
  }



  public void createMGMBillingPDF(MGMBillingInfo bInfo, String fileName) throws IOException, COSVisitorException {

    String str = null;
    String[][] content;

    PDFont fontBold = PDType1Font.HELVETICA_BOLD;
    PDFont fontNormal = PDType1Font.HELVETICA;

    PDFCreator pdfCreater = new PDFCreator(fileName);

    pdfCreater.startCreator();

    // Write Logo
    if ( logoPath != null ) {
      pdfCreater.drawImage(logoPath, COLUMN1_X, LOGO_Y);
    }

    // Write Title
    // Cener on Page
    String title = "Daily Billing Invoice - " + bInfo.getPractice();

    int titleX = (int) ((pdfCreater.getPageWidth() / 2.0) - (title.length() / 2.0 * 6.0));
    pdfCreater.drawText(fontBold, 14, titleX, TITLE_Y, title);

    // Write Patient Information
    int y = PATIENT_INFO_Y;

    pdfCreater.drawText(fontBold, FONT_NORMAL, COLUMN1_X, y, "Patient Info:");
    y -= CHAR_HEIGHT;
    pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN1_X, y, bInfo.getPatientName());
    y -= CHAR_HEIGHT;
    pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN1_X, y, bInfo.getPatientAddress1());
    if (bInfo.getPatientAddress2() != null) {
      y -= CHAR_HEIGHT;
      pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN1_X, y, bInfo.getPatientAddress2());
    }
    y -= CHAR_HEIGHT;
    str = bInfo.getPatientCity() + ", " + bInfo.getPatientState();
    str += " " + bInfo.getPatientZip();
    pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN1_X, y, str);
    y -= CHAR_HEIGHT;
    pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN1_X, y, "DOB: " + bInfo.getPatientDOB());
    y -= CHAR_HEIGHT;
    pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN1_X, y, "Sex: " + bInfo.getPatientSex());

    int dosY = y - (2 * CHAR_HEIGHT);

    // Write Insurance Information
    y = PATIENT_INFO_Y;

    pdfCreater.drawText(fontBold, FONT_NORMAL, COLUMN2_X, y, "Insurance Information:");
    y -= CHAR_HEIGHT;
    content = new String[2][3];
    content[0][0] = "Prime: " + bInfo.getInsPrimName();
    content[0][1] = "Subscriber ID#: " + bInfo.getInsPrimSubId();
    content[0][2] = "Group #: " + ((bInfo.getInsPrimGroupNum() == null) ? " " : bInfo .getInsPrimGroupNum());
    content[1][0] = "Sec: " + bInfo.getInsSecName();
    content[1][1] = "Subscriber ID#: " + bInfo.getInsSecSubId();
    content[1][2] = "Group #: " + ((bInfo.getInsSecGroupNum() == null) ? " " : bInfo .getInsSecGroupNum());

    pdfCreater.drawTable(COLUMN2_X, y, 150, content, CHAR_HEIGHT,
        FONT_NORMAL, false, false);

    // Write Date of Service (DOS)
    pdfCreater.drawText(fontBold, FONT_NORMAL, COLUMN1_X, dosY, "DOS:");
    pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN1_X + 35, dosY, bInfo.getDateOfService());

    // Write Facility Information
    pdfCreater.drawText(fontBold, FONT_NORMAL, COLUMN2_X, FACILITY_Y, "Facility:");
    str = bInfo.getFacilityAddress1() + " ";
    str += bInfo.getFacilityAddress1() + " ";
    str += bInfo.getFacilityCity() + ", ";
    str += bInfo.getFacilityState() + " ";
    str += bInfo.getFacilityZip();
    pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN2_X + 45, FACILITY_Y, str);

    // Write Doctors Information
    pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN2_X, dosY, "Guarantor: " + bInfo.getGuarantor());
    pdfCreater.drawText(fontNormal, FONT_NORMAL, COLUMN2_X, dosY - CHAR_HEIGHT, "Provider: " + bInfo.getProvider());

    // Write CPTs
    content = new String[bInfo.getCptInfo().size() + 1][4];
    content[0][0] = "CPT";
    content[0][1] = "MOD";
    content[0][2] = "Units";
    content[0][3] = "Charge";
    Iterator<CPTInfo> it = bInfo.getCptInfo().iterator();
    int row = 1;
    while (it.hasNext()) {
      CPTInfo info = it.next();
      content[row][0] = info.getCPT();
      content[row][1] = info.getMOD();
      content[row][2] = info.getUnits() + "";
      content[row][3] = info.getCharge() + "";
      row++;
    }

    pdfCreater.drawTable(COLUMN1_X, CPT_Y, 50, content, CHAR_HEIGHT, FONT_NORMAL, false, true);

    // Write Diagnostics
    row = 0;
    int col = 0;
    int count = 1;
    int numDiags = bInfo.getDiags().size();
    int maxCols = 5;
    int maxRows = (int) Math.ceil((float) numDiags / maxCols);

    content = new String[maxRows][maxCols];
    for (String diag : bInfo.getDiags()) {
      content[row][col] = "(" + count + ") " + diag;
      col++;
      if (col == maxCols) {
        col = 0;
        row++;
      }
      count++;
    }

    pdfCreater.drawText(fontBold, FONT_NORMAL, DIAG_COLUMN_X, CPT_Y, "Diagnotics");
    pdfCreater.drawTable(DIAG_COLUMN_X, CPT_Y - CHAR_HEIGHT, 60, content, CHAR_HEIGHT, FONT_NORMAL, false, false);

    pdfCreater.endCreator();
  }
  
  

  public static void main(String[] args) {
    
    org.apache.log4j.BasicConfigurator.configure();
    
    // Create Test data, will come from Database
    MGMBillingInfo bInfo = new MGMBillingInfo();
    bInfo.setPractice("Tri-State Medical Associates");

    bInfo.setPatientName("John J. Jones");
    bInfo.setPatientAddress1("123 Maple Tree Lane");
    bInfo.setPatientCity("Culver");
    bInfo.setPatientState("TX");
    bInfo.setPatientZip("77388");
    bInfo.setPatientDOB("03/14/1968");
    bInfo.setPatientSex("M");

    bInfo.setInsPrimName("BCBS of Texas");
    bInfo.setInsPrimSubId("ZGP787654345");
    bInfo.setInsPrimGroupNum("989895");
    bInfo.setInsSecName("United Health");
    bInfo.setInsSecSubId("02023657768");

    bInfo.setFacilityAddress1("325 Main St.");
    bInfo.setFacilityAddress2("Suite 201");
    bInfo.setFacilityCity("Culver");
    bInfo.setFacilityState("TX");
    bInfo.setFacilityZip("77388");

    bInfo.setGuarantor("Same");
    bInfo.setProvider("Dr. Robert King");

    bInfo.setDateOfService("05/19/2014");

    bInfo.addDiag("300.00");
    bInfo.addDiag("788.42");
    bInfo.addDiag("780.52");
    bInfo.addDiag("740.80");
    bInfo.addDiag("280.1");
    bInfo.addDiag("790.09");
    bInfo.addDiag("232.17");
    bInfo.addDiag("332.87");
    bInfo.addDiag("456.09");
    bInfo.addDiag("311.9");
    bInfo.addDiag("320.90");
    bInfo.addDiag("421.34");

    CPTInfo info = new CPTInfo();
    info.setCPT("99203");
    info.setMOD("25");
    info.setUnits(1);
    info.setCharge(225.00);
    bInfo.addCptInfo(info);

    info = new CPTInfo();
    info.setCPT("99420");
    info.setUnits(1);
    info.setCharge(100.00);
    bInfo.addCptInfo(info);

    info = new CPTInfo();
    info.setCPT("99203");
    info.setMOD("25");
    info.setUnits(1);
    info.setCharge(225.00);
    bInfo.addCptInfo(info);

    info = new CPTInfo();
    info.setCPT("99203");
    info.setMOD("25");
    info.setUnits(1);
    info.setCharge(225.00);
    bInfo.addCptInfo(info);

    bInfo.setPatientPayment(30.00);
    
    // Create PDF file
    try {        
      NEMMGBillingPDF pdf = new NEMMGBillingPDF("C://temp//WDM-Header.jpg");
      pdf.createMGMBillingPDF(bInfo, "C:\\temp\\pn-demo.pdf");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (COSVisitorException e) {
      e.printStackTrace();
    }
  }
}
