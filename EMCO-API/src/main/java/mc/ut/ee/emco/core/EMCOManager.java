package mc.ut.ee.emco.core;

import java.util.logging.Logger;

import mc.ut.ee.emco.data.CrowdCharacterization;
import mc.ut.ee.emco.reasoner.controller.BasicFuzzyController;
import mc.ut.ee.emco.reasoner.engine.FuzzyLogicEngine;
import mc.ut.ee.emco.reasoner.variables.IllegalSetException;


/**
 * @author Huber Flores
 */

public class EMCOManager extends CrowdCharacterization {
	
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			
	
	public void applyCharacterization(){
		transformMethods();
		reLoadFuzzyEngine();
	}
	
	
	public void transformMethods(){
		EMCOProfiler profiler = new EMCOProfiler();
		profiler.setCandidateComponents(getMobileComponents());
		profiler.createOffloadableComponents();
	}
	
	
	public void reLoadFuzzyEngine(){
		FuzzyLogicEngine engine = new FuzzyLogicEngine();
		BasicFuzzyController fls;
		
		engine.setSets(getVariables());
		engine.setRules(getRules());
		try {
			engine.reDefineFuzzyData();
		} catch (IllegalSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fls = engine.getBasicFuzzyController();
		if (fls!=null){
			/**Usage:
			fls.fuzzify("BANDWIDTH", 100);  
			fls.fuzzify("CPULOAD_INSTANCE", 10);
		    System.out.println("Delegate with a grade of truth to: " + fls.defuzzify("OFFLOAD"));*/
		}
		
	}
	


}


