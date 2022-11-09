package at.htlkaindorf.bsp_103_waehrungsrechner;

import at.htlkaindorf.bsp_103_waehrungsrechner.Data.ConvertCurrency;
import at.htlkaindorf.bsp_103_waehrungsrechner.Data.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet (name = "Result", value = "/Result")
public class Result extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");


        /*Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "currency.csv");
        Files.lines(path);*/

        ConvertCurrency convertCurrency = (ConvertCurrency) req.getSession().getAttribute("result");
        try (PrintWriter out = resp.getWriter()){
            out.println((new ObjectMapper()).writeValueAsString(convertCurrency));
        }
    }
}
