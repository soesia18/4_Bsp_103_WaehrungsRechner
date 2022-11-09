package at.htlkaindorf.bsp_103_waehrungsrechner;

import at.htlkaindorf.bsp_103_waehrungsrechner.Data.ConvertCurrency;
import at.htlkaindorf.bsp_103_waehrungsrechner.Data.Currency;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "WaehrungsRechner", value = "/WaehrungsRechner")
public class CurrencyCalculater extends HttpServlet {

    private List<Currency> currencies;

    @Override
    public void init() throws ServletException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("currency.csv");
        currencies = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().skip(1).map(Currency::new).collect(Collectors.toList());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("currencyCalculater.html").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("test/html");
        resp.setCharacterEncoding("UTF-8");
        String basis = req.getParameter("basisSelection");
        String target = req.getParameter("targetSelection");

        Currency basisCurrency = currencies.stream().filter(currency -> currency.getName().equalsIgnoreCase(basis)).collect(Collectors.toList()).get(0);
        Currency targetCurrency = currencies.stream().filter(currency -> currency.getName().equalsIgnoreCase(target)).collect(Collectors.toList()).get(0);

        System.out.println(basisCurrency);
        System.out.println(targetCurrency);

        double basisAmount = Double.parseDouble(req.getParameter("basisAmount"));
        double usdollar = basisAmount / basisCurrency.getCurrencyKey();
        double targetAmount = usdollar * targetCurrency.getCurrencyKey();

        ConvertCurrency convertCurrency = new ConvertCurrency(basisCurrency, targetCurrency, basisAmount, targetAmount);

        req.getSession().setAttribute("result", convertCurrency);

        req.getRequestDispatcher("currencyResult.html").forward(req, resp);
    }
}
