package Models;

import javafx.beans.property.*;

public class RevenueSummary {
    private final DoubleProperty totalRevenue;
    private final DoubleProperty totalInventoryCost;
    private final DoubleProperty netProfit;

    public RevenueSummary(double totalRevenue, double totalInventoryCost, double netProfit) {
        this.totalRevenue = new SimpleDoubleProperty(totalRevenue);
        this.totalInventoryCost = new SimpleDoubleProperty(totalInventoryCost);
        this.netProfit = new SimpleDoubleProperty(netProfit);
    }

    public double getTotalRevenue() { return totalRevenue.get(); }
    public DoubleProperty totalRevenueProperty() { return totalRevenue; }

    public double getTotalInventoryCost() { return totalInventoryCost.get(); }
    public DoubleProperty totalInventoryCostProperty() { return totalInventoryCost; }

    public double getNetProfit() { return netProfit.get(); }
    public DoubleProperty netProfitProperty() { return netProfit; }
}
