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

def expectations = [
        concat: [String],
        zero: [],
        two: [String, Integer],
        three: [String, Integer, Date]
]

afterMethodCall { call ->
    expectations.each { name, expectation ->
        if (call.methodAsString == name && argTypesMatches(call, expectation as Class[])) {
            addStaticTypeError "Method [$name] with matching arguments found: ${expectation.size()}", call
        }
    }
}