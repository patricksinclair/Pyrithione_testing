import javax.tools.Tool;
import java.util.Random;

public class BioSystem {

    Random rand = new Random();

    private int L, N_alive;
    private double c, timeElapsed;

    private Microhabitat[] microhabitats;


    public BioSystem(int L, int N_alive, double c){
        this.L = L;
        this.N_alive = N_alive;
        this.c = c;
        this.microhabitats = new Microhabitat[L];
        this.timeElapsed = 0.;

        for(int i = 0; i < L; i++){
            microhabitats[i] = new Microhabitat(0, c);
        }
        microhabitats[0].setN_alive(N_alive);
    }

    public double getTimeElapsed(){
        return timeElapsed;
    }

    public int getCurrentLivePopulation(){
        int runningTotal = 0;
        for(Microhabitat m : microhabitats) {
            runningTotal += m.getN_alive();
        }
        return runningTotal;
    }

    public int getCurrentDeadPopulation(){
        int runningTotal = 0;
        for(Microhabitat m : microhabitats) {
            runningTotal += m.getN_dead();
        }
        return runningTotal;
    }


    public void performAction(){

        int N = getCurrentLivePopulation();
        //int randBacIndex = rand.nextInt(N);
        int mh_index = 0;
        Microhabitat rand_mh = microhabitats[mh_index];

        double death_rate = rand_mh.dRate();
        double growth_rate = rand_mh.gRate();

        double R_max = 5.2;
        //double life_or_death = rand_mh.replication_or_death_rate();

        double rand_chance = rand.nextDouble()*R_max;
        // CHECK THE VALUES YOU GET FOR THE DEATH RATE, MAKE SURE IF IT'S NEGTIVE ETC
        if(rand_chance <= growth_rate){
            rand_mh.replicateABActerium();
        }else if(rand_chance > growth_rate && rand_chance <= death_rate){
            rand_mh.killABacterium();
        }

        timeElapsed += 1./((double)N*R_max);
    }




    public static void popSizeOverTime(double c, int N){

        int L = 1;
        int nReps = 10;
        double duration = 20.;
        int nTimeMeasurements = 200;
        double interval = duration/(double)nTimeMeasurements; //the time interval at which each measurement is recorded

        String filename = "Pyrithione_testing_c="+String.valueOf(c)+".txt";
        String[] fileHeaders = {"time", "nAlive", "nDead"};

        double[][] allTimeMeasurements = new double[nReps][];
        int[][] allLiveMeasurements = new int[nReps][]; //array which contains all the repeated measurements
        int[][] allDeadMeasurements = new int[nReps][];

        for(int nR = 0; nR < nReps; nR++){

            BioSystem bioSys = new BioSystem(L, N, c);
            boolean alreadyRecorded = false;

            // this array will store the population of the microhabitat for each time measurement
            double[] timeMeasurements = new double[nTimeMeasurements];
            int[] populationOverTime = new int[nTimeMeasurements+1];
            int[] deadPopulationOverTime = new int[nTimeMeasurements];
            // this is used to index the population measurements
            int timerCounter = 0;

            while(bioSys.timeElapsed < duration){

                bioSys.performAction();

                if((bioSys.getTimeElapsed()%interval >= 0. && bioSys.getTimeElapsed()%interval <= 0.01) && !alreadyRecorded){

                    System.out.println("rep: "+nR+"\ttime elapsed: "+String.valueOf(bioSys.getTimeElapsed()));
                    timeMeasurements[timerCounter] = bioSys.getTimeElapsed();
                    populationOverTime[timerCounter] = bioSys.getCurrentLivePopulation();
                    deadPopulationOverTime[timerCounter] = bioSys.getCurrentDeadPopulation();

                    alreadyRecorded = true;
                    timerCounter++;
                }
                if(bioSys.getTimeElapsed()%interval >= 0.1) alreadyRecorded = false;
            }

            allTimeMeasurements[nR] = timeMeasurements;
            allLiveMeasurements[nR] = populationOverTime;
            allDeadMeasurements[nR] = deadPopulationOverTime;

        }
        double[] averagedTimes = Toolbox.averagedResults(allTimeMeasurements);
        double[] averagedLivePops = Toolbox.averagedResults(allLiveMeasurements);
        double[] averagedDeadPops = Toolbox.averagedResults(allDeadMeasurements);

        double[][] collatedResults = {averagedTimes, averagedLivePops, averagedDeadPops};

        Toolbox.printResultsToFileWithHeaders(filename, fileHeaders, collatedResults);
    }











}
