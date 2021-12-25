/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuesystemproj.stocustic;
import java.math.*;
/**
 *
 * @author 20114
 */
public abstract class MM_abstract extends queuesystemproj.QSparameters_abstract{
    private float P0;
    private float Pn;
    private float L;
    private float Lq;
    private float W;
    private float Wq;
    private float Pk;
    protected float Ru;

    public MM_abstract() {
        this.setLampda(0.0f);
        this.setMu(0.0f);
        this.flagState.resetState();
    }
    public MM_abstract(float Lampda,float Mu){
        this.setLampda(Lampda);
        this.setMu(Mu);
    }
    protected void updateRu(){
        this.Ru = this.getLampda()/this.getMu();
    }
    
    public float getRu(){
        return this.Ru;
    }
    public void setP0(float P0){
        this.P0 = P0;
    }
    public float getP0(){
        return this.P0;
    }
    public float getPn(){
        return this.Pn;
    }
    public void setPn(float Pn){
        this.Pn = Pn;
    }
    public void setL(float L){
        this.L = L;
    }
    public float getL(){
        return this.L;
    }
    public void setLq(float Lq){
        this.Lq = Lq;
    }
    public float getLq(){
        return this.Lq;
    }
    public void setW(float W){
        this.W = W;
    }
    public float getW(){
        return this.W;
    }
    public void setWq(float Wq){
        this.Wq = Wq;
    }
    public float getWq(){
        return this.Wq;
    }
    abstract protected float calculateP0();
    abstract protected float calculatePn(int n);
    abstract public float GetExpectedCustomersAtSystem();
    abstract public float GetExpectedCustomersAtQueue();
    abstract public float GetExpectedWaitingAtSystem();
    abstract public float GetExpectedWaitingAtQueue();
    
    protected int factorial(int f){
        return (f>=2)?(f*factorial(f-1)):(1);
    }
    protected float power(float base , float power){
        return (float)(Math.pow(base, power));
    }
}
