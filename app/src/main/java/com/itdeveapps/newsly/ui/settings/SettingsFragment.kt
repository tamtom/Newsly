package com.itdeveapps.newsly.ui.settings


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.itdeveapps.newsly.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : DaggerFragment() {
    @Inject
    lateinit var settingsPrefrences: SettingsPrefrences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryItem.setOnClickListener {
            val items = Country.getAllCountries()

            val checkedItem = settingsPrefrences.getCountry(Country.USA).ordinal
           val alertDialog = AlertDialog.Builder(context)
                .setTitle("Country")
                .setSingleChoiceItems(items, checkedItem
                ) { dialog, which ->
                    settingsPrefrences.setCountry(Country.values()[which])
                }
                .setPositiveButton("Ok") { dialog, which ->

                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()

                }

                .show()
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context!!, R.color.purple));
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context!!, R.color.purple));
        }
    }

}
