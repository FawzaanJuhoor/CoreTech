package db;

import Models.AppointmentInvoiceInfo;
import Models.ServiceItem;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoicePDFGenerator {

    public static AppointmentInvoiceInfo getInvoiceInfo(int appointmentId) {
        AppointmentInvoiceInfo info = null;
        List<ServiceItem> items = new ArrayList<>();

        String sql = "{ call GetInvoiceDetails(?, ?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, appointmentId);
            stmt.registerOutParameter(2, java.sql.Types.REF_CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            while (rs.next()) {
                if (info == null) {
                    info = new AppointmentInvoiceInfo();
                    info.setAppointmentId(rs.getInt("AppointmentID"));
                    info.setCustomerName(rs.getString("CustomerName"));
                    info.setEmail(rs.getString("EmailID"));
                    info.setPhone(rs.getString("PhoneNo"));
                    info.setAddress(rs.getString("Address"));
                    info.setMake(rs.getString("Make"));
                    info.setModel(rs.getString("Model"));
                    info.setYear(rs.getInt("Year"));
                    info.setVin(rs.getString("VIN"));
                    info.setServiceType(rs.getString("ServiceType"));
                    info.setServiceDate(rs.getDate("ServiceDate").toLocalDate());
                    info.setStatus(rs.getString("ServiceStatus"));
                    info.setMechanicName(rs.getString("MechanicName"));
                }

                if (rs.getString("ItemName") != null) {
                    items.add(new ServiceItem(
                            rs.getString("ItemName"),
                            rs.getInt("QuantityUsed"),
                            rs.getDouble("Price")
                    ));
                }
            }

            if (info != null) {
                info.setServiceItems(items);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return info;
    }

    public static boolean generateInvoice(AppointmentInvoiceInfo info, File file) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            doc.addPage(page);

            PDPageContentStream cs = new PDPageContentStream(doc, page);
            float margin = 50, y = 750, rowH = 20, pageW = page.getMediaBox().getWidth(), tableW = pageW - 2 * margin;

// ✅ Logo + Title aligned
            try {
                File logoFile = new File("src/main/resources/Images/BlackVersion.png");
                if (logoFile.exists()) {
                    PDImageXObject logo = PDImageXObject.createFromFileByContent(logoFile, doc);

                    float logoWidth = 100;
                    float logoHeight = 40;
                    float titleFontSize = 18;

                    // Draw logo on left
                    cs.drawImage(logo, margin, y - logoHeight, logoWidth, logoHeight);

                    // Draw centered title: "Invoice"
                    String title = "Invoice";
                    float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(title) / 1000 * titleFontSize;
                    float titleX = (page.getMediaBox().getWidth() - titleWidth) / 2;

                    cs.beginText();
                    cs.setFont(PDType1Font.HELVETICA_BOLD, titleFontSize);
                    cs.newLineAtOffset(titleX, y - 20);
                    cs.showText(title);
                    cs.endText();

                    y -= 60; // move down for next content

                } else {
                    System.out.println("⚠️ Logo file not found at: " + logoFile.getAbsolutePath());
                }
            } catch (IOException ex) {
                System.out.println("⚠️ Failed to load logo: " + ex.getMessage());
            }


            // Customer & Vehicle Info
            String[][] infoPairs = {
                    {"Customer Name", info.getCustomerName()},
                    {"Email", info.getEmail()},
                    {"Phone", info.getPhone()},
                    {"Address", info.getAddress()},
                    {"Vehicle", info.getMake() + " " + info.getModel() + " (" + info.getYear() + ")"},
                    {"VIN", info.getVin()}
            };

            cs.setFont(PDType1Font.HELVETICA, 12);
            for (String[] pair : infoPairs) {
                cs.beginText();
                cs.newLineAtOffset(margin, y);
                cs.showText(pair[0] + ": " + pair[1]);
                cs.endText();
                y -= rowH;
            }

            y -= 10;
            cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
            cs.beginText();
            cs.newLineAtOffset(margin, y);
            cs.showText("Appointment ID: " + info.getAppointmentId()
                    + " | Date: " + info.getServiceDate()
                    + " | Status: " + info.getStatus()
                    + " | Mechanic: " + info.getMechanicName());
            cs.endText();
            y -= 30;

            // Table Header
            String[] headers = {"Item", "Qty", "Price", "Line Total"};
            float[] colWidths = {tableW * 0.4f, tableW * 0.2f, tableW * 0.2f, tableW * 0.2f};
            float x = margin;

            cs.setNonStrokingColor(34, 147, 194);
            cs.addRect(margin, y - rowH, tableW, rowH);
            cs.fill();
            cs.setNonStrokingColor(255, 255, 255);

            for (int i = 0; i < headers.length; i++) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
                cs.newLineAtOffset(x + 5, y - 15);
                cs.showText(headers[i]);
                cs.endText();
                x += colWidths[i];
            }
            y -= rowH;
            cs.setNonStrokingColor(0, 0, 0);

            // Items
            List<ServiceItem> items = info.getServiceItems();
            double total = 0;
            for (ServiceItem item : items) {
                String[] row = {
                        item.getItemName(),
                        String.valueOf(item.getQuantity()),
                        "$" + String.format("%.2f", item.getUnitPrice()),
                        "$" + String.format("%.2f", item.getLineTotal())
                };
                x = margin;
                for (int i = 0; i < row.length; i++) {
                    cs.beginText();
                    cs.setFont(PDType1Font.HELVETICA, 12);
                    cs.newLineAtOffset(x + 5, y - 15);
                    cs.showText(row[i]);
                    cs.endText();
                    x += colWidths[i];
                }
                y -= rowH;
                total += item.getLineTotal();
            }

            // Total Summary
            cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
            cs.beginText();
            cs.newLineAtOffset(margin + tableW - 150, y - 15);
            cs.showText("Total Cost: $" + String.format("%.2f", total));
            cs.endText();

            // Footer
            y -= 40;
            cs.setFont(PDType1Font.HELVETICA_OBLIQUE, 10);
            cs.beginText();
            cs.newLineAtOffset(margin, 50);
            cs.showText("Generated on: " + java.time.LocalDate.now() + "  |  © CoreTech Autocare");
            cs.endText();

            cs.close();
            doc.save(file);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}