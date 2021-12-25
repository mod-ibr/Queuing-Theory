
package queuesystemproj.Deterministic;

import queuesystemproj.QSparameters_abstract;


public abstract class DD1K_abstract extends QSparameters_abstract{
    
    protected int Ti;
    protected int K;
    protected int [] listToDisplay;
    protected int GCM = 0;
    protected int lengthOfPlotSegments = 0;
    
    public void setK (int k){
        this.K=k;
    }
    public int getK (){
        return this.K;
    }

    public void setTi (int Ti){
        this.Ti=Ti;
    }
    public int getTi (){
        return this.Ti;
    }
  
    public void setGCM (int GCM){
        this.GCM=GCM;
    }
    public int getGCM (){
        return this.GCM;
    }
    public void setLengthOfPlotSegments (int lengthOfPlotSegments){
        this.lengthOfPlotSegments=lengthOfPlotSegments;
    }
    public int getLengthOfPlotSegments (){
        return this.lengthOfPlotSegments;
    }
    public void calcGCM(){
        for(int i = 1; (i <= Math.round(1/this.getLampda())) && (i <= Math.round(1/this.getMu())); i++){  
            if((Math.round(1/this.getLampda()))%i==0 && (Math.round(1/this.getMu())) %i==0){  
                this.setGCM(i);
            }
        }
    }
    
    public int seriesDotsToPlot(int length){
        System.out.println("@ seriesDotsToPlot");
        int sum = 0;
        for (int iteration = 0 ; (iteration < length) && ( length >= 0) ; iteration++){
            sum = sum + (int)(this.getGCM());   // 4 is the min coefficient for Lampda and Mu
        }
        return this.calculateCustomersAtTimeT(sum);
    }
    //@Override
    abstract public int calcTi ();
    //@Override
    abstract public int calculateTiMin ();
    //@Override
    abstract public int calculateCustomersAtTimeT(int time);
    //@Override
    abstract public float calculateWaitingTimeAtCustomerN(int customerN);
}
