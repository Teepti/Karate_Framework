
package Karate_Framework.Demo.TestCases;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.RunnerOptions;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;



/**
 * The Class UserRunnerTest runs all the scenarios written in the feature files present in 
 * the classpath and generate the reports in xml,html and json format.It collects Json format 
 * of all the scenarios that are executed in a very presentable format using cucumber reporting.
 */
public class UserRunnerTest {
	
	/**
	 * Test parallel is the starter test method that executes when this java file is called
	 * It does not return anything.It picks all the feature files from the classpath and executes
	 * the scenarios according to tags if mentioned.If tags not mentioned then it executes all the scenarios.
	 * It also calls the generateReport method.
	 */
	@Test
	public void testParallel() {
		String karateOutputPath = "target/files";
        String env = System.getProperty("karate.env");
        System.out.println(env);
		
		try {
			
			Results stats = Runner.parallel(UserRunnerTest.class, 5, karateOutputPath);
		
			generateReport(env);
			Assert.assertTrue(stats.getFailCount() == 0, "There are scenario failures");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Generate report
	 * This method collects the path of all the JSon files executed at the time of execution 
	 * and pass to the ReportBuilder class that will help in generating very presentable reports.
	 * @param env takes the value from commandLine and used in this method for reporting.
	 * @return This method does not return anything.
	 */
	public static void generateReport(String env){
		List<String> allJsonFiles = getJsonFiles();
		List<String> jsonFiles = new ArrayList<String>();
		for(int i=0;i<allJsonFiles.size();i++) {
			jsonFiles.add("target/files/"+allJsonFiles.get(i));
		}	
			String timeStamp = getTimeStamp();
			String filePath = System.getProperty("user.dir") + "/KarateTestResults/" + env+ "/" + timeStamp;
			File reportOutputDirectory = new File(filePath);
			String projectName = "Demo";
			Configuration configuration = new Configuration(reportOutputDirectory,projectName);
			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles,configuration);
			reportBuilder.generateReports();
			System.out.println("Report got generated");
		}
	
	/**
	 * Gets the json files.
	 * @return the json files
	 */
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
	
	/**
	 * Validate empty json file.
	 * @param filePath the file path
	 * @return true, if successful
	 */
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
	
	/**
	 * Gets the current time stamp.
	 * @return the formatted time stamp
	 */
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
