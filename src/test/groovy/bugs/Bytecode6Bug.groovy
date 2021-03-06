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
package groovy.bugs

/**
 * @version $Revision$
 */
class Bytecode6Bug extends GroovyTestCase {

    void testPostFixReturn() {
        def i = 1
        def closure = { i++ }
        def value = closure()
        
        assert value == 1
        assert i == 2
    }
    
    void testPreFixReturn() {
        def i = 1
        def closure = { return ++i }
        def value = closure()
        
        assert value == 2
        assert i == 2
    }
}