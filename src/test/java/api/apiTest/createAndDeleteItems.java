package api.apiTest;

import api.factoryRequest.FactoryRequest;
import api.factoryRequest.RequestInfo;
import api.utils.Properties;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;

public class createAndDeleteItems {
    RequestInfo requestInfo = new RequestInfo();

    String token;

    @BeforeEach
    public void setup() {

        String auth = Base64.getEncoder().encodeToString((Properties.pageUser + ":" + Properties.pagePassword).getBytes());
        RequestInfo requestInfoAuth = new RequestInfo().setHost(Properties.apiHost + "/authentication/token.json").setHeader("Authorization", "Basic " + auth);
        token = FactoryRequest.make("get").send(requestInfoAuth).then().extract().path("TokenString");
    }

    @Test
    public void verifyCreateAndDeleteItems() throws FileNotFoundException {
        requestInfo.setHeader("Content-Type", "application/json").setHeader("Token", token);

        for (int i = 0; i < 4; i++) {
            JSONObject createItemBody = new JSONObject();
            createItemBody.put("Content", "Item " + new Date().getTime());
            createItemBody.put("Priority", 3);
            requestInfo.setHost(Properties.apiHost + "/items.json").setBody(createItemBody.toString());
            Response response = FactoryRequest.make("post").send(requestInfo);
            response.then()
                    .log().all()
                    .statusCode(200)
                    .body("Content", equalTo(createItemBody.get("Content")))
                    .body("Priority", equalTo(createItemBody.get("Priority")));
        }

        requestInfo.setHost(Properties.apiHost + "/items.json");
        Response response = FactoryRequest.make("get").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200);

        JSONArray items = new JSONArray(response.then().extract().response().asString());

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = new JSONObject(items.get(i).toString());
            requestInfo.setHost(Properties.apiHost + "/items/" + item.get("Id") + ".json");
            response = FactoryRequest.make("delete").send(requestInfo);
            response.then()
                    .log().all()
                    .statusCode(200)
                    .body("Deleted", equalTo(true));
        }

    }
}
