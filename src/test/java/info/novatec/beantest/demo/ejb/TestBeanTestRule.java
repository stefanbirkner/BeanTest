/*
 * Bean Testing.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.novatec.beantest.demo.ejb;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import info.novatec.beantest.junit.BeanTestRule;
import info.novatec.beantest.demo.entities.MyEntity;
import info.novatec.beantest.demo.exceptions.MyException;

import org.junit.Test;
import org.junit.Rule;

/**
 * This test verifies that the {@code BeanTestRule} is a proper
 * replacement for {@code BaseBeanTest}
 *
 * @author Stefan Birkner (mail@stefan-birkner.de)
 */ 
public class TestBeanTestRule {
    
    @Rule
    public final BeanTestRule beanTest = new BeanTestRule();
    
    @Test
    public void shouldInjectEJBAsCDIBean() {
        MyEJBService myService = beanTest.getBean(MyEJBService.class);
        //An Entity should be persisted and you should see a message logged in the console.
        myService.callOtherServiceAndPersistAnEntity();
        //Let's create a reference of another EJB to query the database.
        MyOtherEJBService myOtherService = beanTest.getBean(MyOtherEJBService.class);
        
        assertThat(myOtherService.getAllEntities(), hasSize(1));

    }
}