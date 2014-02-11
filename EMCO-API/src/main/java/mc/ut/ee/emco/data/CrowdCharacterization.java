package mc.ut.ee.emco.data;

import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * @author Huber Flores
 */

public abstract class CrowdCharacterization  {

	
	@Expose
	private String mobileApplication;
	@Expose
	private String deviceID;
	@Expose
	private String description;
	@Expose
	private List<String> candidateComponents; 
	@Expose
	private List<CrowdSet> variables; 
	@Expose
	private List<CrowdRule> rules;

	//@Expose
	//private List<ExecutionPlan> plans;
	
	public List<String> getMobileComponents() {
	    return candidateComponents;
	}
	
	public String getMobileApplication(){
		return mobileApplication;
	}
	
	public List<CrowdSet> getVariables(){
		return variables;
	}
	
	public List<CrowdRule> getRules(){
		return rules;
	}

	
}




