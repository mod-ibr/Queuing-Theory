/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuesystemproj.Deterministic;


public interface DD1K1_interface {
    
    abstract public void getLampda (float lampda);
    abstract public void getMu (float mu);
    abstract public void getK (int k);
    abstract public float calcTi ();
    abstract public float calculateTiMin ();
    abstract public int   calculateCustomersAtTimeT(float time);
    abstract public float calculateWaitingTimeAtCustomerN(int customerN);
}
