/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Currency;
import java.text.DecimalFormat;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author harry
 */

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless

public class CurrencyController {
    public static double TOTAL_AMOUNT = 0;
    @PersistenceContext(unitName = "Currency_Converter") //JPA unit name
    private EntityManager emManager;
    
    //the function which handle the data in and call the function which is int the model
    public String convert(String src, String desti, int amount){
        
        String convertResult = null;
        String currSymbol = null;
        DecimalFormat dFormat = new DecimalFormat("#0.00");
       
        Currency newCurr = emManager.find(Currency.class , src); //select the src row
        if(newCurr == null){
            System.out.println("Something wrong..");
        }else
            {
            convertResult =  newCurr.convert(desti,amount);
            if (!convertResult.equals("0.0")){
            //the judgement that verify that the input this valid or not
                double tempAmount = Double.parseDouble(convertResult);
                convertResult = dFormat.format(tempAmount);
                //swith the symbol of the currency
                switch (desti) {

                    case "EUR":  currSymbol = "€";
                             break;

                    case "eur":  currSymbol = "€";
                             break;

                    case "GBP":  currSymbol = "￡";
                             break;

                    case "gbp":  currSymbol = "￡";
                             break;         

                    case "SEK":  currSymbol = "kr";
                             break;

                    case "sek":  currSymbol = "kr";
                             break;         

                    case "USD":  currSymbol = "$";
                             break;

                    case "usd":  currSymbol = "$";
                             break; 

                    case "RMB":  currSymbol = "¥";
                             break;

                    case "rmb":  currSymbol = "¥";
                             break; 
                }

                convertResult = currSymbol +" "+ convertResult;

                }
            else
            {
                convertResult = "Your input is wrong! Please check!";
            }
        
        }

        return convertResult;
    }
    // Similar function: which handle the total amount of the currency. All the user created
    public String toatalConvert(String src, int amount){
        
        String totalConvertResult = null;
        DecimalFormat dFormat = new DecimalFormat("#0.00");
        double tempAmount = 0.0;
  
        Currency newCurr = emManager.find(Currency.class , src);
         if(newCurr != null){
         
            totalConvertResult =  newCurr.convert("USD",amount);
            tempAmount = Double.parseDouble(totalConvertResult);
         }
            TOTAL_AMOUNT = TOTAL_AMOUNT + tempAmount;
            totalConvertResult = dFormat.format(TOTAL_AMOUNT);
            totalConvertResult = "$ " + totalConvertResult;
           
        return totalConvertResult;

    }
    
}
