package mc.ut.ee.emco.core;

import java.util.List;
import java.util.logging.Logger;

public class EMCOProfiler {
	
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private List<String> candidateComponents = null;
	
	
	public void createOffloadableComponents(){
		
		if (candidateComponents!=null){
			log.info("Mobile components transformed");
			
			
		}
	
	}
	
	
	public void setCandidateComponents(List<String> toOffload){
		this.candidateComponents = toOffload;
	}

}
