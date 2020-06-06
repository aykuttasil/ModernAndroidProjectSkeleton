/**
 * Designed and developed by Aykut Asil (@aykuttasil)
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
package com.aykuttasil.modernapp.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Observer
import io.reactivex.Flowable

fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
  removeObserver(observer)
  observe(owner, observer)
}

fun <T> LiveData<T>.toFlowable(owner: LifecycleOwner): Flowable<T> {
  return Flowable.fromPublisher(LiveDataReactiveStreams.toPublisher(owner, this))
}
