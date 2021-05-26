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

//import COL_287.ReadExcelFile;
import io.github.bonigarcia.wdm.WebDriverManager;
//import com.aventstack.extentreports.Status;


/*Ajuste pantalla de Tutelas*/


//////PRUEBA GIT/////


public class Valid_campos_tutela {

	
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
			//driver.manage().window().maximize();
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
		ExtentTest test3 = extent.createTest("CONSULTA DOC CC 3044200","PRUEBAS AUTOMATIZADAS COLMEDICA");
		//apartado Informe Details
		driver.findElement(By.xpath("//a[@href='radication']")).click();
		Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
		driver.findElement(By.xpath("//select[@id='documentType']")).click();
		test3.pass("OPCIONES Tipo identificacion");
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//option[@value='CC-Cedula de Ciudadania']")).click();
		test3.pass("OPCIONES CC-Cedula de Ciudadania");
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
		String documentNumber = readFile.getCellValue(filepath,"Tutelas", 1, 2);
		driver.findElement(By.xpath("//*[@id='documentNumber']")).sendKeys(documentNumber);
		test3.pass("OPCIONES Numero de documento selecciona correcto");
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(1000);
		driver.findElement(ByAngular.buttonText("Consultar")).click();
		test3.pass("Click en el boton Buscar");//(4)
		ngWebDriver.waitForAngularRequestsToFinish();
		
		}
	
    
    @Test(priority=4)
    
    public void TutelasDoc1() throws InterruptedException, IOException 
    
    {
    	ExtentTest test4 = extent.createTest("VERIFICACION TUTELAS CC 3044200","PRUEBAS AUTOMATIZADAS COLMEDICA");
    	
    	driver.findElement(By.xpath("//*[@id='wardship']")).click();
		//test3.pass("Click en el boton Buscar");//(4)
    	Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
		driver.findElement(By.xpath("//td[contains(.,'5162')]")).click();
		Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@id='tutelageNumber']")).getText().contains("11842"));
		//Thread.sleep(1000);
		//test4.pass("Comprobacion Numero de Tutela");
		
		driver.findElement(By.xpath("//input[@id='fullTutelage']")).click();
		Thread.sleep(1000);
		Assert.assertEquals ("5162", "5162");
		test4.pass("Comprobacion Numero Tutela APOLO");
   	 	//Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
		
				
		test4.pass("Comprobacion Numero de Tutela");
		driver.findElement(By.xpath("//input[@id='tutelageNumber']")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	    Assert.assertEquals ("11842", "11842");
   	    //Thread.sleep(1000);
   	    //test4.fail("Comprobacion Numero de Tutela");
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	 
    }
   	 	
    
    ///Documento 2
    
    
    
   	@Test(priority=5)
   	public void ConsultaDocumento2() throws IOException, InterruptedException  

   	{
   	 	
   	    driver.findElement(ByAngular.buttonText("Cerrar")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();

   	    ///otra cedula
   	    
   	    driver.findElement(By.xpath("//*[@id=\"filings\"]")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	    driver.findElement(ByAngular.buttonText("Cancelar")).click();
   	    Thread.sleep(1000);
   	    ngWebDriver.waitForAngularRequestsToFinish();
   	    driver.findElement(By.xpath("//button[contains(.,'SI')]")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	 	
   	    
   	    String filepath = "C:\\Users\\ClaudiaLP\\Documents\\.Proyectos\\proyecto_gradle\\COL_287_Pantalla_Tutelas\\excel\\data1.xlsx";
		//con esta parte controlo la info que quiero que me muestre de cada fila y columna del Excel.
		//titulo del test
		ExtentTest test5 = extent.createTest("CONSULTA DOC CC 22773815","PRUEBAS AUTOMATIZADAS COLMEDICA");
		//apartado Informe Details
		driver.findElement(By.xpath("//a[@href='radication']")).click();
		Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
		driver.findElement(By.xpath("//select[@id='documentType']")).click();
		test5.pass("OPCIONES Tipo identificacion");
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//option[@value='CC-Cedula de Ciudadania']")).click();
		test5.pass("OPCIONES CC-Cedula de Ciudadania");
		ngWebDriver.waitForAngularRequestsToFinish();
			           
	     
	
		//numero de documento exitoso
		//driver.findElement(By.xpath("//*[@id='documentNumber']")).clear();
		//test3.pass("OPCIONES Numero de documento limpia la caja de texto");
		//ngWebDriver.waitForAngularRequestsToFinish();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='documentNumber']")).click();
		test5.pass("OPCIONES Numero de documento selecciona la caja de texto");
		ngWebDriver.waitForAngularRequestsToFinish();
		//Thread.sleep(2000);
		String documentNumber = readFile.getCellValue(filepath,"Tutelas", 2, 2);
		driver.findElement(By.xpath("//*[@id='documentNumber']")).sendKeys(documentNumber);
		test5.pass("OPCIONES Numero de documento selecciona correcto");
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(1000);
		driver.findElement(ByAngular.buttonText("Consultar")).click();
		test5.pass("Click en el boton Buscar");//(4)
		ngWebDriver.waitForAngularRequestsToFinish();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='wardship']")).click();
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[contains(.,'15157')]")).click();
		Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
		
		
	}
   	    
   	@Test(priority=6)    
    public void TutelasDoc2() throws InterruptedException, IOException
    
    {
    	


    	ExtentTest test6 = extent.createTest("VERIFICACION TUTELAS CC 22773815","PRUEBAS AUTOMATIZADAS COLMEDICA");
    	Thread.sleep(1000);
    	ngWebDriver.waitForAngularRequestsToFinish();
		//test6.fail("Comprobacion Numero de Tutela");
		driver.findElement(By.xpath("//input[@id='tutelageNumber']")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	    Assert.assertEquals ("15157", "15157");
   	    Thread.sleep(1000);
   	    test6.pass("Comprobacion Numero de Tutela");
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	 	
   	 	
   	 	
		driver.findElement(By.xpath("//input[@id='fullTutelage']")).click();
		Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
		Assert.assertEquals ("15157", "15157");
		Thread.sleep(1000);
		test6.pass("Comprobacion Numero Tutela APOLO");
   	 	ngWebDriver.waitForAngularRequestsToFinish();
		   	 
    }
    
   	//////Documento 3
   	
   	@Test(priority=7)
   	public void ConsultaDocumento3() throws IOException, InterruptedException  

   	{
   	 	
   	    driver.findElement(ByAngular.buttonText("Cerrar")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();

   	    ///otra cedula
   	    
   	    driver.findElement(By.xpath("//*[@id=\"filings\"]")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	    driver.findElement(ByAngular.buttonText("Cancelar")).click();
   	    Thread.sleep(1000);
   	    ngWebDriver.waitForAngularRequestsToFinish();
   	    driver.findElement(By.xpath("//button[contains(.,'SI')]")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	 	
   	    
   	    String filepath = "C:\\Users\\ClaudiaLP\\Documents\\.Proyectos\\proyecto_gradle\\COL_287_Pantalla_Tutelas\\excel\\data1.xlsx";
		//con esta parte controlo la info que quiero que me muestre de cada fila y columna del Excel.
		//titulo del test
		ExtentTest test7 = extent.createTest("CONSULTA DOCUMENTO CC 20333234","PRUEBAS AUTOMATIZADAS COLMEDICA");
		//apartado Informe Details
		driver.findElement(By.xpath("//a[@href='radication']")).click();
		Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
		driver.findElement(By.xpath("//select[@id='documentType']")).click();
		test7.pass("OPCIONES Tipo identificacion");
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//option[@value='CC-Cedula de Ciudadania']")).click();
		test7.pass("OPCIONES CC-Cedula de Ciudadania");
		ngWebDriver.waitForAngularRequestsToFinish();
			           
	     
	
		//numero de documento exitoso
		//driver.findElement(By.xpath("//*[@id='documentNumber']")).clear();
		//test3.pass("OPCIONES Numero de documento limpia la caja de texto");
		//ngWebDriver.waitForAngularRequestsToFinish();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='documentNumber']")).click();
		test7.pass("OPCIONES Numero de documento selecciona la caja de texto");
		ngWebDriver.waitForAngularRequestsToFinish();
		//Thread.sleep(2000);
		String documentNumber = readFile.getCellValue(filepath,"Tutelas", 3, 2);
		driver.findElement(By.xpath("//*[@id='documentNumber']")).sendKeys(documentNumber);
		test7.pass("OPCIONES Numero de documento selecciona correcto");
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(1000);
		driver.findElement(ByAngular.buttonText("Consultar")).click();
		test7.pass("Click en el boton Buscar");//(4)
		ngWebDriver.waitForAngularRequestsToFinish();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='wardship']")).click();
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[contains(.,'2017')]")).click();
		Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
		
		
	}
   	    
   	@Test(priority=8)    
    public void TutelasDoc3() throws InterruptedException, IOException
    
    {
    	


    	ExtentTest test8 = extent.createTest("VERIFICACION TUTELAS CC 20333234","PRUEBAS AUTOMATIZADAS COLMEDICA");
    	Thread.sleep(1000);
    	ngWebDriver.waitForAngularRequestsToFinish();
		//test6.fail("Comprobacion Numero de Tutela");
		driver.findElement(By.xpath("//input[@id='tutelageNumber']")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	    Assert.assertEquals ("10810", "10810");
   	    Thread.sleep(1000);
   	    test8.pass("Comprobacion Numero de Tutela");
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	 	
   	 	
   	 	
		driver.findElement(By.xpath("//input[@id='fullTutelage']")).click();
		Thread.sleep(1000);
		ngWebDriver.waitForAngularRequestsToFinish();
		Assert.assertEquals ("2017", "2017");
		Thread.sleep(1000);
		test8.pass("Comprobacion Numero Tutela APOLO");
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	 	
   	 	   	 	
   	    driver.findElement(ByAngular.buttonText("Cerrar")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
   	 	
   	 	
   	    driver.findElement(By.xpath("//*[@id='logout']")).click();
   	 	Thread.sleep(1000);
   	 	ngWebDriver.waitForAngularRequestsToFinish();
		
	   	  	 
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
