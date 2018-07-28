package com.ThaThrowdown;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperClass {

    static String filePath = System.getProperty("user.dir") + "/SeleniumScreenshots";
    static String fileSeperator = filePath+System.getProperty("file.separator");
    static String childFolder = "folder";


    public static void makeParentDirectory() throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            System.out.println("Directory created");
        } else {
            System.out.println("Directory exists");
        }
    }

    public static void makeChildDirectory() throws IOException {
        Path path = Paths.get(filePath + fileSeperator + childFolder);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            System.out.println("Directory created");
        } else {
            System.out.println("Directory exists");
        }
    }

    public  static  String childPath(String fileName){

        return  filePath+fileName;
    }
    public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {

        makeParentDirectory();
       // makeChildDirectory();

        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date newDate = new Date();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileHandler.copy(src, new File(filePath, fileName + dateFormat.format(newDate).replace(" ", "").concat(".jpg")));


    }

    public  static  void setChildDirectory(String folderName){

        childFolder=folderName;

    }


}





