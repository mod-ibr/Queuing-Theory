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
public class MMc extends MM_abstract{
    
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
    *    r
    *    c
    *
    */
    private float r;
    private int c;
    private float Ci;
    
    
    public MMc( float Lampda, float Mu, int c) {
        super(Lampda, Mu);
        this.setc(c);
        this.updater();
        this.updateRu();
        this.calculateP0();
        this.updateCi();
    }
    public MMc() {
        super(0, 0);
        this.updater();
        this.updateRu();
        this.setc(0);
    }
    @Override
    public void updateRu(){
        this.Ru = this.getr()/this.getc();
    }
    public void setc(int c){
        this.c = c;
    }
    public int getc(){
        return this.c;
    }
    public float getCi(){
        return this.Ci;
    }
    private void updateCi(){
        this.Ci = (this.getc()-this.getr());
    }
    private void updater(){
        this.r = this.getLampda()/this.getMu();
    }
    public float getr(){
        return this.r;
    }
    @Override
    protected float calculateP0(){
        float SumCalculation = 0;
        float totalCalculation = 0;
        if (this.getRu() < 1){
            for (int n = 0 ; n <= this.getc()-1 ; n++){
                SumCalculation = SumCalculation + (power(this.getr(), n))/(factorial(n));
            }
            totalCalculation = SumCalculation +((this.getc() * power(this.getr(),this.getc()))/(factorial(this.getc())*(this.getc()- this.getr())));
        }
        else if (this.getRu() >= 1){
            for (int n = 0 ; n <= this.getc()-1 ; n++){
                SumCalculation = SumCalculation + (1/factorial(n))*(power(this.getr(), n));
            }
            totalCalculation = SumCalculation +((1/(factorial(this.getc())))*(power(this.getr(), this.getc()))*((this.getc()*this.getMu())/((this.getc()*this.getMu())-this.getLampda())));
        }
        this.setP0(1/totalCalculation);
        return this.getP0();
    }
    @Override
    protected float calculatePn(int n){
        if ( (n >= 0)&&(n<this.getc() ) ){
            this.setPn ( (power(this.getr(), n))*(1/(factorial(n)))*(this.getP0()));
        }
        else if (n >= this.getc() ){
        this.setPn( (power(this.getr(), n))*(1/(factorial(this.getc())))*(1/(power(this.getc(), n-this.getc())))*(this.getP0()));
        }
        return this.getPn();
    }
    @Override
    public float GetExpectedCustomersAtSystem(){
        //prepare Lq
        GetExpectedCustomersAtQueue();
        this.setL( this.getLq() +this.getr());
        return this.getL();
    }
    @Override
    public float GetExpectedCustomersAtQueue(){
        this.setLq(((power(this.getr(), this.getc())*(this.getLampda())*(this.getMu()))/((factorial(this.getc()-1))*(power((this.getc()*this.getMu()-this.getLampda()), 2))))*(this.getP0()));
        return this.getLq();
    }
    @Override
    public float GetExpectedWaitingAtSystem(){
        //prepare Lq
        GetExpectedCustomersAtQueue();
        this.setW ((this.getLq())/(this.getLampda()) +1/(this.getMu()));
        return this.getW();
    }
    @Override
    public float GetExpectedWaitingAtQueue(){
        //prepare Lq
        GetExpectedCustomersAtQueue();
        this.setWq ( (this.getLq())/(this.getLampda()));
        return this.getWq();
    }  
} 