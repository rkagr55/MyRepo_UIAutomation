package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import main.BaseClass;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelUtils extends BaseClass{
  
	 public static String getData(String testCaseName, String columnName) {
		 int row=-1;
		 
		 for(int i=1; i<=sh.getLastRowNum(); i++) {
			 if(sh.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName)) row=i;
		 }
		 
	    int col=-1;
		 
		 for(int i=1; i<sh.getRow(0).getLastCellNum(); i++) {
			 if(sh.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) col=i;
		 }
			Cell cell=sh.getRow(row).getCell(col);
		 
			if(cell.getCellType()==CellType.STRING)   return cell.getStringCellValue();  
			
			else if(cell.getCellType()==CellType.BOOLEAN) return  String.valueOf(cell.getBooleanCellValue()); 
			
			else if(cell.getCellType()==CellType.NUMERIC) { 
			  if(DateUtil.isCellDateFormatted(cell)) { 
				   Date date = cell.getDateCellValue(); 
				   SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yy"); 
				   return ft.format(date); }
			  
			  return  String.valueOf((long)cell.getNumericCellValue());
			  }
		return null;
	 }	 
 }