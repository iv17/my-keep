package rs.ac.uns.ftn.mykeepserver;

import org.junit.Test;

import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class GetterSetterTest {

	// The package to be tested
	private String modelPackageName = "rs.ac.uns.ftn.mykeepserver.model";
	private String dtoPackageName = "rs.ac.uns.ftn.mykeepserver.web.dto";

	@Test
	public void validate() {
		Validator validator = ValidatorBuilder.create()
				.with(new SetterMustExistRule(), new GetterMustExistRule())
				.with(new NoPublicFieldsExceptStaticFinalRule())
				.with(new SetterTester(), new GetterTester())
				.build();
		validator.validate(modelPackageName);
		validator.validate(dtoPackageName);

	}
}
