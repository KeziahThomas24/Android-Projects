package com.nseaf.mycalculator;

import android.text.Html;

public class Calculator {
    String numberString="0";
    String detailsString="";
    long longOp1 = 0;
    double doubleOp1 = 0;
    long intNumber;
    double realNumber;
    boolean isIntNumber=true;
    boolean numHasRadixPoint=false;
    long memoryInt=0;
    double memoryDouble=0.0;
    boolean isIntMemory=true;
    boolean addFlag=false;
    boolean subFlag=false;
    boolean mulFlag=false;
    boolean divFlag=false;
    int clearFlag = 0;
    boolean perFlag = false;
    double perValue;
    double places = 10.0;


    public Calculator() {
    }

    public void processNumber(int i) {
        if(numberString.length()<12) {  // limit of 12 digits
            if (numHasRadixPoint){
                realNumber = realNumber + i/places;
                places *= 10;
            }
            else{
                intNumber = intNumber * 10 + i;
                realNumber = realNumber * 10 + i;
            }
            if (numHasRadixPoint){
                numberString = String.valueOf(Math.round(realNumber*100.0)/100.0);
            }
            else{
                numberString = String.valueOf(intNumber);
            }

            detailsString += i;

        }
        else
            detailsString="The number is too long..";
    }

    public void processOperator(String op) {
        if(numberString.length()<12) {  // limit of 12 digits
            String[] components = detailsString.split("\\s+");
            if (isIntNumber) {
                if (components.length > 0) {
                    longOp1 = Integer.parseInt(components[0]);

                }
            } else {
                if (components.length > 0) {
                    doubleOp1 = Double.parseDouble(components[0]);
                }
            }

            if (op.equals("+")){
                addFlag = true;
            }
            else if (op.equals("-")){
                subFlag = true;
            }
            else if (op.equals("×")){
                mulFlag = true;
            }
            else if (op.equals("÷")){
                divFlag = true;
            }
            else if (op.equals("=")){
                performCal();
            }
            detailsString += " "+ op + " ";
            intNumber = 0;
            realNumber = 0.0;
            places = 10;
            numHasRadixPoint = false;


        }
        else
            detailsString="Max limit reached..";
    }

    public void performCal() {
        if (perFlag) {
            if (isIntNumber){
                numberString = String.valueOf(longOp1 + intNumber);
            }
            else {
                numberString = String.valueOf(doubleOp1 + realNumber);
            }
        }
        if (addFlag) {
            if (isIntNumber){
                numberString = String.valueOf(longOp1 + intNumber);
            }
            else {
                numberString = String.valueOf(doubleOp1 + realNumber);
            }
        }
        if (subFlag) {
            if (isIntNumber){
                numberString = String.valueOf(longOp1 - intNumber);
            }
            else {
                numberString = String.valueOf(doubleOp1 - realNumber);
            }
        }
        if (mulFlag) {
            if (isIntNumber){
                numberString = String.valueOf(longOp1 * intNumber);
            }
            else {
                numberString = String.valueOf(doubleOp1 * realNumber);
            }
        }
        if (divFlag) {
            if (isIntNumber){
                numberString = String.valueOf(longOp1 / intNumber);
            }
            else {
                numberString = String.valueOf(doubleOp1 / realNumber);
            }
        }
        longOp1 = 0;
        doubleOp1 = 0;
        addFlag = false;
        subFlag = false;
        mulFlag = false;
        divFlag = false;
        perFlag = false;
    }


    public void clearClicked() {
        clearFlag += 1;
        if (clearFlag == 1) {
            numberString="0";
            intNumber=0;
            realNumber=0.0;
            isIntNumber=true;
            numHasRadixPoint=false;
        }
        else if (clearFlag == 2) {
            detailsString="";
            clearFlag = 0;
        }

    }

    public void expClicked() {
        if (numberString.length()>0 && numberString.length()<12) {
            double value = Integer.parseInt(numberString);
            numberString = String.valueOf(Math.round(Math.pow(2.718281828,value)*100.0)/100.0);
            detailsString = String.valueOf(Html.fromHtml("e<sup><small>"+value+"</small></sup>"));
        }
    }

    public void piClicked() {
        numberString = String.valueOf(3.14);
        detailsString = "𝜋";
    }

    public void perClicked() {
        if (numberString.length()>0 && numberString.length()<12) {
            perValue = Integer.parseInt(numberString);
            numberString = String.valueOf(perValue/100);
            realNumber = perValue/100;
            detailsString += "%";
            perFlag = true;
            isIntNumber = false;
            numHasRadixPoint = true;
        }
    }

    public void radixClicked() {
        if (numberString.length()<12) {
            numberString += ".";
            detailsString += ".";
            isIntNumber = false;
            numHasRadixPoint = true;
        }
    }

    public void memPlusClicked() {
        if(isIntMemory){
            if(isIntNumber) {
                memoryInt += intNumber;
                detailsString = "Memory: "+memoryInt;
            }
            else {
                isIntNumber=false;
                memoryDouble = memoryInt + realNumber;
                detailsString = "Memory: "+memoryDouble;
            }
        }
    }

    public void memMinusClicked() {
        if(isIntMemory){
            if(isIntNumber) {
                memoryInt -= intNumber;
                detailsString = "Memory: "+memoryInt;
            }
            else {
                isIntNumber=false;
                memoryDouble = memoryInt - realNumber;
                detailsString = "Memory: "+memoryDouble;
            }
        }
    }

    public void memRecallClicked() {
        if(isIntMemory){
            if(isIntNumber) {
                detailsString = "Memory: "+memoryInt;
            }
            else {
                detailsString = "Memory: "+memoryDouble;
            }
        }
    }

    public void memClearClicked() {
        if(isIntMemory){
            if(isIntNumber) {
                memoryInt = 0;
                detailsString = "Memory: "+memoryInt;
            }
            else {
                memoryDouble = 0;
                detailsString = "Memory: "+memoryInt;
            }
        }
    }

}
