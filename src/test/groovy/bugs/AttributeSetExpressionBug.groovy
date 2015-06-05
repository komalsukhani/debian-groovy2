/*
 * Copyright 2003-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @author Pilho Kim
 * @version $Revision$
 */

package groovy.bugs

class AttributeSetExpressionBug extends GroovyTestCase {
    void testAttributeSetAccess() {
        def a = new HasStaticFieldSomeClass()
        a.name = a.name * 3
        assert a.@name == "gettter" * 3 
        assert a.name == "gettter"

        new HasStaticFieldSomeClass().@name = "changed bar"
        assert( HasStaticFieldSomeClass.class.@name == "changed bar" )

        HasStaticFieldSomeClass.class.@name = "changed static bar"
        assert( HasStaticFieldSomeClass.class.@name == "changed static bar" )
    }
}

class HasStaticFieldSomeClass {
    static String name = "bar" 
    static String getName() {
        return "gettter"
    }
}