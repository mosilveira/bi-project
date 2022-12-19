package com.example.bi.helper;

import com.example.bi.entities.DataInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Id", "Title", "Description", "Published"};
    static String SHEET = "Página1";

    public static boolean hasExcelFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static List<DataInfo> excelToTutorials(InputStream inputStream) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<DataInfo> dataList = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                DataInfo dataInfo = new DataInfo();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 1:
                            dataInfo.setPatientId((long) currentCell.getNumericCellValue());
                            break;

                        case 2:
                            dataInfo.setName(currentCell.getStringCellValue());
                            break;

                        case 9:
                            dataInfo.setGender(currentCell.getStringCellValue());
                            break;

                        case 16:
                            dataInfo.setLevel(currentCell.getStringCellValue());
                            break;

                        case 17:
                            dataInfo.setMicroArea(currentCell.getStringCellValue());
                            break;

                        case 29:
                            dataInfo.setCareInfo((int) currentCell.getNumericCellValue());
                            break;

                        case 30:
                            dataInfo.setPa((int) currentCell.getNumericCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                dataList.add(dataInfo);
            }

            workbook.close();

            return dataList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
