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


    public void performAction(){

        int N = getCurrentLivePopulation();
        int randBacIndex = rand.nextInt(N);
        int mh_index = 0;
        Microhabitat rand_mh = microhabitats[mh_index];

        double death_rate = rand_mh.dRate();

        double R_max = 5.2;
        double life_or_death = rand_mh.replication_or_death_rate();

        if(life_or_death >=0){
            double rand_chance = rand.nextDouble()*R_max;

            if(rand_chance <= migrate_rate) migrate(mh_index);
            else if(rand_chance > migrate_rate && rand_chance <= life_or_death+migrate_rate) replicate(mh_index);

        }else{
            double rand_chance = rand.nextDouble()*R_max;
            life_or_death*=-1;

            if(rand_chance <= migrate_rate) migrate(mh_index);
            else if(rand_chance > migrate_rate && rand_chance <= life_or_death+migrate_rate) death(mh_index);
        }

        timeElapsed += 1./((double)N*R_max);
    }















}
