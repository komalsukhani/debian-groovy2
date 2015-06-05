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
package org.codehaus.groovy.classgen.asm

/**
 * Test the utility class InstructionSequence for testing bytecode instructions generated by Groovy
 *
 * @author Guillaume Laforge
 */
class InstructionSequenceHelperClassTest extends GroovyTestCase {

    static final seq = new InstructionSequence(instructions: [
                "A 1",
                "B 2",
                "C",
                "D 3",
                "E 4",
                "F"
        ])

    void testSequence() {
        assert seq.hasSequence([
                "A 1",
                "B 2",
                "C",
                "D 3",
                "E 4",
                "F"
        ])

        assert seq.hasSequence([
                "A",
                "B",
                "C",
                "D",
                "E",
                "F"
        ])

        assert seq.hasSequence([
                "A 1",
                "C",
                "E 4",
        ])

        assert seq.hasSequence([
                "A",
                "E 4",
        ])
    }

    void testStrictSequence() {
        assert seq.hasStrictSequence([
                "A 1",
                "B 2",
                "C",
                "D 3",
                "E 4",
                "F"
        ])

        assert seq.hasStrictSequence([
                "B 2",
                "C",
                "D 3",
        ])

        assert seq.hasStrictSequence([
                "A",
                "B",
                "C",
                "D",
                "E",
                "F"
        ])

        assert seq.hasStrictSequence([
                "B",
                "C",
                "D",
        ])
    }

    void testStrictSequenceWithBytecodeExample() {
        def instrs = new InstructionSequence(instructions: [
                "ILOAD 9",
                "ILOAD 11",
                "IADD"
        ])

        assert instrs.hasStrictSequence([
                "ILOAD",
                "ILOAD",
                "IADD"
        ])
    }
    
    void testStrictSequencePatternPrefixRepetition() {
        def instrs = new InstructionSequence(instructions: [
                "A", "A", "B",
                "A", "A", "C",
        ])

        assert instrs.hasStrictSequence([
                "A", "A", "B"
        ])
        assert instrs.hasStrictSequence([
                "A", "A", "C"
        ])
    }
    
}
