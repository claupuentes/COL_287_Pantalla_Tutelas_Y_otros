package COL_287;

import org.testng.annotations.Test;
import java.io.IOException;
//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//import com.aventstack.extentreports.Status;

/* Verificación mensaje modal centro medico */


public class Mensaje_Centro_Medico {

	public class Mensaje_prod_PRE {

		WebDriver driver;
		NgWebDriver ngWebDriver;
		JavascriptExecutor jsDriver;
		public ReadExcelFile readFile;
		ExtentHtmlReporter htmlReporter;
		ExtentReports extent;
		

		 	@BeforeSuite
			public void setUp() 
		 	
		 	{
				htmlReporter = new ExtentHtmlReporter("Colmedica.html");
				extent = new ExtentReports();
				extent.attachReporter(htmlReporter);
		 	}

		 	
			@BeforeClass
			public void setUpTest() throws Exception
			
			{
				
				WebDriverManager.chromedriver().setup();
				System.setProperty("webdriver.chrome.driver", "C:\\Driver_v89\\chromedriver.exe");
				driver = new ChromeDriver();
				jsDriver = (JavascriptExecutor)driver;
				ngWebDriver = new NgWebDriver(jsDriver);
				driver.get("http://10.2.41.18:9079/authentication");
				readFile = new ReadExcelFile();
				ngWebDriver.waitForAngularRequestsToFinish();	
			}
			
		@Test(priority=1)
		public void Login()  throws IOException 
		
