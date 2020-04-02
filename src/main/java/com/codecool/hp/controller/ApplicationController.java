package com.codecool.hp.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/application"})
public class ApplicationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuilder = getStringBuilder(req);
        String email = getJsonValue(stringBuilder,"email");
        String name = getJsonValue(stringBuilder,"name");
        MailUtility.sendMail(email, "Hogwarts application", "Dear " +name+ " we got your application to Hogwarts.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(resp.encodeRedirectURL("http://localhost:3000/application"));
    }

    private StringBuilder getStringBuilder(HttpServletRequest req) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer;
    }

    private String getJsonValue(StringBuilder buffer,String jsonKey){
        String data = buffer.toString();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(data);
        String result = "";

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has(jsonKey)) {
                result = jsonObject.get(jsonKey).getAsString();
            }
        }

        return result;
    }
}
