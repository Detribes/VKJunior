package com.example.vkjunior.ui

import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.vkjunior.R
import com.example.vkjunior.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("Fragment is null")

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            onBindViews()
            observeViewModel()
            setOnClickListeners()
        }
    }
    @RequiresApi(Build.VERSION_CODES.S)
    private fun observeViewModel() {
        val blurEffect =  RenderEffect.createBlurEffect(60f, 10f, Shader.TileMode.MIRROR)
        viewModel.screenSettings.observe(viewLifecycleOwner) {
            if (it.micActive) {
                binding.ibMic.setImageResource(R.drawable.ic_mic_on)
            } else {
                binding.ibMic.setImageResource(R.drawable.ic_mic_off)
            }
            if (it.cameraActive) {
                binding.ibCamera.setImageResource(R.drawable.ic_videocam)
            } else {
                binding.ibCamera.setImageResource(R.drawable.ic_videocam_off)
            }
            if (it.isReversed) {
                binding.tvFirst.text = trimLongString(getString((R.string.second_person)))
                binding.tvFirst.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_micro_on_white,0)
                binding.ivFirstPerson.setImageResource(R.drawable.second_person)
                binding.ivBlurFirst.setImageResource(R.drawable.second_person)
                binding.ivBlurFirst.setRenderEffect(blurEffect)
                binding.tvSecondPerson.text = trimLongString(getString((R.string.first_person)))
                if (it.micActive) {
                    binding.tvSecondPerson.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_micro_on_white,0)
                } else {
                    binding.tvSecondPerson.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_micro_off_white,0)
                }
                binding.ivSecondPerson.setImageResource(R.drawable.first_person)
                binding.ivBlurSecond.setImageResource(R.drawable.first_person)
                binding.ivBlurSecond.setRenderEffect(blurEffect)

            } else {
                binding.tvFirst.text = trimLongString(getString((R.string.first_person)))
                if (it.micActive) {
                    binding.tvFirst.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_micro_on_white,0)
                } else {
                    binding.tvFirst.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_micro_off_white,0)
                }
                binding.ivFirstPerson.setImageResource(R.drawable.first_person)
                binding.ivBlurFirst.setImageResource(R.drawable.first_person)
                binding.ivBlurFirst.setRenderEffect(blurEffect)
                binding.tvSecondPerson.text = trimLongString(getString((R.string.second_person)))
                binding.tvSecondPerson.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_micro_on_white,0)
                binding.ivSecondPerson.setImageResource(R.drawable.second_person)
                binding.ivBlurSecond.setImageResource(R.drawable.second_person)
                binding.ivBlurSecond.setRenderEffect(blurEffect)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.S)
    private fun onBindViews(){
        val blurEffect =  RenderEffect.createBlurEffect(60f, 10f, Shader.TileMode.MIRROR)
        with(binding){
            binding.tvFirst.text = trimLongString(getString((R.string.first_person)))
            binding.ivFirstPerson.setImageResource(R.drawable.first_person)
            binding.ivBlurFirst.setImageResource(R.drawable.first_person)
            binding.ivBlurFirst.setRenderEffect(blurEffect)
            binding.tvSecondPerson.text = trimLongString(getString((R.string.second_person)))
            binding.ivSecondPerson.setImageResource(R.drawable.second_person)
            binding.ivBlurSecond.setImageResource(R.drawable.second_person)
            binding.ivBlurSecond.setRenderEffect(blurEffect)
        }
    }
    private fun setOnClickListeners() = with(binding) {
        ibMic.setOnClickListener {
            viewModel.clickMicro()
        }
        ibCamera.setOnClickListener {
            viewModel.clickCamera()
        }
        ibPhone.setOnClickListener {
            requireActivity().finish()
        }
        ibHand.setOnClickListener {
            myAlertDialog()
        }
        ivSettings.setOnClickListener {
            viewModel.reversePersons()
        }
        ivMessage.setOnClickListener {
            launchMessageScreen()
        }
        ivGroup.setOnClickListener {
            launchContactFragment()
        }
    }
    private fun myAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.Hello)
        builder.setMessage(R.string.doings)

        val dialog = builder.create()
        dialog.show()
    }
    private fun trimLongString(string: String): String{
        return if (string.length > LENGTH_LIMIT) {
            string.substring(0, LENGTH_LIMIT) + "..."
        } else {
            string
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun launchContactFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_contactsFragment)
    }
    private fun launchMessageScreen() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_APP_MESSAGING)
        startActivity(intent)
    }
    companion object {
        const val LENGTH_LIMIT = 18
    }
}