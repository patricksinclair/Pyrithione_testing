public class Microhabitat {

    private int N_alive;
    private double c;

    int N_dead;

    public Microhabitat(int N_alive, double c){
        this.N_alive = N_alive;
        this.c = c;

        this.N_dead = 0;
    }



    public int getN(){return N_alive + N_dead;}
    public int getN_alive(){return N_alive;}
    public void setN_alive(int N_alive){this.N_alive = N_alive;}
    public int getN_dead(){return N_dead;}
    public double getC(){
        return c;
    }

    public void replicateABActerium(){N_alive++;}

    public void killABacterium(){
        if(N_alive > 0){
            N_alive--;
            N_dead++;
        }
    }

    public double beta(){
        return 5.;
    }


    // set the growth rate to consistently be 0.1 here.
    // this is slightly different to the nutrients sims where phi(c) determined
    // growth and death rates
    public double gRate(){
        return 0.1;
    }


    public double dRate(){
        double cB = c/beta();
        return 1. - (6.*cB*cB)/(5. + cB*cB);
    }


















}
