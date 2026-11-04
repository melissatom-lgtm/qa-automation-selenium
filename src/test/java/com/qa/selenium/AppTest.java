
package com.qa.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    // PRUEBA 1: Abrir Google
    @Test
    public void abrirGoogleChrome() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://www.google.com");

            assertTrue(
                driver.getCurrentUrl().contains("google.com")
            );

            System.out.println("PRUEBA 1 EXITOSA");

        } finally {
            driver.quit();
        }
    }

    // PRUEBA 2: Buscar en Google
    @Test
    public void buscarEnGoogle() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://www.google.com");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
            );

            WebElement buscador = wait.until(
                d -> d.findElement(By.name("q"))
            );

            buscador.sendKeys(
                "Automatizacion de pruebas Selenium"
            );

            buscador.sendKeys(Keys.ENTER);

            wait.until(
                d -> d.getCurrentUrl().contains("search")
            );

            assertTrue(
                driver.getCurrentUrl().contains("search")
            );

            System.out.println("PRUEBA 2 EXITOSA");

        } finally {
            driver.quit();
        }
    }

    // PRUEBA 3: Inicio de sesion correcto
    @Test
    public void iniciarSesionSauceDemo() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
            );

            WebElement usuario = wait.until(
                d -> d.findElement(By.id("user-name"))
            );

            usuario.sendKeys("standard_user");

            WebElement password = driver.findElement(
                By.id("password")
            );

            password.sendKeys("secret_sauce");

            driver.findElement(
                By.id("login-button")
            ).click();

            wait.until(
                d -> d.getCurrentUrl().contains("inventory.html")
            );

            assertTrue(
                driver.getCurrentUrl().contains("inventory.html")
            );

            System.out.println("PRUEBA 3 EXITOSA");

        } finally {
            driver.quit();
        }
    }

    // PRUEBA 4: Contraseña incorrecta
    @Test
    public void iniciarSesionIncorrecta() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
            );

            WebElement usuario = wait.until(
                d -> d.findElement(By.id("user-name"))
            );

            usuario.sendKeys("standard_user");

            WebElement password = driver.findElement(
                By.id("password")
            );

            password.sendKeys("123456");

            driver.findElement(
                By.id("login-button")
            ).click();

            // Esperar el mensaje de error
            WebElement mensajeError = wait.until(
                d -> d.findElement(
                    By.cssSelector("[data-test='error']")
                )
            );

            // Verificar el mensaje
            assertEquals(
                "Epic sadface: Username and password do not match any user in this service",
                mensajeError.getText()
            );

            // Verificar que no ingreso a productos
            assertTrue(
                !driver.getCurrentUrl().contains("inventory.html")
            );

            System.out.println("PRUEBA 4 EXITOSA");

        } finally {
            driver.quit();
        }
    }
}
