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
public class MMcK extends MM_abstract{
    
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
    *    K
    *
    */

    private float r;
    private int c;
    private int K;
    private float Pk;
    private float Lampda_dash;

    
    public MMcK( float Lampda, float Mu, int c, int K) {
        super(Lampda, Mu);
        this.setK(K);
        this.setc(c);
        this.updater();
        this.updateRu();
        this.calculateP0();
        this.calculatePk();
        this.setLampdaDash();
    }
    public MMcK() {
        super(0, 0);
        this.updater();
        this.updateRu();
        this.setK(0);
        this.setc(0);
    }
    @Override
    public void updateRu(){
        this.Ru = ( this.getr()/this.getc());
    }
    public int getK(){
        return this.K ;
    }
    public float getPk(){
        return this.Pk;
    }
    public void setK(int K){
        this.K = K;
    }
    public void setc(int c){
        this.c = c;
    }
    public int getc(){
        return this.c;
    }
    private void updater(){
        this.r = (this.getLampda()/this.getMu());
    }
    
    public float getr(){
        return this.r;
    }
    public float getLampdaDash(){
        return this.Lampda_dash ;
    }
    public void setLampdaDash(){
        this.Lampda_dash = this.getLampda()*(1- calculatePk());
    }
    @Override
    public float calculateP0() {

        float sumCalculation = 0;
        float totalCalculation = 0;
        if (this.getRu() != 1) {
            for (int n = 0; n <= this.getc() - 1; n++) {
                sumCalculation = sumCalculation + (power(this.getr(), n)) / (factorial(n));
            }
            System.out.println("sumCalculation : " + sumCalculation);
            float func = ((power(this.getr(), this.getc()) / (factorial(this.getc())))
                    * ((1 - power(this.getRu(), (this.getK() - this.getc() + 1))) / (1 - this.getRu())));
            System.out.println("func : " + func);
            totalCalculation = sumCalculation + func;
        } else if (this.getRu() == 1) {
            for (int n = 0; n <= this.getc() - 1; n++) {
                sumCalculation = sumCalculation + ((power(this.getr(), n)) / (factorial(n)));
            }
            totalCalculation = sumCalculation + ((power(this.getr(),
                    this.getc()
            )) / (factorial(this.getc()))) * (this.getK() - this.getc() + 1);
        }
        System.out.println("totalCalculation = " + totalCalculation);
        this.setP0(1 / totalCalculation);
        return this.getP0();
    }
    @Override
    protected float calculatePn(int n){
        if ((n>=0) && (n<this.getc())){
            this.setPn( ((power(this.getr(),n))/(factorial(n)))*this.getP0());
        }
        else if ((n>=this.getc())&&(n<=this.getK())){
            this.setPn( (power(this.getr(),n))*(1/(power(this.getc(),n-this.getc())*factorial(this.getc())))*(this.getP0()));
        }
        return this.getPn();
    }
    @Override
    public float GetExpectedCustomersAtSystem(){
        float sumCalculation = 0;
        //preparing Lq
        GetExpectedCustomersAtQueue();
        for (int n = 0 ; n <= this.getc()-1 ; n++){
            sumCalculation = sumCalculation + (this.getc() - n)*(power(this.getr(),n))/(factorial(n));
        }
        this.setL(this.getLq() + this.getc() - this.getP0()*sumCalculation);
        return this.getL();
    }
    @Override
    public float GetExpectedCustomersAtQueue(){
        float part1;
        float part2;
        part1 = ((this.getRu()*power(this.getr(), this.getc())*this.getP0())/(factorial(this.getc())*power(1-this.getRu(),2)));
        part2 = (1-power(this.getRu(),this.getK()-this.getc()+1)-(1-this.getRu())*(this.getK()-this.getc()+1)*power(this.getRu(),this.getK()-this.getc()));
        this.setLq(part1*part2);
        return this.getLq();
    }
    @Override
    public float GetExpectedWaitingAtSystem(){
        //prepare Lampda Dash
        setLampdaDash();
        //prepare L
        GetExpectedCustomersAtSystem();
        this.setW( this.getL()/this.getLampdaDash());
        return this.getW();
    }
    @Override
    public float GetExpectedWaitingAtQueue(){
        //prepare Lampda Dash
        setLampdaDash();
        //prepare Lq
        GetExpectedCustomersAtQueue();
        this.setWq(this.getLq()/this.getLampdaDash());
        return this.getWq();
    }
    public float calculatePk() {
        /**
         * Q:
         * Pk formula in MMcK as it is in Pk formula at MM1K.
        */
        if (this.getRu() != 1) {
            return (power(this.getRu(), this.getK()) * ((1 - this.getRu())
                    / (1 - power(this.getRu(), (this.getK() + 1)))));
        } else if (this.getRu() != 1) {
            return (1 / (this.getK() + 1));
        }
        return -1;
    }
}