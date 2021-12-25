/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuesystemproj;

import Frames.QS_HomePage_frame;
import queuesystemproj.Deterministic.*;
import queuesystemproj.stocustic.*;

/**
 *
 * @author 20114
 */
public class QueueSystemProj {

    /**
     * @param args the command line arguments
     */
    public static Frames.QS_HomePage_frame homePage_Frame;
    public static void main(String[] args) {
        // TODO code application logic here
  /** 
    *
    *      MMcK M = new MMcK(1, (float)1/6, 3,7);
    *      System.out.println("L : "+ M.GetExpectedCustomersAtSystem());
    *      System.out.println("Lq : "+M.GetExpectedCustomersAtQueue());
    *      System.out.println("W : "+M.GetExpectedWaitingAtSystem());
    *      System.out.println("Wq : "+M.GetExpectedWaitingAtQueue());
    *      
    *      System.out.println("Ru : "+M.getRu());
    *      System.out.println("P0 : "+M.getP0());
    *      
    *      System.out.println("Ci : "+M.getCi());
    *
    *      DD1K_abstract DD1k_obj = new DD1K_LampdaGreater(0.25f, 0.125f,5);
    *      System.out.println(DD1k_obj.getTi());
    *      float [] arr = new float [40];
    *      System.out.println(1/DD1k_obj.getLampda());
    *      for (int i =0 ; i < 40 ; i++){
    *          arr[i]=DD1k_obj.seriesDotsToPlot(i);
    *      }
    *      for (int i =0 ; i < 40 ; i++){
    *          System.out.println("list ["+i+"]  :  "+arr[i]);
    *      }
    **/
        homePage_Frame = new QS_HomePage_frame();
        homePage_Frame.setVisible(true);
        MMcK M = new MMcK(1, 0.1666666667f, 3, 7);
        System.out.println("r : " + M.getr());
        System.out.println("Ru : " + M.getRu());
        System.out.println("P0 : " + M.calculateP0());
        System.out.println("Lq : " + M.GetExpectedCustomersAtQueue());
        System.out.println("L : " + M.GetExpectedCustomersAtSystem());
        System.out.println("Pk : "+ M.calculatePk());
        System.out.println("Lambda_Dash : " + M.getLampdaDash());
        System.out.println("W : " + M.GetExpectedWaitingAtSystem());
        System.out.println("Wq : " + M.GetExpectedWaitingAtQueue());
    } 
}
