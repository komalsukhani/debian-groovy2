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
package org.codehaus.groovy.ast.source

import org.codehaus.groovy.control.*
import org.codehaus.groovy.control.io.*
import org.codehaus.groovy.ast.stmt.*

abstract class SourceBaseTestCase extends GroovyTestCase {

    private classNode
    public classNode() {
      if (classNode!=null) return node
      def cu = new CompilationUnit(null,null, new GroovyClassLoader(this.class.classLoader))
      def source = new StringReaderSource(script,cu.configuration)
      def su = cu.addSource(new SourceUnit("Script_"+this.name, source, cu.configuration, cu.classLoader, cu.errorCollector))
      cu.compile(Phases.CONVERSION)
      classNode = cu.firstClassNode
      return classNode
    }
    
    def sourceInfo(expression) {
      return [  expression.lineNumber,
                expression.columnNumber,
                expression.lastLineNumber,
                expression.lastColumnNumber 
             ]
    }
    
    def statements(String method="run") {
      def ret = classNode().getMethod(method).code
      if (ret instanceof BlockStatement) return ret.statements
      if (ret==null) return null
      return [ret]
    }
}