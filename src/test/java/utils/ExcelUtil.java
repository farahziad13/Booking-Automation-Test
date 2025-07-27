package utils;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    public static String getCellValue(String filePath, int row, int col) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row r = sheet.getRow(row);
            if (r == null) return null;

            Cell cell = r.getCell(col);
            if (cell == null) return null;

            // Use DataFormatter to handle numeric/date properly
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }
    }
}
