/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuesystemproj.stocustic;

/**
 *
 * @author 20114
 */
public class MM1 extends MM_abstract{
    /*
    
    inhireted Attributes:
        from QS_parameters_abstract :
            Lampda
            Mu
        from MM_abstract :
            P_0
            P_n
            L
            Lq
            W
            Wq
    New Attribute:
    
    */
    
    public MM1( float Lampda, float Mu) {
        super(Lampda, Mu);
        this.updateRu();
        this.calculateP0();
        this.GetExpectedCustomersAtQueue();
        this.GetExpectedCustomersAtSystem();
        this.GetExpectedWaitingAtQueue();
        this.GetExpectedWaitingAtSystem();
    }
    public MM1() {
        super(0, 0);
        this.updateRu();
    }

    @Override
    protected float calculateP0(){
        this.setP0((1-this.getRu()));
        return this.getP0();
    }
    
    @Override
    protected float calculatePn(int n){
        this.setPn( ( (float)Math.pow(this.getRu(), n))*(1-this.getRu()));
        return this.getPn();
    }
    
    @Override
    public float GetExpectedCustomersAtSystem(){
        this.setL((this.getLampda())/(this.getMu()-this.getLampda()));
        return this.getL();
    }
    
    @Override
    public float GetExpectedCustomersAtQueue(){
        this.setLq(GetExpectedCustomersAtSystem()*this.getRu());
        return this.getLq();
    }
    
    @Override
    public float GetExpectedWaitingAtSystem(){
        this.setW(1/(this.getMu()-this.getLampda()));
        return this.getW();
    }
    @Override
    public float GetExpectedWaitingAtQueue(){
        this.setWq(GetExpectedWaitingAtSystem()*this.getRu());
        return this.getWq();
    }
}
