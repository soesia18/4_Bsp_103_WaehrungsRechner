package at.htlkaindorf.bsp_103_waehrungsrechner.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Currency {
    private String name;
    private String shortName;
    private double currencyKey;

    public Currency (String line){
        name = line.split(";")[0];
        shortName = line.split(";")[1];
        currencyKey = Double.parseDouble(line.split(";")[2]);
    }
}
