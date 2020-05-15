package Karate_Framework.Demo.TestCases;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TestParallel {
	
	
	    
	@Test
	public void testParallel() {
		
		Results results = Runner.parallel(getClass(), 5);
		TestParallel.generateReport(results.getReportDir());
		assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
	}
	public static void generateReport(String karateOutputPath) {
			Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
			List<String> jsonPaths = new ArrayList<String> (jsonFiles.size());
			jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
			Configuration config = new Configuration(new File("target"), "Demo");
			ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
			reportBuilder.generateReports();
	    }
	}


