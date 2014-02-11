package mc.ut.ee.emco.util;


import mc.ut.ee.emco.data.CrowdCharacterization;
import mc.ut.ee.emco.reasoner.controller.BasicFuzzyController;
import mc.ut.ee.emco.reasoner.controller.FuzzyOp;
import mc.ut.ee.emco.reasoner.deffuzifyer.CentroidMethod;
import mc.ut.ee.emco.reasoner.functions.FunctionException;
import mc.ut.ee.emco.reasoner.functions.TrapezoidalMembershipFunction;
import mc.ut.ee.emco.reasoner.modifier.FzSet;
import mc.ut.ee.emco.reasoner.variables.IllegalSetException;
import mc.ut.ee.emco.reasoner.variables.LinguisticVariable;


/**
 * @author Huber Flores
 *
 */

public class ExampleFuzzyInference extends CrowdCharacterization {

	/**
	 * Mobile
	 */
	//Bandwidth
	private FzSet speedSlow;
	private FzSet speedNormal;
	private FzSet speedFast;
	
	//CPU Load
	private FzSet loadLow;
	private FzSet loadNormal;
	private FzSet loadHigh;
	
	
	/**
	 * Cloud
	 */
	//Instance server 
	private FzSet connectionsLow;
	private FzSet connectionsNormal;
	private FzSet connectionsHigh;
	
	
	
	//Offload Decision
	private FzSet remoteProcessing;
	private FzSet localProcessing;
	
	
	public ExampleFuzzyInference(){
		
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args)  throws FunctionException {
		BasicFuzzyController bfc = new BasicFuzzyController();
		ExampleFuzzyInference fle = new ExampleFuzzyInference();
		
		fle.createVariables(bfc);
		fle.createRules(bfc);
		
		//Here input to value is introduced
		bfc.fuzzify("BANDWIDTH", 100);  
		bfc.fuzzify("CPULOAD", 10);
		bfc.fuzzify("CONNECTIONS", 150);
	     
	     
	    CentroidMethod cm=new CentroidMethod();
	    cm.setSamplesPoints(10);
	    bfc.setDefuzzifyerMethod(cm);
	     

	    System.out.println("Delegate with a grade of truth to: " + bfc.defuzzify("OFFLOAD"));

	}
	
	
	public void createVariables(BasicFuzzyController bfc) throws FunctionException {
				
			/**possible values - Fuzzy sets*/
		    try {
		    	/**
				 * Mobile variables
				 */
		    	LinguisticVariable mv1 = new LinguisticVariable("BANDWIDTH");
				speedSlow = mv1.addSet("Slow", new TrapezoidalMembershipFunction(0, 5, 11, 16));
				speedNormal = mv1.addSet("Normal", new TrapezoidalMembershipFunction(11, 38, 54, 92));
				speedFast = mv1.addSet("Fast", new TrapezoidalMembershipFunction(54, 110, 160, 180)); //trapezoidal case - right
				bfc.addVariable(mv1);
				
				LinguisticVariable mv2 = new LinguisticVariable("CPULOAD");
				loadLow = mv2.addSet("CPU Low", new TrapezoidalMembershipFunction(0, 9, 18, 36));
				loadNormal = mv2.addSet("CPU Normal", new TrapezoidalMembershipFunction(27, 45, 54, 72));
				loadHigh = mv2.addSet("CPU High",new TrapezoidalMembershipFunction(63, 81, 90, 100));
				bfc.addVariable(mv2);
				
				//n variables can be added (e.g. Memory, etc.)
				
				/**
	u8			 * Cloud variables
				 */
				
				//LinguisticVariable cv1 = new LinguisticVariable("CONNECTIONS");
				//connectionsLow = cv1.addSet("Low concurrent users", new TrapezoidalMembershipFunction(0, 12, 24, 48));
				//connectionsNormal = cv1.addSet("Normal concurrent users", new TrapezoidalMembershipFunction(36, 60, 72, 96));
				//connectionsHigh = cv1.addSet("High concurrent users",new TrapezoidalMembershipFunction(84, 108, 120, 132));
				//bfc.addVariable(cv1);
				
				//m variables can be added (e.g. number of servers, etc.)
				
				
				/**
				 * Predictable variables
				 */
				LinguisticVariable decision = new LinguisticVariable("OFFLOAD");
				localProcessing = decision.addSet("Local processing", new TrapezoidalMembershipFunction(0, 12, 24, 48));
				remoteProcessing = decision.addSet("Remote processing", new TrapezoidalMembershipFunction(36, 60, 72, 84));
				bfc.addVariable(decision);
				
				
			} catch (IllegalSetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
			//System.out.println("Variables added to the Model");
			
	}		
	


	public void createRules(BasicFuzzyController bfc){
		 bfc.addRule(FuzzyOp.and(speedSlow, loadLow), localProcessing);
		 bfc.addRule(FuzzyOp.and(speedSlow, loadNormal), localProcessing);
		 bfc.addRule(FuzzyOp.and(speedSlow, loadHigh), localProcessing);
		 bfc.addRule(FuzzyOp.and(speedNormal, loadLow), remoteProcessing);
		 bfc.addRule(FuzzyOp.and(speedNormal, loadNormal), remoteProcessing);
		 bfc.addRule(FuzzyOp.and(speedFast, loadLow), remoteProcessing);
		 bfc.addRule(FuzzyOp.and(speedFast, loadNormal), remoteProcessing);
		 bfc.addRule(FuzzyOp.and(speedFast, loadLow), remoteProcessing);
		 
		 
		 		 
	}

}
