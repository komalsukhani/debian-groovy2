/*
 * Copyright 2003-2010 the original author or authors.
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

class Groovy4170Bug extends GroovyTestCase {
    void testClosureCurrying() {
        new CurryFoo4170().bar()
    }
}

class CurryFoo4170 {
     protected void foo1(String s, int i) {
         println 'Hurray! Foo1 can be curried.'
     }
     private void foo2(String s, int i) {
         println 'Hurray! Foo2 can be curried.'
     }
     public void bar() {
         this.&foo1.curry('anything', 1).call()
         this.&foo2.curry('anything', 1).call()
     }
}