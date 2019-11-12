package com.convert.conversioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.convert.conversioncalculator.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String userInput = "";
    ArrayList<Integer> binNum = new ArrayList<>();//set equal to user input
    ArrayList<Integer> hexOrOctNum = new ArrayList<>();//set equal to user input
    String numSystem = "hex";

    String startingNumberSystem = "";
    Boolean startingNumChosen = false;
    String convertedNumberSystem = "";
    Boolean convertingNumChosen = false;

    public static Map<String, String> hexToBinTable;
    public static Map<String, String> hexToDeciTable;
    public static Map<String, String> octToBinTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeHexAndOctalHashes();
        binNum.add(1);
        binNum.add(1);
        binNum.add(1);
        binNum.add(1);

        hexOrOctNum.add(1);
        hexOrOctNum.add(1);
        hexOrOctNum.add(1);

        setContentView(R.layout.activity_main);
        convertStringToArrayList("101010");
        //differentBaseToDeci(numSystem, hexOrOctNum);
//        binToDeci(binNum);
//        deciToWholeBin(100);
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

        hexToDeciTable = new HashMap<>();
        hexToDeciTable.put("A", "10");
        hexToDeciTable.put("B", "11");
        hexToDeciTable.put("C", "12");
        hexToDeciTable.put("D", "13");
        hexToDeciTable.put("E", "14");
        hexToDeciTable.put("F", "15");

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

    //converts from decimal to different base
    public int differentBaseToDeci(String numSystem, ArrayList numGiven){
        int deciNum = 0;
        System.out.print("decinum " + deciNum);
        if ( numSystem.equals("oct")){

            for (int p = 0; p < numGiven.size();p++){

                deciNum += Math.pow(8,p) * (int)numGiven.get(p);
            }
        } else if (numSystem.equals("hex")){//numSystem.equals("hex")
            for (int i = 0; i < numGiven.size(); i++){
                System.out.print("decinum in loop " + deciNum);
                if ("1234567890".contains(String.valueOf(numGiven.get(i)))){//adds int values from hex to decivalue
                    deciNum += Math.pow(16,i) * (int)numGiven.get(i);
                } else {//adds letter values from hex to decivalue
                    deciNum += Math.pow(16,i) * Integer.valueOf(hexToBinTable.get(String.valueOf(numGiven.get(i))));
                }
            }
        }
        System.out.print("decinum " + deciNum);

//        TextView textViewToChange = (TextView) findViewById(R.id.hello);
//        textViewToChange.setText(deciNum);
        return deciNum;
    }

    //TODO finish class
    public void binToHexOrOct(String numSystem, ArrayList binNum){
        //convert binNum to String
        String testBinNum;
        numSystem = "hex";
        if (numSystem.equals("hex")){
            //add 0 to end of array until ArrayList.size % 4 == 0
            //look up binary value to hex
        }
    }

    //TODO finish class
    public ArrayList hexOrOctToBinary(String numSystem, ArrayList hexOrOctNum){
        String binaryValue = "";
        ArrayList<Integer> binaryNum = new ArrayList();
        if (numSystem.equals("hex")){
            for (int i  = 0; i < hexOrOctNum.size(); i++){
                binaryValue += hexToBinTable.get(hexOrOctNum.get(i));
            }

        }
        //call method to convert String to ArrayList
        return hexOrOctNum;
    }

    //TODO: Finish method
    public ArrayList convertStringToArrayList(String string){
        ArrayList array = new ArrayList();
        for (int i = string.length(); i < 3; i--){
            array.add(string.substring(i - 2, i - 1));
            System.out.print(string.substring(i - 2, i - 1));
        }

        return array;
    }

    //TODO: Finish method
    public String convertArrayListToString(ArrayList arrayList){
       String string = "";
       return string;
    }

    //TODO: Finish method
    public void reverseArrayList(){

    }

    public void buttonOneClicked (View view){
        userInput += 1;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonTwoClicked (View view){
        userInput += 2;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonThreeClicked (View view){
        userInput += 3;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonFourClicked (View view){
        userInput += 4;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonFiveClicked (View view){
        userInput += 5;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonSixClicked (View view){
        userInput += 6;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonSevenClicked (View view){
        userInput += 7;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonEightClicked (View view){
        userInput += 8;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonNineClicked (View view){
        userInput += 9;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonZeroClicked (View view){
        userInput += 0;
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonAClicked (View view){
        userInput += "A";
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonBClicked (View view){
        userInput += "B";
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonCClicked (View view){
        userInput += "C";
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonDClicked (View view){
        userInput += "D";
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonEClicked (View view){
        userInput += "E";
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }
    public void buttonFClicked (View view){
        userInput += "F";
        TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
        textViewToChange.setText(userInput);
    }

    public void delRecentChar (View view){
        if (userInput.equals("") && !convertedNumberSystem.equals("")){
            convertedNumberSystem = "";
            convertingNumChosen = false;
            TextView endNumSys = (TextView) findViewById(R.id.convertedNumberSystemText);
            endNumSys.setText(convertedNumberSystem);
        } else if (userInput.equals("") && !startingNumberSystem.equals("")){
            startingNumberSystem = "";
            startingNumChosen = false;
            TextView beginNumSys = (TextView) findViewById(R.id.startingNumberSystemText);
            beginNumSys.setText(startingNumberSystem);
        } else if (!userInput.equals("")){
            userInput = userInput.substring(0,userInput.length() - 1);
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }

    public void clearUserInput (View view){
        makeLettersGoneOrVisible("visible");
        startingNumberSystem = "";
        convertedNumberSystem = "";
        userInput = "";
        startingNumChosen = false;
        convertingNumChosen = false;
        TextView beginNumSys = (TextView) findViewById(R.id.startingNumberSystemText);
        beginNumSys.setText(startingNumberSystem);
        TextView endNumSys = (TextView) findViewById(R.id.convertedNumberSystemText);
        endNumSys.setText(convertedNumberSystem);
        TextView userInputToChange = (TextView) findViewById(R.id.userInputText);
        userInputToChange.setText(userInput);
    }

    //TODO: Finish method
    //if selected as starting number system
    //make letters, and numbers that are not 1 || 0 disappear from buttons and text
    public void buttonBinaryClicked (View view){
        if (!startingNumChosen){
            startingNumChosen = true;
            startingNumberSystem += "Binary";
            TextView textViewToChange = (TextView) findViewById(R.id.startingNumberSystemText);
            textViewToChange.setText(startingNumberSystem);
            makeLettersGoneOrVisible("gone");
        } else if (!convertingNumChosen && !startingNumberSystem.equals("Binary")){
            convertingNumChosen = true;
            convertedNumberSystem += "Binary";
            TextView textViewToChange = (TextView) findViewById(R.id.convertedNumberSystemText);
            textViewToChange.setText(convertedNumberSystem);
        }
    }

    //TODO: Finish method
    //if selected as starting number system
    //make letters disappear from buttons and text
    public void buttonDecimalClicked (View view){
        if (!startingNumChosen){
            startingNumChosen = true;
            startingNumberSystem += "Decimal";
            TextView textViewToChange = (TextView) findViewById(R.id.startingNumberSystemText);
            textViewToChange.setText(startingNumberSystem);
            makeLettersGoneOrVisible("gone");
        } else if (!convertingNumChosen && !startingNumberSystem.equals("Decimal")){
            convertingNumChosen = true;
            convertedNumberSystem += "Decimal";
            TextView textViewToChange = (TextView) findViewById(R.id.convertedNumberSystemText);
            textViewToChange.setText(convertedNumberSystem);
        }
    }

    //TODO: Finish method
    public void buttonHexidecimalClicked (View view){
        if (!startingNumChosen){
            startingNumChosen = true;
            startingNumberSystem += "Hexidecimal";
            TextView textViewToChange = (TextView) findViewById(R.id.startingNumberSystemText);
            textViewToChange.setText(startingNumberSystem);
        } else if (!convertingNumChosen && !startingNumberSystem.equals("Hexidecimal")){
            convertingNumChosen = true;
            convertedNumberSystem += "Hexidecimal";
            TextView textViewToChange = (TextView) findViewById(R.id.convertedNumberSystemText);
            textViewToChange.setText(convertedNumberSystem);
        }
    }

    //TODO: Finish method
    //if selected as starting number system
    //make letters disappear from buttons and text
    public void buttonOctalClicked (View view){
        if (!startingNumChosen){
            startingNumChosen = true;
            startingNumberSystem += "Octal";
            TextView textViewToChange = (TextView) findViewById(R.id.startingNumberSystemText);
            textViewToChange.setText(startingNumberSystem);
            makeLettersGoneOrVisible("gone");
        } else if (!convertingNumChosen && !startingNumberSystem.equals("Octal")){
            convertingNumChosen = true;
            convertedNumberSystem += "Octal";
            TextView textViewToChange = (TextView) findViewById(R.id.convertedNumberSystemText);
            textViewToChange.setText(convertedNumberSystem);
        }
    }

    public void makeLettersGoneOrVisible (String goneOrVisible){
        if (goneOrVisible.equals("gone")){
            View A = findViewById(R.id.AButton);
            A.setVisibility(View.GONE);
            View B = findViewById(R.id.BButton);
            B.setVisibility(View.GONE);
            View C = findViewById(R.id.CButton);
            C.setVisibility(View.GONE);
            View D = findViewById(R.id.DButton);
            D.setVisibility(View.GONE);
            View E = findViewById(R.id.EButton);
            E.setVisibility(View.GONE);
            View F = findViewById(R.id.FButton);
            F.setVisibility(View.GONE);
        } else if (goneOrVisible.equals("visible")){
            View A = findViewById(R.id.AButton);
            A.setVisibility(View.VISIBLE);
            View B = findViewById(R.id.BButton);
            B.setVisibility(View.VISIBLE);
            View C = findViewById(R.id.CButton);
            C.setVisibility(View.VISIBLE);
            View D = findViewById(R.id.DButton);
            D.setVisibility(View.VISIBLE);
            View E = findViewById(R.id.EButton);
            E.setVisibility(View.VISIBLE);
            View F = findViewById(R.id.FButton);
            F.setVisibility(View.VISIBLE);
        }
    }

    public void makeNumbersGoneOrVisible (String goneOrVisible){
        if (goneOrVisible.equals("gone")){
            View A = findViewById(R.id.AButton);
            A.setVisibility(View.GONE);
            View B = findViewById(R.id.BButton);
            B.setVisibility(View.GONE);
            View C = findViewById(R.id.CButton);
            C.setVisibility(View.GONE);
            View D = findViewById(R.id.DButton);
            D.setVisibility(View.GONE);
            View E = findViewById(R.id.EButton);
            E.setVisibility(View.GONE);
            View F = findViewById(R.id.FButton);
            F.setVisibility(View.GONE);
        } else if (goneOrVisible.equals("visible")){
            View A = findViewById(R.id.AButton);
            A.setVisibility(View.VISIBLE);
            View B = findViewById(R.id.BButton);
            B.setVisibility(View.VISIBLE);
            View C = findViewById(R.id.CButton);
            C.setVisibility(View.VISIBLE);
            View D = findViewById(R.id.DButton);
            D.setVisibility(View.VISIBLE);
            View E = findViewById(R.id.EButton);
            E.setVisibility(View.VISIBLE);
            View F = findViewById(R.id.FButton);
            F.setVisibility(View.VISIBLE);
        }
    }

    public void showHomeScreen (View view){
        userInput = "";
        setContentView(R.layout.activity_main);
    }

    public void showCalculatorScreen (View view){
        setContentView(R.layout.activity_calculator);
    }

    public void showPracticeProblemScreen (View view){
        setContentView(R.layout.activity_practice_problems);
    }


}
