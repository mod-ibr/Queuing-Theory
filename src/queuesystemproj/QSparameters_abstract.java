/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuesystemproj;

import queuesystemproj.statusFlags.FlagStates;

/**
 *
 * @author 20114
 */
public abstract class QSparameters_abstract {
    
    private float Lampda;
    private float Mu;
    
    protected FlagStates flagState = new FlagStates();
    
    public void setLampda (float lampda){
        this.Lampda=lampda;
    }
    public void setMu (float mu){
        this.Mu=mu;
    }
    
    public float getLampda (){
        return this.Lampda;
    }
    public float getMu (){
        return this.Mu;
    }
  
}
