package PetStore;

import PetStore.files.payload;
import PetStore.pojo.store;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetAPI {

    @Test
    public void CreateNewPet (){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(payload.createPetBody())
                .when().post("/pet")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();


        JsonPath js = new JsonPath(response);

        String petID = js.getString("id");

        //--------FIND PET BY ID AND ASSERT DOES IT EXIST


        String response1 = given().log().all()
                .when().get("/pet/" + petID)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = new JsonPath(response1);

        String name = js1.getString("name");
        String categoryID = js1.getString("category.id");
        String categoryName = js1.getString("category.name");

        Assert.assertEquals(name, "Cat");
        Assert.assertEquals(categoryID, "2");
        Assert.assertEquals(categoryName, "Blacki");
    }

    @Test
    public void DeletePet (){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(payload.createPetBody())
                .when().post("/pet")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        String petID = js.getString("id");
        System.out.println(petID);

        //----------------DELETE PET----------------

        String response1 = given().log().all()
                .when().delete("/pet/"+petID)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = new JsonPath(response1);
        String type = js1.getString("type");
        String message = js1.getString("message");

        Assert.assertEquals(type, "unknown");
        Assert.assertEquals(message, petID);

    }

    @Test
    public void CreateStoreOrderWithPayLoad (){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(payload.createStoreBody())
                .when().post("/store/order")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();


        JsonPath js = new JsonPath(response);

        String storeID = js.getString("id");


        //--------FIND STORE BY ID AND ASSERT DOES IT EXIST


        String response1 = given().log().all()
                .when().get("/store/order/" + storeID)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = new JsonPath(response1);

        String petID = js1.getString("petId");
        String quantity = js1.getString("quantity");
        String complete = js1.getString("complete");

        Assert.assertEquals(petID, "51");
        Assert.assertEquals(quantity, "3");
        Assert.assertEquals(complete, "true");
    }


    @Test
    public void CreateStoreOrderWithPojo (){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        store createStore = new store();
        createStore.setId(5);
        createStore.setPetId(53);
        createStore.setQuantity(4);
        createStore.setShipDate("2021-02-22");
        createStore.setStatus("unplaced");
        createStore.setComplete(false);


        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(createStore)
                .when().post("/store/order")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();


        JsonPath js = new JsonPath(response);

        String storeID = js.getString("id");


        //--------FIND STORE BY ID AND ASSERT DOES IT EXIST


        String response1 = given().log().all()
                .when().get("/store/order/" + storeID)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = new JsonPath(response1);

        String petID = js1.getString("petId");
        String quantity = js1.getString("quantity");
        String complete = js1.getString("complete");

        Assert.assertEquals(petID, "53");
        Assert.assertEquals(quantity, "4");
        Assert.assertEquals(complete, "false");
    }

    @Test
    public void DeleteStoreOrderWithPojo (){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        store createStore = new store();
        createStore.setId(5);
        createStore.setPetId(54);
        createStore.setQuantity(4);
        createStore.setShipDate("2021-02-22");
        createStore.setStatus("unplaced");
        createStore.setComplete(false);


        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(createStore)
                .when().post("/store/order")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();


        JsonPath js = new JsonPath(response);

        String storeID = js.getString("id");

        String response1 = given().log().all()
                .when().delete("/store/order/" + storeID)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = new JsonPath(response1);
        String type = js1.getString("type");
        String message = js1.getString("message");

        Assert.assertEquals(type, "unknown");
        Assert.assertEquals(message, storeID);


    }

}
