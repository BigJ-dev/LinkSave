package com.merga.linkSave.models;

import com.merga.linkSave.services.UserActionService;
import javafx.util.Pair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Test {

    public static void main(String[] args) {
        Map<Double,String> itemInStock = new HashMap<>();
        Map<Double,String> sendEmail = new HashMap<>();

        itemInStock.put(1.00,"book");
        itemInStock.put(2.00,"laptops");
        itemInStock.put(9.00,"book");
        itemInStock.put(7.00,"laptops");
        itemInStock.put(2000.00,"fan");
        itemInStock.put(1000.00,"fan");


        itemInStock.forEach((k,v)->{
               if(k < 10){
                   sendEmail.put(k,v);
               }});
        System.out.println("build list of items");
        sendEmail.forEach((k,v) ->{

            System.out.println("The send list has item:" + k + ":"+v);
        });

    }
}
