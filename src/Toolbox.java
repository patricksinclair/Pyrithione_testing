import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Toolbox {

    public static double[] convertArrayListToDoubleArray(ArrayList<Double> inputData){
        double[] outputData = new double[inputData.size()];

        for(int i = 0; i < inputData.size(); i++){
            outputData[i] = inputData.get(i);
        }
        return outputData;
    }

    public static int[] convertArrayListToIntArray(ArrayList<Integer> inputData){
        int[] outputData = new int[inputData.size()];

        for(int i = 0; i < inputData.size(); i++){
            outputData[i] = inputData.get(i);
        }
        return outputData;
    }

    public static double[] averagedJaggedResults(double[][] inputData){
        int maxColVals = 0;
        for(int i = 0; i < inputData.length; i++){
            if(inputData[i].length > maxColVals)
                maxColVals = inputData[i].length;
        }

        double[] outputData = new double[maxColVals];

        // need to iterate along all the measurements for t1 -> t2 -> etc
        // so outer loop is getting all the values per each sampling interval
        for(int j = 0; j < maxColVals; j++){
            double sum = 0.;
            double nVals = 0;

            for(int i = 0; i < inputData.length; i++){
                // this condition ensures there are no out of bounds exceptions
                if(j < inputData[i].length){
                    sum += inputData[i][j];
                    nVals += 1.;
                }
            }
            outputData[j] = sum/nVals;
        }
        return outputData;
    }

    public static double[] averagedJaggedResults(int[][] inputData){
        int maxColVals = 0;
        for(int i = 0; i < inputData.length; i++){
            if(inputData[i].length > maxColVals)
                maxColVals = inputData[i].length;
        }

        double[] outputData = new double[maxColVals];

        // need to iterate along all the measurements for t1 -> t2 -> etc
        // so outer loop is getting all the values per each sampling interval
        for(int j = 0; j < maxColVals; j++){
            double sum = 0.;
            double nVals = 0;

            for(int i = 0; i < inputData.length; i++){
                // this condition ensures there are no out of bounds exceptions
                if(j < inputData[i].length){
                    sum += inputData[i][j];
                    nVals += 1.;
                }
            }
            if(nVals > 1) outputData[j] = sum/nVals;
        }
        return outputData;
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
            String header_output = "#";
            for(String heady : headers){
                header_output += heady+"\t";
            }
            bw.write(header_output.trim());
            bw.newLine();

            // iterate down all the measurements from each time
            for(int nM = 0; nM < nMeasurements; nM++){

                String output = "";
                // iterate accross all the things measured at each timestep
                for(int nD = 0; nD < nData; nD++){

                    String formattedOutput = String.format("%.3f", collatedResults[nD][nM]);
                    output += formattedOutput+"\t";
                    //output += String.valueOf(collatedResults[nD][nM])+"\t";

                }
                bw.write(output.trim());
                bw.newLine();
            }
            bw.close();

        }catch (IOException e){}
    }



    public static void averageJaggedResults(String filename, String[] headers, double[][] collatedResults){

    }
}
