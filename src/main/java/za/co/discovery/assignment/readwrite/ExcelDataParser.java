package za.co.discovery.assignment.readwrite;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.domain.PlanetNode;
import za.co.discovery.assignment.domain.Route;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
@Component
public class ExcelDataParser {

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }
    public Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }
    public List<PlanetNode> readPlanetNode() throws IOException {
        List<PlanetNode> planets = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream("/data.xls");
        Workbook workbook = getWorkbook(inputStream, "data.xls");
        Sheet planetsSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = planetsSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            PlanetNode planetNode = new PlanetNode();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        planetNode.setPlanetNode((String)
                                getCellValue(nextCell));
                        break;
                    case 1:
                        planetNode.setPlanetName((String)
                                getCellValue(nextCell));
                        break;
                }

            }
            if(!"Planet Name".equals(planetNode.getPlanetName()))
                planets.add(planetNode);
        }

        workbook.close();
        inputStream.close();

        return planets;
    }
    public List<Route> readRoute() throws IOException {
        List<Route> routes = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream("/data.xls");
        Workbook workbook = getWorkbook(inputStream, "data.xls");
        Sheet routesSheet = workbook.getSheetAt(1);
        Iterator<Row> iterator = routesSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Route aRoute = new Route();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        if(!"Route Id".equals(getCellValue(nextCell))){
                        aRoute.setRouteId((Double)
                                getCellValue(nextCell));
                        }
                        break;
                    case 1:
                        aRoute.setPlanetOrigin((String)
                                getCellValue(nextCell));
                        break;
                    case 2:
                        if(!"Planet Destination".equals(getCellValue(nextCell))){
                        aRoute.setPlanetDestination((String)
                                getCellValue(nextCell));
                        }
                        break;
                    case 3:
                        if(!"Distance(Light Years)".equals(getCellValue(nextCell))) {
                            aRoute.setDistance((Double)
                                    getCellValue(nextCell));
                        }
                        break;
                }

            }
            if(!"Planet Origin".equals(aRoute.getPlanetOrigin()))
                routes.add(aRoute);
        }

        workbook.close();
        inputStream.close();

        return routes;
    }




}