		{
			//titulo del test
			 ExtentTest test1 = extent.createTest("MODULO LOGIN","PRUEBAS AUTOMATIZADAS COLMEDICA");
			 //apartado Informe Details
			 //test1.log(Status.INFO, "Starting Test Case"); //(1)
			 test1.pass("Navigated to http://10.2.41.18:9079/authentication");//(2)	
			 //por cada test que trabaje con Excel debo poner la dirección del archivo excel de su computadora 
			 String filepath = "C:\\Users\\ClaudiaLP\\Documents\\.Proyectos\\proyecto_gradle\\Colmedica_Front\\excel\\data1.xlsx";
			 //con esta parte controlo la info que quiero que me muestre de cada fila y columna del Excel.
			 String username = readFile.getCellValue(filepath,"Login", 1, 0);
		     driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);
			 test1.pass("Texto introducido en la caja Usuario"); //(3)  
			 String password = readFile.getCellValue(filepath,"Login", 1, 1);
		     driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password); 
		     test1.pass("Texto introducido en la caja ContraseNa"); //(3)
		     driver.findElement(ByAngular.buttonText("Entrar")).click();
		     test1.pass("Click en el boton entrar");//(4)
		     //test1.info("Test Completed"); 
		     ngWebDriver.waitForAngularRequestsToFinish();
		 	
		}
		
		
		@Test(priority=2)
		public void office() throws InterruptedException 

		{
			
			//titulo del test
			ExtentTest test2 = extent.createTest("MODULO OFICINA","PRUEBAS AUTOMATIZADAS COLMEDICA");
			//apartado Informe Details
			test2.pass("Seleccion Opcion 221-AUTORIZACIONES EXAMENES"); //(3)
			driver.findElement(By.xpath("//option[@ng-reflect-ng-value='221']")).click(); //221-AUTORIZACIONES EXAMENES (CALL) 
			Thread.sleep(1000);
			ngWebDriver.waitForAngularRequestsToFinish();
			driver.findElement(ByAngular.buttonText("Entrar")).click();
			Thread.sleep(1000);
			test2.pass("Click en el boton entrar");//(4)
			ngWebDriver.waitForAngularRequestsToFinish();	
		
		}

		///Documento 1
		
		@Test(priority=3)
		public void ConsultaDocumento1() throws IOException, InterruptedException  

		{
			  
			String filepath = "C:\\Users\\ClaudiaLP\\Documents\\.Proyectos\\proyecto_gradle\\COL_287_Pantalla_Tutelas_Y_otros\\excel\\data1.xlsx";
			//con esta parte controlo la info que quiero que me muestre de cada fila y columna del Excel.
			//titulo del test
			ExtentTest test3 = extent.createTest("CONSULTA DOCUMENTO 1","PRUEBAS AUTOMATIZADAS COLMEDICA");
			//apartado Informe Details
			driver.findElement(By.xpath("//a[@href='radication']")).click();
			Thread.sleep(1000);
			ngWebDriver.waitForAngularRequestsToFinish();
			driver.findElement(By.xpath("//select[@id='documentType']")).click();
			test3.pass("OPCIONES Tipo identificacion");
			ngWebDriver.waitForAngularRequestsToFinish();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//option[@value='RC-Registro Civil']")).click();
			test3.pass("OPCIONES RC-Registro Civil");
			ngWebDriver.waitForAngularRequestsToFinish();
				           
		     
		
			//numero de documento exitoso
			//driver.findElement(By.xpath("//*[@id='documentNumber']")).clear();
			//test3.pass("OPCIONES Numero de documento limpia la caja de texto");
			//ngWebDriver.waitForAngularRequestsToFinish();
			//Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='documentNumber']")).click();
			test3.pass("OPCIONES Numero de documento selecciona la caja de texto");
			ngWebDriver.waitForAngularRequestsToFinish();
			//Thread.sleep(2000);
			String documentNumber = readFile.getCellValue(filepath,"Radicaex", 3, 1);
			driver.findElement(By.xpath("//*[@id='documentNumber']")).sendKeys(documentNumber);
			test3.pass("OPCIONES Numero de documento selecciona correcto");
			ngWebDriver.waitForAngularRequestsToFinish();
			Thread.sleep(1000);
			driver.findElement(ByAngular.buttonText("Consultar")).click();
			test3.pass("Click en el boton Buscar");//(4)
			ngWebDriver.waitForAngularRequestsToFinish();
			
					
			}
		
		
	    @Test(priority=3)
	    
	    public void centro_medico() throws InterruptedException 
	    
	    {
		
		//centro medico
	    //driver.findElement(By.xpath("/html/body/app-root/app-customer-information/section/article[1]/div[4]/div[1]/select")).click();
	    driver.findElement(By.xpath("//select[@ng-reflect-model='0-Red adscrita']")).click();
	    Thread.sleep(1000);
	    ngWebDriver.waitForAngularRequestsToFinish();
	    driver.findElement(By.xpath("//option[@value='1-Centro médico']")).click();
	    Thread.sleep(1000);
	    ngWebDriver.waitForAngularRequestsToFinish();
	    //driver.findElement(By.xpath("//option[@value='1-Centro médico']")).click();
	    //driver.findElement(By.xpath("//option[@value='2-No adscrita']")).click();
		driver.findElement(ByAngular.buttonText("Buscar")).click();
		Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
	    }
		
		
		@Test(priority=4)
		public void VerificacionMensaje() throws IOException, InterruptedException 
		
		{
	        
			ExtentTest test4 = extent.createTest("VERIFICACION MENSAJE CENTRO MEDICO, PRUEBAS AUTOMATIZADAS COLMEDICA");
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='swal2-title']")).getText().contains("No se encuentran sesiones"));
	        test4.pass("MENSAJE MODAL EMERGENTE  No se encuentran sesiones");//(4)
	        ngWebDriver.waitForAngularRequestsToFinish();
	        
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='swal2-content']")).getText().contains("No se encuentra sesiones en historia clínica para el usuario consultado en los últimos 12 meses"));
	        test4.pass("MENSAJE MODAL EMERGENTE  No se encuentra sesiones en historia clínica para el usuario consultado en los últimos 12 meses");//(4)
	        ngWebDriver.waitForAngularRequestsToFinish();
	        Thread.sleep(2000);
	        driver.findElement(ByAngular.buttonText("OK")).click();
	        Thread.sleep(1000);
	        
		}
		
		
		
		
		@AfterTest
		public void tearDownTest() {
	   
	    //close browser
	            driver.close();
	            driver.quit();
	            System.out.println("Test Completed Successfully");
	            
	    }  
		
	    @AfterSuite
	    public void tearDown() {
	         extent.flush();    
	    }

	}
		
		

}
