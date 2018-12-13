package com.wdeanmedical.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDCcitt;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.util.PDFMergerUtility;

public class PDFCreator {

	private PDDocument document;
	private PDPage page;
	private PDPageContentStream contentStream;
	private String fileName;

	public PDFCreator(String fileName) {
		this.fileName = fileName;
	}

	public void startCreator() throws IOException, COSVisitorException {
		document = new PDDocument();
		page = new PDPage();
		document.addPage(page);

		contentStream = new PDPageContentStream(document, page);
	}

	public void endCreator() throws IOException, COSVisitorException {
		contentStream.close();

		document.save(fileName);
		document.close();
	}

	public void mergePDFFiles(String[] files, String mergedFile) throws IOException, COSVisitorException {
		PDFMergerUtility ut = new PDFMergerUtility();
		for ( String file : files) {
			ut.addSource(file);
		}
		ut.setDestinationFileName(mergedFile);
		ut.mergeDocuments();
	}
	
	public float getPageWidth() {
		return page.findMediaBox().getWidth();
	}

	public void drawImage(String image, int x, int y) throws IOException {
		PDXObjectImage ximage = null;
		if (image.toLowerCase().endsWith(".jpg")) {
			ximage = new PDJpeg(document, new FileInputStream(image));
		} else if (image.toLowerCase().endsWith(".tif")
				|| image.toLowerCase().endsWith(".tiff")) {
			ximage = new PDCcitt(document, new RandomAccessFile(
					new File(image), "r"));
		} else {
			BufferedImage awtImage = ImageIO.read(new File(image));
			ximage = new PDPixelMap(document, awtImage);
		}

		contentStream.drawImage(ximage, x, y);
	}

	public void drawText(PDFont font, int fontSize, int x, int y, String text)
			throws IOException, COSVisitorException {

		contentStream.beginText();
		contentStream.setFont(font, fontSize);
		contentStream.moveTextPositionByAmount(x, y);
		text = (text == null) ? " " : text;
		contentStream.drawString(text);
		contentStream.endText();
	}

	public void drawTable(float x, float y, float columWidth,
			String[][] content, int charHeight, int fontHeight,
			boolean drawLines, boolean boldFirstRow) throws IOException {

		final int rows = content.length;
		final int cols = content[0].length;
		final float rowHeight = charHeight;
		// final float tableWidth = page.findMediaBox().getWidth()-(2*margin);
		final float tableHeight = rowHeight * rows;
		final float colWidth = columWidth; // tableWidth/(float)cols;
		final float cellMargin = 5f;
		final float tableWidth = colWidth * cols;

		if (drawLines) {
			float nexty = y;
			for (int i = 0; i <= rows; i++) {
				contentStream.drawLine(x, nexty, x + tableWidth, nexty);
				nexty -= rowHeight;
			}

			// Draw the columns
			float nextx = x;
			for (int i = 0; i <= cols; i++) {
				contentStream.drawLine(nextx, y, nextx, y - tableHeight);
				nextx += colWidth;
			}
		}

		// Now add the text
		if (boldFirstRow) {
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontHeight);
		} else {
			contentStream.setFont(PDType1Font.HELVETICA, fontHeight);
		}

		float textx = x + cellMargin;
		float texty = y - ((drawLines) ? charHeight : 0);
		for (int r = 0; r < content.length; r++) {
			for (int c = 0; c < content[r].length; c++) {
				String text = content[r][c];
				contentStream.beginText();

				contentStream.moveTextPositionByAmount(textx, texty);

				text = (text == null) ? " " : text;
				contentStream.drawString(text);
				contentStream.endText();
				textx += colWidth;
			}
			texty -= rowHeight;
			textx = x + cellMargin;
			contentStream.setFont(PDType1Font.HELVETICA, fontHeight);
		}
	}
}
