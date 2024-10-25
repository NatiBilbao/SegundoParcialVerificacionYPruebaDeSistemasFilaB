package api.apiTest;

import api.factoryRequest.FactoryRequest;
import api.factoryRequest.RequestInfo;
import api.utils.JsonUtil;
import api.utils.Properties;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;

public class createUserToken {
    RequestInfo requestInfo = new RequestInfo();

    @Test
    public void verifyCreateUserToken() throws FileNotFoundException {
        requestInfo.setHeader("Content-Type", "application/json");

        JSONObject createUserBody = new JSONObject();
        String email = "bilbaocanonatalia45" + new Date().getTime() + "@gmail.com";
        String password = "Parcial123.";
        String fullName = "Natalia Bilbao Cano";

        createUserBody.put("Email", email);
        createUserBody.put("Password", password);
        createUserBody.put("FullName", fullName);

        requestInfo.setHost(Properties.apiHost + "/user.json").setBody(createUserBody.toString());

        Response response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Email", equalTo(email))
                .body("FullName", equalTo(fullName));

        String auth = Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
        requestInfo.setHost(Properties.apiHost + "/authentication/token.json").setHeader("Authorization", "Basic " + auth);
        response = FactoryRequest.make("get").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("UserEmail", equalTo(email));

        String token = response.then().extract().path("TokenString");

        requestInfo.setHeader("Authorization", null);

        JSONObject createProjectBody = JsonUtil.getJSONFromFile("src/test/resources/JSONS/CreateProject.json");

        requestInfo.setHost(Properties.apiHost + "/projects.json").setBody(createProjectBody.toString()).setHeader("Token", token);
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(createProjectBody.get("Content")))
                .body("Icon", equalTo(createProjectBody.get("Icon")));

        requestInfo.setHost(Properties.apiHost + "/authentication/token.json").setHeader("Token", token);
        response = FactoryRequest.make("delete").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200);

        requestInfo.setHost(Properties.apiHost + "/projects.json").setBody(createProjectBody.toString()).setHeader("Token", token);
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("ErrorMessage", equalTo("Not Authenticated"))
                .body("ErrorCode", equalTo(102));

    }
}
