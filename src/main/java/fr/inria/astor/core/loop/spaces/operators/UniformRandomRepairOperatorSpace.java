package fr.inria.astor.core.loop.spaces.operators;

import fr.inria.astor.core.entities.SuspiciousModificationPoint;
import fr.inria.astor.core.setup.ConfigurationProperties;
import fr.inria.astor.core.setup.RandomManager;

/**
 * Represents an strategy to randomly select operator from the operator space
 * passed as argument.
 * 
 * @author Matias Martinez, matias.martinez@inria.fr
 *
 */
public class UniformRandomRepairOperatorSpace extends OperatorSelectionStrategy {

	public UniformRandomRepairOperatorSpace(OperatorSpace space) {
		super(space);
	}

	/*@Override
	public AstorOperator getNextOperator() {
		AstorOperator[] operators = getOperatorSpace().values();
		return operators[RandomManager.nextInt4Operaion(operators.length)];
	}*/
	@Override
	public AstorOperator getNextOperator(int id) {
		AstorOperator[] operators = getOperatorSpace().values();
		return operators[RandomManager.nextInt4Operaion(operators.length,id)];
	}
/*
	@Override
	public AstorOperator getNextOperator2(SuspiciousModificationPoint modificationPoint) {
		AstorOperator[] operators = getOperatorSpace().values();
		return operators[RandomManager.nextInt4Operaion2(operators.length)];
	}*/

	/**
	 * Given a suspicious modif point, the method randomly decides to mutate it according to its suspicious
	 * @param modificationPoint
	 * @return
	 */
	protected boolean mutateModificationPoint(SuspiciousModificationPoint modificationPoint) {

		if (ConfigurationProperties.getPropertyBool("probagenmutation")) {
			double randomVal = RandomManager.nextDouble4Operation();
			double suspiciousValue = modificationPoint.getSuspicious().getSuspiciousValue();
			return ((suspiciousValue * ConfigurationProperties.getPropertyDouble("mutationrate")) 
					>= randomVal);

		}
		//By default, we mutate the point
		return true;
	}

	@Override
	public AstorOperator getNextOperator(SuspiciousModificationPoint modificationPoint) {
		
		//If we decide to mutate the point according to its suspiciousness value
		if(mutateModificationPoint(modificationPoint)){
			//here, this strategy does not take in account the modifpoint to select the op.
			//return this.getNextOperator();
			//return this.getNextOperator();
			return this.getNextOperator(modificationPoint.getProgramVariant().getId());
		}
		else{
			//We dont mutate the modif point
			return null;
		}
		
		
	}
	/*@Override
	public AstorOperator getNextOperator2(SuspiciousModificationPoint modificationPoint) {

		//If we decide to mutate the point according to its suspiciousness value
		if(mutateModificationPoint(modificationPoint)){
			//here, this strategy does not take in account the modifpoint to select the op.
			//return this.getNextOperator();
			return this.getNextOperator2();
		}
		else{
			//We dont mutate the modif point
			return null;
		}


	}*/

	/*@Deprecated
	public AstorOperator getNextOperator(double suspiciousValue) {
		double randomVal = RandomManager.nextDouble4Operation();
		if (!ConfigurationProperties.getPropertyBool("probagenmutation")
				|| (suspiciousValue * ConfigurationProperties.getPropertyDouble("mutationrate")) >= randomVal) {
			return this.getNextOperator();
		}
		// As we use a mutation rate to indicate the prob of mutation, if there
		// is not mutation , return null
		return null;
	}*/
}
