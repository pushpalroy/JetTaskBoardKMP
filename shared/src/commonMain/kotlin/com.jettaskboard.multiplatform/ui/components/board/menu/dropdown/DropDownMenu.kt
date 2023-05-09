import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.util.dropdown.JDropdownMenu
import com.jettaskboard.multiplatform.util.dropdown.JDropdownMenuItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun DropdownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismiss: () -> Unit,
    navigateToChangeBgScreen: (String) -> Unit
) {
    // DropDown
    JDropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = onDismiss,
    ) {
        JDropdownMenuItem(
            onSelect = { navigateToChangeBgScreen("") }
        ) {
            Icon(
                painter = painterResource("ic_baseline_wallpaper_24.xml"),
                modifier = Modifier.size(18.dp),
                contentDescription = "star",
                tint = Color(0xFFFFEB3B)
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Change Background"
            )
        }

        JDropdownMenuItem(
            onSelect = { navigateToChangeBgScreen("") }
        ) {
            Icon(
                painter = painterResource("ic_baseline_filter_list_24.xml"),
                modifier = Modifier.size(18.dp),
                contentDescription = "star",
                tint = Color(0xFFFFEB3B)
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Filter"
            )
        }

        JDropdownMenuItem(
            onSelect = { navigateToChangeBgScreen("") }
        ) {
            Icon(
                painter = painterResource("ic_baseline_automation_icon.xml"),
                modifier = Modifier.size(18.dp),
                contentDescription = "star",
                tint = Color(0xFFFFEB3B)
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Automation"
            )
        }
    }
}
