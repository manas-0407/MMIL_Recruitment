package org.mmil.Recruitment_MMIL.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mmil.Recruitment_MMIL.models.Registrations;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class GetExcelData {

    private final String[] headers = {"Name" , "Email" ,
            "Phone No" , "University RollNo." ,
            "Year" , "Branch"};

    public ByteArrayInputStream dataToExcel(List<Registrations> registerDtos) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);

        for(int i=0;i<headers.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int row_pointer = 1;
        for(Registrations dto : registerDtos){

            row = sheet.createRow(row_pointer);

            row.createCell(0).setCellValue(dto.getName());
            row.createCell(1).setCellValue(dto.getEmail());
            row.createCell(2).setCellValue(dto.getMobile_no());
            row.createCell(3).setCellValue(dto.getUniversity_roll());
            row.createCell(4).setCellValue(dto.getYear());
            row.createCell(5).setCellValue(dto.getBranch());

            row_pointer ++;
        }

        workbook.write(out);

        ByteArrayInputStream ret = new ByteArrayInputStream(out.toByteArray());

        workbook.close();
        out.close();

        return ret;
    }
}