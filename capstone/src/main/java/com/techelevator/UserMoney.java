package com.techelevator;
import java.math.BigDecimal;

public class UserMoney {

    private BigDecimal userBalance = new BigDecimal("0.00");

    public BigDecimal getUserBalance(){
        return userBalance;
    }

    public BigDecimal addOne(){
        BigDecimal funds = new BigDecimal("1.00");
        return this.userBalance = userBalance.add(funds);
    }

    public BigDecimal addTwo(){
        BigDecimal funds = new BigDecimal("2.00");
        return this.userBalance = userBalance.add(funds);
    }

    public BigDecimal addFive(){
        BigDecimal funds = new BigDecimal("5.00");
        return this.userBalance = userBalance.add(funds);
    }

    public BigDecimal addTen(){
        BigDecimal funds = new BigDecimal("10.00");
        return this.userBalance = userBalance.add(funds);

    }

    public BigDecimal subtract(String cost) {
        BigDecimal funds = new BigDecimal(cost);
        return this.userBalance = userBalance.subtract(funds);
    }


}
