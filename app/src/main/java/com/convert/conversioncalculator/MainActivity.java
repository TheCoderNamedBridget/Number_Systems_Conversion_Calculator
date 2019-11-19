package com.convert.conversioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//TODO: Change theme of all buttons to make UX better
//TODO: Write methods to show steps of the conversions
//TODO: For show steps screen provide explainations for all conversions: binToHexOrOct, hexOrOctToBinary

//TODO: PRACTICEPROBLEMSCREEN: make UI and UX intuitive/usable


public class MainActivity extends AppCompatActivity {

    String userInput = "";
    String computerOutputText = "";
    ArrayList<Integer> binNum = new ArrayList<>(userInput.length());//set equal to user input
    ArrayList<Integer> hexNum = new ArrayList<>();//set equal to user input
    ArrayList<Integer> octNum = new ArrayList<>();

    String startingNumberSystem = "";
    Boolean startingNumChosen = false;
    String convertedNumberSystem = "";
    Boolean convertingNumChosen = false;

    String correctAnswer;
    String curScreen = "";
    String stepsOfConversion;

    public static Map<String, String> hexToBinTable;
    public static Map<String, String> hexToDeciTable;
    public static Map<String, String> octToBinTable;
    public static Map<String, String> binToOctTable;
    public static Map<String, String> binToHexTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeHexAndOctalHashes();
        setContentView(R.layout.activity_main);
    }

    //Initializes key for Hex and Oct values
    public void initializeHexAndOctalHashes (){
        binToOctTable = new HashMap<>();
        binToOctTable.put("000", "0");
        binToOctTable.put("001", "1");
        binToOctTable.put("010", "2");
        binToOctTable.put("011", "3");
        binToOctTable.put("100", "4");
        binToOctTable.put("101", "5");
        binToOctTable.put("110", "6");
        binToOctTable.put("111", "7");
        binToOctTable.put("1000", "8");
        binToOctTable.put("1001", "9");

        binToHexTable = new HashMap<>();
        binToHexTable.put("0000", "0");
        binToHexTable.put("0001", "1");
        binToHexTable.put("0010", "2");
        binToHexTable.put("0011", "3");
        binToHexTable.put("0100", "4");
        binToHexTable.put("0101", "5");
        binToHexTable.put("0110", "6");
        binToHexTable.put("0111", "7");
        binToHexTable.put("1000", "8");
        binToHexTable.put("1001", "9");
        binToHexTable.put("1010", "A");
        binToHexTable.put("1011", "B");
        binToHexTable.put("1100", "C");
        binToHexTable.put("1101", "D");
        binToHexTable.put("1110", "E");
        binToHexTable.put("1111", "F");

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
        octToBinTable.put("9", "1001");

    }

    public void callCalculateFromActivity(View view){
        calculateConvertedValue("Activity");
        curScreen = "calculator";
        Button showShowStepsButton = findViewById(R.id.showSteps);
        showShowStepsButton.setVisibility(View.VISIBLE);
    }
    //Runs logic of entire calculator
    //Conversion methods are called here
    //Display to activities are called here
    public String calculateConvertedValue(String whereIsTheCallFrom){
        if (whereIsTheCallFrom.equals("Practice Problem")){
            curScreen = "practice";
        }
        if (startingNumChosen == true && convertingNumChosen == true){

            if (startingNumberSystem.equals("Decimal")){
                //convert to binary, octal, hex works
                ArrayList binaryValueOfUser = deciToWholeBin(Integer.valueOf(userInput));
                if (convertedNumberSystem.equals("Binary")){
                    String stringBinaryValueOfUser = convertArrayListToString(deciToWholeBin(Integer.valueOf(userInput)));
                    if (whereIsTheCallFrom.equals("Activity")){
                        TextView showOutputDeciToBin = findViewById(R.id.computerOutputText);
                        showOutputDeciToBin.setText(stringBinaryValueOfUser);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return stringBinaryValueOfUser;
                    }

                } else if (convertedNumberSystem.equals("Hexidecimal")){
                    //convert to binary then hex
                    String deciToHex = binToHexOrOct("hex", binaryValueOfUser);
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputDeciToHex = findViewById(R.id.computerOutputText);
                        showOutputDeciToHex.setText(deciToHex);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return deciToHex;
                    }
                } else if (convertedNumberSystem.equals("Octal")){
                    String deciToOct = binToHexOrOct("oct", binaryValueOfUser);
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputDeciToOct = findViewById(R.id.computerOutputText);
                        showOutputDeciToOct.setText(deciToOct);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return deciToOct;
                    }
                }
            } else if (startingNumberSystem.equals("Binary")){//Convert to oct hex or deci
                //convert to decimal,oct,hex works
                if (convertedNumberSystem.equals("Decimal")){
                    binNum = convertStringToArrayList(userInput);
                    String intValueOfUserInput = String.valueOf(binToDeci(binNum));
                    System.out.println("Insidelogic " + intValueOfUserInput);
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputBinToDeci = findViewById(R.id.computerOutputText);
                        showOutputBinToDeci.setText(intValueOfUserInput);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return intValueOfUserInput;
                    }
                } else if (convertedNumberSystem.equals("Hexidecimal")){
                    hexNum = convertStringToArrayList(userInput);
                    String hexValue = binToHexOrOct("hex",hexNum);
                    System.out.println("octalValue " + hexValue);
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputBinToHex = findViewById(R.id.computerOutputText);
                        showOutputBinToHex.setText(hexValue);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return hexValue;
                    }
                } else if (convertedNumberSystem.equals("Octal")){
                    octNum = convertStringToArrayList(userInput);
                    String octalValue = binToHexOrOct("oct",octNum);
                    System.out.println("octalValue " + octalValue);
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputBinToOct = findViewById(R.id.computerOutputText);
                        showOutputBinToOct.setText(octalValue);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return octalValue;
                    }
                }
            } else if (startingNumberSystem.equals("Octal")){//Convert to Binary then hex or deci
                //Octal to Binary, Decimal, hex works
                octNum = convertStringToArrayList(userInput);
                String binaryValue = convertArrayListToString(hexOrOctToBinary("oct", octNum));
                System.out.println("binaryValueinoctal " + binaryValue);
                if (convertedNumberSystem.equals("Hexidecimal")){
                    String hexValue = binToHexOrOct("hex",convertStringToArrayList(binaryValue));
                    System.out.println("hexValueinoctal " + hexValue);
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputOctToHex = findViewById(R.id.computerOutputText);
                        showOutputOctToHex.setText(hexValue);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return hexValue;
                    }
                } else if (convertedNumberSystem.equals("Decimal")){
                    octNum = convertStringToArrayList(userInput);
                    String intValueOfUserInput = String.valueOf(differentBaseToDeci("oct", octNum));
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputOctToDeci = findViewById(R.id.computerOutputText);
                        showOutputOctToDeci.setText(intValueOfUserInput);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return intValueOfUserInput;
                    }
                } else if (convertedNumberSystem.equals("Binary")){//works now
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputOctToBin = findViewById(R.id.computerOutputText);
                        showOutputOctToBin.setText(binaryValue);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return binaryValue;
                    }
                }
            } else if (startingNumberSystem.equals("Hexidecimal")){//Convert to Binary then Octal or deci
                //Convert to decimal/binary/hex works
                //Convert to binary
                hexNum = convertStringToArrayList(userInput);
                String binaryValue = convertArrayListToString(hexOrOctToBinary("hex", hexNum));
                if (convertedNumberSystem.equals("Octal")){
                    String octValue = binToHexOrOct("oct",convertStringToArrayList(binaryValue));
                    System.out.println("octValueinhex " + octValue);
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputHexToOct = findViewById(R.id.computerOutputText);
                        showOutputHexToOct.setText(octValue);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return octValue;
                    }
                } else if (convertedNumberSystem.equals("Decimal")){
                    hexNum = convertStringToArrayList(userInput);
                    String intValueOfUserInput = String.valueOf(differentBaseToDeci("hex", hexNum));
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputHexToDeci = findViewById(R.id.computerOutputText);
                        showOutputHexToDeci.setText(intValueOfUserInput);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return intValueOfUserInput;
                    }
                } else if (convertedNumberSystem.equals("Binary")){//works now
                    if (whereIsTheCallFrom.equals("Activity")) {
                        TextView showOutputHexToBin = findViewById(R.id.computerOutputText);
                        showOutputHexToBin.setText(binaryValue);
                    } else if (whereIsTheCallFrom.equals("Practice Problem")){
                        return binaryValue;
                    }
                }
            }
        }
        return "AN ERROR HAS OCCURRED";
    }

    //returns int value of binary arraylist given
    //Binary numbers are read right to left starting with the MVB(most valueable bit)
    //Works for whole numbers

    //“\r\n” new line
    public int binToDeci(ArrayList binNum){
        int deciNum = 0;
        correctAnswer = "Start with a decimal value of 0. Start at the least valuable, right most bit. ";
        for (int i = 0; i < binNum.size(); i++){

            if (binNum.get(i).toString().equals("1")){
                deciNum += Math.pow(2,i);
                correctAnswer = correctAnswer + "\r\n Because the value is a 1 we add 2 raised to the " + i + " power(" + Math.pow(2,i) + ") to the decimal value";

            } else {
                correctAnswer = correctAnswer + "\r\n Because the value is a 0, the decimal value is unchanged";
            }
        }
        correctAnswer = correctAnswer + "\r\n So the correct answer is " + deciNum;
        System.out.println("binToDeci DECINUM VALUE = " + deciNum);
        return deciNum;
    }

    //returns binary equivalent of entered decimal value
    //Works for whole numbers
    public ArrayList deciToWholeBin(int deciNum){

        correctAnswer = "Start with a binary value of 0. Start with the largest power of 2 that can be subtracted from the decimal value without making the decimal value < 0. ";
        ArrayList binNum = new ArrayList();
        ArrayList actualBinNum = new ArrayList();
        int curDeciValue = deciNum;
        for (int i = 0; i < 20; i++) {//intialize ArrayList
            binNum.add(0);
        }

        int curIndex = (int)(Math.log(deciNum) / Math.log(2));
        //System.out.println("underlog " + curIndex);
        while (curDeciValue != 0){

            //System.out.println("INWHILELOOP CurIndex " + curIndex + " curDecValue " + curDeciValue);

            if (curDeciValue - Math.pow(2, curIndex) >= 0) {

                correctAnswer = correctAnswer + " \r\n If the 2 raised to the " + curIndex + "(" + Math.pow(2,curIndex) + ") subtracted from the decimal value > 0 (" + (curDeciValue - Math.pow(2,curIndex)) + ") add a 1 to the binary value";
                curDeciValue -= Math.pow(2, curIndex);
                //System.out.println("inwhileaboveadd " + curIndex);
                binNum.remove(curIndex);
                binNum.add(curIndex, "1");
                //System.out.println("at index 3 " + binNum.get(3));
            } else {
                correctAnswer = correctAnswer + " \r\n If the 2 raised to the " + curIndex + "(" + Math.pow(2,curIndex) + ") subtracted from the decimal value < 0 (" +(curDeciValue - Math.pow(2,curIndex))+ ") add a 0 to the binary value";
            }
            //System.out.println("HERE " + (curIndex == 0) + " " + (curIndex) );
            curIndex -= 1;
        }
        //System.out.println("SizeofBinNumArray " + binNum.size());
        for (int extraZero = binNum.size() - 1; extraZero >= 0; extraZero-- ){
            if (binNum.get(extraZero).equals("1")){

                if (actualBinNum.size() == 0){

                    int actualBinNumLength = extraZero + 1;
                    System.out.println("actualBinNumLength " + actualBinNumLength);
                    for (int f = 0; f < actualBinNumLength ; f++){
                        actualBinNum.add("0");
                    }
                }
                System.out.println("one being added at index " + extraZero);
                actualBinNum.remove(extraZero);
                actualBinNum.add(extraZero , "1");

            } else if (binNum.get(extraZero).equals("0") && actualBinNum.size() != 0){
                System.out.println("zero being added at index " + extraZero);
                actualBinNum.remove(extraZero);
                actualBinNum.add(extraZero , "0");
            }
        }
        correctAnswer = correctAnswer + " \r\n So the correct answer is " + actualBinNum;
        return actualBinNum;
    }


    //converts from decimal to different base
    public int differentBaseToDeci(String numSystem, ArrayList numGiven){
        int deciNum = 0;

        System.out.print("decinum " + deciNum);
        if ( numSystem.equals("oct")){
            correctAnswer = "Start with the digit in the ones place and a decimal value of 0. ";
            for (int p = 0; p < numGiven.size();p++){
                correctAnswer = correctAnswer + " \r\n Add 8 raised to the power of " + p + " (" + Math.pow(8,p) + ") times the value " + (String)numGiven.get(p) + ". Then add it (" + Math.pow(8,p) * Integer.parseInt((String)numGiven.get(p)) +") to the decimal value";
                deciNum += Math.pow(8,p) * Integer.parseInt((String)numGiven.get(p));
            }
        } else if (numSystem.equals("hex")){//numSystem.equals("hex")
            correctAnswer = "To convert from hexadecimal to decimal you must know a basic rule. A = 10, B = 11, C = 12, D = 13, E = 14, F = 15";
            for (int i = 0; i < numGiven.size(); i++){
                System.out.print("decinum in loop " + deciNum);

                if ("1234567890".contains(String.valueOf(numGiven.get(i)))){//adds int values from hex to decivalue
                    correctAnswer = correctAnswer + " \r\n Add 16 raised to the power of " + i + " (" + Math.pow(16,i) + ") times the value " + (String)numGiven.get(i) + ". Then add it (" + Math.pow(16,i) * Integer.parseInt((String)numGiven.get(i)) +") to the decimal value";
                    deciNum += Math.pow(16,i) * Integer.parseInt((String)numGiven.get(i));
                } else {//adds letter values from hex to decivalue
                    correctAnswer = correctAnswer + " \r\n Add 16 raised to the power of " + i + " (" + Math.pow(16,i) + ") times the value " + hexToDeciTable.get(String.valueOf(numGiven.get(i))) + ". Then add it (" + Math.pow(16,i) * Integer.parseInt(hexToDeciTable.get(String.valueOf(numGiven.get(i)))) +") to the decimal value";
                    deciNum += Math.pow(16,i) * Integer.valueOf(hexToDeciTable.get(String.valueOf(numGiven.get(i))));
                }
            }

        }
        System.out.print("decinum " + deciNum);

        correctAnswer = correctAnswer + " \r\n So the correct answer is " + deciNum;
        return deciNum;
    }

    //TODO COMEBACK
    //converts binary value to hex or oct
    //returns String
    public String binToHexOrOct(String numSystem, ArrayList binNum){
        //convert binNum to String
        correctAnswer = " When converting from binary it is helpful to have a table in front of you.";
        String binNumString = convertArrayListToString(binNum);
        String hexNumStringValue = "";
        String octNumStringValue = "";

        if (numSystem.equals("hex")){

            while (binNumString.length() % 4 != 0){
                binNumString = "0" + binNumString;
            }
            for (int i = 0; i < binNumString.length()/4; i++){
                System.out.println("HEXTESTING Substring of binary value " + binNumString.substring(binNumString.length()-(4*i + 4), binNumString.length()-4*i));
                hexNumStringValue = binToHexTable.get(binNumString.substring(binNumString.length()-(4*i + 4), binNumString.length()-(4*i))) + hexNumStringValue;
            }

            System.out.println("hexNumStringValue " + hexNumStringValue);
            return hexNumStringValue;
        } else if (numSystem.equals("oct")){
            while (binNumString.length() % 3 != 0){
                binNumString = "0" + binNumString;
            }
            System.out.println("OCTALTESTING Binary Value: " + binNumString + " octNumStringValue " + octNumStringValue);
            for (int i = 0; i < binNumString.length()/3; i++){
                System.out.println("OCTALTESTING Substring of binary value " + binNumString.substring(binNumString.length()-(3*i + 3), binNumString.length()-3*i));
                octNumStringValue = binToOctTable.get(binNumString.substring(binNumString.length()-(3*i + 3), binNumString.length()- 3*i)) + octNumStringValue ;
            }
            System.out.println(octNumStringValue);
            return octNumStringValue;
        }
        return "Nothing";
    }

    //TODO COMEBACK
    //converts hex or oct value to binary
    //returns arraylist of binary
    public ArrayList hexOrOctToBinary(String numSystem, ArrayList hexOrOctNum){
        correctAnswer = " When converting to binary it is helpful to have a table in front of you.";
        String binaryValue = "";
        ArrayList<Integer> binaryNum;
        if (numSystem.equals("hex")){
            for (int i  = 0; i < hexOrOctNum.size(); i++){
                binaryValue += hexToBinTable.get(hexOrOctNum.get(hexOrOctNum.size()- (i+1)));
            }
        } else if (numSystem.equals("oct")){
            for (int i  = 0; i < hexOrOctNum.size(); i++){
                binaryValue += octToBinTable.get(hexOrOctNum.get(hexOrOctNum.size()- (i+1)));
            }
        }
        binaryNum = convertStringToArrayList(binaryValue);
        //call method to convert String to ArrayList
        return binaryNum;
    }

    //converts string to arraylist
    //returns ArrayList
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

    //converts ArrayList to String
    //returns String
    public String convertArrayListToString(ArrayList arrayList){
       String string = "";
       for (int i = 0; i < arrayList.size(); i++){
           string += arrayList.get(arrayList.size() - (i + 1));
       }
       return string;
    }

    //Reverses order of ArrayList
    //returns ArrayList
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
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }

    }
    public void buttonTwoClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 2;
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonThreeClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 3;
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonFourClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 4;
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonFiveClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 5;
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonSixClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 6;
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonSevenClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 7;
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonEightClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 8;
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonNineClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 9;
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonZeroClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += 0;
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonAClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "A";
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonBClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "B";
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonCClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "C";
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonDClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "D";
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonEClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "E";
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }
    public void buttonFClicked (View view){
        if (!startingNumChosen){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
        } else if (startingNumChosen) {
            userInput += "F";
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }

    public void delRecentChar (View view){
        if (userInput.equals("") && !convertedNumberSystem.equals("")){
            convertedNumberSystem = "";
            convertingNumChosen = false;
            TextView endNumSys = findViewById(R.id.convertedNumberSystemText);
            endNumSys.setText(convertedNumberSystem);
        } else if (userInput.equals("") && !startingNumberSystem.equals("")){
            startingNumberSystem = "";
            startingNumChosen = false;
            makeLettersGoneOrVisible("visible");
            makeNumbersGoneOrVisible("visible");
            TextView beginNumSys = findViewById(R.id.startingNumberSystemText);
            beginNumSys.setText(startingNumberSystem);
        } else if (!userInput.equals("")){
            userInput = userInput.substring(0,userInput.length() - 1);
            TextView textViewToChange = findViewById(R.id.userInputText);
            textViewToChange.setText(userInput);
        }
    }

    public void clearUserInputFromActivty (View view){
        clearUserInput();
    }

    public void clearUserInput (){
        correctAnswer = "";
        makeLettersGoneOrVisible("visible");
        makeNumbersGoneOrVisible("visible");
        startingNumberSystem = "";
        convertedNumberSystem = "";
        userInput = "";
        computerOutputText = "";
        startingNumChosen = false;
        convertingNumChosen = false;
        TextView beginNumSys = findViewById(R.id.startingNumberSystemText);
        beginNumSys.setText(startingNumberSystem);
        TextView endNumSys = findViewById(R.id.convertedNumberSystemText);
        endNumSys.setText(convertedNumberSystem);
        TextView userInputToChange = findViewById(R.id.userInputText);
        userInputToChange.setText(userInput);
        TextView computerOutputToChange = findViewById(R.id.computerOutputText);
        computerOutputToChange.setText(userInput);
    }

    public void buttonBinaryClicked (View view){
        if (!startingNumChosen){
            startingNumChosen = true;
            startingNumberSystem += "Binary";
            TextView textViewToChange = findViewById(R.id.startingNumberSystemText);
            textViewToChange.setText(startingNumberSystem);
            makeLettersGoneOrVisible("gone");
            makeNumbersGoneOrVisible("gone");
        } else if (!convertingNumChosen && !startingNumberSystem.equals("Binary")){
            convertingNumChosen = true;
            convertedNumberSystem += "Binary";
            TextView textViewToChange = findViewById(R.id.convertedNumberSystemText);
            textViewToChange.setText(convertedNumberSystem);
        }
    }

    public void buttonDecimalClicked (View view){
        if (!startingNumChosen){
            startingNumChosen = true;
            startingNumberSystem += "Decimal";
            TextView textViewToChange = findViewById(R.id.startingNumberSystemText);
            textViewToChange.setText(startingNumberSystem);
            makeLettersGoneOrVisible("gone");
        } else if (!convertingNumChosen && !startingNumberSystem.equals("Decimal")){
            convertingNumChosen = true;
            convertedNumberSystem += "Decimal";
            TextView textViewToChange = findViewById(R.id.convertedNumberSystemText);
            textViewToChange.setText(convertedNumberSystem);
        }
    }

    public void buttonHexidecimalClicked (View view){
        if (!startingNumChosen){
            startingNumChosen = true;
            startingNumberSystem += "Hexidecimal";
            TextView textViewToChange = findViewById(R.id.startingNumberSystemText);
            textViewToChange.setText(startingNumberSystem);
        } else if (!convertingNumChosen && !startingNumberSystem.equals("Hexidecimal")){
            convertingNumChosen = true;
            convertedNumberSystem += "Hexidecimal";
            TextView textViewToChange = findViewById(R.id.convertedNumberSystemText);
            textViewToChange.setText(convertedNumberSystem);
        }
    }

    public void buttonOctalClicked (View view){
        if (!startingNumChosen){
            startingNumChosen = true;
            startingNumberSystem += "Octal";
            TextView textViewToChange = findViewById(R.id.startingNumberSystemText);
            textViewToChange.setText(startingNumberSystem);
            makeLettersGoneOrVisible("gone");
        } else if (!convertingNumChosen && !startingNumberSystem.equals("Octal")){
            convertingNumChosen = true;
            convertedNumberSystem += "Octal";
            TextView textViewToChange = findViewById(R.id.convertedNumberSystemText);
            textViewToChange.setText(convertedNumberSystem);
        }
    }

    public void makeLettersGoneOrVisible (String goneOrVisible){
        if (curScreen.equals("calculator")){
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

    }

    public void makeNumbersGoneOrVisible (String goneOrVisible){
        if (curScreen.equals("calculator")) {
            if (goneOrVisible.equals("gone")) {

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

            } else if (goneOrVisible.equals("visible")) {
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
    }

    //Generates practice problem in the correct number system
    //Based on user input of : StartingNumSystem, ConvertedNumsystem, Range of number,
    public void generateRandomPracticeNumber (View view){

        TextView startingNumSystem = findViewById(R.id.startingNumberSystemText);
        String startNumSystem =  startingNumSystem.getText().toString();
        startingNumberSystem = startNumSystem;

        TextView endingNumSystem = findViewById(R.id.convertedNumberSystemText);
        String endNumSystem =  endingNumSystem.getText().toString();

        if (startNumSystem.equals("")){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
            return;
        }
        if (endNumSystem.equals("")){
            Toast.makeText(getApplicationContext(), "Please Choose a Number System", Toast.LENGTH_SHORT).show();
            return;
        }
        convertedNumberSystem = startingNumberSystem;
        startingNumberSystem = "Decimal";



        EditText startingLimit = findViewById(R.id.lowerLimitSubmitted);
        if (startingLimit.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please Choose a Lower Limit", Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println("BADVALUE " + startingLimit.getText().toString());
        int start =  Integer.valueOf(startingLimit.getText().toString());



        EditText endingLimit = findViewById(R.id.upperLimitNum);
        if (endingLimit.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please Choose an Upper Limit", Toast.LENGTH_SHORT).show();
            return;
        }
        int end =  Integer.valueOf(endingLimit.getText().toString());

        if (start > end){
            Toast.makeText(getApplicationContext(), "Lower limit cannot be less than upper limit", Toast.LENGTH_SHORT).show();
            return;
        }

        userInput = String.valueOf((int)(Math.random() * ((end - start) + 1) + start));

        System.out.println("RandomNumberGenerated " + userInput);

        if (startNumSystem.equals("Decimal") && start != end){
            TextView showProblem = findViewById(R.id.valueOfNumToConvert);
            showProblem.setText(userInput);
        } else if (start != end) {
            System.out.println("CURNUMSYS " + startingNumberSystem);
            System.out.println("CONNNNNUMSYS " + convertedNumberSystem);
            userInput = calculateConvertedValue("Practice Problem");
            System.out.println("Convertedrandomnumber " + userInput);
            TextView showProblem = findViewById(R.id.valueOfNumToConvert);
            showProblem.setText(userInput);
        }
        convertedNumberSystem = endNumSystem;
        startingNumberSystem = startNumSystem;
        correctAnswer = calculateConvertedValue("Practice Problem");
    }

    public void checkAndShowCorrectAnswer (View view){
        //checks user input
        //grades
        //shows correct answer
        Button showShowStepsButton = findViewById(R.id.showSteps);
        showShowStepsButton.setVisibility(View.VISIBLE);

        EditText startingLimit = findViewById(R.id.lowerLimitSubmitted);
        String start =  startingLimit.getText().toString();

        EditText endingLimit = findViewById(R.id.upperLimitNum);
        String end =  endingLimit.getText().toString();

        EditText userAnswer = findViewById(R.id.answerFromUser2);
        String userAnswerGiven =  userAnswer.getText().toString();
        if(!userInput.equals("") && !startingNumberSystem.equals("") && !convertedNumberSystem.equals("") && !start.equals(null) && !end.equals(null)) {
            Button submitButton = findViewById(R.id.submitAnswer);
            if (userAnswerGiven.equals(correctAnswer)) {
                submitButton.setBackgroundColor(Color.GREEN);
                submitButton.setText("Correct");
                //show text CORRECT!
            } else {
                submitButton.setBackgroundColor(Color.RED);
                submitButton.setText("Incorrect");
                //show text incorrect
                //button show answer
                //user can resubmit a different answer
            }

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
        curScreen = "calculator";
        setContentView(R.layout.activity_calculator);
        clearUserInput();
    }

    public void showPracticeProblemScreen (View view){
        curScreen = "Practice Problems";
        setContentView(R.layout.activity_practice_problems);
    }

    public void showShowStepsScreen (View view){
        curScreen = "Show Steps";
        setContentView(R.layout.activity_show_steps);
        TextView setStartingNumberSystem = findViewById(R.id.startingNumberSystem);
        setStartingNumberSystem.setText(startingNumberSystem);

        TextView setEndingNumberSystem = findViewById(R.id.convertedNumberSystem);
        setEndingNumberSystem.setText(convertedNumberSystem);

        TextView setSteps = findViewById(R.id.actualSteps);
        setSteps.setText(correctAnswer);
    }

}
