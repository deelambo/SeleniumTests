package com.ThaThrowdown;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
         //write your code here

        WebDriver driver= new ChromeDriver();
        driver.get("http://youtube.co.uk");
       ScreenshotHelper.takeScreenShot(driver,"lobo");
       driver.close();





//
//        System.out.println(" enter your name");
//        Scanner scanner = new Scanner(System.in);
//
//        String name = scanner.nextLine();
//
//         String name2 = "Legend";
//        System.out.println(String.format("hello %s %s",name,name2));

//        Path path = Paths.get("lobo.png");
//
//        System.out.println(path);

    }


}
