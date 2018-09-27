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
        int duration = 20;
        int nTimeMeasurements = 200;

        String filename = "Pyrithione_testing_c="+String.valueOf(c)+".txt";

        for(int nR = 0; nR < nReps; nR++){

            BioSystem bioSys = new BioSystem(L, N, c);
            boolean alreadyRecorded = false;

            while(bioSys.timeElapsed < duration){



            }




        }



    }











}
