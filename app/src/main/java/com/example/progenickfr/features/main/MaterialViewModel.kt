import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.progenickfr.domain.model.MaterialNeedStore
import com.example.progenickfr.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

class materialneed(private val repository: UserRepository) : ViewModel() {

    // Variables de texto
    var searchMAterial by mutableStateOf("")
    var materialTake by mutableStateOf("")
    var placeWork by mutableStateOf("")
    var placeStore by mutableStateOf("")

    // NUEVA: Variable para la URI de la imagen seleccionada
    var selectedImageUri by mutableStateOf<Uri?>(null)

    var isSyncing by mutableStateOf(false)

    private val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()

    fun onSendClick() {
        val user = auth.currentUser
        val uid = user?.uid ?: ""

        if (uid.isNotEmpty() && selectedImageUri != null) {
            isSyncing = true
            repository.checkUserExists(uid) { perfil ->
                val nombreParaExcel = perfil?.name ?: user?.email ?: "Anónimo"
                uploadImageAndSendReport(nombreParaExcel)
            }
        } else if (selectedImageUri == null) {
            // Error: "Falta la foto"
        }
    }

    private fun uploadImageAndSendReport(nombreParaExcel: String) {
        val storageRef = storage.reference.child("evidencias/${System.currentTimeMillis()}.jpg")

        selectedImageUri?.let { uri ->
            storageRef.putFile(uri)
                .addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                        sendReport(nombreParaExcel, downloadUrl.toString())
                    }
                }
        }
    }

    private fun sendReport(nombreParaExcel: String, imageUrl: String) {
        val need_material = MaterialNeedStore(
            search_material = searchMAterial,
            place_work = placeWork,
            place_store = placeStore,
            take_material = materialTake,
            created_by = nombreParaExcel,
            foto_url = imageUrl
        )

        repository.materialneed(need_material) { success ->
            isSyncing = false
            if (success) {
                searchMAterial = ""
                materialTake = ""
                selectedImageUri = null
            }
        }
    }}