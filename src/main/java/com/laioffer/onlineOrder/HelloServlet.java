package com.laioffer.onlineOrder;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//import jdk.jpackage.internal.IOUtils;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import com.laioffer.onlineOrder.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html");
//
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");

//        response.setContentType("application/json");
//        JSONObject customer = new JSONObject();  // 创建object
//        customer.put("email", "laioffer@gmail.com");
//        customer.put("first_name", "Irina");
//        customer.put("age", 30);
//        response.getWriter().print(customer);
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();  // objectMapper是什么？
        Customer customer = new Customer();
        customer.setEmail("irina@gmail.com");
        customer.setFirstName("Irina");
        customer.setPassword("123456");

        response.getWriter().print(mapper.writeValueAsString(customer));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = mapper.readValue(IOUtils.toString(req.getReader()), Customer.class);
        System.out.println(customer.getEmail());
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());
        System.out.println(customer.getEmail());
        System.out.println(customer.getPassword());
        System.out.println(customer.isEnabled());


          JSONObject jsonRequest = new JSONObject(IOUtils.toString(req.getReader()));
//        String email = jsonRequest.getString("email");
//        int age = jsonRequest.getInt("age");
//        System.out.println("Email is: " + email);
//        System.out.println("Age is: " + age);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        resp.getWriter().print(jsonRequest);
    }

    public void destroy() {
    }
}