package snapDealpackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SnapDealclass {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Soniya\\SnapDeal\\lib\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get(" https://www.snapdeal.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement ele1 = driver.findElement(By.xpath("//span[@class='catText']"));
		WebElement ele2 = driver.findElement(By.xpath("//*[@class='navlink']/div/div/div/div/p[2]/a"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(ele1).moveToElement(ele2).click().build().perform();
		
		String itemcount = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText().replaceAll("[^0-9]", "");
		System.out.println(itemcount);
		
		Map<String, Integer> productPrices = new HashMap<>();
		List<WebElement> products = driver.findElements(By.xpath("//p[@class='product-title']"));
        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
        
        for(int i =0;i<products.size();i++) {
        	String productName = products.get(i).getText();
        	int productPrice = Integer.parseInt(prices.get(i).getText().replaceAll("[^0-9]", ""));
        	productPrices.put(productName, productPrice);
        }
        
       // List<WebElement> show = driver.findElements(By.xpath("//span[contains(text(),'Show')]"));
        List<WebElement> show = driver.findElements(By.xpath("//div[@id='see-more-products']"));
        WebDriverWait wait = new WebDriverWait(driver,30);
        
        while(show.size()==1) {
        	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='see-more-products")));
        	Thread.sleep(3000);
        	driver.findElement(By.xpath("//div[@id='see-more-products']")).click();
        	
        	for(int i =0;i<products.size();i++) {
            	String productName = products.get(i).getText();
            	int productPrice = Integer.parseInt(prices.get(i).getText().replaceAll("[^0-9]", ""));
            	productPrices.put(productName, productPrice);
            }
        	
        	
        }
		
		System.out.println(productPrices);
		
		int itemcountfinal = Integer.parseInt(itemcount);
		
		if(itemcountfinal==products.size()) {
			System.out.println("SUCCESS");
		}
		
		else
			System.out.println("FAIL");
		
		
		int maxPrice = Collections.max(productPrices.values());
		for (Entry<String, Integer> maxValue : productPrices.entrySet()) {
			if(maxValue.getValue() == maxPrice) {
				String maxKey = maxValue.getKey();
			}
		}
		
		WebElement e3 = driver.findElement(By.xpath("//div[@class='product-tuple-image ']/a/picture/img[starts-with(@title,maxKey)]"));
		WebElement e4 = driver.findElement(By.xpath("//div[@class='product-tuple-image ']/child::div/div"));
		
		actions.moveToElement(e3).moveToElement(e4).click().build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'view details')]")).click();
		
		
		
		List <WebElement> size = driver.findElements(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left']"));
		System.out.println(size.size());
		
		//Finding max size
		int s = Integer.parseInt(driver.findElement(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left'][1]/div")).getAttribute("selectedattr"));
		for(int i =1;i <= size.size() ;i++) {
			if(s <  Integer.parseInt(driver.findElement(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left'][i]/div")).getAttribute("selectedattr"))) {
				
				s = Integer.parseInt(driver.findElement(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left'][i]/div")).getAttribute("selectedattr"));
				
			}
		}
		
		for(int i =1;i <= size.size() ;i++) {
		if(driver.findElement(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left']/div[@selectedattr='s']")).getAttribute("class")=="soldout") {
			
		}
			
		else {
			driver.findElement(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left'][i]/div[@selectedattr='s-1']")).click();
		}
	}
		
		driver.findElement(By.xpath("//div/span[contains(text(),'add to cart')]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'open-cart')]")).click();
		
		if (productPrices.containsKey(driver.findElement(By.xpath("//a[contains(@title,productName)]")).getAttribute("title")) == true) {
			System.out.println("Found the product name successfully"+driver.findElement(By.xpath("//a[contains(@title,productName)]")).getAttribute("title"));
		}
		
		else{
			System.out.println("Product name is not available in hashmap");
		}
		
		int p = Integer.parseInt(driver.findElement(By.xpath("//span[@class='item-price']")).getText().replaceAll("[^0-9]", ""));
		if(productPrices.containsValue(p)== true) {
			System.out.println("Found the product price successfully"+ p);
		}
		
		else {
			System.out.println("Product price is not available in hashmap");
		}
		
		
		int minPrice = Collections.min(productPrices.values());
		for (Entry<String, Integer> minValue : productPrices.entrySet()) {
			if(minValue.getValue() == minPrice) {
				String minKey = minValue.getKey();
			}
		}
		
		actions.moveToElement(e3).moveToElement(e4).click().build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'view details')]")).click();
		
		//Finding max size
				
				for(int i =1;i <= size.size() ;i++) {
					if(s <  Integer.parseInt(driver.findElement(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left'][i]/div")).getAttribute("selectedattr"))) {
						
						s = Integer.parseInt(driver.findElement(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left'][i]/div")).getAttribute("selectedattr"));
						
					}
				}
				
				for(int i =1;i <= size.size() ;i++) {
				if(driver.findElement(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left']/div[@selectedattr='s']")).getAttribute("class")=="soldout") {
					
				}
					
				else {
					driver.findElement(By.xpath("//div[@class='attr-value-cont sqt-attr-val']/div[@class='pull-left'][i]/div[@selectedattr='s-1']")).click();
				}
			}
				
				driver.findElement(By.xpath("//div/span[contains(text(),'add to cart')]")).click();
				driver.findElement(By.xpath("//div[contains(@class,'open-cart')]")).click();
				
				if (productPrices.containsKey(driver.findElement(By.xpath("//a[contains(@title,productName)]")).getAttribute("title")) == true) {
					System.out.println("Found the product name successfully"+driver.findElement(By.xpath("//a[contains(@title,productName)]")).getAttribute("title"));
				}
				
				else{
					System.out.println("Product name is not available in hashmap");
				}
				
				
				if(productPrices.containsValue(p)== true) {
					System.out.println("Found the product price successfully"+ p);
				}
				
				else {
					System.out.println("Product price is not available in hashmap");
				}
				
		/*WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='linkTest']")));*/
		
		//driver.findElement(By.xpath("//span[@class='linkTest']")).click();		
		//System.out.println(driver.getTitle());

	}

}
