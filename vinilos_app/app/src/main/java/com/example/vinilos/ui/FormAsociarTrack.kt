package com.example.vinilos.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentFormAsociarTrackBinding
import com.example.vinilos.viewmodels.FormAsociarTrackViewModel

class FormAsociarTrack : Fragment() {

    companion object {
        fun newInstance() = FormAsociarTrack()
    }

    private var _binding: FragmentFormAsociarTrackBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FormAsociarTrackViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormAsociarTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {

        }
        val args: FormAsociarTrackArgs by navArgs()
        viewModel = ViewModelProvider(this, FormAsociarTrackViewModel.Factory(activity.application, args.albumId))
            .get(FormAsociarTrackViewModel::class.java)
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
        viewModel.saveSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                onSavedTrack()
                goFromAsociarTrackToDetail(requireView())
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

    private fun onSavedTrack() {
        Toast.makeText(activity, "Track creado correctamente", Toast.LENGTH_LONG).show()
        viewModel.onSavedTrackHandled()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputNombreTrack.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.inputNombreTrackLayout.error = "Campo requerido"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.inputNombreTrackLayout.error = null
            }
        })

        binding.inputDuracionTrack.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val duration = s.toString()

                val error = when {
                    duration.isEmpty() -> "Campo requerido"
                    !isValidDuration(duration) -> "Formato incorrecto. Usa mm:ss"
                    else -> null
                }

                binding.inputDuracionTrackLayout.error = error
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.inputDuracionTrackLayout.error = null
            }
        })

        val goBackButton: Button = view.findViewById(R.id.atrasRelacionalTrack)
        val saveButton: Button = view.findViewById(R.id.guardarRelacionalTrack)

        goBackButton.setOnClickListener {
            goFromAsociarTrackToDetail(view)
        }

        saveButton.setOnClickListener {
            if (validateAllInputs()) {
                saveInputData()
            } else {
                Toast.makeText(activity, "Por favor, completa todos los campos correctamente", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun goFromAsociarTrackToDetail(view: View) {
        view.findNavController().popBackStack()
    }

    fun saveInputData() {
        val trackName = binding.inputNombreTrack.text.toString()
        val trackDuration = binding.inputDuracionTrack.text.toString()
        viewModel.getTrackData(trackName, trackDuration)
    }

    fun isValidDuration(duration: String): Boolean {
        val pattern = Regex("^([0-5]?[0-9]):([0-5][0-9])$")
        val matcher = pattern.matchEntire(duration)

        return matcher != null
    }

    fun validateAllInputs(): Boolean {
        val trackName = binding.inputNombreTrack.text.toString()
        val trackDuration = binding.inputDuracionTrack.text.toString()

        val isNameValid = trackName.isNotEmpty()
        val isDurationValid = isValidDuration(trackDuration)

        binding.inputNombreTrackLayout.error = if (isNameValid) null else "Campo requerido"
        binding.inputDuracionTrackLayout.error = when {
            trackDuration.isEmpty() -> "Campo requerido"
            !isValidDuration(trackDuration) -> "Formato incorrecto. Usa mm:ss"
            else -> null
        }

        return isNameValid && isDurationValid
    }
}