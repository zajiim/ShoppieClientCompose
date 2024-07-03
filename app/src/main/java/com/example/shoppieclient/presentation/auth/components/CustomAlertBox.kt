package com.example.shoppieclient.presentation.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppieclient.R
import com.example.shoppieclient.presentation.on_boarding.components.CustomButtonOnBoarding
import com.example.shoppieclient.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertBox(
    modifier: Modifier = Modifier,
    message: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    BasicAlertDialog(onDismissRequest = { onCancel() }
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(),
            tonalElevation = AlertDialogDefaults.TonalElevation,
            color = LightGray,
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Text(text = message)

                Image(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(id = R.drawable.app_logo), contentDescription = null)
                
                CustomButtonOnBoarding(text = "Back to login") {
                    onConfirm()
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingButton() {
    CustomAlertBox(message = "Your account is created", onConfirm = { /*TODO*/ }) {
        
    }
}