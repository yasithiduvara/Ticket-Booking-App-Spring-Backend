package ticketingsystem.ticket;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE_PATH = "system.log"; // Path to the log file
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Log info messages to both console and file
    public static void logInfo(String message) {
        String logMessage = formatMessage("INFO", message);
        System.out.println(logMessage);
        writeToFile(logMessage);
    }

    // Log error messages to both console and file
    public static void logError(String message, Exception e) {
        String logMessage = formatMessage("ERROR", message + " - " + e.getMessage());
        System.err.println(logMessage);
        writeToFile(logMessage);
    }

    // Format the message with the current date and type of log
    private static String formatMessage(String logType, String message) {
        return "[" + LocalDateTime.now().format(dateFormatter) + "][" + logType + "] " + message;
    }

    // Write log messages to a file
    private static void writeToFile(String message) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE_PATH, true)))) {
            out.println(message);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
