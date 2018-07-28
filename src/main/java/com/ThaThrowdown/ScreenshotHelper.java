package com.ThaThrowdown;


import org.apache.commons.io.FileUtils;
import org.apache.maven.shared.utils.io.DirectoryScanner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ScreenshotHelper {


    static String filePath = System.getProperty("user.dir") + "/SeleniumScreenshots/";
    static ImageDiff imageDiff;
    static DirectoryScanner scanner = new DirectoryScanner();

    public static void makeParentDirectory() throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            System.out.println("Directory created");
        } else {
            System.out.println("Directory exists");
        }
    }

    public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {

        makeParentDirectory();

        scanner.setIncludes(new String[]{fileName, "**/*.png"});
        scanner.setBasedir(System.getProperty("user.dir"));
        scanner.setCaseSensitive(false);
        scanner.scan();
        String[] allFiles = scanner.getIncludedFiles();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        if (allFiles.length == 0) {
            FileUtils.copyFile(src, new File(filePath, fileName + (".png")));
        }
        else {

            for (String file : allFiles) {
                if (file.equalsIgnoreCase(filePath.replace(System.getProperty("user.dir") + "/", "") + fileName + ".png")) {
                    FileUtils.copyFile(src, new File(filePath, fileName + ("new.png")));
                }
            }
        }

        compareImages(fileName);
        if (compareImages(fileName)==true){

            System.out.println("The images are not the same check the comparison pic");


        }




    }


    public static boolean compareImages(String expectedFile) throws IOException {

        BufferedImage expectedImage = null;
        BufferedImage actualImage = null;
        scanner.setIncludes(new String[]{expectedFile, "**/*.png"});
        scanner.setBasedir(System.getProperty("user.dir"));
        scanner.setCaseSensitive(false);
        scanner.scan();
        String[] allFiles = scanner.getIncludedFiles();
        if (allFiles.length ==1) {

            expectedImage = ImageIO.read(new File(filePath + expectedFile + ".png"));
            actualImage=ImageIO.read(new File(filePath + expectedFile + ".png"));
        }
        else if (allFiles.length > 1) {
            try {
                expectedImage = ImageIO.read(new File(filePath + expectedFile + ".png"));
                actualImage = ImageIO.read(new File(filePath + expectedFile + "new.png"));
            } catch (IIOException e) {

                System.out.println("something went wrong");
                System.out.println(e.getMessage());
            }
        }
        ImageDiffer imageDiffer = new ImageDiffer();
        imageDiff = imageDiffer.makeDiff(expectedImage, actualImage);

        if (imageDiff.hasDiff())

        {
            ImageIO.write(imageDiff.getMarkedImage(), "PNG", new File(filePath, (expectedFile+"ComparisonPic.png")));

        } else {
            System.out.println("the images " + expectedFile + " and " + expectedFile + "new" + " " + "are the same");
        }



        return imageDiff.hasDiff();
    }

}