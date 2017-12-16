/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.view;

import com.controller.CurrencyController;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author harry
 */

//view which communicate with the surface and give the information to the controller
@Named(value = "currencyManager")
@ConversationScoped
public class CurrencyManager implements Serializable {


    private static final long serialVersionUID = 16247164405L;
    @EJB
    private CurrencyController CurrencyCon;
    private String srcCurrency;
    private String toCurrency;
    private int Amount;
    private String conversionResult = null;
    private String totalConversionResult = null;
    @Inject
    private Conversation conversation;
    
    
    
     private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    //current convertion result
     public String getconversionResult(){
         return conversionResult;
     }
     //totall amount result
     public String gettotalConversionResult(){
         return totalConversionResult;
     }
    // the whole process once the user start the button
    public void convertCurr(){
      
        try {
            startConversation();
            conversionResult = CurrencyCon.convert(srcCurrency,toCurrency,Amount);
            totalConversionResult = CurrencyCon.toatalConvert(srcCurrency,Amount);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
 
    // the destinatiation currency name
      public void settoCurrency(String input){
        this.toCurrency = input;
    }
    public String gettoCurrency(){
        return toCurrency;
    }
    
    //Currency amount
     public void setAmount(String input){
        this.Amount = Integer.parseInt(input);
    }
    public String getAmount(){
        return String.valueOf(Amount);
    }
    
    
  //source Currency name
    public void setsrcCurrency(String input){
        this.srcCurrency = input;
    }
    public String getsrcCurrency(){
        return srcCurrency;
    }
    
  
    
    
}
