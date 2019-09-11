package br.com.valid.location;

import br.com.valid.location.LocationAPI;
import br.com.valid.location.LocationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author rodmafra
 */
public class ReaderMain {


    private String xlsFile = this.getClass().getClassLoader().getResource( "Template_Mapas.xlsx").getFile();

    private static String url = "https://maps.googleapis.com/maps/api/geocode/json?address=AV.+PRINCESA+ISABEL+395,+Porto+Alegre,+CA&key=";

    public void read() throws IOException {

        FileInputStream excelFile = new FileInputStream(xlsFile);

        Workbook workbook = new XSSFWorkbook(excelFile);

        final Sheet sheet = workbook.getSheetAt(0);
        final LocationAPI locationAPI = new LocationAPI("https://maps.googleapis.com/maps/api/geocode/json");
        sheet.forEach( row -> {
            if (!  row.getCell(0).getStringCellValue().isEmpty() &&
            row.getRowNum() != 0) {

                LocationRequest request = new LocationRequest();

                request.setAddress(row.getCell(1).getStringCellValue());
                request.setCity(row.getCell(2).getStringCellValue());
                request.setState(row.getCell(3).getStringCellValue());
                try {
                    final LocationResponse locationResponse = locationAPI.executeLocationApiCall(request);
                    System.out.println(locationResponse.getLat()+","+locationResponse.getLon());
                    //row.createCell(4).setCellValue(locationResponse.getLat()+","+locationResponse.getLon());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        excelFile.close();

    }



}
