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
public class MM1K extends MM_abstract{
   /**
    *
    *inhireted Attributes:
    *    from QS_parameters_abstract :
    *        Lampda
    *        Mu
    *    from MM_abstract :
    *        P_0
    *        P_n
    *        L
    *        Lq
    *        W
    *        Wq
    *New Attribute:
    *    K
    *
    */
    
    private int K;
    private float Pk;
    private float Lampda_dash;
    
    public MM1K( float Lampda, float Mu, int K) {
        super(Lampda, Mu);
        this.setK(K);
        this.updateRu();
        this.calculateP0();
        this.calculatePk();
        this.updateLampdaDash();
    }
    public MM1K() {
        super(0, 0);
        this.setK(0);
        this.updateRu();
        this.updateLampdaDash();
    }
    public int getK(){
        return this.K ;
    }
    public void setK(int K){
        this.K = K;
    }
    public float getLampdaDash(){
        return this.Lampda_dash ;
    }
    public void updateLampdaDash(){
        this.calculateP0();
        this.Lampda_dash = this.getLampda()*(1-calculatePk());
    }
    @Override
    protected float calculateP0(){
        this.setP0( (this.getRu() != 1)?(( (1-this.getRu())/( 1 - power(this.getRu(), this.getK()+1)))):(1/(this.getK()+1)));
        return this.getP0();
    }
    public float getPk(){
        return this.Pk ;
    }
    private void calculateLampdaDash(){
        this.updateLampdaDash ();
    }
    @Override
    protected float calculatePn(int n){
        calculateP0();
        this.setPn((this.getRu() != 1)?( (power(this.getRu(), n)) * this.getP0() ):( (float)(this.getP0()) ));
        return this.getPn();
    }
    
    @Override
    public float GetExpectedCustomersAtSystem(){
        float sumNtoK=0;
        for (int n=0 ; (n <= this.getK())&&(this.getRu() != 1) ; n++){
            sumNtoK = sumNtoK + n*calculatePn(n);
        }
        this.setL((this.getRu() != 1)?(sumNtoK):(this.getK()/2));
        return this.getL();
    }
    
    @Override
    public float GetExpectedCustomersAtQueue(){
        //preparing Lampda dash
        calculateLampdaDash();
        //preparing Wq
        GetExpectedWaitingAtQueue();
        
        this.setLq(this.getL()-this.getLampdaDash()/this.getMu());
        return this.getLq();
    }
    
    @Override
    public float GetExpectedWaitingAtSystem(){
        this.setW(getL()/this.getLampdaDash());
        return this.getW();
    }
    @Override
    public float GetExpectedWaitingAtQueue(){
     this.setWq(this.getW()-1/this.getMu());
        return this.getWq();  
    }
    protected float calculatePk(){
        System.out.println("Pk = "+ this.calculatePn(this.K));
        this.Pk = this.calculatePn(this.K);
        return this.getPk();
    }
}
