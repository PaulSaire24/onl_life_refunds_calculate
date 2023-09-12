package com.bbva.rbvd.lib.r401;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/RBVDR401-app.xml",
		"classpath:/META-INF/spring/RBVDR401-app-test.xml",
		"classpath:/META-INF/spring/RBVDR401-arc.xml",
		"classpath:/META-INF/spring/RBVDR401-arc-test.xml" })
public class RBVDR401Test {

	@Spy
	private Context context;

	@Resource(name = "rbvdR401")
	private RBVDR401 rbvdR401;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.rbvdR401;
		if(this.rbvdR401 instanceof Advised){
			Advised advised = (Advised) this.rbvdR401;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeTest(){
		rbvdR401.execute();
		Assert.assertEquals(0, context.getAdviceList().size());
	}
	
}
