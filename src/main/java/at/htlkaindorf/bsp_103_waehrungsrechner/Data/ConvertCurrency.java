package at.htlkaindorf.bsp_103_waehrungsrechner.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConvertCurrency {
    private Currency basis;
    private Currency target;

    private double basisAmount;
    private double targetAmount;
}
