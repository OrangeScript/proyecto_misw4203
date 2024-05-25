package com.example.vinilos.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentFormCrearAlbumBinding
import com.example.vinilos.viewmodels.FormCrearAlbumViewModel

class FormCrearAlbum : Fragment() {

    companion object {
        fun newInstance() = FormCrearAlbum()
    }

    private var _binding: FragmentFormCrearAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FormCrearAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormCrearAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {

        }
        val args: FormAsociarTrackArgs by navArgs()
        viewModel = ViewModelProvider(this, FormCrearAlbumViewModel.Factory(activity.application))
            .get(FormCrearAlbumViewModel::class.java)
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
        viewModel.saveSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                onSavedAlbum()
                goFromCrearAlbumToOpciones(requireView())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    private fun onSavedAlbum() {
        Toast.makeText(activity, "Álbum creado correctamente", Toast.LENGTH_LONG).show()
        viewModel.onSavedAlbumHandled()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputCrearAlbumNombre.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.inputCrearAlbumNombre.error = "Campo requerido"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.inputCrearAlbumNombre.error = null
            }
        })

        val goBackButton: Button = view.findViewById(R.id.atrasCrearAlbum)
        val saveButton: Button = view.findViewById(R.id.guardarCrearAlbum)
        val genreDropdown: Spinner = view.findViewById(R.id.dropdownCrearAlbumGenero)
        val recordLabelDropdown: Spinner = view.findViewById(R.id.dropdownCrearAlbumDisquera)

        val availableGenres : Array<String> = arrayOf("Classical", "Salsa", "Rock", "Folk")
        val availableRecordLabels : Array<String> = arrayOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")

        genreDropdown.adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, availableGenres)
        recordLabelDropdown.adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, availableRecordLabels)

        goBackButton.setOnClickListener {
            goFromCrearAlbumToOpciones(view)
        }

        saveButton.setOnClickListener {
            if (validateAllInputs()) {
                saveInputData()
            } else {
                Toast.makeText(activity, "Por favor, completa todos los campos correctamente", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun goFromCrearAlbumToOpciones(view: View) {
        view.findNavController().popBackStack()
    }

    fun saveInputData() {
        val albumName = binding.inputCrearAlbumNombre.text.toString()
        val albumCover = binding.inputCrearAlbumImagen.text.toString()
        val albumReleaseDate = binding.inputCrearAlbumFecha.text.toString()
        val albumDescription = binding.inputCrearAlbumDescripcion.text.toString()
        val albumGenre = binding.dropdownCrearAlbumGenero.selectedItem.toString()
        val albumRecordLabel = binding.dropdownCrearAlbumDisquera.selectedItem.toString()
        viewModel.getAlbumData(albumName, albumCover, albumReleaseDate, albumGenre, albumRecordLabel, albumDescription)
    }

    fun isValidDate(releaseDate: String): Boolean {
        val pattern = Regex("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])\$")
        val matcher = pattern.matchEntire(releaseDate)
        return matcher != null
    }

    fun validateAllInputs(): Boolean {
        val albumName = binding.inputCrearAlbumNombre.text.toString()
        val albumCover = binding.inputCrearAlbumImagen.text.toString()
        val albumReleaseDate = binding.inputCrearAlbumFecha.text.toString()
        val albumDescription = binding.inputCrearAlbumDescripcion.text.toString()

        val isNameValid = albumName.isNotEmpty()
        val isCoverValid = albumCover.isNotEmpty()
        val isDescriptionValid = albumDescription.isNotEmpty()
        val isReleaseDateValid = isValidDate(albumReleaseDate)

        binding.layoutCrearAlbumNombre.error = if (isNameValid) null else "Campo requerido"
        binding.layoutCrearAlbumImagen.error = if (isCoverValid) null else "Campo requerido"
        binding.inputCrearAlbumDescripcion.error = if (isDescriptionValid) null else "Campo requerido"
        binding.layoutCrearAlbumFecha.error = when {
            albumReleaseDate.isEmpty() -> "Campo requerido"
            !isValidDate(albumReleaseDate) -> "Formato incorrecto. Debe ser YYYY-mm-dd"
            else -> null
        }

        return isNameValid && isCoverValid && isDescriptionValid && isReleaseDateValid
    }
}