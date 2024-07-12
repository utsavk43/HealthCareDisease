package com.healthcare.disease.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.healthcare.disease.R
import com.healthcare.disease.domain.common.state.UiState
import com.healthcare.disease.domain.common.state.requireSuccess
import com.healthcare.disease.domain.login.state.LoginUiState
import com.healthcare.disease.domain.login.vm.LoginViewModel
import com.healthcare.disease.ui.commonComposableView.UsernameTextField
import com.healthcare.disease.ui.commonComposableView.MediumTitleText
import com.healthcare.disease.ui.commonComposableView.NormalButton
import com.healthcare.disease.ui.commonComposableView.PasswordTextField
import com.healthcare.disease.ui.commonComposableView.TitleText
import com.healthcare.disease.ui.navigation.Screen
import com.healthcare.disease.ui.theme.AppTheme

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Main card Content for Login
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.paddingLarge)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimens.paddingLarge)
                    .padding(bottom = AppTheme.dimens.paddingExtraLarge)
            ) {

                // Heading Jetpack Compose
                MediumTitleText(
                    modifier = Modifier
                        .padding(top = AppTheme.dimens.paddingLarge)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center
                )

                // Login Logo
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .padding(top = AppTheme.dimens.paddingSmall),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = R.drawable.jetpack_compose_logo)
                        .crossfade(enable = true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = stringResource(id = R.string.title_login_heading_text)
                )

                // Heading Login
                TitleText(
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                    text = stringResource(id = R.string.title_login_heading_text)
                )

                // Login Inputs Composable
                LoginInputs(
                    viewModel.uiState,
                    viewModel::setUserName,
                    viewModel::setPassword,
                    onSubmit = {
                        navController.navigate(
                            Screen.Dashboard.navigationPath(viewModel.uiState.requireSuccess { username })
                        ) {
                            popUpTo(Screen.LoginFlow.referencePath) { inclusive = true }
                        }
                    },
                    onForgotPasswordClick = {}
                )

            }
        }
    }
}


@Composable
private fun LoginInputs(
    uiState: UiState<LoginUiState>,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {

    // Login Inputs Section
    Column(modifier = Modifier.fillMaxWidth()) {
        UsernameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = uiState.requireSuccess { username },
            onValueChange = onUsernameChange,
            label = stringResource(id = R.string.hint_login_email_or_username),
            isError = false,
            errorText = ""
        )

        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = uiState.requireSuccess { password },
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.hint_login_password),
            isError = false,
//            errorText = stringResource(id = loginState.errorState.passwordErrorState.errorMessageStringResource),
            imeAction = ImeAction.Done
        )

        // Forgot Password
        Text(
            modifier = Modifier
                .padding(top = AppTheme.dimens.paddingSmall)
                .align(alignment = Alignment.End)
                .clickable {
                    onForgotPasswordClick.invoke()
                },
            text = stringResource(id = R.string.btn_forgot_password),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium
        )

        // Login Submit Button
        NormalButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.btn_login),
            onClick = onSubmit
        )

    }
}