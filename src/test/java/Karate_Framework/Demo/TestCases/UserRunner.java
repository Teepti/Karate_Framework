package Karate_Framework.Demo.TestCases;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.core.Result;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

@KarateOptions(tags = {"~@ignore"})
public class UserRunner {
		
	@Test
	public static void testParallel() {
		String karateOutputPath = "target/files";
		Results stats = Runner.parallel(UserRunner.class, 5, karateOutputPath);
		try {
			generateReport();
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue("There are scenario failures",stats.getFailCount() == 0);
	}
	public static void generateReport() {
		List<String> allJsonFiles = getJsonFiles();
		List<String> jsonFiles = new ArrayList<String>();
		for(int i=0;i<allJsonFiles.size();i++) {
			jsonFiles.add("target/files/"+allJsonFiles.get(i));
		}	
			String timeStamp = getTimeStamp();
			String environment = "qa";
			String filePath = System.getProperty("user.dir") + "/KarateTestResults/" + environment +"/"+ timeStamp;
			File reportOutputDirectory = new File(filePath);
			String projectName = "Demo";
			Configuration configuration = new Configuration(reportOutputDirectory,projectName);
			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles,configuration);
			reportBuilder.generateReports();
			System.out.println("Report got generated");
		}
	
	public static List<String> getJsonFiles(){
		
		List<String> allJsonFiles = new ArrayList<String>();
		File folder = new File(System.getProperty("user.dir")+"/target/files");
		File[] listOfFiles = folder.listFiles();
		for(int i=0;i<listOfFiles.length;i++) {
			if(listOfFiles[i].isFile()) {
				if(listOfFiles[i].getName().endsWith("json")) {
					System.out.println("in Json");
					boolean jsonFileValidationStatus = validateEmptyJsonFile(System.getProperty("user.dir")+"/target/files/"+listOfFiles[i].getName());
					System.out.println("File" + listOfFiles[i].getName() + jsonFileValidationStatus);
					if(jsonFileValidationStatus == true) {
						allJsonFiles.add(listOfFiles[i].getName());
					}
				}
			}
		}
		
		return allJsonFiles;
		
	}
	
	public static boolean validateEmptyJsonFile(String filePath) {
		try {
			File file = new File(filePath);
			if(file.length() == 0) {
				try 
				{
					if(file.delete()) {
						System.out.println("Corrupted File Deleted Successfully");
					}
				}catch(Exception e) {
					System.out.println("Corrupted File could not deleted successfully,exception occured "+e);
				}
				return false;
			}else {
				return true;
			}
		}catch(Exception e) {
			System.out.println("Exception occured while validating JSON file is empty or not"+e);
			return false;
		}
		
	}
	
	public static String getTimeStamp() {
		Date todayDate = new Date();
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String timeStamp = df.format(todayDate).trim();
		String tempStr = timeStamp.replace(" ", "_");
		String tempStr1 = tempStr.replace(":", "_");
		timeStamp = tempStr1.replace("-","_");
		
		return timeStamp;
		
	}

}
