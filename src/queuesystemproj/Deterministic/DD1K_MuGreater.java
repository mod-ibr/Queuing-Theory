/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuesystemproj.Deterministic;

import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author 20114
 */
public class DD1K_MuGreater extends DD1K_abstract {
    
    private int initialMembers;
    private int equality = 0;
    
    public DD1K_MuGreater(){
        this.setK(0);
        this.setLampda(0.0f);
        this.setMu(0.0f);
        this.Ti=0;
        this.initialMembers=0;
        this.flagState.resetState();
    }
    public DD1K_MuGreater(float Lampda, float Mu, int K, int initialMembers, int lengthOfPlotSegments){
        this.setK(K);
        this.setLampda(Lampda);
        this.setMu(Mu);
        this.setInitialMembers(initialMembers);
        this.calcGCM();
        this.setLengthOfPlotSegments(lengthOfPlotSegments);
        this.equality = (this.getLampda()== this.getMu())?(1):(0);
        
        this.flagState.resetState();
        this.calcTi();
        
        System.out.println("new Lampda = "+this.getLampda());
        System.out.println("new Mu = "+this.getMu());
        System.out.println("new K = "+this.getK());
        System.out.println("new M = "+this.getInitialMembers());
        System.out.println("new Ti = "+this.getTi());
        System.out.println("new GCM = "+this.getGCM());
        if (this.equality == 1){
            System.out.println("Lampda = Mu in this object!");
        }
    }
    public int getInitialMembers (){
        return this.initialMembers;
    }
    public void setInitialMembers(int m){
        //equality = (this.getLampda()==this.getMu())?(1):(0);
        this.initialMembers = m;
    }
    
    @Override
    public int calculateTiMin (){
        System.out.println("@ calculateTiMin");
        System.out.println("Ti Starts at :" + this.getTi());
        
        while (this.calculateCustomersAtTimeT(this.getTi()) == 0 ){
            System.out.println(this.calculateCustomersAtTimeT(this.getTi()));
            this.setTi((int)(this.getTi()+(1/this.getLampda())));
            System.out.println("Ti @ Min itiration = "+this.getTi());
        }
        return (int)(this.getTi()-(int)(1/this.getLampda()));
    }
    @Override
    public int calcTi (){
        System.out.println("@ calcTi");
        switch(this.equality){
            case 0:{
                System.out.println("@ not-equal calcTi");
                this.setTi((int)((float)this.getInitialMembers()/((float)(this.getMu()-this.getLampda()))));
                System.out.println("Ti @ frist itiration = "+this.getTi());
                this.setTi(this.calculateTiMin());
            }break;
            case 1:{
                System.out.println("@ equal calcTi");
                this.setTi(0);
            }break;
        }
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
        switch (this.equality ){
            case 0:{
                System.out.println("@ not-Equality");
                if  (this.flagState.getSteadyState() == 0 || this.flagState.getSteadyState() == 2 ){
                        System.out.println("Transient");
                        return (this.getInitialMembers() + (int)(this.getLampda()*time) - (int)(this.getMu()*time));
                    }
                else if (this.flagState.getSteadyState() == 1){
                    int i = 0;
                    for (i = 0 ;  time >= this.getTi()+i ;i=i+Math.round(1/this.getLampda())){
                    }
                    i = i- Math.round(1/this.getLampda());
                    System.out.println("Steady");
                    if ( (time >= this.getTi()+i) && (time < (this.getTi()+(1/this.getLampda())-(1/this.getMu()))+i )){
                        return 0;
                    }
                    else if ( (time >= (this.getTi()+(1/this.getLampda())-(1/this.getMu()))+i) && (time < (this.getTi()+(1/this.getLampda()))+i )){
                        return 1;
                    }
                    return 0;//or 1
                }
            }break;
            case 1:{
                System.out.println("@ Equality");
                return this.getInitialMembers();
            }
        }
        
        //return (flagState.getSteadyState() == 0 )?((int)(Lampda*time)-(int)(Mu*time - (float)(Mu/Lampda))):(K-1); 
        return -1;
        
    }
    
    @Override
    public float calculateWaitingTimeAtCustomerN(int customerN){
        System.out.println("@ calculateWaitingTimeAtCustomerN");
        if (customerN <= (int)(this.getLampda()*this.getTi())){
            this.flagState.setTransState();
        }
        else if (customerN > (int)(this.getLampda()*this.getTi())){
            this.flagState.setSteadyState();
        }
        switch (this.equality){
            case 0:{
                System.out.println("@ Not-Equality");
                switch (this.flagState.getSteadyState()){
                    case 0 :{
                        System.out.println("@ Transient");
                        if (customerN == 0){
                            return ((this.initialMembers-1)/(2*this.getMu()));
                                }
                        else {
                            return ((this.getInitialMembers() +customerN -1)*(1/this.getMu())-(customerN/this.getLampda()));  //Wq(n) = ( M - 1 + n) (1/mu) – n(1/ lampda)
                        }
                    }
                    case 1 :{
                        System.out.println("@ Steady");
                        return 0;   //Wq(n) = 0
                    }
                }
            }break;
            case 1:{
                System.out.println("@ Equality");
                return (this.getInitialMembers() -1)*(1/this.getMu());  //For lampda = mu,  Wq(n) = (M-1)(1/ mu) for all customers.
            }
        }
        return -1;
    }
}
