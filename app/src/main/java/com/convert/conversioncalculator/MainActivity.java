package com.convert.conversioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.convert.conversioncalculator.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String userInput = "";
    String computerOutputText = "";
    ArrayList<Integer> binNum = new ArrayList<>(userInput.length());//set equal to user input
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



        setContentView(R.layout.activity_main);
        //convertStringToArrayList("101010");
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

    //TODO finish this class
    public void calculateConvertedValue(View view){
        if (startingNumChosen = true && convertingNumChosen == true){

            if (startingNumberSystem.equals("Decimal")){
                if (convertedNumberSystem.equals("Binary")){//TODO write conversion arrarylist to string method
                    int intValueOfUserInput = 0;
                    intValueOfUserInput = Integer.valueOf(userInput);
                    deciToWholeBin(intValueOfUserInput);
                    TextView showOutputDeciToBin = (TextView) findViewById(R.id.computerOutputText);
                    //convert arraylist to string
                    showOutputDeciToBin.setText(userInput);
                } else if (convertedNumberSystem.equals("Hexidecimal")){
                    //convert to binary then hex
                } else if (convertedNumberSystem.equals("Octal")){
                    //convert to binary then oct
                }
            } else if (startingNumberSystem.equals("Binary")){//Convert to oct hex or deci
                if (convertedNumberSystem.equals("Decimal")){//TODO FIX THIS: number is being converted correctly but array is coming in in reverse order of what the user sees, wrigt reverse array method
                    binNum = convertStringToArrayList(userInput);
                    String intValueOfUserInput = String.valueOf(binToDeci(binNum));
                    System.out.println("Insidelogic " + intValueOfUserInput);
                    TextView showOutputDeciToBin = (TextView) findViewById(R.id.computerOutputText);
                    showOutputDeciToBin.setText(intValueOfUserInput);
                } else if (convertedNumberSystem.equals("Hexidecimal")){
                    //call binary to hex method
                } else if (convertedNumberSystem.equals("Octal")){
                    //call bainry to oct method
                }
            } else if (startingNumberSystem.equals("Octal")){//Convert to Binary then hex or deci
                //Convert to binary
                if (convertedNumberSystem.equals("Hexidecimal")){
                    //call binary to hex method
                } else if (convertedNumberSystem.equals("Decimal")){
                    //call binary to decimal method
                }
            } else if (startingNumberSystem.equals("Hexidecimal")){//Convert to Binary then Octal or deci
                //Convert to binary
                if (convertedNumberSystem.equals("Octal")){
                    //call binary to octal method
                } else if (convertedNumberSystem.equals("Decimal")){
                    //call binary to decimal method
                }
            }
        }
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
        System.out.println("insidestringtoarraylist " + userInput);
        ArrayList array = new ArrayList(userInput.length());
        for (int i = 0; i < string.length(); i++){
            System.out.println(" Substring being added " + string.substring(string.length() - (i + 1), string.length() - i));
            array.add(string.substring(string.length() - (i + 1), string.length() - i));

        }
        System.out.println("arraysizeafterloopconvert " + array.size());
        binNum = array;
        return array;
    }

    //TODO: Finish method
    public String convertArrayListToString(ArrayList arrayList){
       String string = "";
       return string;
    }

    //TODO: Finish method
    public ArrayList reverseArrayList(ArrayList array){


        ArrayList reversedArray = new ArrayList();
        System.out.println("insidereverse " + array.size());
        for (int i = 0; i < array.size(); i++){
            System.out.println("Value being added " + (array.get(array.size() - (1 + i))));
            reversedArray.add(array.get(array.size() - (1 + i)));
            System.out.println("Value of i " + i + " Array size " + array.size());
        }
        System.out.println("insidereverse outisde of loop");
        return reversedArray;
    }

    public void buttonOneClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen){
            userInput += 1;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }

    }
    public void buttonTwoClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 2;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonThreeClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 3;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonFourClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 4;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonFiveClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 5;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonSixClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 6;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonSevenClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 7;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonEightClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 8;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonNineClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 9;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonZeroClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 0;
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonAClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "A";
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonBClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "B";
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonCClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "C";
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonDClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "D";
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonEClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "E";
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonFClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "F";
            TextView textViewToChange = (TextView) findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
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
            makeLettersGoneOrVisible("visible");
            makeNumbersGoneOrVisible("visible");
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
        makeNumbersGoneOrVisible("visible");
        startingNumberSystem = "";
        convertedNumberSystem = "";
        userInput = "";
        computerOutputText = "";
        startingNumChosen = false;
        convertingNumChosen = false;
        TextView beginNumSys = (TextView) findViewById(R.id.startingNumberSystemText);
        beginNumSys.setText(startingNumberSystem);
        TextView endNumSys = (TextView) findViewById(R.id.convertedNumberSystemText);
        endNumSys.setText(convertedNumberSystem);
        TextView userInputToChange = (TextView) findViewById(R.id.userInputText);
        userInputToChange.setText(userInput);
        TextView computerOutputToChange = (TextView) findViewById(R.id.computerOutputText);
        computerOutputToChange.setText(userInput);
    }

    public void buttonBinaryClicked (View view){
        if (!startingNumChosen){
            startingNumChosen = true;
            startingNumberSystem += "Binary";
            TextView textViewToChange = (TextView) findViewById(R.id.startingNumberSystemText);
            textViewToChange.setText(startingNumberSystem);
            makeLettersGoneOrVisible("gone");
            makeNumbersGoneOrVisible("gone");
        } else if (!convertingNumChosen && !startingNumberSystem.equals("Binary")){
            convertingNumChosen = true;
            convertedNumberSystem += "Binary";
            TextView textViewToChange = (TextView) findViewById(R.id.convertedNumberSystemText);
            textViewToChange.setText(convertedNumberSystem);
        }
    }

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

            View two = findViewById(R.id.twoButton);
            two.setVisibility(View.GONE);

            View three = findViewById(R.id.threeButton);
            three.setVisibility(View.GONE);

            View four = findViewById(R.id.fourButton);
            four.setVisibility(View.GONE);

            View five = findViewById(R.id.fiveButton);
            five.setVisibility(View.GONE);

            View six = findViewById(R.id.sixButton);
            six.setVisibility(View.GONE);

            View seven = findViewById(R.id.sevenButton);
            seven.setVisibility(View.GONE);

            View eight = findViewById(R.id.eightButton);
            eight.setVisibility(View.GONE);

            View nine = findViewById(R.id.nineButton);
            nine.setVisibility(View.GONE);

        } else if (goneOrVisible.equals("visible")){
            View two = findViewById(R.id.twoButton);
            two.setVisibility(View.VISIBLE);

            View three = findViewById(R.id.threeButton);
            three.setVisibility(View.VISIBLE);

            View four = findViewById(R.id.fourButton);
            four.setVisibility(View.VISIBLE);

            View five = findViewById(R.id.fiveButton);
            five.setVisibility(View.VISIBLE);

            View six = findViewById(R.id.sixButton);
            six.setVisibility(View.VISIBLE);

            View seven = findViewById(R.id.sevenButton);
            seven.setVisibility(View.VISIBLE);

            View eight = findViewById(R.id.eightButton);
            eight.setVisibility(View.VISIBLE);

            View nine = findViewById(R.id.nineButton);
            nine.setVisibility(View.VISIBLE);
        }
    }

    public void showHomeScreen (View view){
        userInput = "";
        startingNumChosen = false;
        convertingNumChosen = false;
        startingNumberSystem = "";
        convertedNumberSystem = "";
        setContentView(R.layout.activity_main);
    }

    public void showCalculatorScreen (View view){
        setContentView(R.layout.activity_calculator);
    }

    public void showPracticeProblemScreen (View view){
        setContentView(R.layout.activity_practice_problems);
    }

}
