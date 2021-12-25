
/**
 * 
 * @author  : Ayman_Saleh
 * @date    : 12/5/2021
 * @version : 2.1.0
 * File     : FlagStates Class
 * 
 **/

package queuesystemproj.statusFlags;

public class FlagStates {
    private int lampdaError;
    private int muError;
    private int timeError;
    private int initialMembersError;
    private int customerNumberError;
    private int queueMaxMember;
    private int steady;
    private int parallelServersError;
    private int StocusticSpecialError;
    private int lengthOfPlotSegmentsError;

    private String msgErrorStates;

    public FlagStates() {
        this.lampdaError =  0;
        this.muError =  0;
        this.timeError =  0;
        this.initialMembersError=0;
        this.customerNumberError=0;
        this.queueMaxMember = 0;
        this.parallelServersError=0;
        this.StocusticSpecialError=0;
        this.lengthOfPlotSegmentsError=0;
        this.msgErrorStates = "";
    }
    public void setLampdaError(){
        this.msgErrorStates += "Lampda,";
        System.out.println("#Error In Lampda");
        this.lampdaError=1;
    }
    public void setMuError(){
        this.msgErrorStates += "Mu,";
        System.out.println("#Error In Mu");
        this.muError=1;
    }
    public void setTimeError(){
        msgErrorStates += "Time,";
        System.out.println("#Error In Time");
        this.timeError=1;
    }
    public void setCustomerNumberError(){
        this.msgErrorStates += "N,";
        System.out.println("#Error In Customer Number");
        this.customerNumberError=1;
    }
    public void setQueueMaxMemberError(){
        this.msgErrorStates += "K";
        System.out.println("#Error In Customer QueueMaxMember");
        this.queueMaxMember=1;
    }
    public void setSteadyState(){
        this.steady=1;
    }
    public void setTransState(){
        this.steady=0;
    }
    public void setParallelServerError(){
        msgErrorStates += ",ParallelServerError";
        System.out.println("#Error In ParallelServerError");
        this.parallelServersError=1;
    }
    public void setInitialMemberError(){
       msgErrorStates += ",InitialMembers";
       System.out.println("#Error In InitialMembers");
       this.initialMembersError=1;
    }
    public void setLengthOfPlotSegmentsError(){
       msgErrorStates += ",Number Of Plot Segments";
       System.out.println("#Error In lengthOfPlotSegmentsError");
       this.lengthOfPlotSegmentsError=1;
    }
    public void setStocusticSpecialError(){
       msgErrorStates += "\nInStocustic Models Lampda Must be greater than Mu. \nAnd System Capacity Greater than No. Servers";
       System.out.println("#Error In StocusticSpecialError");
       this.initialMembersError=1;
    }
    public int getLampdaError(){
        return this.lampdaError;
    }
    public int getMuError(){
        return this.muError;
    }
    public int getTimeError(){
        return this.timeError;
    }
    public int getInitialMemberError(){
        return this.initialMembersError;
    }
    public int getQueueMaxMemberError(){
        return this.queueMaxMember;
    }
    public int getCustomerNumberError(){
        return this.customerNumberError;
    }
    public int getSteadyState(){
        return this.steady;
    }
    public int getParallelServersError(){
        return this.parallelServersError;
    }
    public int getLengthOfPlotSegmentsError(){
        return this.lengthOfPlotSegmentsError;
    }
    
    public void resetState(){
        this.lampdaError =  0;
        this.muError =  0;
        this.timeError =  0;
        this.initialMembersError=0;
        this.customerNumberError=0;
        this.queueMaxMember=0;
        this.parallelServersError=0;
        this.StocusticSpecialError=0;
        this.lengthOfPlotSegmentsError=0;
        this.steady = 2;
        this.msgErrorStates = "";
        System.out.println("All Errors Cleared!");
    }
    public String getMSGErrorStates(){
        return ((this.msgErrorStates.equals(""))?("Clear"):("Error Detected at \n"+this.msgErrorStates));
        
    }
}