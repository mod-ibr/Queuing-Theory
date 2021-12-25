
package queuesystemproj.Deterministic;

/**
 *
 * @author Ayman Saleh Zien
 */
public class DD1K_LampdaGreater extends DD1K_abstract{
    public DD1K_LampdaGreater(){
        this.setK(0);
        this.setLampda(0.0f);
        this.setMu(0.0f);
        this.Ti=0;
        this.flagState.resetState();
    }
    public DD1K_LampdaGreater(float Lampda, float Mu, int K , int lengthOfPlotSegments){
        this.setK(K);
        this.setLampda(Lampda);
        this.setMu(Mu);
        this.flagState.resetState();
        this.calcTi();
        this.setLengthOfPlotSegments(lengthOfPlotSegments);
        this.calcGCM();
        
        System.out.println("new Lampda = "+this.getLampda());
        System.out.println("new Mu = "+this.getMu());
        System.out.println("new K = "+this.getK());
        System.out.println("new Ti = "+this.getTi());
        System.out.println("new GCM = "+this.getGCM());
    }
    @Override
    public int calculateTiMin (){
        System.out.println("@ calculateTiMin");
        while (this.calculateCustomersAtTimeT(this.getTi()) > (this.getK()-1)){
            System.out.println(this.calculateCustomersAtTimeT(this.getTi()));
            this.setTi((int)(this.getTi()-(1/this.getLampda())));
            System.out.println("Ti @ Min itiration = "+this.getTi());
        }
        return (int)(getTi()+(1/this.getLampda()));
    }
    @Override
    public int calcTi (){
        System.out.println("@ calcTi");
        this.setTi((int)((this.getK() - (this.getMu()/this.getLampda()))/(this.getLampda()-this.getMu())));
        System.out.println("Ti @ frist itiration = "+this.getTi());
        this.setTi(calculateTiMin());
        this.flagState.setTransState();
        return this.getTi();
    }
    
    @Override
    public int calculateCustomersAtTimeT(int time){
        System.out.println("@ calculateCustomersAtTimeT");
        System.out.println("Catch time = "+time);
        
        if (this.flagState.getSteadyState() != 2){
            if (time < this.getTi()){
                System.out.println("Transient On");
                this.flagState.setTransState();
            }
            else {
                System.out.println("Steady On");
                this.flagState.setSteadyState();
            }
        }
        
        if (flagState.getSteadyState() == 0 || flagState.getSteadyState() == 2){
            System.out.println("Transient");
            if (time == 0 ){
                return 0;
            }
            else {System.out.println("=================> Number Of Cust Is  "+ ((int)((this.getLampda()*time))-((int)(this.getMu()*time - (float)(this.getMu()/this.getLampda())))));
                return (time < (1/this.getLampda()))?(0):((int)((this.getLampda()*time))-((int)(this.getMu()*time - (float)(this.getMu()/this.getLampda()))));
            }
                
        }
        else if (flagState.getSteadyState() == 1){
            System.out.println("Steady");
            int i = 0;
            for (i = 0 ;  time <= this.getTi()+i ;i=i+this.getGCM()*Math.round(1/this.getMu()));
            i = i-this.getGCM()*Math.round(1/this.getMu());
            if ( (time > this.getTi()+i+this.getGCM()) && (time < this.getTi()+i+2*this.getGCM())){
                return this.getK()-2;
            }
            else{
                return this.getK()-1;
            }
        }
        
        //return (flagState.getSteadyState() == 0 )?((int)(Lampda*time)-(int)(Mu*time - (float)(Mu/Lampda))):(K-1); 
        return -1;
    }
    @Override
    public float calculateWaitingTimeAtCustomerN(int customerN){
        
        System.out.println("@ calculateWaitingTimeAtCustomerN");
        System.out.println("Catch N = "+customerN);
        
        if (customerN < (int)(this.getLampda()*this.getTi())){
            this.flagState.setTransState();
        }
        else if (customerN >= (int)(this.getLampda()*this.getTi())){
            this.flagState.setSteadyState();
        }
        switch (flagState.getSteadyState()){
            case 0:{
                System.out.println("Transient");
                if (customerN == 0){
                    return 0;
                }
                else if (customerN  < (this.getTi()*this.getLampda())){
                    return (((float)(1/this.getMu())-(float)(1/this.getLampda()))*(customerN-1));
                    
                }
                
            }break;
            case 1:{
                System.out.println("Steady");
                if (this.getLampda()%this.getMu() == 0){
                    return (((float)(1/this.getMu())-(float)(1/this.getLampda()))*(this.getLampda()*this.getTi()-2)); //or -3
                    }
                    else {
                        return (((float)(1/this.getMu())-(float)(1/this.getLampda()))*(this.getLampda()*this.getTi()-3)); //or -3
                    }
                
            }
        }
        //return (flagState.getSteadyState() == 0 )?(((float)(1/Mu)-(float)(1/Lampda))*(customerN-1)):(((float)(1/Mu)-(float)(1/Lampda))*(Lampda*Ti-2));
    return -1;
    }  
}
