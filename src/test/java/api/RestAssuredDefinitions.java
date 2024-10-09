package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredDefinitions {
    public static String RestPost(String URLSoap,String xmlRequest) {
        String contentType = "text/xml; charset=UTF-8";
        String accept = "text/xml";
        String cookie = "AT_P=1881259786.20480.0000";

        Response response = RestAssured.given()
                .header("Content-Type", contentType)
                .header("Accept", accept)
                .header("Cookie", cookie)
                .body(xmlRequest)
                .redirects().follow(true)
                .post(URLSoap);
        if (response.statusCode() == 200) {
            System.out.println("SOAP request sent successfully!");
            System.out.println("Response: " + response.getBody().asString());
            return response.getBody().asString();
        } else {
            System.out.println("Error sending SOAP request: " + response.statusCode());
             System.out.println("Response body: " + response.getBody().asString());
            return null;
        }
    }
}
