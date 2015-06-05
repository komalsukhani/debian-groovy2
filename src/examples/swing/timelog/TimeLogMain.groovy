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
package groovy.swing.timelog

import groovy.swing.SwingBuilder

SwingBuilder swing = new SwingBuilder();
swing.lookAndFeel('system')

swing.model = new TimeLogModel()

swing.actions() {
    action(name:'Start', id:'startAction') {
        stopButton.requestFocusInWindow()
        doOutside {
            model.startRecording(tfClient.text)
        }
    }
    action(name:'Stop', id:'stopAction') {
        doOutside {
            model.stopRecording();
            clientsTable.revalidate()
        }
    }
}

frame = swing.build(TimeLogView)
frame.pack()
frame.show()

