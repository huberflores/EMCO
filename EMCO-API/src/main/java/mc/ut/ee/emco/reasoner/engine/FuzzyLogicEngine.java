package mc.ut.ee.emco.reasoner.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import mc.ut.ee.emco.data.CrowdRule;
import mc.ut.ee.emco.data.CrowdSet;
import mc.ut.ee.emco.reasoner.controller.BasicFuzzyController;
import mc.ut.ee.emco.reasoner.controller.FuzzyOp;
import mc.ut.ee.emco.reasoner.deffuzifyer.CentroidMethod;
import mc.ut.ee.emco.reasoner.functions.TrapezoidalMembershipFunction;
import mc.ut.ee.emco.reasoner.modifier.FzSet;
import mc.ut.ee.emco.reasoner.variables.IllegalSetException;
import mc.ut.ee.emco.reasoner.variables.LinguisticVariable;


/**
 * @author Huber Flores
 */

public class FuzzyLogicEngine{

	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private List<CrowdRule> rules = null;
	private List <CrowdSet> variables = null;
	private ArrayList<FzSet> fz;
	
	private FzSet localProcessing;
	private FzSet remoteProcessing;
	
	private BasicFuzzyController fls = null;
	
	
	public void reDefineFuzzyData() throws IllegalSetException{
		log.info("Reloading fuzzy data");
		BasicFuzzyController bfc = new BasicFuzzyController();
		
	
		createVariables(bfc);
		createRules(bfc);
		
	     
	    CentroidMethod cm=new CentroidMethod();
	    cm.setSamplesPoints(10);
	    bfc.setDefuzzifyerMethod(cm);
	    
	 
	    fls = bfc;
	   	
	}
	
	
	public void createVariables(BasicFuzzyController bfc) throws IllegalSetException{
		if (variables!=null){
			
			LinguisticVariable lv;
			FzSet fuzzy;
			fz = new ArrayList<FzSet>();
			
			for (int i=0; i<variables.size(); i++){
				//log.info("Number of variables " + variables.get(i).getLinguisticVariable());
				
				lv = new LinguisticVariable(variables.get(i).getLinguisticVariable());
				for (int j=0; j<variables.get(i).getLinguisticTerms().size(); j++){
					//log.info("Number of terms " + variables.get(i).getLinguisticTerms().get(j).getId());
					
					fuzzy = lv.addSet(variables.get(i).getLinguisticTerms().get(j).getId(), new TrapezoidalMembershipFunction
							(variables.get(i).getLinguisticTerms().get(j).getValue1(),
							 variables.get(i).getLinguisticTerms().get(j).getValue2(),
							 variables.get(i).getLinguisticTerms().get(j).getValue3(),
							 variables.get(i).getLinguisticTerms().get(j).getValue4()));
					fuzzy.setLabel(variables.get(i).getLinguisticTerms().get(j).getId());
			
					//log.info("Fuzzy sets as objects are stored in fz");
					fz.add(fuzzy);
					
				}
				
				bfc.addVariable(lv);
				
			}
			
		
			LinguisticVariable decision = new LinguisticVariable("OFFLOAD");
			localProcessing = decision.addSet("Local processing", new TrapezoidalMembershipFunction(0, 12, 24, 48));
			remoteProcessing = decision.addSet("Remote processing", new TrapezoidalMembershipFunction(36, 60, 72, 84));
			bfc.addVariable(decision);
			
			
		}
	}
	
	
	public void createRules(BasicFuzzyController bfc){
		if (rules!=null){
			for (int i=0; i<rules.size(); i++){
				FzSet operand1 =getFz(rules.get(i).getOperand1());
				FzSet operand2 =getFz(rules.get(i).getOperand2());
				
				if ((operand1!=null) && (operand2!=null)){
					bfc.addRule(FuzzyOp.and(getFz(rules.get(i).getOperand1()), getFz(rules.get(i).getOperand2())), getDecision(rules.get(i).getResult()));
				}
				
				
			}
		}
	}
	
	
	public FzSet getDecision(String result){
		if (result.trim().equals("offload")) {
			return remoteProcessing;
		}else{
			return localProcessing;
		}
	}
	
	public FzSet getFz(String operand){
		
		for (int i=0; i<fz.size(); i++){
			if (operand.trim().equals(fz.get(i).getLabel())){
				return fz.get(i);
			}
		}
		return null;
	}
	
	
	
	public void setSets(List<CrowdSet> dataSet){
		this.variables = dataSet;
	}	
	
	
	public void setRules(List<CrowdRule> dataRules){
		this.rules = dataRules;
	}
	
	public BasicFuzzyController getBasicFuzzyController(){
		return fls;
	}
	
}
