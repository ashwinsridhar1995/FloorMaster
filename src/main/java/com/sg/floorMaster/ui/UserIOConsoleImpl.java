/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floorMaster.ui;

import java.util.Scanner;

/**
 *
 * @author ashwinsridhar
 */
public class UserIOConsoleImpl implements UserIO{
    Scanner reader = new Scanner(System.in);
    
    @Override
    public void print(String message) {
         System.out.println(message);
    }

   
    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double number = Double.parseDouble(reader.nextLine());
        return number;
    }

  
    @Override
    public double readDouble(String prompt, double min, double max) {
        Boolean inrange = false;
        double number = 0;
        while(inrange == false){
            System.out.println(prompt);
            number = Double.parseDouble(reader.nextLine());
            if(number >= min && number <= max){
                inrange = true;
            }
        }
        return number;
        
    }

  
    @Override
    public float readFloat(String prompt) {
       System.out.println(prompt);
        float number = Float.parseFloat(reader.nextLine());
        return number; 
    }

   
    @Override
    public float readFloat(String prompt, float min, float max) {
        Boolean inrange = false;
        float number = 0;
        while(inrange == false){
            System.out.println(prompt);
            number = Float.parseFloat(reader.nextLine());
            if(number >= min && number <= max){
                inrange = true;
            }
        }
        return number;
    }

    
    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int number = Integer.parseInt(reader.nextLine());
        return number;
    }

  
    @Override
    public int readInt(String prompt, int min, int max) {
        Boolean inrange = false;
        int number = 0;
        while(inrange == false){
            System.out.println(prompt);
            number = Integer.parseInt(reader.nextLine());
            if(number >= min && number <= max){
                inrange = true;
            }
        }
        return number;
    }

    
    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long number = Long.parseLong(reader.nextLine());
        return number;
    }

    
    @Override
    public long readLong(String prompt, long min, long max) {
        Boolean inrange = false;
        long number = 0;
        while(inrange == false){
            System.out.println(prompt);
            number = Long.parseLong(reader.nextLine());
            if(number >= min && number <= max){
                inrange = true;
            }
        }
        return number;
    }

   
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String characters = reader.nextLine();
        return characters;
    }
}
