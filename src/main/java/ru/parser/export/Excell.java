package ru.parser.export;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import ru.parser.vo.Vacancy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by set on 02.03.17.
 */
public class Excell implements Export{


    public  void export(List<Vacancy> vacancies) {
        String filePath = System.getProperty("user.dir") + File.separator + "test.xls";

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Тестовый файл");


        HSSFFont font = workbook.createFont();
        // указываем, что хотим его видеть жирным
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // создаем стиль для ячейки
        font.setFontHeightInPoints((short) 12);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);


        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Title");
        row.getCell(0).setCellStyle(style);
        row.createCell(1).setCellValue("City");
        row.getCell(1).setCellStyle(style);
        row.createCell(2).setCellValue("Company Name");
        row.getCell(2).setCellStyle(style);
        row.createCell(3).setCellValue("Salary");
        row.getCell(3).setCellStyle(style);

        int count=1;

        for (Vacancy v:vacancies) {
            Row row1 = sheet.createRow(count++);
            row1.createCell(0).setCellValue(v.getTitle());
            row1.createCell(1).setCellValue(v.getCity());
            row1.createCell(2).setCellValue(v.getCompanyName());
            row1.createCell(3).setCellValue(v.getSalary());
        }


        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);

        try (FileOutputStream out = new FileOutputStream(new File(filePath))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Excel файл успешно создан!");
    }
}
