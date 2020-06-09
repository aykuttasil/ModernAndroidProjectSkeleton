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
package com.aykuttasil.modernapp.ui.user

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.aykuttasil.modernapp.R
import com.aykuttasil.modernapp.databinding.ActivityUserBinding
import com.aykuttasil.modernapp.di.ViewModelFactory
import com.aykuttasil.modernapp.ui.common.BaseActivity
import com.aykuttasil.modernapp.util.delegates.contentView
import com.aykuttasil.domain.util.logd
import javax.inject.Inject

class UserActivity : BaseActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private val viewModel by viewModels<UserViewModel> { viewModelFactory }

  private val binding: ActivityUserBinding by contentView(R.layout.activity_user)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    logd { "onCreate" }

    /*
    viewModel.getUser().observe(this, Observer {
      when (it) {
        is Resource.Loading -> {
          Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
        }
        is Resource.Success -> {
          Toast.makeText(this, it.data?.userEmail, Toast.LENGTH_SHORT).show()
        }
        is Resource.Error -> {
          Toast.makeText(this, it.throwable?.message, Toast.LENGTH_SHORT).show()
        }
      }
    })

     */

    viewModel.viewState.observe(this, Observer {
      if (it.isLoading) {
        Toast.makeText(this, "Lütfen Bekleyiniz", Toast.LENGTH_SHORT).show()
      } else {
        Toast.makeText(this, "İşlem Tamamlandı", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, it.userEntity?.userName, Toast.LENGTH_SHORT).show()
      }
    })

    viewModel.errorState.observe(this, Observer {
      Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
    })

    viewModel.getUser()

  }
}
