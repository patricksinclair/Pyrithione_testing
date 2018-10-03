import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Toolbox {

    public static void writeContoursToFile(ArrayList<Double> xData, ArrayList<Double> yData, ArrayList<Double> zData, String filename){

        try{
            File file = new File(filename+".txt");
            if(!file.exists()) file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            int n = xData.size();

            for(int i = 0; i < n; i++){
                for(int j = i*n; j < (i+1)*n; j++){
                    String output = String.valueOf(xData.get(i)) + " " + String.valueOf(yData.get(j)) +" " + String.valueOf(zData.get(j));
                    bw.write(output);
                    bw.newLine();
                }
                bw.newLine();
            }
            bw.close();
        }catch (IOException e){}
    }

    public static double[] averagedResults(double[][] inputData){
        int nReps = inputData.length;
        int nMeasurements = inputData[0].length; //no. of times a measurement was taken

        double[] averagedResults = new double[nMeasurements];

        for(int m = 0; m < nMeasurements; m++){
            double runningTotal = 0.;
            for(int r = 0; r < nReps; r++){
                runningTotal += inputData[r][m];
            }
            averagedResults[m] = runningTotal/(double)nReps;
        }
        return averagedResults;
    }

    public static double[] averagedResults(int[][] inputData){
        int nReps = inputData.length;
        int nMeasurements = inputData[0].length; //no. of times a measurement was taken

        double[] averagedResults = new double[nMeasurements];

        for(int m = 0; m < nMeasurements; m++){
            double runningTotal = 0.;
            for(int r = 0; r < nReps; r++){
                runningTotal += (double)inputData[r][m];
            }
            averagedResults[m] = runningTotal/(double)nReps;
        }
        return averagedResults;
    }

    public static double[][] averagedResults(int[][][] inputData){

        int nReps = inputData.length;
        int nTimes = inputData[0].length;
        int L = inputData[0][0].length;

        double[][] averagedResults = new double[nTimes][L];

        for(int t = 0; t < nTimes; t++){

            for(int l = 0; l < L; l++){

                double runningTotal = 0.;

                for(int r = 0; r < nReps; r++){
                    runningTotal += (double)inputData[r][t][l];
                }
                averagedResults[t][l] = runningTotal/(double)nReps;
            }
        }
        return averagedResults;
    }

    public static double[][] averagedResults(double[][][] inputData){

        int nReps = inputData.length;
        int nTimes = inputData[0].length;
        int L = inputData[0][0].length;

        double[][] averagedResults = new double[nTimes][L];

        for(int t = 0; t < nTimes; t++){

            for(int l = 0; l < L; l++){

                double runningTotal = 0.;

                for(int r = 0; r < nReps; r++){
                    runningTotal += inputData[r][t][l];
                }
                averagedResults[t][l] = runningTotal/(double)nReps;
            }
        }
        return averagedResults;
    }

    public static void printAveragedResultsToFile(String filename, double[][] inputData){

        try {
            File file = new File(filename+".txt");
            if(!file.exists()) file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            int nTimes = inputData.length;
            int L = inputData[0].length;

            for(int l = 0; l < L; l++){
                String output = String.valueOf(l)+" ";
                for(int t = 0; t < nTimes; t++){
                    output += String.valueOf(inputData[t][l]+ " ");
                }
                bw.write(output);
                bw.newLine();
            }
            bw.close();
        }catch (IOException e){}
    }

    public static void printResultsToFileWithHeaders(String filename, String[] headers, double[][] collatedResults){
        // this takes an array of the headers for the measurements and also a 2D array of the results in one,
        // should be more modular this way
        // could add in length comparison stuff if i ever feel really fancy


        try{
            File file = new File(filename+".txt");
            if(!file.exists()) file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            int nData = collatedResults.length; // the no. of arrays in the collated data array, i.e. no. of columns in file
            int nMeasurements = collatedResults[0].length;

            // writes the headers to the file
            String header_output = "";
            for(String heady : headers){
                header_output += "heady\t";
            }
            bw.write(header_output.trim());
            bw.newLine();

            // iterate down all the measurements from each time
            for(int nM = 0; nM < nMeasurements; nM++){

                String output = "";
                // iterate accross all the things measured at each timestep
                for(int nD = 0; nD < nData; nD++){

                    output += String.valueOf(collatedResults[nD][nM])+"\t";

                }
                bw.write(output.trim());
                bw.newLine();
            }
            bw.close();

        }catch (IOException e){}



    }
}
