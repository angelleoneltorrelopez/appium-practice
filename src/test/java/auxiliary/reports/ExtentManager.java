package auxiliary.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static final String date = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
    private static final String reportFileName = "TestReport" + date + ".html";
    private static final String fileSeparator = System.getProperty("file.separator");
    private static final String reportFilepath = System.getProperty("user.dir") + fileSeparator + "TestReport";
    private static final String reportFileLocation = reportFilepath + fileSeparator + reportFileName;
    public static ExtentReports getInstance() {
        if (extent == null)
            extent = createInstance();
        return extent;
    }

    public static ExtentReports createInstance() {
        String filePath = getReportPath();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filePath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automated tests (Project Name)");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", "iOS");
        extent.setSystemInfo("AUT", "QA");
        return extent;
    }

    private static String getReportPath() {
        File testDirectory = new File(ExtentManager.reportFilepath);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                return reportFileLocation;
            } else {
                return System.getProperty("user.dir");
            }
        }
        return reportFileLocation;
    }
}
