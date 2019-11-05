package com.example.conversioncalculatordecimalbinaryhexidecimaloctal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> binNum = new ArrayList<>();//set equal to user input

    public static Map<String, String> hexToBinTable;
    public static Map<String, String> octToBinTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeHexAndOctalHashes();
        binNum.add(1);
        binNum.add(1);
        binNum.add(1);
        binNum.add(1);

        setContentView(R.layout.activity_main);
        binToDeci(binNum);
        deciToWholeBin(100);
    }

    //Initializes key for Hex and Oct values
    public void initializeHexAndOctalHashes (){

        hexToBinTable = new HashMap<>();
        hexToBinTable.put("0", "0000");
        hexToBinTable.put("1", "0001");
        hexToBinTable.put("2", "0010");
        hexToBinTable.put("3", "0011");
        hexToBinTable.put("4", "0100");
        hexToBinTable.put("5", "0101");
        hexToBinTable.put("6", "0110");
        hexToBinTable.put("7", "0111");
        hexToBinTable.put("8", "1000");
        hexToBinTable.put("9", "1001");
        hexToBinTable.put("A", "1010");
        hexToBinTable.put("B", "1011");
        hexToBinTable.put("C", "1100");
        hexToBinTable.put("D", "1101");
        hexToBinTable.put("E", "1110");
        hexToBinTable.put("F", "1111");

        octToBinTable = new HashMap<>();
        octToBinTable.put("0", "000");
        octToBinTable.put("1", "001");
        octToBinTable.put("2", "010");
        octToBinTable.put("3", "011");
        octToBinTable.put("4", "100");
        octToBinTable.put("5", "101");
        octToBinTable.put("6", "110");
        octToBinTable.put("7", "111");
        octToBinTable.put("8", "1000");

    }

    public void conversionLogicStreams(String startingNumSystem, String endingNumberSytem, String numGiven){

    }

    //returns int value of binary arraylist given
    //Binary numbers are read right to left starting with the MVB(most valueable bit)
    //Works for whole numbers
    public int binToDeci(ArrayList binNum){
        int deciNum = 0;
        for (int i = 0; i < binNum.size(); i++){

            if (binNum.get(i).toString().equals("1")){
                deciNum += Math.pow(2,i);
            }
        }
        System.out.println("binToDeci DECINUM VALUE = " + deciNum);
        return deciNum;
    }

    //returns binary equivalent of entered decimal value
    //Works for whole numbers
    public ArrayList deciToWholeBin(int deciNum){

        ArrayList binNum = new ArrayList();
        int curDeciValue = deciNum;
        for (int i = 0; i < 20; i++) {//intialize ArrayList
            binNum.add(0);
        }

        int curIndex = (int)(Math.log(deciNum) / Math.log(2));
        //System.out.println("underlog " + curIndex);
        while (curDeciValue != 0){

            //System.out.println("INWHILELOOP CurIndex " + curIndex + " curDecValue " + curDeciValue);

            if (curDeciValue - Math.pow(2, curIndex) >= 0) {

                curDeciValue -= Math.pow(2, curIndex);
                //System.out.println("inwhileaboveadd " + curIndex);
                binNum.remove(curIndex);
                binNum.add(curIndex,"1");
                //System.out.println("at index 3 " + binNum.get(3));
            }
            //System.out.println("HERE " + (curIndex == 0) + " " + (curIndex) );
            curIndex -= 1;
        }
        //System.out.println("SizeofBinNumArray " + binNum.size());
        for (int test = 0; test < binNum.size(); test++ ){
            System.out.println("Value of Binum starting at lsb = " + test + " " + binNum.get(test));
        }
        return binNum;
    }

    public void differentBaseToDeci(String numSystem, String numGiven){

    }

    //Todo finish class
    public void binToHexOrOct(String numSystem, ArrayList binNum){
        //convert binNum to String
        String testBinNum;
        numSystem = "hex";
        if (numSystem.equals("hex")){
            //hexToBinTable.
        }
    }

    //Todo finish class
    public ArrayList hexOrOctToBinary(String numSystem, ArrayList hexOrOctNum){
        String binaryValue = "";

        if (numSystem.equals("hex")){
            for (int i  = 0; i < hexOrOctNum.size(); i++){

                binaryValue += hexToBinTable.get(hexOrOctNum.get(i));
            }

        }
        //call method to convert String to ArrayList
        return hexOrOctNum;
    }




    public ArrayList convertStringToArrayList(String string){
        ArrayList array = new ArrayList();
        return array;
    }

    public String convertArrayListToString(ArrayList arrayList){
       String string = "";
       return string;
    }

    public void reverseArrayList(){

    }
}
