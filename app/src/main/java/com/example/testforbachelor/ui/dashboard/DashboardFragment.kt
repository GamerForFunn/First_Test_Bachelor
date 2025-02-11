package com.example.testforbachelor.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testforbachelor.R
import com.example.testforbachelor.databinding.FragmentDashboardBinding
import com.example.testforbachelor.ui.game_2.Game2ViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        // Get a reference to the button
        val goToGame2Button: Button = binding.goToGame2Button

        // Set a click listener on the button
        goToGame2Button.setOnClickListener {
            // Navigate to Game2Fragment
            findNavController().navigate(R.id.action_navigation_user_to_game2Fragment)
        }

        /*val goToDashboardButton: Button = binding.goToDashboardButton
        goToDashboardButton.setOnClickListener {
            findNavController().navigate(R.id.action_game2Fragment_to_navigation_dashboard)
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}