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

class Groovy3831Bug extends GroovyTestCase {
    void testClosureDefinitionInSpecialCallsInConstructorsV1() {
        def test = new Test3831V1('hello', ["world"])
        assert test.string == 'hello'
        assert test.uris.size() == 1
    }

    void testClosureDefinitionInSpecialCallsInConstructorsV2() {
        // just loading of class is test enough as it was giving VerifyError earlier.
        new Test3831V2()
    }
}

class Test3831V1 {
    String string
    URI[] uris
 
    public Test3831V1(String string, URI[] uris) {
        this.string = string
        this.uris = uris
    }

    public Test3831V1(String string, List uris) {
        this(string, uris.collect { new URI(it) } as URI[])
    }
}

class Test3831V2 {
    public Test3831V2(cl) {}

    public Test3831V2() {
        this({1})
    }
}