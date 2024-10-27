package utils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    // Instance of ConfigReader to retrieve configuration properties
    private ConfigReader configReader = new ConfigReader();

    /**
     * Retrieves data from a specified cell in an Excel sheet.
     *
     * @param sheetName The name of the Excel sheet to retrieve data from.
     * @param rowNum The row number of the cell (0-based index).
     * @param colNum The column number of the cell (0-based index).
     * @return The data in the specified cell as a String, or an empty string if an error occurs.
     */
    public String getExcelData(String sheetName, int rowNum, int colNum) {
        String cellData = ""; // Initialize cell data variable

        // Attempt to open the Excel file and retrieve cell data
        try (FileInputStream fis = new FileInputStream(configReader.getProperty("excelFilePath"))) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create workbook instance for the file
            cellData = workbook.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue(); // Get cell value

            // Note: Workbook close is handled by try-with-resources, no explicit close needed.
        } catch (IOException e) {
            // Log any exception that occurs during file or cell reading
            LogUtil.error("Error reading Excel data: " + e.getMessage());
        }

        return cellData; // Return the retrieved cell data
    }
}
