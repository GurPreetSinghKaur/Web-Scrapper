package en.gurpreet.cst3130;

import org.apache.commons.text.WordUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Onbuy Thread
 * <p> This is a thread in charge to scrape products from Onbuy</p>
 *  @author  Gur Preet Singh Kaur - M00812691
 *  @version 1.0
 *  @since   2021-09-01
 */
public class Onbuy extends WebScrapper implements Runnable   {

    //Define Variables
    private String url;
    private String website;

    /**
     * Initialise variables with default values
     */
    Onbuy( ) {
        url = "none";
        website = "none";
    }

    //Getters and Setters
    public String getUrl() {
        return url;
    }
    public void setUrl (String url) {
        this.url = url;
    }

    @Override
    public void run() {
        //run
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //Hibernate get Session Factory
        HibernateConfig hibernateConfig = (HibernateConfig) context.getBean("hibernateFactoryBean");

        //make a new Utility object
        Utility utility = new Utility();

        /* web scrapping starts here*/
        while (true){

            try {
                      int page = 0;
                for (int pagination = 0; pagination < 1; pagination++) {


                    System.out.print("Pagination started, scrapped 0 to  " + page + " products \n");
                    String myUrl =  url + page;


                    final Document document = Jsoup.connect(myUrl).
                        //    .proxy("194.113.73.85", 8080)
                    ignoreHttpErrors(false).timeout(5000)

                            .userAgent("Chrome")
                            .get();
                    Elements products = document.select(".product");

                    //get the name
              //      List<String> elementName = document.select(".product").eachAttr("data-product_name");
                    List<String> elementName =   products.select(".product").select("a").select("img").eachAttr("alt");

                    //Clean the product names
                    ArrayList<String> tmpProductList = new ArrayList<>();

                    //get all the String values from the list
                    String tempName = elementName.toString().replace("|", "").
                                    replace("(","").
                                    replace(")","").
                                    replace("Product","").
                                    replace("2nd Generation","").
                                    replace("Dual Sim","").
                                    replace("Single Sim","").
                                    replace("Single SIM","").
                                    replace("Single SIM","").
                                    replace("]","").
                                    //    replace("  ","").
                                    //   replace("  ","").
                                            replace("e]",""). //check if needed
                                            replace("[","").
                                    replace("3GB RAM ","").
                                    replace("PRODUCT", "").
                                    replace("&quot;", "").
                                    replace("A2894","").
                                    replace("Unlocked","").
                                    replaceAll(",","").
                                    replace("5G", "").
                            replace("2020", "").
                                    replace("3GB","").
                                    replace("RAM","");
                    //Convert list object to an array
                    String[] productName = tempName.split("Apple");

                    //get the Price
                    Elements elementPrice = document.select(".value");
                    String[] productPrice = elementPrice.text().replace("From","").split("£");

                    //get the URL address for each product
                    List<String> url = products.select(".product").select("a").eachAttr("href");

                    //get the images for each product
                    List<String> images = products.select(".product").select("a").select("img").eachAttr("data-src");

                    //get all the String values from the list
                    String tmpUrl = url.toString().replace("[", "").
                                                   replace("]","");
                    //Convert list object to an array
                    String[] productUrl = tmpUrl.split(", |  ");

      //  document.select(".product").eachAttr("data-product_name").stream().forEach(System.out::println);
  //    Arrays.stream(productUrl).forEach(System.out::println);

      for (int i = 0 ; i < productName.length - 1; i++ ){
          System.out.println(productName[i+1] + " " + productUrl[i] + " " + productPrice[i+1] + ""  );
          System.out.println("productName length is " + productName.length + "productUrl " + productUrl.length + "price lenmgth is " + productPrice.length );
      }

    System.out.println("this is name"+ products.select(".product").select("a").select("img").eachAttr("alt") );//.eachAttr("data-src"));
                    // Start loop to insert products into the database
                    for (int i = 0; i < productName.length -1; i++) {

                        utility.findSize(productUrl[i]);

                        if (!productUrl[i].contains("samsung") & !productName[i+1].contains("Samsung") & utility.getSize() != null) {

                            utility.findColor(productName[i+1]);
                            utility.findSize(productUrl[i]);
                            utility.findBrand(tempName);
                            String tmpColor =  WordUtils.capitalize(utility.getColor());
                            String[] model = productName[i+1].split(tmpColor);
                            Price price = new Price();
                            Double formattedPrice = Double.parseDouble(productPrice[i+1].replace(",",""));
                            Phone phone = new Phone();
                            phone.setStorage(utility.getSize());
                            phone.setModel(model[0].trim().replaceAll(utility.getSize(),""));
                            phone.setColor(utility.getColor());

                            price.setWebsiteUrl(productUrl[i]);

                            String search = model[0].trim().replace(" ","-").replace("|","") + "  " + utility.getColor().replace(" ", "-");

                            final Document searchForImage = Jsoup.connect("https://www.argos.co.uk/search/"+search+"/category:42793786/brands:apple/").get();
                            Elements imageDiv = searchForImage.getElementsByAttributeValue("data-test","component-product-card-imageWrapper").select("img");
                            //Select Images
                            List<String> image= imageDiv.eachAttr("src");

                            //get all the String values from the list
                            String tmpImage=  image.toString().replace("[","")
                                    .replace("]","");
                            //Convert list object to an array
                            String[] productImage = tmpImage.split(", |  ");

                           // phone.setImage("https:"+productImage[0]);
                            phone.setImage(images.get(i));
                            phone.setBrand(utility.getBrand());
                            price.setPrice(formattedPrice);
                            price.setWebsite(getWebsite());
                            //Find if the phone exists otherwise create a new one and save it
                            Phone foundPhone = hibernateConfig.findPhone(phone);
                            //Assign the phone to the price
                            price.setPhone(foundPhone);

                            //Find the price if it exists and updated otherwise save it
                            hibernateConfig.findPrice(price);

                        }
                    }//Finish loop to products to database

                    page += 30;

                }
            } catch (Exception ex) {

                ex.printStackTrace();

            }


            try {
                Thread.sleep(200000);
            }catch (InterruptedException e) {
                System.out.println("Session is closed now for Onbuy");
                break;

            }
        }

    }



}









