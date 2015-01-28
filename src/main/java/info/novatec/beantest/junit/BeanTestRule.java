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

package info.novatec.beantest.junit;

import java.lang.annotation.Annotation;
import org.junit.rules.ExternalResource;
import info.novatec.beantest.api.BeanProviderHelper;


/**
 * Provides access to beans that are managed by
 * {@link BeanProviderHelper}.
 * <p>Add the rule to your test class and retrieve beans by calling
 * {@link #getBean(Class, Annotation...)}
 * <pre>
 * public class YourTest {
 *   @Rule
 *   public final BeanTestRule beanTest = new BeanTestRule();
 * 
 *   @Test
 *   public void should_do_something() {
 *     YourClass bean = beanTest.getBean(YourClass.class);
 *     ...
 *   }
 * }
 * </pre>
 *
 * @author Stefan Birkner (mail@stefan-birkner.de)
 */
public class BeanTestRule extends ExternalResource {
    
    private BeanProviderHelper bm;
    
    @Override
    protected void before() {
        bm = BeanProviderHelper.getInstance();
    }

    @Override
    protected void after() {
        bm.shutdown();
    }
    
    public <T> T getBean(Class<T> beanClass, Annotation... qualifiers) {
        return bm.getBean(beanClass, qualifiers);
    }
    
}
