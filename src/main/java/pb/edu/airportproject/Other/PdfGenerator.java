package pb.edu.airportproject.Other;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import pb.edu.airportproject.Model.Flight;
import pb.edu.airportproject.Model.Ticket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PdfGenerator {

    private static final String PDF_FILE = "C:\\Giereczki\\PDF_List.pdf";
    //    private static final String PDF_FILE = "D:\\studia\\maGISTER\\Rozproszone_systemy_internetowe\\projekt\\moj\\RSI_PROJEKT_SOAP-master\\PDF_List.pdf";
//    private static final String PDF_FILE2 = "D:\\studia\\maGISTER\\Rozproszone_systemy_internetowe\\projekt\\moj\\RSI_PROJEKT_SOAP-master\\PDF_Ticket.pdf";
    private static final String PDF_FILE2 = "C:\\Giereczki\\PDF_List2.pdf";

    public PdfGenerator() {
    }

    public static PdfGenerator getInstance() {
        return PdfGeneratorHolder.INSTANCE;
    }

    private static class PdfGeneratorHolder {

        private static final PdfGenerator INSTANCE = new PdfGenerator();
    }

    public File createPdfForFly(List<Flight> flights, List<Ticket> tickets) throws IOException {
        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(PDF_FILE));

            Document document = new Document(pdf);
            float columnWidth[] = {120f, 100f, 200f, 80f, 160f, 120f};
            Table table = new Table(columnWidth);
            table.setTextAlignment(TextAlignment.CENTER);
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Numer lotu")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Skad")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Dokad")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Godzina")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Data")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Pozostałe miejsca")));

            for (Flight flight : flights) {
                for (Ticket ticket : tickets) {
                    if (ticket.flightId.equals(flight.flyCode)) {
                        flight.seatsNumber = flight.seatsNumber - 1;
                    }
                }
            }

            for (Flight flight : flights) {
                table.addCell(new Cell().add(new Paragraph(flight.getFlyCode().toString())));
                table.addCell(new Cell().add(new Paragraph(flight.getCityFrom())));
                table.addCell(new Cell().add(new Paragraph(flight.getCityTo())));
                table.addCell(new Cell().add(new Paragraph(flight.getDepartureDate().format(DateTimeFormatter.ofPattern("HH:mm")))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(flight.departureDate.getDayOfMonth() + "-" + flight.departureDate.getMonthValue() + "-" + flight.departureDate.getYear()))));
                table.addCell(new Cell().add(new Paragraph(Integer.toString(flight.seatsNumber))));
            }

            document.add(table);
            document.close();
            return new File(PDF_FILE);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR_PDF_GENERATOR");
            return new File(PDF_FILE);
        }
    }

    public File createPdfTicket(List<Flight> flights, List<Ticket> tickets, Long flyCode) throws IOException {
        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(PDF_FILE2));

            Document document = new Document(pdf);
            float columnWidth[] = {120f, 100f, 200f, 80f, 160f, 120f};
            Table table = new Table(columnWidth);
            table.setTextAlignment(TextAlignment.CENTER);
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Numer lotu")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Skad")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Dokad")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Godzina")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Data")));
            table.addCell(new Cell()
                    .setBackgroundColor(new DeviceRgb(172, 185, 199))
                    .add(new Paragraph("Numer miejsca")));


            List<Ticket.TicketWithSeatNumber> userFlights = new ArrayList<>();

            for (Flight flight : flights) {
                for (Ticket ticket : tickets) {
                    if (ticket.flightId.equals(flight.flyCode)) {
                        Ticket.TicketWithSeatNumber ticketWithFlight = new Ticket.TicketWithSeatNumber(flight, ticket.seatNumber);
                        userFlights.add(ticketWithFlight);
                    }
                }
            }

            for (Ticket.TicketWithSeatNumber ticketWithFlight : userFlights) {
                if ((ticketWithFlight.flight.getFlyCode()).equals(flyCode)) {
                    table.addCell(new Cell().add(new Paragraph(ticketWithFlight.flight.getFlyCode().toString())));
                    table.addCell(new Cell().add(new Paragraph(ticketWithFlight.flight.getCityFrom())));
                    table.addCell(new Cell().add(new Paragraph(ticketWithFlight.flight.getCityTo())));
                    table.addCell(new Cell().add(new Paragraph(ticketWithFlight.flight.getDepartureDate().format(DateTimeFormatter.ofPattern("HH:mm")))));
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(ticketWithFlight.flight.departureDate.getDayOfMonth() + "-" + ticketWithFlight.flight.departureDate.getMonthValue() + "-" + ticketWithFlight.flight.departureDate.getYear()))));
                    table.addCell(new Cell().add(new Paragraph(Integer.toString(ticketWithFlight.seatNumber))));
                }
            }
            document.add(table);
            document.close();
            return new File(PDF_FILE2);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR_PDF_GENERATOR");
            return new File(PDF_FILE2);
        }
    }

    public byte[] convertFileToByteArray() throws FileNotFoundException, IOException {
        File file = new File(PDF_FILE);
        FileInputStream fis = new FileInputStream(file);
        byte[] arr = new byte[(int) file.length()];
        fis.read(arr);
        fis.close();
        return arr;
    }
}